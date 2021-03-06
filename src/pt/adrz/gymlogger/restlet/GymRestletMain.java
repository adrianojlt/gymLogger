package pt.adrz.gymlogger.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;


public class GymRestletMain {
	
	public GymRestletMain() { }

	public static void main(String[] args) throws Exception { 
		
		Component c = new Component();

    	c.getServers().add(Protocol.HTTP,9009);
    	c.getClients().add(Protocol.CLAP);
    	
    	VirtualHost host = c.getDefaultHost();

    	GymRestletApi gymApp = new GymRestletApi();
    	GymRestletWeb gymWeb = new GymRestletWeb();
    	
    	host.attach("/api", gymApp);
    	host.attach("/api/v1", gymApp);

    	host.attachDefault(gymWeb);
    	
    	c.setDefaultHost(host);
    	c.start();
	}
}
