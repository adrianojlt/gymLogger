package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exercise implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String ID 				= "id";
	public static final String ID_MUSCLEGROUP 	= "id_musclegroup";
	public static final String NAME 			= "name";
	public static final String NOME 			= "nome";

	private int id;
	private String name;
	private String nome;

	private MuscleGroup muscleGroup;
	private List<Repetition> repetitions;
	
	public Exercise() {
		this.repetitions = new ArrayList<Repetition>();
	}

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
