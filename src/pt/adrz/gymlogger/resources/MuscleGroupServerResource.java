package pt.adrz.gymlogger.resources;

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

public class MuscleGroupServerResource extends ServerResource {

	private MuscleGroupDAO groupDAO = FactoryGym.getMuscleGroupDAO( FactoryGym.STORAGE_TYPE.MYSQL_JDBC );

	private Integer id;

	@Override
	protected void doInit() throws ResourceException {
		this.id = Integer.valueOf(this.getAttribute("id"));
	}
	
	@Get
	public Representation retrieve() {

		Representation rep = new EmptyRepresentation();
		Status status = null;
		MuscleGroup group = new MuscleGroup();

		try {

			group = groupDAO.getMuscleGroup(id);
			rep = new JacksonRepresentation<MuscleGroup>(group);
			status = Status.SUCCESS_OK;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL,e.getMessage());
		}

		this.getResponse().setStatus(status);
		return rep;
	}
}
