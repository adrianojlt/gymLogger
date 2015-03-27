package pt.adrz.gymlogger.resources;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutServerResource extends ServerResource {

	private WorkoutDAO workoutsDAO = FactoryGym.getWorkoutDAO( FactoryGym.STORAGE_TYPE.MYSQL_JDBC );
	
	private Integer id;

	@Override
	protected void doInit() throws ResourceException {
		this.id = Integer.valueOf(this.getAttribute("id"));
	}
	
	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		Workout workout;

		try {

			workout = workoutsDAO.getWorkoutById(id);
			rep =  new JacksonRepresentation<Workout>(workout);
			status = Status.SUCCESS_OK;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, e.getMessage());
		}

		this.getResponse().setStatus(status);
		return rep;
	}
	
	@Delete
	public Representation delete() {
		
        Status status;

        try {

            status = Status.SUCCESS_OK;

        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage());
        }
        this.getResponse().setStatus(status);
        return new StringRepresentation("{}", MediaType.APPLICATION_JSON);
	}
}
