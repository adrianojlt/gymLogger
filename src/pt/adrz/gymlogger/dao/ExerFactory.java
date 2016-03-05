package pt.adrz.gymlogger.dao;

import java.util.List;

import pt.adrz.gymlogger.dao.jdbc.ExerDAOJDBC;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.MuscleGroup;

public abstract class ExerFactory {
	
	public static final int MYSQL_JDBC = 1;
	public static final int MYSQL_SPRING_JDBC = 2;
	public static final int MYSQL_JPA = 3;
	public static final int MYSQL_HIBERNATE = 4;
	
	public abstract List<Exercise> listAllExercices();
	public abstract Exercise getExerciceById(int id);
	public abstract List<Exercise> getExercicesByMuscleGroupId(int id);
	public abstract List<MuscleGroup> listAllMuscleGroups();
	
	public static ExerFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
			case MYSQL_JDBC: 				return new ExerDAOJDBC();
			case MYSQL_SPRING_JDBC: 		return null;
			case MYSQL_JPA: 				return null;
			case MYSQL_HIBERNATE: 			return null;
			default: return null;
		}
	}
}
