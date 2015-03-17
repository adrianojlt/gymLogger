package pt.adrz.gymlogger.dao;

public class MysqlJdbcDAO extends FactoryGym {

	//@Override
	//public Exercice getExerciceDAO() { return null; }

	@Override
	public MuscleGroupDAO getMuscleGroupDAO() {
		return null;
	}

	@Override
	public WorkoutDAO getWorkoutDAO() {
		return new WorkoutDAOJDBC();
	}

	@Override
	public RepetitionDAO getRepetitionDAO() {
		return new RepetitionDAOJDBC();
	}
}
