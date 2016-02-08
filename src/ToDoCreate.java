import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Class for interface of to-do-list
 * @author ANAS
 *
 */
public class ToDoCreate extends JPanel implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String typeEvent = "TODO";
	private ArrayList<JTextField> txtTodo = new ArrayList<JTextField>();
	private ArrayList<JCheckBox> check = new ArrayList<JCheckBox>();
	private JButton btnAddTask, btnSave, btnCancel;
	private JPanel pnlToDo, pnlButton, pnlSubject;
	private GridBagConstraints gc = new GridBagConstraints();
	private JScrollPane scroll;
	private JLabel lblSubject, lblDate;
	private JTextField txtSubject;
	private JDateChooser txtDate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Create inteface for creating new to-do-list
	 * @param user Current user login
	 */
	public ToDoCreate(User user) {
		this.user = user;
		init();
	}
	
	/**
	 * Create interface for displaying saved to-do-list
	 * @param user Current user login
	 * @param data Saved to-do-list data
	 * @param date Date which saved to-do-list created
	 */
	public ToDoCreate(User user, ToDoData data, Date date) {
		this.user = user;
		txtTodo = data.getTextField();
		check = data.getCheckBox();
		
		createSubjectField();
		createButton();
		setupSubjectPanel();
		setupToDoPanel();
		
		txtSubject.setText(data.getSubject());
		txtSubject.setEditable(false);
		txtDate.setDate(date);
		lblDate.setVisible(false);
		txtDate.setVisible(false);
		
		setBorder(BorderFactory.createTitledBorder("To Do List"));
		setLayout(new BorderLayout());
		add(pnlSubject, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);
	}

	/**
	 * Initiate building the inteface
	 */
	private void init() {
		createSubjectField();
		createTextField();
		createButton();
		setupSubjectPanel();
		setupToDoPanel();

		setBorder(BorderFactory.createTitledBorder("To Do List"));
		setLayout(new BorderLayout());
		add(pnlSubject, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Create subject field
	 */
	private void createSubjectField() {
		lblSubject = new JLabel("Subject");
		txtSubject = new JTextField(20);
		
		lblDate = new JLabel("Date");
		txtDate = new JDateChooser();
	}

	/**
	 * Create text field
	 */
	private void createTextField() {
		txtTodo.add(new JTextField(20));
		check.add(new JCheckBox("Done"));
	}

	/**
	 * Create button and link to listener
	 */
	private void createButton() {
		btnAddTask = new JButton("Add Task");
		btnSave = new JButton("Save");
		btnCancel = new JButton("Cancel");

		btnAddTask.addActionListener(new ToDoAction());
		btnSave.addActionListener(new ToDoAction());
		btnCancel.addActionListener(new ToDoAction());
	}
	
	/**
	 * Setup panel of subject
	 */
	private void setupSubjectPanel() {
		pnlSubject = new JPanel(new GridBagLayout());
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.gridx = 0;
		
		gc.gridy = 0;
		gc.gridx = 0; pnlSubject.add(lblSubject, gc);
		gc.gridx = 1; pnlSubject.add(txtSubject, gc);
		
		gc.gridy = 1;
		gc.gridx = 0; pnlSubject.add(lblDate, gc);
		gc.gridx = 1; pnlSubject.add(txtDate, gc);
	}

	/**
	 * Setup to-do field and checkbox
	 */
	private void setupToDoPanel() {
		pnlToDo = new JPanel(new GridBagLayout());
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.gridx = 0;
		for (int i = 0; i < txtTodo.size(); i++) {
			gc.gridy = i;
			pnlToDo.add(txtTodo.get(i), gc);
		}
		gc.gridx = 1;
		for (int i = 0; i < check.size(); i++) {
			gc.gridy = i;
			pnlToDo.add(check.get(i), gc);
		}
		scroll = new JScrollPane(pnlToDo);

		pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnAddTask);
		pnlButton.add(Box.createRigidArea(new Dimension(5, 0)));
		pnlButton.add(btnSave);
		pnlButton.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Get subject of to-do-list
	 * @return Subject of to-do-list
	 */
	public String getSubject() {
		return txtSubject.getText();
	}
	
	/**
	 * Get date of to-do-list
	 * @return Date of to-do-list
	 */
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(txtDate.getDate());
	}
	
	/**
	 * Get type of event
	 * @return Type of event
	 */
	public String getTypeEvent() {
		return typeEvent;
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
	 * @return Checkbox for each activities
	 */
	public ArrayList<JCheckBox> getCheckBox() {
		return check;
	}
	
	/**
	 * Save to-do-list 
	 * @param dir Location of file to save
	 */
	private void saveEvent(String dir) {
		try {
			FileOutputStream outFile = new FileOutputStream(dir);
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.writeObject(new ToDoData(txtSubject.getText(), txtTodo, check));
			out.close();
			outFile.close();
			displaySaveSuccess();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Display dialog confirmation success
	 */
	private void displaySaveSuccess() {
		String title = "Event successfully save...";
		String msg =
				"To Do List: " +txtSubject.getText()+ 
				"\nhas been save....";
		JOptionPane.showMessageDialog(
				null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Class for listener for button
	 */
	private class ToDoAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAddTask) {
				createTextField();
				int i = txtTodo.size() - 1;
				gc.gridx = 0; gc.gridy = i;
				pnlToDo.add(txtTodo.get(i), gc);
				int j = check.size() - 1;
				gc.gridx = 1; gc.gridy = j;
				pnlToDo.add(check.get(j), gc);
				validate();
				repaint();
			}
			else if (e.getSource() == btnSave) {
				String dir = 
						user.getUserDir()+ "/TODO/" +sdf.format(txtDate.getDate());
				File file = new File(dir);
				if (!file.exists())
					file.mkdirs();
				dir += "/" +txtSubject.getText()+ ".ser";
				saveEvent(dir);
			}
		}
	}
}
