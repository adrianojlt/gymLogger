package pt.adrz.gymlogger.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import pt.adrz.gymlogger.restlet.filters.CustomHeaders;
import pt.adrz.gymlogger.restlet.resources.*;

public class GymApi extends Application {

	@Override
	public Restlet createInboundRoot() {
		
		CustomHeaders headers = new CustomHeaders(this.getContext());
		
		Router router = new Router(this.getContext());

		router.attach("/users", MuscleGroupsServerResource.class);
		router.attach("/users/{id}", MuscleGroupsServerResource.class);

		router.attach("/workouts", WorkoutsServerResource.class);
		router.attach("/workouts/", WorkoutsServerResource.class);
		router.attach("/workouts/{id}", WorkoutServerResource.class);
		router.attach("/workouts/{id}/", WorkoutServerResource.class);
		
		router.attach("/groups", MuscleGroupsServerResource.class);
		router.attach("/groups/", MuscleGroupsServerResource.class);
		router.attach("/groups/{id}", MuscleGroupServerResource.class);
		router.attach("/groups/{id}/", MuscleGroupServerResource.class);

		router.attach("/exercices", ExercisesServerResource.class);
		router.attach("/exercises/", ExercisesServerResource.class);
		router.attach("/exercises/{id}", ExerciseServerResource.class);
		router.attach("/exercises/{id}/", ExerciseServerResource.class);
		
		headers.setNext(router);

		return headers;
	}
}
