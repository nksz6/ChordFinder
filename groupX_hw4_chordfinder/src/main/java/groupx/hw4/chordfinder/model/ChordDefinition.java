package groupx.hw4.chordfinder.model;
//Intervals enum is in same package

import java.util.List; //List is only util used in here

//Defines the chords for the updated JSON format

public class ChordDefinition {
	
	//full chord name, display_name (abbreviation), and intervals
	private String name;
	private String display_name;
	private List<String> intervals; //using list
	
	//JSON constructor
	public ChordDefinition() {
	}
	
	//getters and setters for all
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {		
		this.display_name = display_name;
	}
	public List<String> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<String> intervals) {
		this.intervals = intervals;
	}
	public List<Integer> getIntervalSemitones() {
		return intervals.stream() //stream interval names
				.map(Interval::fromString) //fromString & getSemitons in Intervals Enum
				.map(Interval::getSemitones)
				.toList(); //like [4, 3] for MAJOR_THIRD, MINOR_THIRD
	}
	
	//debug
	//@Override
	//public String toString() {
		//return "ChordDefinition{" + "name='" + name + '\'' + ", display_name='" + display_name + '\'' + ", intervals=" + intervals + '}';
	//}
	
}
