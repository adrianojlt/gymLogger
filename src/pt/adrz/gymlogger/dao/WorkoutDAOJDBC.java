package pt.adrz.gymlogger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.gymlogger.connection.ConnectionFactory;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.MuscleGroup;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class WorkoutDAOJDBC implements WorkoutDAO {
	
	private static final String QUERY_GET_WORKOUTS = 
		"SELECT id , start , end FROM workout ORDER BY id DESC";

	private static final String QUERY_COUNT_WORKOUTS = 
		"SELECT COUNT(*) FROM workout ORDER BY id DESC";

	private static final String QUERY_GET_WORKOUTS_BY_ID = 
		"SELECT id , start , end FROM workout WHERE id = ?;";

	private static final String QUERY_GET_ALL_WORKOUTS = 
		"SELECT w.id AS wid, w.start, w.end, g.id AS gid, g.name AS gname, e.id AS eid, e.name AS ename, r.id AS rid, r.num, r.weight "
		+ "FROM workout w "
		+ "INNER JOIN repetition r ON r.id_workout = w.id "
		+ "INNER JOIN exercise e ON e.id = r.id_exercise "
		+ "INNER JOIN musclegroup g ON g.id = e.id_musclegroup "
		+ "ORDER BY wid DESC, rid ASC;";

	private static final String QUERY_GET_GROUPS_FROM_WORKOUT = 
		"SELECT g.id AS gid, g.name AS gname, e.id AS eid, e.name AS ename, r.id AS rid, r.num AS num, r.weight AS weight "
		+ "FROM repetition r "
		+ "INNER JOIN exercise e  		ON e.id = r.id_exercise "
		+ "INNER JOIN musclegroup g  	ON g.id = e.id_musclegroup "
		+ "WHERE r.id_workout = ? "
		+ "ORDER BY rid ASC;";

	private static final String QUERY_INSERT_WORKOUT 	= "INSERT INTO Workout (id_User,start,end) VALUES (1,?,?);";
	private static final String QUERY_INSERT_REPETITION = "INSERT INTO Repetition ( id_workout, id_exercise, weight, num ) VALUES (?,?,?,?);";

	private int noOfRecords;
	//private RepetitionDAO repetitionData;
	
	

	@Override
	public List<Workout> getWorkouts() {
		return null;
	}
	
	@Override
	public int getNoOfRecords() { return noOfRecords; }

	@Override
	public List<Workout> getWorkouts(int start, int count) {
		
		List<Workout> workouts = new ArrayList<Workout>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();

			// JDBC statement as "Scroll Sensitive"
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			// For better performance It is recommend to set the following "hints" to the JDBC Driver:
			st.setFetchSize(count);
			st.setMaxRows(count);
			
			//rs = st.executeQuery(QUERY_GET_WORKOUTS);
			//rs = st.executeQuery(QUERY_GET_WORKOUTS + " Limit " + (pageNum-1) * pageSize + ", " + pageSize);
			rs = st.executeQuery(QUERY_GET_WORKOUTS + " Limit " + start + ", " + count);

			while ( rs.next() ) { workouts.add(this.processWorkout(rs)); }

			for ( Workout workout : workouts) { 
				workout.setGroups(this.getGroups(workout));
			}
			
			rs = st.executeQuery(QUERY_COUNT_WORKOUTS);
			if(rs.next()) this.noOfRecords = rs.getInt(1);
			
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
	public List<Workout> getWorkoutsWithGroups() {
		
		List<Workout> workouts = new ArrayList<Workout>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(QUERY_GET_ALL_WORKOUTS);
			
			while ( rs.next() ) {
				
				if ( rs.isFirst() ) { // process the hole row ...
					
					Repetition rep = new Repetition();
					Exercise exercise = new Exercise();
					MuscleGroup group = new MuscleGroup();
					Workout workout = new Workout();

					rep.setId(rs.getInt("rid"));
					rep.setNum(rs.getInt("num"));
					rep.setWeight(rs.getFloat("weight"));

					exercise.setId(rs.getInt("eid"));
					exercise.setName(rs.getString("ename"));

					group.setId(rs.getInt("gid"));
					group.setName(rs.getString("gname"));

					workout.setId(rs.getInt("wid"));
					workout.setStart(rs.getTimestamp(Workout.START));
					workout.setEnd(rs.getTimestamp(Workout.END));
					
					exercise.getRepetitions().add(rep);
					group.getExercises().add(exercise);
					workout.getGroups().add(group);
					
					workouts.add(workout);
				}
				else {

					Workout lastWorkout 	= workouts.get(workouts.size()-1);
					MuscleGroup lastGroup 	= lastWorkout.getGroups().get(lastWorkout.getGroups().size()-1);
					Exercise lastExercise 	= lastGroup.getExercises().get(lastGroup.getExercises().size() - 1);
					
					if ( lastWorkout.getId() == rs.getInt("wid")) {
						
						if ( lastGroup.getId() == rs.getInt("gid") ) {
							
							if ( lastExercise.getId() == rs.getInt("eid") ) { // new repetition ...

								Repetition rep = new Repetition();

								rep.setId(rs.getInt("rid"));
								rep.setNum(rs.getInt("num"));
								rep.setWeight(rs.getFloat("weight"));
								
								lastExercise.getRepetitions().add(rep);
								lastGroup.getExercises().set(lastGroup.getExercises().size() - 1, lastExercise);
								lastWorkout.getGroups().set(lastWorkout.getGroups().size() - 1, lastGroup);
								
								workouts.set(workouts.size() - 1, lastWorkout);
							}
							else { // new exercise

								Repetition rep = new Repetition();
								Exercise exercise = new Exercise();

								rep.setId(rs.getInt("rid"));
								rep.setNum(rs.getInt("num"));
								rep.setWeight(rs.getFloat("weight"));

								exercise.setId(rs.getInt("eid"));
								exercise.setName(rs.getString("ename"));
								
								exercise.getRepetitions().add(rep);
								lastGroup.getExercises().add(exercise);
								lastWorkout.getGroups().set(lastWorkout.getGroups().size() - 1, lastGroup);
								
								workouts.set(workouts.size() - 1, lastWorkout);
							}
						}
						else { // new musclegroup
							
							Repetition rep = new Repetition();
							Exercise exercise = new Exercise();
							MuscleGroup group = new MuscleGroup();

							rep.setId(rs.getInt("rid"));
							rep.setNum(rs.getInt("num"));
							rep.setWeight(rs.getFloat("weight"));

							exercise.setId(rs.getInt("eid"));
							exercise.setName(rs.getString("ename"));

							group.setId(rs.getInt("gid"));
							group.setName(rs.getString("gname"));
							
							exercise.getRepetitions().add(rep);
							group.getExercises().add(exercise);
							lastWorkout.getGroups().add(group);

							workouts.set(workouts.size() - 1, lastWorkout);
						}
					}
					else {
						
						Repetition rep = new Repetition();
						Exercise exercise = new Exercise();
						MuscleGroup group = new MuscleGroup();
						Workout workout = new Workout();

						rep.setId(rs.getInt("rid"));
						rep.setNum(rs.getInt("num"));
						rep.setWeight(rs.getFloat("weight"));

						exercise.setId(rs.getInt("eid"));
						exercise.setName(rs.getString("ename"));

						group.setId(rs.getInt("gid"));
						group.setName(rs.getString("gname"));

						workout.setId(rs.getInt("wid"));
						workout.setStart(rs.getTimestamp(Workout.START));
						workout.setEnd(rs.getTimestamp(Workout.END));
						
						exercise.getRepetitions().add(rep);
						group.getExercises().add(exercise);
						workout.getGroups().add(group);
						
						workouts.add(workout);
					}
				}
			}
			//while ( rs.next() ) { workouts.add(this.processWorkout(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs, st, conn); }
	
		return workouts;
	}

	@Override
	public List<Workout> listAllWorkouts(int offset, int noOfRecords) {
		return null;
	}

	@Override
	public Workout getWorkoutById(int id) throws Exception {
		
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
			
			RepetitionDAO repetitionDAO = new RepetitionDAOJDBC();

			workout.setRepetitions(repetitionDAO.getRepetitions(workout));
			workout.setGroups(this.getGroups(workout));
		}
		catch (SQLException eSQL) 			{ throw eSQL; }
		catch (NullPointerException eNull) 	{ throw eNull; }
		catch (Exception e) 				{ throw e; }
		finally { ConnectionFactory.close(rs, ps, conn); }

		return workout;
	}
	
	public List<MuscleGroup> getGroups(Workout workout) {

		List<MuscleGroup> groups = new ArrayList<MuscleGroup>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(QUERY_GET_GROUPS_FROM_WORKOUT);
			ps.setInt(1,workout.getId());	
			rs = ps.executeQuery();
			
			while ( rs.next() ) {
				
				if ( rs.isFirst() ) { 
					
					Repetition rep = new Repetition();
					Exercise exercise = new Exercise();
					MuscleGroup group = new MuscleGroup();

					rep.setId(rs.getInt("rid"));
					rep.setNum(rs.getInt("num"));
					rep.setWeight(rs.getFloat("weight"));

					exercise.setId(rs.getInt("eid"));
					exercise.setName(rs.getString("ename"));

					group.setId(rs.getInt("gid"));
					group.setName(rs.getString("gname"));

					exercise.getRepetitions().add(rep);
					group.getExercises().add(exercise);
					groups.add(group);
					
				}
				else {
					
					MuscleGroup lastGroup 	= groups.get(groups.size() - 1);
					Exercise lastExercise 	= lastGroup.getExercises().get(lastGroup.getExercises().size() - 1);
					
					if ( lastGroup.getId() == rs.getInt("gid") ) {
						
						if ( lastExercise.getId() == rs.getInt("eid") ) { // new repetition ...

							Repetition rep = new Repetition();

							rep.setId(rs.getInt("rid"));
							rep.setNum(rs.getInt("num"));
							rep.setWeight(rs.getFloat("weight"));
							
							lastExercise.getRepetitions().add(rep);
							lastGroup.getExercises().set(lastGroup.getExercises().size() - 1, lastExercise);
							groups.set(groups.size() - 1, lastGroup);
						}
						else { // new exercise

							Repetition rep = new Repetition();
							Exercise exercise = new Exercise();

							rep.setId(rs.getInt("rid"));
							rep.setNum(rs.getInt("num"));
							rep.setWeight(rs.getFloat("weight"));

							exercise.setId(rs.getInt("eid"));
							exercise.setName(rs.getString("ename"));
							
							exercise.getRepetitions().add(rep);
							lastGroup.getExercises().add(exercise);
							groups.set(groups.size() - 1, lastGroup);
							
						}
					}
					else { // new musclegroup
						
						Repetition rep = new Repetition();
						Exercise exercise = new Exercise();
						MuscleGroup group = new MuscleGroup();

						rep.setId(rs.getInt("rid"));
						rep.setNum(rs.getInt("num"));
						rep.setWeight(rs.getFloat("weight"));

						exercise.setId(rs.getInt("eid"));
						exercise.setName(rs.getString("ename"));

						group.setId(rs.getInt("gid"));
						group.setName(rs.getString("gname"));
						
						exercise.getRepetitions().add(rep);
						group.getExercises().add(exercise);
						groups.add(group);

					}
				}
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(conn); }
		
		return groups;
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
			//conn.setAutoCommit(false);
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

				
				// using 'update batch'
				//ps.addBatch();
				//ps.executeBatch();
				//conn.commit();
			}

		}
		catch (SQLException eSQL) { 
			eSQL.printStackTrace(); 
		}
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
