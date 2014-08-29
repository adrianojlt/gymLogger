package pt.adrz.gymlogger.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Workout implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	//private Date start;
	private Timestamp start;
	//private Date end;
	private Timestamp end;
	
	private List<Repetition> repetitions;

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
}
