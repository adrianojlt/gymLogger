package pt.adrz.gymlogger.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Exercice.class)
public class Exercices extends ArrayList<Exercice>{
	
	private static final long serialVersionUID = 1L;

	public Exercices() { super(); }
	
	public Exercices(Collection<? extends Exercice> exercices) {
		super(exercices);
	}
	
	@XmlElement(name = "exercice")
	public List<Exercice> getExercices() {
		return this;
	}

	public void setExercices(List<Exercice> exercices) {
		this.addAll(exercices);
	}
}
