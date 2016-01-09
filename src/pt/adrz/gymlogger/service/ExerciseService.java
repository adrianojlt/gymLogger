package pt.adrz.gymlogger.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pt.adrz.gymlogger.dao.ExerFactory;
import pt.adrz.gymlogger.dao.ExerciseDAO;
import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.MuscleGroupDAO;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.MuscleGroup;



@Path("/exercises")
public class ExerciseService {
	
	//ExerFactory exercise = ExerFactory.getDAOFactory(ExerFactory.MYSQL_JDBC);
	private ExerciseDAO exerciseDAO = FactoryGym.getExerciseDAO(FactoryGym.STORAGE_TYPE.MYSQL_JDBC);
	private MuscleGroupDAO groupDAO = FactoryGym.getMuscleGroupDAO(FactoryGym.STORAGE_TYPE.MYSQL_JDBC);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findAll() {
		return Response.status(200).entity(exerciseDAO.listAllExercices()).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response numberOfExercises() {
		return Response.status(200).entity(exerciseDAO.listAllExercices().size()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findById(@PathParam("id") int id) {

		Exercise exercise = exerciseDAO.getExerciceById(id);

		if ( exercise == null ) { 
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return Response.status(200).entity(exercise).build();
	}
	
	@GET
	@Path("/group/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findByGroupId(@PathParam("id") int id) {
		return Response.status(200).entity(exerciseDAO.getExercicesByMuscleGroupId(id)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postData(Exercise exercise) throws Exception {

		String result = "Exercise name : " + exercise.getName();
		String msc = "muscle id : " + exercise.getMuscleGroup().getId();
		MuscleGroup group = groupDAO.getMuscleGroup(exercise.getMuscleGroup().getId());

		if ( group == null) {
			throw new WebApplicationException("Group Not Found", Response.Status.NOT_FOUND);
		}

		exercise.setMuscleGroup(group);
		return Response.status(201).entity(exercise).build();
	}
	
	@GET
	@Path("/responsecode")
	public Response notFound() {
		return Response.status(Status.OK).build();
	}
}
