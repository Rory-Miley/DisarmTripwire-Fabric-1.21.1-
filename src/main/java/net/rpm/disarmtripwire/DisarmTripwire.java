package net.rpm.disarmtripwire;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisarmTripwire implements ModInitializer {
	public static final String MOD_ID = "disarmtripwire";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DisarmTripwireHandler.registerDisarmTripwireEvent();
		LOGGER.info("Registered disarm tripwire event.");
	}

}