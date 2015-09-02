package pt.adrz.gymlogger.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;


public class GymMain {
	
	public GymMain() { }

	public static void main(String[] args) throws Exception { 
		
		Component c = new Component();

    	c.getServers().add(Protocol.HTTP,9009);
    	c.getClients().add(Protocol.CLAP);
    	
    	VirtualHost host = c.getDefaultHost();

    	// Application
    	GymApp gymApp = new GymApp();
    	
    	host.attachDefault(gymApp);
    	
    	c.setDefaultHost(host);
    	c.start();
	}
}
