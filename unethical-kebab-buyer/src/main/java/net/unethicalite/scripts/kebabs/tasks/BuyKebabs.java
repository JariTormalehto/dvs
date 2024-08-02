package net.unethicalite.scripts.kebabs.tasks;

import net.unethicalite.api.entities.NPCs;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.movement.Movement;
import net.unethicalite.api.movement.Reachable;
import net.runelite.api.ItemID;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.unethicalite.api.entities.Players;

import java.util.concurrent.ThreadLocalRandom;

public class BuyKebabs implements ScriptTask
{

	private int getRandomDelay(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private void waitForIdle()
	{
		Player local = Players.getLocal();
		while (local.isMoving() || local.isAnimating() || local.isInteracting())
		{
			try
			{
				Thread.sleep(2000); // Wait for 500 ms before checking again
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}


	@Override
	public boolean validate()
	{
		return !Inventory.isFull() && Inventory.contains(ItemID.FISHING_ROD) && Inventory.contains(ItemID.SANDWORMS);
	}

	@Override
	public int execute()
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

		NPC spot = NPCs.getNearest("Rod Fishing spot");
		if (spot == null)
		{
			Movement.walkTo(1824, 3772, 0);
			return getRandomDelay(800, 2500);
		}

		if (!Reachable.isInteractable(spot))
		{
			Movement.walkTo(spot);
			return getRandomDelay(800, 2500);
		}

		spot.interact("Bait");
		waitForIdle();
		return getRandomDelay(800, 2500);
	}
}
