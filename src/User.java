import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User class for storing information (setter and getter class)
 * @author ANAS
 *
 */
public class User implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private String userID, pass, name, email, contactNo, userDir;
	private Date dateCreated;
	private SimpleDateFormat sdf = new SimpleDateFormat("MMMM, dd yyyy (EEEE)");
	
	/**
	 * Default constructor
	 * Not do anything
	 */
	public User() {}
	
	/**
	 * Create new User object with arguments
	 * @param userID User ID of user
	 * @param pass Password of user
	 * @param name Name of user
	 * @param email Email of user
	 * @param contactNo Contact no of user
	 */
	public User(String userID, String pass, String name, String email, String contactNo) {
		this.userID = userID;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		dateCreated = new Date();
		userDir = "./dat/" +userID;
	}
	
	/**
	 * Set user ID
	 * @param userID User ID of user
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * Set password
	 * @param pass Password of user
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Set name
	 * @param name Name of user
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set email
	 * @param email Email of user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Set contact no
	 * @param contactNo Contact no of user
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	/**
	 * Get user ID
	 * @return User ID of user
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Get name
	 * @return Name of user
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get password
	 * @return Password of user
	 */ 
	public String getPass() {
		return pass;
	}
	
	/**
	 * Get email
	 * @return Email of user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Get contact no
	 * @return Contact no of user
	 */
	public String getContactNo() {
		return contactNo;
	}
	
	/**
	 * Get date created
	 * @return Date of user created
	 */
	public String getDateCreated() {
		return sdf.format(dateCreated);
	}

	/**
	 * Get user working directory
	 * @return User working directory
	 */
	public String getUserDir() {
		return userDir;
	}
}
