package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.util.List;

public class MuscleGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String nome;

	private List<Exercice> exercices;
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public List<Exercice> getExercices() { return exercices; }
	public void setExercices(List<Exercice> exercices) { this.exercices = exercices; }
}
