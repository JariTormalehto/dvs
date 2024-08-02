package net.unethicalite.plugins.quester;

import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(name = "Unethical Quester", enabledByDefault = false)
@Slf4j
public class UnethicalQuesterPlugin extends Plugin
{


	@Inject
	private UnethicalQuesterConfig config;

	@Override
	protected void startUp()
	{
	}

	@Provides
	UnethicalQuesterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(UnethicalQuesterConfig.class);
	}

}

