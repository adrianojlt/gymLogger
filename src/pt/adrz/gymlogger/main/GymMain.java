package pt.adrz.gymlogger.main;

import java.sql.SQLException;
import java.util.Collection;

import pt.adrz.gymlogger.connection.ConnectionFactory;
import pt.adrz.gymlogger.dao.ExerciseDAO;
import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.dao.jcabi.Exercises;
import pt.adrz.gymlogger.dao.jcabi.MySqlExercises;
import pt.adrz.gymlogger.model.Exercise;


public class GymMain {
	
	public static void main(String[] args) {
		
		//muscleGroup();
		textNoORM();
	}

	public static void textNoORM() {

		try {
			ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Exercises exercises = new MySqlExercises(ConnectionFactory.getDataSource());
		
		for (pt.adrz.gymlogger.dao.jcabi.Exercise exercise : exercises.iterate()) {
			System.out.println(exercise.name());
		}
	}
	
	private static void muscleGroup() {
		
		ExerciseDAO exerciseDAO = FactoryGym.getExerciseDAO(FactoryGym.STORAGE_TYPE.MYSQL_JCABI);
		
		Collection<Exercise> exercises = exerciseDAO.getExercicesByMuscleGroupId(2);
		
//		for (Exercise exercise : exercises) {
//			System.out.println(exercise);
//		}

		Exercise exercise = exerciseDAO.getExerciceById(102);
		System.out.println(exercise);
	
	}
}
