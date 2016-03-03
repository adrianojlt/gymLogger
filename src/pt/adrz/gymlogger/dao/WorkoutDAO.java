package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.constants.Database;
import pt.adrz.gymlogger.model.Workout;

public interface WorkoutDAO {
	
	public static final String QUERY_GET_WORKOUTS = 
		"SELECT id , start , end FROM " +  Database.WORKOUT.getTableName() + " ORDER BY id DESC";
	
	public List<Workout> getWorkoutsWithRepetitions();
	public List<Workout> getWorkoutsWithGroups();
	public List<Workout> getWorkouts();
	public List<Workout> getWorkouts(Integer start, Integer count);
	public int getNoOfRecords();
	public List<Workout> listAllWorkouts( int offset , int noOfRecords );
	public Workout getWorkoutById(int id) throws Exception;
	public Integer createWorkout(Workout workout);
	public boolean removeWorkout(Workout workout);
}
