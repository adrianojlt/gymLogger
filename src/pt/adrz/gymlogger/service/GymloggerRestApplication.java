package pt.adrz.gymlogger.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class GymloggerRestApplication extends ResourceConfig {
	
	public GymloggerRestApplication() {

		packages("pt.adrz.gymlogger.service");
		register(JacksonFeature.class);
	}
}
