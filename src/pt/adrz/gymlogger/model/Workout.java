package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Workout implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ID 		= "id";
	public static final String ID_USER 	= "id_user";
	public static final String START 	= "start";
	public static final String END 		= "end";

	private Integer id;
	private Timestamp start;
	private Timestamp end;
	
	private List<Repetition> repetitions;
	private List<MuscleGroup> groups;
	
	public Workout() {
		this.repetitions = new ArrayList<Repetition>();
		this.groups = new ArrayList<MuscleGroup>();
	}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Date getStart() { return start; }
	//public void setStart(Date start) { this.start = start; }
	public void setStart(Timestamp start) { this.start = start; }
	public Date getEnd() { return end; }
	//public void setEnd(Date end) { this.end = end; }
	public void setEnd(Timestamp end) { this.end = end; }

	public List<Repetition> getRepetitions() { return repetitions; }
	public void setRepetitions(List<Repetition> repetitions) { this.repetitions = repetitions; }

	public List<MuscleGroup> getGroups() { return groups; }
	public void setGroups(List<MuscleGroup> groups) { this.groups = groups; }
}
