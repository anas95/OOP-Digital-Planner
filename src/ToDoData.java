import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 * Class for storing information on to-do-list event
 * @author ANAS
 *
 */
public class ToDoData implements Serializable{

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private ArrayList<JTextField> txtTodo;
	private ArrayList<JCheckBox> check;

	/**
	 * Store data of to-do-list
	 * @param subject Subject of to-do-list
	 * @param txtTodo List of activities
	 * @param check Checkbox for each activities
	 */
	public ToDoData(String subject, ArrayList<JTextField> txtTodo, ArrayList<JCheckBox> check) {
		this.subject = subject;
		this.txtTodo = txtTodo;
		this.check = check;
	}
	
	/**
	 * Get subject of to-do-list
	 * @return Subject of to-do-list
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Get list of activities
	 * @return List of activities
	 */
	public ArrayList<JTextField> getTextField() {
		return txtTodo;
	}
	
	/**
	 * Get checkbox for each activities
	 * @return Checkbos for each activities
	 */
	public ArrayList<JCheckBox> getCheckBox() {
		return check;
	}
}
