package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Repetition implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String ID 			= "id";
	public static final String ID_WORKOUT 	= "id_workout";
	public static final String ID_EXERCISE 	= "id_exercise";
	public static final String WEIGHT 		= "weight";
	public static final String NUM 			= "num";
	
	private Integer id;
	private Float weight;
	private Integer num;

	private Workout workout;
	private Exercise exercise;
	
	public Repetition() { }

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Float getWeight() { return weight; }
	public void setWeight(Float weight) { this.weight = weight; }
	public Integer getNum() { return num; }
	public void setNum(Integer num) { this.num = num; }

	public Exercise getExercise() { return exercise; }
	public void setExercise(Exercise exercise) { this.exercise = exercise; }

	public Workout getWorkout() { return workout; }
	public void setWorkout(Workout workout) { this.workout = workout; }
}
