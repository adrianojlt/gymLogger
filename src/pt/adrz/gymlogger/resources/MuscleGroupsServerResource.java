package pt.adrz.gymlogger.resources;

import java.util.List;

import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.MuscleGroupDAO;
import pt.adrz.gymlogger.model.MuscleGroup;

public class MuscleGroupsServerResource extends ServerResource {
	
	private MuscleGroupDAO groupDAO = FactoryGym.getMuscleGroupDAO( FactoryGym.STORAGE_TYPE.MYSQL_JDBC );
	
	@Override
	protected void doInit() throws ResourceException { }
	
	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		List<MuscleGroup> group; 
		
		try {

			group = groupDAO.getMuscleGroups();
			rep = new JacksonRepresentation<List<MuscleGroup>>(group); 
			status = Status.SUCCESS_OK;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL,e.getMessage());
		}

		this.getResponse().setStatus(status);
		return rep;
	}
}
