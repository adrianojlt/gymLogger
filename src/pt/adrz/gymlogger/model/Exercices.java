package pt.adrz.gymlogger.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Exercise.class)
public class Exercices extends ArrayList<Exercise>{
	
	private static final long serialVersionUID = 1L;

	public Exercices() { super(); }
	
	public Exercices(Collection<? extends Exercise> exercices) {
		super(exercices);
	}
	
	@XmlElement(name = "exercice")
	public List<Exercise> getExercices() {
		return this;
	}

	public void setExercices(List<Exercise> exercices) {
		this.addAll(exercices);
	}
}
