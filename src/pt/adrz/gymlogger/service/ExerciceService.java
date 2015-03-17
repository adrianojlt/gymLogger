package pt.adrz.gymlogger.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pt.adrz.gymlogger.dao.ExerciceFactory;
import pt.adrz.gymlogger.model.Exercice;
import pt.adrz.gymlogger.model.Exercices;



@Path("/exercices")
public class ExerciceService {
	
	ExerciceFactory exercices = ExerciceFactory.getDAOFactory(ExerciceFactory.MYSQL_JDBC);
	
	@GET
	//@Produces({ MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Exercice> findAll() {
	//public Response findAll() {
		return exercices.listAllExercices();
		//return Response.status(200).entity(data.listAllExercices()).build);
		//return Response.status(200).entity(new Exercices(data.listAllExercices())).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response numberOfExercices() {
		return Response.status(200).entity(exercices.listAllExercices().size()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response findById(@PathParam("id") int id) {
		Exercice exercice = exercices.getExerciceById(id);
		if ( exercice == null ) { throw new WebApplicationException(Response.Status.NOT_FOUND); }
		return Response.status(200).entity(exercice).build();
	}
	
	@GET
	@Path("/group/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Exercice> findByGroupId(@PathParam("id") int id) {
	//public Response findByGroupId(@PathParam("id") int id) {
		//Exercices exercices = new Exercices(data.getExercicesByMuscleGroupId(id));
		return exercices.getExercicesByMuscleGroupId(id);
		//return Response.ok(exercices).build();
		//return Response.status(200).entity(exercices).build();
	}
	
	@GET
	@Path("/responsecode")
	public Response notFound() {
		return Response.status(Status.OK).build();
	}
}
