package pt.adrz.gymlogger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutDAOJDBC implements WorkoutDAO {
	
	private static final String QUERY_GET_WORKOUTS = 
		"SELECT id , start , end FROM workout ORDER BY id DESC;";

	private static final String QUERY_GET_WORKOUTS_BY_ID = 
		"SELECT id , start , end FROM workout WHERE id = ?;";

	private static final String QUERY_GET_ALL_WORKOUTS = 
		"SELECT w.id AS id_work , r.id AS id_rep , w.start , w.end , g.name AS group_name , r.id_exercise , e.name AS exercise_name , r.weight , r.num  "
		+ "FROM workout w "
		+ "INNER JOIN repetition r ON r.id_workout = w.id "
		+ "INNER JOIN exercise e ON e.id = r.id_exercise "
		+ "INNER JOIN musclegroup g ON g.id = e.id_musclegroup "
		+ "WHERE w.id > 0 ORDER BY id_rep DESC;";

	private static final String QUERY_INSERT_WORKOUT 	= "INSERT INTO Workout (id_User,start,end) VALUES (1,?,?);";
	private static final String QUERY_INSERT_REPETITION = "INSERT INTO Repetition ( id_workout, id_exercise, weight, num ) VALUES (?,?,?,?);";

	//private RepetitionDAO repetitionData;
	
	@Override
	public List<Workout> getWorkouts() {
		
		List<Workout> workouts = new ArrayList<Workout>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(QUERY_GET_WORKOUTS);

			while ( rs.next() ) { workouts.add(this.processWorkout(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs, st, conn); }
	
		return workouts;
	}


	@Override
	public List<Workout> getWorkoutsWithRepetitions() {

		List<Workout> workouts = new ArrayList<Workout>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(QUERY_GET_WORKOUTS);

			while ( rs.next() ) { workouts.add(this.processWorkout(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs, st, conn); }
		
		RepetitionDAO repetitionDAO = new RepetitionDAOJDBC();
		
		// get repetitions for all workouts in the list
		for ( Workout workout : workouts) { 
			List<Repetition> repetitions = repetitionDAO.getRepetitions(workout);
			workout.setRepetitions(repetitions);
		}

		return workouts;
	}

	@Override
	public List<Workout> listAllWorkouts(int offset, int noOfRecords) {
		return null;
	}

	@Override
	public Workout getWorkoutById(int id) {
		
		Workout workout = new Workout();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(QUERY_GET_WORKOUTS_BY_ID);
			ps.setInt(1,id);	
			ps.setMaxRows(1);
			rs = ps.executeQuery();
			
			if ( rs.next() ) {
				workout.setId(rs.getInt(Workout.ID));
				workout.setStart(rs.getTimestamp(Workout.START));
				workout.setEnd(rs.getTimestamp(Workout.END));
			}
			else { 
				return null;
			}
			
			RepetitionDAO repetitionDAO = new RepetitionDAOJDBC();
			List<Repetition> repetitions = repetitionDAO.getRepetitions(workout);
			workout.setRepetitions(repetitions);
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (NullPointerException eNull) { }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs, ps, conn); }
		return workout;
	}
	
	
	public List<Workout> listWorkoutRepetition() {
		
		List<Workout> workouts = new ArrayList<Workout>();
		Connection conn = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(QUERY_GET_WORKOUTS);

			while ( rs.next() ) { 
				workouts.add(this.processWorkout(rs)); 
			}
			
			for ( Workout workout : workouts) {
				workout.getId();
			}

		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(conn); }
		return workouts;
	}
	
	public Integer createWorkout(Workout workout) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		Integer workoutID = 0;

		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(QUERY_INSERT_WORKOUT, Statement.RETURN_GENERATED_KEYS);
			
			ps.setTimestamp(1, new Timestamp(workout.getStart().getTime()));
			ps.setTimestamp(2, new Timestamp(workout.getEnd().getTime()));

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if ( rs.next() ) workoutID = rs.getInt(1);
			
			List<Repetition> repetitions = workout.getRepetitions();
			
			for ( Repetition rep : repetitions ) {

				ps = conn.prepareStatement(QUERY_INSERT_REPETITION);

				ps.setInt(1, workoutID);
				ps.setInt(2, rep.getExercise().getId());
				ps.setFloat(3, rep.getWeight());
				ps.setInt(4, rep.getNum());

				ps.executeUpdate();
			}

		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(conn); }

		return workoutID;
	}
	
	public boolean removeWorkout(Workout workout) {
		return false;
	}
	
	private Workout processWorkout(ResultSet rs) throws SQLException {

		Workout workout = new Workout();

		workout.setId(rs.getInt(Workout.ID));
		workout.setStart(rs.getTimestamp(Workout.START));
		workout.setEnd(rs.getTimestamp(Workout.END));

		return workout;
	}

	
}
