package pt.adrz.gymlogger.dao.jcabi;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

import pt.adrz.gymlogger.dao.ExerciseDAO;

public class MySqlExercise implements Exercise {
	
	private final int id;
	private final DataSource source;
	
	public MySqlExercise(int id, DataSource source) {
		this.id = id;
		this.source = source;
	}

	@Override
	public int id() {
		return this.id;
	}

	@Override
	public String name() {

		String name = null;

		try {
			name =  new JdbcSession(this.source)
				.sql(ExerciseDAO.SQL_QUERY_GET_BY_ID)
				.set(this.id)
				.select(new SingleOutcome<String>(String.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}

}
