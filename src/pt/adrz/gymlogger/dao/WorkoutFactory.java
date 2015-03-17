package pt.adrz.gymlogger.dao;

import java.util.List;
import pt.adrz.gymlogger.model.Workout;

public abstract class WorkoutFactory {
	
	enum STORAGE_TYPE {
		MYSQL_JDBC,
		MYSQL_SPRING_JDBC,
		MYSQL_JPA,
		MYSQL_HIBERNATE,
		ORACLE,
		MSSQL,
		DB4O
	}

	public abstract List<Workout> getAllWorkouts();
	public abstract List<Workout> listAllWorkouts( int offset , int noOfRecords );
	public abstract Workout getWorkoutById(int id);
	public abstract Integer createWorkout(String json);
	
	
	public static WorkoutFactory getDAOFactory( STORAGE_TYPE whichFactory) {

		switch (whichFactory) {
			//case MYSQL_JDBC: 				return new ExerciceDAOJDBC();
			case MYSQL_SPRING_JDBC: 		return null;
			case MYSQL_JPA: 				return null;
			case MYSQL_HIBERNATE: 			return null;
			default: return null;
		}
	}
	
	public static WorkoutDAO getWorkoutDAO() {
		return new WorkoutDAOJDBC();
	}
}
