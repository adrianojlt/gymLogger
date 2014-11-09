package pt.adrz.gymlogger.resources;

import java.util.List;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.dao.WorkoutDAOJDBC;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutServerResource extends ServerResource {

	private WorkoutDAO workoutsDAO = new WorkoutDAOJDBC();
	
	private Integer id;

	@Override
	protected void doInit() throws ResourceException {
		this.id = Integer.valueOf(this.getAttribute("id"));
	}
	
	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;

		try {

			Workout workout = workoutsDAO.getWorkoutById(id);
			JacksonRepresentation<Workout> jWorkout = new JacksonRepresentation<Workout>(workout);
			rep = jWorkout;

			status = Status.SUCCESS_OK;
		} catch (Exception e) {
			e.printStackTrace();
			rep = new StringRepresentation(e.getMessage());
			status = Status.SERVER_ERROR_INTERNAL;
		}

		this.getResponse().setStatus(status);
		return rep;
	}
}
