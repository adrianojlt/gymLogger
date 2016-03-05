package pt.adrz.gymlogger.dao;

import pt.adrz.gymlogger.dao.jdbc.ExerciseDAOJDBC;
import pt.adrz.gymlogger.dao.jdbc.MuscleGroupDAOJDBC;
import pt.adrz.gymlogger.dao.jdbc.RepetitionDAOJDBC;
import pt.adrz.gymlogger.dao.jdbc.WorkoutDAOJDBC;

public class MysqlJdbcDAO extends FactoryGym {

	@Override
	public MuscleGroupDAO getMuscleGroupDAO() {
		return new MuscleGroupDAOJDBC();
	}

	@Override
	public WorkoutDAO getWorkoutDAO() {
		return new WorkoutDAOJDBC();
	}

	@Override
	public RepetitionDAO getRepetitionDAO() {
		return new RepetitionDAOJDBC();
	}

	@Override
	public ExerciseDAO getExerciseDAO() {
		return new ExerciseDAOJDBC();
	}
}
