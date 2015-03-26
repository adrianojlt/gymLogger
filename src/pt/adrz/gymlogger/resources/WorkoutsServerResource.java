package pt.adrz.gymlogger.resources;

import java.io.IOException;
import java.util.List;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.dao.WorkoutDAOJDBC;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutsServerResource extends ServerResource {
	
	private WorkoutDAO workoutsDAO = FactoryGym.getWorkoutDAO( FactoryGym.STORAGE_TYPE.MYSQL_JDBC );

	@Override
	protected void doInit() throws ResourceException {

	}

	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		List<Workout> workouts;

		try {

			workouts = workoutsDAO.getWorkouts();
			rep = new JacksonRepresentation<List<Workout>>(workouts); 
			status = Status.SUCCESS_OK;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage());
		}

		this.getResponse().setStatus(status);
		return rep;
	}
	
	@Post
	public Representation create(Representation representation) {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		Integer workoutID;
		JacksonRepresentation<Workout> jWorkout;
		Workout workout;
		
		try {

			jWorkout = new JacksonRepresentation<Workout>(representation, Workout.class);
			workout = jWorkout.getObject();
			
			if ( workout.getRepetitions() == null || workout.getRepetitions().isEmpty() ) throw new NullPointerException("empty repetitions");
			
			workoutID = this.workoutsDAO.createWorkout(workout);

			rep = new JacksonRepresentation<Workout>(workout);
			status = Status.SUCCESS_CREATED;

		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage());
		}
		
        this.getResponse().setLocationRef(getRequest().getResourceRef().addSegment(workoutID.toString()));
		this.getResponse().setStatus(status);
		return rep;
	}
}
