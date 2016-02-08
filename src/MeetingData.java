import java.io.Serializable;

/**
 * Class for storing data of meeting event
 * @author ANAS
 *
 */
public class MeetingData implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private String subject, type, location, time;
	
	/**
	 * Creating object for storing data of meeting
	 * @param type Type of meeting
	 * @param subject Subject of meeting
	 * @param location Location of meeting
	 * @param time Time of meeting
	 */
	public MeetingData(String type, String subject, String location, String time) {
		this.type = type;
		this.subject = subject;
		this.location = location;
		this.time = time;
	}
	
	/**
	 * Getter for subject
	 * @return Subject of meeting
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Getter for type of meeting
	 * @return Type of meeting
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter for location of meeting
	 * @return Location of meeting
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Getter for time of meeting
	 * @return Time of meeting
	 */
	public String getTime() {
		return time;
	}
}
