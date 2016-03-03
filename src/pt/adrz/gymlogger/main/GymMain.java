package pt.adrz.gymlogger.main;

import java.util.Collection;

import pt.adrz.gymlogger.dao.ExerciseDAO;
import pt.adrz.gymlogger.dao.FactoryGym;
import pt.adrz.gymlogger.model.Exercise;


public class GymMain {
	
	public static void main(String[] args) {
		
		muscleGroup();
	}
	
	private static void muscleGroup() {
		
		ExerciseDAO exerciseDAO = FactoryGym.getExerciseDAO(FactoryGym.STORAGE_TYPE.MYSQL_JCABI);
		
		Collection<Exercise> exercises = exerciseDAO.getExercicesByMuscleGroupId(2);
		
		for (Exercise exercise : exercises) {
			System.out.println(exercise);
		}

		//Exercise exercise = exerciseDAO.getExerciceById(102);
		//System.out.println(exercise);
	
	}
}
