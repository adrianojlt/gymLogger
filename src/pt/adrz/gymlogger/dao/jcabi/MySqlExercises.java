package pt.adrz.gymlogger.dao.jcabi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import pt.adrz.gymlogger.dao.ExerciseDAO;

public class MySqlExercises implements Exercises {
	
	private final DataSource source;
	
	public MySqlExercises(DataSource source) {
		this.source = source;
	}

	@Override
	public Iterable<Exercise> iterate() {
		
		Iterable<Exercise> exercises = null;
		
		try {
			exercises = new JdbcSession(this.source)
				.sql(ExerciseDAO.SQL_QUERY_GET_ALL_EXERCICES)
				.select(
					new ListOutcome<Exercise>(
						new ListOutcome.Mapping<Exercise>() {
							@Override
							public Exercise map(ResultSet rset) throws SQLException {
								return new MySqlExercise(rset.getInt(1), source);
							}
						}));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exercises;
	}
}
