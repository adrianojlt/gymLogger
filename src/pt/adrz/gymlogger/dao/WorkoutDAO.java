package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.model.Workout;

public interface WorkoutDAO {
	
	public List<Workout> getWorkoutsWithRepetitions();
	public List<Workout> getWorkoutsWithGroups();
	public List<Workout> getWorkouts();
	public List<Workout> getWorkouts(int start, int count);
	public int getNoOfRecords();
	public List<Workout> listAllWorkouts( int offset , int noOfRecords );
	public Workout getWorkoutById(int id) throws Exception;
	public Integer createWorkout(Workout workout);
	public boolean removeWorkout(Workout workout);
}
