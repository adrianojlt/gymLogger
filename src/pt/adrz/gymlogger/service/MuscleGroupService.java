package pt.adrz.gymlogger.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.adrz.gymlogger.dao.ExerciseDAO;
import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.MuscleGroupDAO;
import pt.adrz.gymlogger.dao.jdbc.MuscleGroupDAOJDBC;
import pt.adrz.gymlogger.model.MuscleGroup;

@Path("/groups")
public class MuscleGroupService {
	
	private MuscleGroupDAO musclegroupDAO;
	private ExerciseDAO exerciseDAO;
	
	public MuscleGroupService() {
		this.musclegroupDAO = new MuscleGroupDAOJDBC();
		this.exerciseDAO = FactoryGym.getExerciseDAO(FactoryGym.STORAGE_TYPE.MYSQL_JDBC);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<MuscleGroup> getMuscleGroups(@HeaderParam("exercises")Boolean exercises) {

		if ( exercises != null && exercises ) 
			return this.musclegroupDAO.getMuscleGroupsWithExercises();
		else 
			return this.musclegroupDAO.getMuscleGroups();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMuscleGroup(@PathParam("id") int id) throws Exception {

		MuscleGroup group = musclegroupDAO.getMuscleGroup(id);

		if ( group == null ) { 
			return Response.status(Response.Status.NOT_FOUND).entity(group).build();
		}

		return Response.status(200).entity(group).build();
	}

	@GET
	@Path("{id}/exercises")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExercisesByMuscleGroup(@PathParam("id") int id) throws Exception {
		return Response.status(200).entity(exerciseDAO.getExercicesByMuscleGroupId(id)).build();
	}
}
