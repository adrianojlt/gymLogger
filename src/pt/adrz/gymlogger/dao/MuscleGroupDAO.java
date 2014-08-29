package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.model.MuscleGroup;

public interface MuscleGroupDAO {
	
	public List<MuscleGroup> getAllMuscleGroup();
	public MuscleGroup getMuscleGroup(int id);
	// add ... 
	// delete ...
	// edit ..
}
