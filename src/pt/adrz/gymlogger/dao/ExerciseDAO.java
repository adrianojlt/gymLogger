package pt.adrz.gymlogger.dao;

import java.util.Collection;
import java.util.List;

import pt.adrz.gymlogger.constants.Database;
import pt.adrz.gymlogger.model.*;

public interface ExerciseDAO {
	
	public static final String SQL_QUERY_GET_ALL_EXERCICES = 
		"SELECT id,name FROM " + Database.EXERCISE.getTableName() + ";";

	public static final String SQL_QUERY_GET_BY_ID = 
		"SELECT id,name FROM " + Database.EXERCISE.getTableName() + " WHERE id = ?;";

	public static final String SQL_QUERY_GET_BY_MUSCLEGROUPID = 
		"SELECT id,name FROM " + Database.EXERCISE.getTableName() + " WHERE id_musclegroup = ?;";


	public Collection<Exercise> listAllExercices();
	public Exercise getExerciceById(int id);
	public Collection<Exercise> getExercicesByMuscleGroupId(int id);
	public Collection<MuscleGroup> listAllMuscleGroups();
}
