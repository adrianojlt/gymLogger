package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.model.Workout;

public interface WorkoutDAO {
	
	public List<Workout> getAllWorkouts();
	public List<Workout> listAllWorkouts( int offset , int noOfRecords );
	public Workout getWorkoutById(int id);
	public Integer createWorkout(String json);
}
