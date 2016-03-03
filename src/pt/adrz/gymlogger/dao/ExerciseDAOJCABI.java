package pt.adrz.gymlogger.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.Outcome;

import pt.adrz.gymlogger.connection.ConnectionFactory;
import pt.adrz.gymlogger.model.Exercise;
import pt.adrz.gymlogger.model.MuscleGroup;

public class ExerciseDAOJCABI implements ExerciseDAO {

	@Override
	public Collection<Exercise> listAllExercices() {
		
		Collection<Exercise> exercises = null;

		Outcome<Collection<Exercise>> outcome =  new Outcome<Collection<Exercise>>() {

			@Override
			public Collection<Exercise> handle(ResultSet rset, Statement stmt) throws SQLException {

				final Collection<Exercise> exercises = new LinkedList<Exercise>();

	            while (rset.next()) {

	            	Exercise exercise = new Exercise();

	            	exercise.setId(rset.getInt(1));
	            	exercise.setName(rset.getString(2));
	            	exercises.add(exercise);
	            }

	            return exercises;
			}
		};

		try {

			exercises = new JdbcSession(ConnectionFactory.getConnection())
				.sql(SQL_QUERY_GET_ALL_EXERCICES)
				.select(outcome);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exercises;
	}

	@Override
	public Exercise getExerciceById(int id) {

		Exercise exercise = null;

		try {

			 exercise = new JdbcSession(ConnectionFactory.getConnection())
				.sql(SQL_QUERY_GET_BY_ID)
				.set(id)
				.select(new Outcome<Exercise>() {
					@Override
					public Exercise handle(ResultSet rset, Statement stmt) throws SQLException {
						Exercise exercise = new Exercise();
						rset.next();
						exercise.setId(rset.getInt(1));
						exercise.setName(rset.getString(2));
						return exercise;
					}
				});

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exercise;
	}

	@Override
	public Collection<Exercise> getExercicesByMuscleGroupId(int id) {

		Collection<Exercise> exercises = null;

		Outcome<Collection<Exercise>> outcome =  new Outcome<Collection<Exercise>>() {

			@Override
			public Collection<Exercise> handle(ResultSet rset, Statement stmt) throws SQLException {

				final Collection<Exercise> exercises = new LinkedList<Exercise>();

	            while (rset.next()) {

	            	Exercise exercise = new Exercise();

	            	exercise.setId(rset.getInt(1));
	            	exercise.setName(rset.getString(2));
	            	exercises.add(exercise);
	            }

	            return exercises;
			}
		};

		try {

			exercises = new JdbcSession(ConnectionFactory.getConnection())
				.sql(SQL_QUERY_GET_BY_MUSCLEGROUPID)
				.set(id)
				.select(outcome);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercises;
	}

	@Override
	public Collection<MuscleGroup> listAllMuscleGroups() {
		// TODO Auto-generated method stub
		return null;
	}
}
