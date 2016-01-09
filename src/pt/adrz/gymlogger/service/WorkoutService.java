package pt.adrz.gymlogger.service;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.adrz.gymlogger.dao.RepetitionDAO;
import pt.adrz.gymlogger.dao.RepetitionDAOJDBC;
import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.dao.WorkoutDAOJDBC;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

//@ApplicationPath("rest")
@Path("/workouts")
public class WorkoutService {
	
	private WorkoutDAO workoutsDAO;
	private RepetitionDAO repetitionsDAO;
	
	public WorkoutService() {
		workoutsDAO = new WorkoutDAOJDBC();
		repetitionsDAO = new RepetitionDAOJDBC();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Workout> getWorkouts() {
		return workoutsDAO.getWorkoutsWithRepetitions();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkout(@PathParam("id") int id) throws Exception {

		Workout workout = workoutsDAO.getWorkoutById(id);

		if ( workout == null ) { 
			throw new WebApplicationException(Response.Status.NOT_FOUND); 
		}

		return Response.status(200).entity(workout).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createWorkout(String representation) {
		//Integer createdWorkoutID = workouts.createWorkout(representation);
		//return Response.status(Response.Status.CREATED).entity("New workout created " + createdWorkoutID).header("idCreated", String.valueOf(createdWorkoutID)).build();
		return null;
	}

	@GET
	@Path("/repetitions")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Repetition> getRepetitions() {
		return repetitionsDAO.getRepetitions();
	}
	
	@GET
	@Path("/tmp")
	@Produces({MediaType.TEXT_HTML})
	public Response tmp() {
		return Response.status(Response.Status.NO_CONTENT).entity("tmp request").build();
	}
}
