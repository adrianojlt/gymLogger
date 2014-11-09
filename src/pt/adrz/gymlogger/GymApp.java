package pt.adrz.gymlogger;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.gymlogger.resources.ExercicesServerResource;
import pt.adrz.gymlogger.resources.WorkoutServerResource;
import pt.adrz.gymlogger.resources.WorkoutsServerResource;

public class GymApp extends Application {

	@Override
	public Restlet createInboundRoot() {
		
		Router router = new Router(this.getContext());
		
		router.attach("/workouts", WorkoutsServerResource.class);
		router.attach("/workouts/", WorkoutsServerResource.class);
		router.attach("/workouts/{id}", WorkoutServerResource.class);
		router.attach("/workouts/{id}/", WorkoutServerResource.class);

		return router;
	}

}
