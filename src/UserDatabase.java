import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to modify, and create user database
 * @author ANAS
 *
 */
public class UserDatabase implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private String dirFile = "./dat/userlist.ser";
	private ArrayList<User> userlist = null;
	
	/**
	 * Check for user database file
	 * If available, load the database
	 * Else, create new database
	 */
	public UserDatabase() {
		File file = new File(dirFile);
		if (file.exists())
			load();
		else
			userlist = new ArrayList<User>();
	}
	
	/**
	 * Add new user into database then store into file
	 * @param userID User ID of user
	 * @param pass Password of user
	 * @param name Name of user
	 * @param email Email of user
	 * @param contactNo Contact no of user
	 */
	public void register(String userID, String pass, String name, String email, String contactNo) {
		userlist.add(new User(userID, pass, name, email, contactNo));
		
		try {
			FileOutputStream outFile = new FileOutputStream(dirFile);
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.writeObject(userlist);
			out.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Store updated database into file
	 */
	public void register() {
		try {
			FileOutputStream outFile = new FileOutputStream(dirFile);
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.writeObject(userlist);
			out.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load database from file
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		try {
			FileInputStream inFile = new FileInputStream(dirFile);
			ObjectInputStream in = new ObjectInputStream(inFile);
			userlist = (ArrayList<User>)in.readObject();
			in.close();
			inFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check user verification
	 * @param userID User ID of user
	 * @param pass Password of user
	 * @return true if userID matched with its password
	 */
	public boolean validateUser(String userID, String pass) {
		for (User user : userlist){
			if (user.getUserID().equals(userID) && user.getPass().equals(pass))
				return true;
		}
		return false;
	}
	
	/**
	 * Check user availibility
	 * @param userID User ID of user
	 * @return true if userID is in database
	 */
	public boolean checkUser(String userID) {
		for (User user : userlist) {
			if (user.getUserID().equals(userID))
				return true;
		}
		return false;
	}
	
	/**
	 * Update user details in database
	 * @param userID User ID of user
	 * @param user Updated user details
	 */
	public void replaceUser(String userID, User user) {
		int i = 0;
		for ( ; i < userlist.size(); i++) {
			if (userlist.get(i).getUserID().equals(userID))
				break;
		}
		userlist.set(i, user);
	}
	
	/**
	 * Get user details from database
	 * @param userID User ID of user
	 * @return user details
	 */
	public User getUserData(String userID) {
		for (User user : userlist) {
			if (user.getUserID().equals(userID))
				return user;
		}
		return null;
	}
}
