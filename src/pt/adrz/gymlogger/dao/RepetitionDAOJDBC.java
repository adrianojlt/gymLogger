package pt.adrz.gymlogger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pt.adrz.gymlogger.model.Exercice;
import pt.adrz.gymlogger.model.MuscleGroup;
import pt.adrz.gymlogger.model.Repetition;
import pt.adrz.gymlogger.model.Workout;

public class RepetitionDAOJDBC implements RepetitionDAO {

	private static final String QUERY_GET_REPETITIONS = 
		"SELECT r.id as id , r.weight as weight , r.num as num , e.id as id_exercice , e.name as name , e.nome as nome , " +
		"g.id as id_musclegroup , g.nome as musclegroupnome, g.name as musclegroupname " +
		"FROM repetition r " + 
		"INNER JOIN exercice e on e.id = r.id_exercice " + 
		"INNER JOIN musclegroup g on g.id = e.id_musclegroup " +
		"ORDER BY id DESC;";

	private static final String QUERY_GET_REPETITIONS_BY_WORKOUTID = 
		"SELECT r.id as id , r.weight as weight , r.num as num , e.id as id_exercice , e.name as name , e.nome as nome " +
		"FROM repetition r INNER JOIN exercice e on e.id = r.id_exercice WHERE r.id_workout = ?";

	/**
	 * Get all repetitions
	 */
	@Override
	public List<Repetition> getRepetitions() {
		
		List<Repetition> repetitions = new ArrayList<Repetition>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {

			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(QUERY_GET_REPETITIONS);

			while ( rs.next() ) { 
				
				Repetition repetition = new Repetition();
				Exercice exercice = new Exercice();
				MuscleGroup muscleGroup = new MuscleGroup();
				
				muscleGroup.setId(rs.getInt("id_musclegroup"));
				muscleGroup.setName(rs.getString("musclegroupname"));
				muscleGroup.setNome(rs.getString("musclegroupnome"));

				exercice.setId(rs.getInt("id_exercice"));
				exercice.setName("name");
				exercice.setMuscleGroup(muscleGroup);
				
				repetition.setId(rs.getInt("id"));
				repetition.setNum(rs.getInt("num"));
				repetition.setWeight(rs.getFloat("weight"));
				repetition.setExercice(exercice);
				
				repetitions.add(repetition);
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs, st, conn); }
		
		return repetitions;
	}

	@Override
	public List<Repetition> getRepetitions(Workout workout) {
		
		List<Repetition> repetitions = new ArrayList<Repetition>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(QUERY_GET_REPETITIONS_BY_WORKOUTID);
			ps.setInt(1,workout.getId());	
			rs = ps.executeQuery();

			while ( rs.next() ) { 

				Repetition repetition = new Repetition();
				Exercice exercice = new Exercice();

				exercice.setId(rs.getInt("id_exercice"));
				exercice.setName(rs.getString("name"));
				exercice.setNome(rs.getString("nome"));

				//repetition.setWorkout(workout);
				repetition.setId(rs.getInt("id"));
				repetition.setWeight(rs.getFloat("weight"));
				repetition.setNum(rs.getInt("num"));
				repetition.setExercice(exercice);

				repetitions.add(repetition);
			}
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs,ps,conn); }
		
		return repetitions;
	}
}