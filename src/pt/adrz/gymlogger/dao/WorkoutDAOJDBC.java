package pt.adrz.gymlogger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.gymlogger.model.Exercice;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutDAOJDBC implements WorkoutDAO {
	
	private static final String QUERY_GET_WORKOUTS = 
		"SELECT id , start , end FROM workout ORDER BY id DESC;";

	private static final String QUERY_GET_WORKOUTS_BY_ID = 
		"SELECT id , start , end FROM workout where id = ?;";

	private static final String QUERY_GET_ALL_WORKOUTS = 
		"select w.id as id_work , r.id as id_rep , w.start , w.end , g.name as group_name , r.id_exercice , e.name as exercice_name , r.weight , r.num  "
		+ "from workout w "
		+ "inner join repetition 	r on r.id_workout 	= w.id "
		+ "inner join exercice 	e on e.id 			= r.id_exercice "
		+ "inner join musclegroup	g on g.id 			= e.id_musclegroup "
		+ "where w.id > 0 order by id_rep desc;";

	//private RepetitionDAO repetitionData;

	@Override
	public List<Workout> listAllWorkouts() {

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
				workout.setId(rs.getInt("id"));
				workout.setStart(rs.getTimestamp("start"));
				workout.setEnd(rs.getTimestamp("end"));
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
	
	public Integer createWorkout(String json) {
		
		// create workout
		return null;
	}
	
	
	private Workout processWorkout(ResultSet rs) throws SQLException {

		Workout workout = new Workout();
		workout.setId(rs.getInt("id"));
		//workout.setStart(rs.getDate("start"));
		workout.setStart(rs.getTimestamp("start"));
		//workout.setEnd(rs.getDate("end"));
		workout.setEnd(rs.getTimestamp("end"));
		return workout;
	}

}
