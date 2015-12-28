package pt.adrz.gymlogger.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pt.adrz.gymlogger.dao.ExerFactory;
import pt.adrz.gymlogger.dao.ExerciseDAO;
import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.model.Exercise;



@Path("/exercises")
public class ExerciseService {
	
	//ExerFactory exercise = ExerFactory.getDAOFactory(ExerFactory.MYSQL_JDBC);
	ExerciseDAO exercise = FactoryGym.getExerciseDAO(FactoryGym.STORAGE_TYPE.MYSQL_JDBC);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findAll() {
		return Response.status(200).entity(exercise.listAllExercices()).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response numberOfExercises() {
		return Response.status(200).entity(exercise.listAllExercices().size()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findById(@PathParam("id") int id) {

		Exercise exercice = exercise.getExerciceById(id);

		if ( exercice == null ) { 
			return Response.status(Response.Status.NOT_FOUND).entity(exercise).build();
		}

		return Response.status(200).entity(exercice).build();
	}
	
	@GET
	@Path("/group/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findByGroupId(@PathParam("id") int id) {
		return Response.status(200).entity(exercise.getExercicesByMuscleGroupId(id)).build();
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postData(Exercise exercise) throws Exception {
		String result = "Exercise name : " + exercise.getName();
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Path("/responsecode")
	public Response notFound() {
		return Response.status(Status.OK).build();
	}
}
