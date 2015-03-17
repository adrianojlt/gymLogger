package pt.adrz.gymlogger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import pt.adrz.gymlogger.model.Exercice;
import pt.adrz.gymlogger.model.MuscleGroup;

public class ExerciceDAOJDBC extends ExerciceFactory {

	@Resource(name = "jdbc/gymlogger")
	private DataSource datasource;
	
	private static final String SQL_QUERY_GET_ALL_EXERCICES = 
		"SELECT id,name FROM exercice;";

	private static final String SQL_QUERY_GET_BY_ID = 
		"SELECT id,name FROM exercice WHERE id = ?;";

	private static final String SQL_QUERY_GET_BY_MUSCLEGROUPID = 
		"SELECT id,name FROM exercice WHERE id_musclegroup = ?;";
	
	public ExerciceDAOJDBC() {
		
	}
	
	private Exercice processExercice(ResultSet rs) throws SQLException {

		Exercice exercice = new Exercice();
		exercice.setId(rs.getInt("id"));
		exercice.setName(rs.getString("name"));
		return exercice;
	}

	@Override
	public List<Exercice> listAllExercices() {

		List<Exercice> list = new ArrayList<Exercice>();
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;


		try {
			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(ExerciceDAOJDBC.SQL_QUERY_GET_ALL_EXERCICES);
			while ( rs.next() ) { list.add(this.processExercice(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(rs,st,conn); }

		return list;
	}

	@Override
	public List<MuscleGroup> listAllMuscleGroups() {
		return null;
	}

	@Override
	public Exercice getExerciceById(int id) {
		
		Exercice exercice = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {

            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(ExerciceDAOJDBC.SQL_QUERY_GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
            	exercice = new Exercice();
            	exercice = this.processExercice(rs);
            }
            else {
            	return null;
            }
        } 
		catch (SQLException eSQL) { eSQL.printStackTrace(); } 
		catch (Exception e) { e.printStackTrace(); }
		finally { ConnectionFactory.close(rs,ps,conn); }

		return exercice;
	}

	@Override
	public List<Exercice> getExercicesByMuscleGroupId(int id) {
		
		List<Exercice> list = new ArrayList<Exercice>();
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(ExerciceDAOJDBC.SQL_QUERY_GET_BY_MUSCLEGROUPID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			while ( rs.next() ) { list.add(this.processExercice(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(conn); }

		return list;
	}
}
