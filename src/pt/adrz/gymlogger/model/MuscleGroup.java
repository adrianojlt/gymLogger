package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.List;

public class MuscleGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String NOME = "nome";

	private Integer id;
	private String name;
	private String nome;

	private List<Exercise> exercises;
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public List<Exercise> getExercises() { return exercises; }
	public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
