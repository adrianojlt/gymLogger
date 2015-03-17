package pt.adrz.gymlogger.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import pt.adrz.gymlogger.model.Exercice;
import pt.adrz.gymlogger.model.MuscleGroup;

public class MuscleGroupDAOJDBC implements MuscleGroupDAO {

	private static final String QUERY_GET_MUSCLEGROUPS = 
			"SELECT g.id , g.name , g.nome , e.id AS id_exercice , e.name AS name_exercice , e.nome AS nome_exercice  FROM Musclegroup g "
		+ 	"INNER JOIN exercice e on g.id = e.id_MuscleGroup;";

	private static final String QUERY_GET_MUSCLEGROUPS_BY_ID = 
			"SELECT g.id , g.name , g.nome , e.id AS id_exercice , e.name AS name_exercice , e.nome AS nome_exercice  FROM Musclegroup g "
		+ 	"INNER JOIN exercice e on g.id = e.id_MuscleGroup "
		+ 	"WHERE g.id = ?;";

	@Override
	public List<MuscleGroup> getMuscleGroups() {

		List<MuscleGroup> groups = new ArrayList<MuscleGroup>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(QUERY_GET_MUSCLEGROUPS);

			while ( rs.next() ) { 
				
				MuscleGroup last = null;

				if ( groups.isEmpty() ) {

					groups.add(this.processNewMuscleGroup(rs));
				}
				else {
					
					last = groups.get(groups.size() - 1);

					Exercice exercice = new Exercice();

					if ( last.getId() == rs.getInt("id") ) {
						
						exercice.setId(rs.getInt("id_exercice"));
						exercice.setName(rs.getString("name_exercice"));
						exercice.setNome(rs.getString("nome_exercice"));

						last.getExercices().add(exercice);
					}
					else {

						groups.add(this.processNewMuscleGroup(rs));
					}
				}
			}

		} catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs, st, conn); }
	
		return groups;
	}

	@Override
	public MuscleGroup getMuscleGroup(int id) {

		MuscleGroup group = new MuscleGroup();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			try {

				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(QUERY_GET_MUSCLEGROUPS_BY_ID);
				ps.setInt( 1 , id );	
				rs = ps.executeQuery();
				
				if ( rs.next() ) group = this.processNewMuscleGroup(rs);

				while ( rs.next() ) { 

					Exercice exercice = new Exercice();

					exercice.setId(rs.getInt("id_exercice"));
					exercice.setName(rs.getString("name_exercice"));
					exercice.setNome(rs.getString("nome_exercice"));
					
					group.getExercices().add(exercice);
				}

			} catch (SQLException e) { e.printStackTrace(); }
			finally { ConnectionFactory.close(rs,ps,conn); }

		return group;
	}
	
	private MuscleGroup processNewMuscleGroup(ResultSet rs) throws SQLException {
		
		MuscleGroup group = new MuscleGroup();
		Exercice exercice = new Exercice();
		List<Exercice> exercices = new ArrayList<Exercice>();
		
		group.setId(rs.getInt("id"));
		group.setName(rs.getString("name"));
		group.setNome(rs.getString("nome"));
	
		exercice.setId(rs.getInt("id_exercice"));
		exercice.setName(rs.getString("name_exercice"));
		exercice.setNome(rs.getString("nome_exercice"));

		exercices.add(exercice);
		group.setExercices(exercices);
		
		return group;
	}
}
