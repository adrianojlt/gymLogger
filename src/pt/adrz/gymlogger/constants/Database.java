package pt.adrz.gymlogger.constants;

public enum Database {
	
	USER("User"),
	WORKOUT("Workout"),
	REPETITION("Repetition"),
	EXERCISE("Exercise"),
	MUSCLEGROUP("MuscleGroup");
	
	private String table;
	
	Database(String table) {
		this.table = table;
	}
	
	public String getTableName() {
		return this.table;
	}
}
