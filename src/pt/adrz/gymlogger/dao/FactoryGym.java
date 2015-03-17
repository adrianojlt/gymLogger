package pt.adrz.gymlogger.dao;

public abstract class FactoryGym {

	public enum STORAGE_TYPE {
		MYSQL_JDBC,
		MYSQL_SPRING_JDBC,
		MYSQL_JPA,
		MYSQL_HIBERNATE,
		ORACLE,
		MSSQL,
		DB4O
	}
	
	//public abstract Exercice getExerciceDAO(); 
	public abstract MuscleGroupDAO getMuscleGroupDAO(); 
	public abstract RepetitionDAO getRepetitionDAO();
	public abstract WorkoutDAO getWorkoutDAO(); 

	public static FactoryGym getFactory( STORAGE_TYPE whichFactory ) {

		switch (whichFactory) {
			case MYSQL_JDBC:
				return new MysqlJdbcDAO();
			case MYSQL_SPRING_JDBC:
			case ORACLE:
			case MSSQL:
				return null;
			case DB4O:
				return null;
			default:
				return null;
		}
	}
	
	public static WorkoutDAO getWorkoutDAO( STORAGE_TYPE whichFactory ) {
		
		switch (whichFactory) {
		
			case MYSQL_JDBC:
				return new WorkoutDAOJDBC();
			case MYSQL_SPRING_JDBC:
				return null;
			case ORACLE:
				return null;
			case MSSQL:
				return null;
			case DB4O:
				return null;
			default:
				return null;
		}
	}

	public static RepetitionDAO getRepetitionDAO( STORAGE_TYPE whichFactory ) {

		switch (whichFactory) {
		
			case MYSQL_JDBC:
				return new RepetitionDAOJDBC();
			case MYSQL_SPRING_JDBC:
				return null;
			default:
				return null;
		}
	}
	
	public static MuscleGroupDAO getMuscleGroupDAO( STORAGE_TYPE whichFactory ) {
		
		switch (whichFactory) {
		
			case MYSQL_JDBC:
				return new MuscleGroupDAOJDBC();
			case MYSQL_SPRING_JDBC:
				return null;
			default:
				return null;
		}
	}
}
