package pt.adrz.gymlogger.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class GymRestletWeb extends Application{
	
	public GymRestletWeb() {
		
	}

    @Override
    public Restlet createInboundRoot() {

        Directory directory = new Directory(getContext(), "file:///");

        directory.setListingAllowed(true);
        directory.setDeeplyAccessible(true);
        directory.setNegotiatingContent(false);
        directory.setIndexName("index.html");

        Router router = new Router(this.getContext());
        router.attachDefault(directory);

        return router;
    }
}
