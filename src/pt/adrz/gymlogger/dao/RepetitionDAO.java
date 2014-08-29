package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public interface RepetitionDAO {
	
	public List<Repetition> getRepetitions();
	public List<Repetition> getRepetitions(Workout workout);

}
