package net.unethicalite.scripts.kebabs.tasks;

import net.runelite.api.ObjectID;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Bank;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;
import net.unethicalite.api.movement.Reachable;
import net.runelite.api.ItemID;
import net.runelite.api.Player;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;

import java.util.concurrent.ThreadLocalRandom;

public class BankKebabs implements ScriptTask
{
	private static final WorldPoint BANK_TILE = new WorldPoint(1804, 3791, 0);

	private int getRandomDelay(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	@Override
	public boolean validate()
	{
		return Inventory.isFull() || !Inventory.contains(ItemID.FISHING_ROD) || !Inventory.contains(ItemID.SANDWORMS);
	}

	@Override
	public int execute()
	{
		Player local = Players.getLocal();
		if (!Bank.isOpen())
		{
			if (!Movement.isRunEnabled())
			{
				Movement.toggleRun();
				return getRandomDelay(800, 2500);
			}

			if (Movement.isWalking())
			{
				return getRandomDelay(800, 2500);
			}

			TileObject booth = TileObjects.getFirstAt(BANK_TILE, x -> x.hasAction("Bank", "Collect"));
			if (booth == null || booth.distanceTo(local) > 20 || !Reachable.isInteractable(booth))
			{
				Movement.walkTo(BANK_TILE);
				return getRandomDelay(800, 2500);
			}

			booth.interact("Bank");
			return getRandomDelay(3000, 5000);
		}

		if (Inventory.contains(ItemID.RAW_ANGLERFISH))
		{
			Bank.depositAll(ItemID.RAW_ANGLERFISH);
			return getRandomDelay(800, 2500);
		}

		return getRandomDelay(800, 2500);
	}
}
