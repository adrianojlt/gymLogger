package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Repetition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Float weight;
	private Integer num;

	private Exercice exercice;
	private Workout workout;

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Float getWeight() { return weight; }
	public void setWeight(Float weight) { this.weight = weight; }
	public Integer getNum() { return num; }
	public void setNum(Integer num) { this.num = num; }

	public Exercice getExercice() { return exercice; }
	public void setExercice(Exercice exercice) { this.exercice = exercice; }

	public Workout getWorkout() { return workout; }
	public void setWorkout(Workout workout) { this.workout = workout; }
}
