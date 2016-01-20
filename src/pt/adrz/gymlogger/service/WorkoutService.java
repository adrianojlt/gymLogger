package pt.adrz.gymlogger.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.adrz.gymlogger.dao.RepetitionDAO;
import pt.adrz.gymlogger.dao.RepetitionDAOJDBC;
import pt.adrz.gymlogger.dao.WorkoutDAO;
import pt.adrz.gymlogger.dao.WorkoutDAOJDBC;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

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
	public List<Workout> getWorkouts(
			@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit,
			@Context HttpHeaders header, @Context HttpServletResponse response) {
		
		List<Workout> workouts = null;
		StringBuilder contentLength = new StringBuilder();
		
		try {
			
			if ( offset == null || limit == null ) {
				workouts = workoutsDAO.getWorkoutsWithRepetitions();
			}
			else {
				
				workouts = workoutsDAO.getWorkouts(offset, limit);
	
				contentLength.append("items ");
				contentLength.append(offset);
				contentLength.append("-");
				contentLength.append((offset + workouts.size() - 1));
				contentLength.append("/");
				contentLength.append(workoutsDAO.getNoOfRecords());
			}
		}
		catch (Exception e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
		}
		
		response.setHeader("Content-Range", contentLength.toString());
		return workouts;
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
	public Response createWorkout(Workout workout) {
		
		try {
			this.workoutsDAO.createWorkout(workout);
		}
		catch (Exception e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}

		//System.out.println("start: " + workout.getStart() + " end: " + workout.getEnd());
		return Response.status(Response.Status.CREATED).entity(workout).build();
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
