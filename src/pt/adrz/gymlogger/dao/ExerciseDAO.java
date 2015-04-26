package pt.adrz.gymlogger.dao;

import java.util.List;
import pt.adrz.gymlogger.model.*;

public interface ExerciseDAO {

	public List<Exercise> listAllExercices();
	public Exercise getExerciceById(int id);
	public List<Exercise> getExercicesByMuscleGroupId(int id);
	public List<MuscleGroup> listAllMuscleGroups();
}
