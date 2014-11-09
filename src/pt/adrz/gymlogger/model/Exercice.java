package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exercice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String nome;

	private MuscleGroup muscleGroup;
	private List<Repetition> repetitions;

	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return this.nome; }

	public List<Repetition> getRepetitions() { return repetitions; }
	public void setRepetitions(List<Repetition> repetitions) { this.repetitions = repetitions; }

	public MuscleGroup getMuscleGroup() { return muscleGroup; }
	public void setMuscleGroup(MuscleGroup muscleGroup) { this.muscleGroup = muscleGroup; }
}
