package net.unethicalite.plugins.quester;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("unethicalquester")
public interface UnethicalQuesterConfig extends Config
{
	@ConfigItem(
			keyName = "rangeGear",
			name = "Ranged gear names",
			description = "",
			position = 0
	)
	default String rangeGearNames()
	{
		return "Ancient d'hide body,Ancient chaps,Infinity boots,Toxic blowpipe,Ava's assembler";
	}

	@ConfigItem(
			keyName = "mageGear",
			name = "Mage gear names",
			description = "",
			position = 1
	)
	default String mageGearNames()
	{
		return "Ahrim's robetop,Ahrim's robeskirt,Trident of the swamp,Book of darkness";
	}

}
