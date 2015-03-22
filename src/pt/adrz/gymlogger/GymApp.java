package pt.adrz.gymlogger;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.gymlogger.filters.CustomHeaders;
import pt.adrz.gymlogger.resources.ExercisesServerResource;
import pt.adrz.gymlogger.resources.MuscleGroupServerResource;
import pt.adrz.gymlogger.resources.MuscleGroupsServerResource;
import pt.adrz.gymlogger.resources.WorkoutServerResource;
import pt.adrz.gymlogger.resources.WorkoutsServerResource;

public class GymApp extends Application {

	@Override
	public Restlet createInboundRoot() {
		
		CustomHeaders headers = new CustomHeaders(this.getContext());
		
		Router router = new Router(this.getContext());
		
		router.attach("/groups", MuscleGroupsServerResource.class);
		router.attach("/groups/", MuscleGroupsServerResource.class);
		router.attach("/groups/{id}", MuscleGroupServerResource.class);
		router.attach("/groups/{id}/", MuscleGroupServerResource.class);

		router.attach("/exercices", ExercisesServerResource.class);
		router.attach("/exercices/", ExercisesServerResource.class);
		router.attach("/exercices/{id}", ExercisesServerResource.class);
		router.attach("/exercices/{id}/", ExercisesServerResource.class);

		router.attach("/workouts", WorkoutsServerResource.class);
		router.attach("/workouts/", WorkoutsServerResource.class);
		router.attach("/workouts/{id}", WorkoutServerResource.class);
		router.attach("/workouts/{id}/", WorkoutServerResource.class);
		
		headers.setNext(router);

		return headers;
	}
}
