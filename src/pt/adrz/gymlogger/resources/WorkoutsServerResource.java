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

import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.dao.WorkoutDAOJDBC;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutsServerResource extends ServerResource {
	
	private WorkoutDAO workoutsDAO = new WorkoutDAOJDBC();

	@Override
	protected void doInit() throws ResourceException {

	}

	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;

		try {

			List<Workout> workouts = workoutsDAO.getAllWorkouts();
			JacksonRepresentation<List<Workout>> jAll = new JacksonRepresentation<List<Workout>>(workouts); 
			rep = jAll;

			status = Status.SUCCESS_OK;
		} catch (Exception e) {
			e.printStackTrace();
			rep = new StringRepresentation(e.getMessage());
			status = Status.SERVER_ERROR_INTERNAL;
		}

		this.getResponse().setStatus(status);
		return rep;
	}
	
	@Post
	public Representation create(Representation representation) {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		JacksonRepresentation<Workout> jWorkout = new JacksonRepresentation<Workout>(representation, Workout.class);
		//JacksonRepresentation<Repetition> jRepetition = new JacksonRepresentation<Repetition>(representation, Repetition.class);
		
		try {
			Workout workout = jWorkout.getObject();
			rep = new JacksonRepresentation<Workout>(workout);
			//Repetition repetition = jRepetition.getObject();
			status = Status.SUCCESS_CREATED;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage());
		}
		
		this.getResponse().setStatus(status);
		return rep;
	}
}
