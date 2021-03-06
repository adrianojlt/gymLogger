package pt.adrz.gymlogger.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import pt.adrz.gymlogger.connection.ConnectionFactory;
import pt.adrz.gymlogger.dao.ExerFactory;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.MuscleGroup;

public class ExerDAOJDBC extends ExerFactory {

	@Resource(name = "jdbc/gymlogger")
	private DataSource datasource;
	
	private static final String SQL_QUERY_GET_ALL_EXERCICES = 
		"SELECT id,name FROM exercise;";

	private static final String SQL_QUERY_GET_BY_ID = 
		"SELECT id,name FROM exercice WHERE id = ?;";

	private static final String SQL_QUERY_GET_BY_MUSCLEGROUPID = 
		"SELECT id,name FROM exercice WHERE id_musclegroup = ?;";
	
	public ExerDAOJDBC() {
		
	}
	
	private Exercise processExercice(ResultSet rs) throws SQLException {

		Exercise exercice = new Exercise();
		exercice.setId(rs.getInt("id"));
		exercice.setName(rs.getString("name"));
		return exercice;
	}

	@Override
	public List<Exercise> listAllExercices() {

		List<Exercise> list = new ArrayList<Exercise>();
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;


		try {
			conn = ConnectionFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(ExerDAOJDBC.SQL_QUERY_GET_ALL_EXERCICES);
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
	public Exercise getExerciceById(int id) {
		
		Exercise exercice = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {

            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(ExerDAOJDBC.SQL_QUERY_GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
            	exercice = new Exercise();
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
	public List<Exercise> getExercicesByMuscleGroupId(int id) {
		
		List<Exercise> list = new ArrayList<Exercise>();
		Connection conn = null;
		
		try {
			conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(ExerDAOJDBC.SQL_QUERY_GET_BY_MUSCLEGROUPID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			while ( rs.next() ) { list.add(this.processExercice(rs)); }
		}
		catch (SQLException eSQL) { eSQL.printStackTrace(); }
		finally { ConnectionFactory.close(conn); }

		return list;
	}
}
