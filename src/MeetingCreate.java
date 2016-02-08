import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Class for creating and viewing a meeting event
 * @author ANAS
 *
 */
public class MeetingCreate extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JRadioButton radPersonal, radGroup;
	private ButtonGroup btnGroup;
	private JLabel lblType, lblSubject, lblDate, lblLocation, lblTime;
	private JTextField txtType, txtSubject, txtLocation, txtTime;
	private JDateChooser txtDate;
	private JButton btnSave;
	private JPanel pnlType, pnlMain, pnlButton, pnlCmb;
	private GridBagConstraints gc = new GridBagConstraints();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String type = new String();
	private DefaultComboBoxModel<String> modelHour, modelMin, modelDur;
	private JComboBox<String> cmbHour, cmbMin, cmbDur;
	private String hour[] = {
			"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
	};
	private String min[] = {
			"00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
			"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
			"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
			"40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
			"50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
	};
	private String dur[] = {"AM", "PM"};
	
	/**
	 * Creating the interface for
	 * creating new event
	 * @param user User login data
	 */
	public MeetingCreate(User user) {
		this.user = user;
		init();
		
		lblType.setVisible(false);
		txtType.setVisible(false);
		txtTime.setVisible(false);
	}
	
	/**
	 * Creating the interface for
	 * viewing existing event
	 * @param user User login data
	 * @param data Meeting data previously created
	 * @param date Date of meeting created
	 */
	public MeetingCreate(User user, MeetingData data, Date date) {
		this.user = user;
		this.type = data.getType();
		init();
		
		txtType.setText(data.getType());
		txtSubject.setText(data.getSubject());
		txtDate.setDate(date);
		txtLocation.setText(data.getLocation());
		txtTime.setText(data.getTime());
		
		pnlType.setVisible(false);
		lblDate.setVisible(false);
		txtDate.setVisible(false);
		pnlButton.setVisible(false);
		pnlCmb.setVisible(false);
		
		txtType.setEditable(false);
		txtSubject.setEditable(false);
		txtLocation.setEditable(false);
		txtTime.setEditable(false);
	}
	
	/**
	 * Initiate building the interface
	 */
	private void init() {
		createTypeRadio();
		createLabelTextField();
		createButton();
		createComboBox();
		setupTypeRadioPanel();
		setupLabelTextFieldComboBoxPanel();
		setupButtonPanel();
		
		setBorder(BorderFactory.createTitledBorder("Meeting"));
		setLayout(new BorderLayout());
		add(pnlType, BorderLayout.NORTH);
		add(pnlMain, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Create radio buttons
	 */
	private void createTypeRadio() {
		radPersonal = new JRadioButton("Personal", true);
		radGroup = new JRadioButton("Group");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(radPersonal);
		btnGroup.add(radGroup);
	}
	
	/**
	 * Create label and text field
	 */
	private void createLabelTextField() {
		lblType = new JLabel("Type");
		lblSubject = new JLabel("Subject");
		lblDate = new JLabel("Date");
		lblLocation = new JLabel("Location");
		lblTime = new JLabel("Time");
		
		txtType = new JTextField(20);
		txtSubject = new JTextField(20);
		txtDate = new JDateChooser();
		txtLocation = new JTextField(20);
		txtTime = new JTextField(20);
	}
	
	/**
	 * Create button and link to action listener
	 */
	private void createButton() {
		btnSave = new JButton("Save");
		
		btnSave.addActionListener(new MeetingAction());
	}
	
	/**
	 * Create combo box for time
	 */
	private void createComboBox() {
		modelHour = new DefaultComboBoxModel<String>(hour);
		modelMin = new DefaultComboBoxModel<String>(min);
		modelDur = new DefaultComboBoxModel<String>(dur);
		
		cmbHour = new JComboBox<String>(modelHour);
		cmbMin = new JComboBox<String>(modelMin);
		cmbDur = new JComboBox<String>(modelDur);
	}
	
	/**
	 * Setup panel for radio button
	 */
	private void setupTypeRadioPanel() {
		pnlType = new JPanel();
		pnlType.setLayout(new BoxLayout(pnlType, BoxLayout.X_AXIS));
		
		pnlType.add(Box.createHorizontalGlue());
		pnlType.add(radPersonal);
		pnlType.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlType.add(radGroup);
		pnlType.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Setup label, text field and combo box panel
	 */
	private void setupLabelTextFieldComboBoxPanel() {
		pnlMain = new JPanel(new GridBagLayout());
		pnlMain.setBorder(BorderFactory.createTitledBorder(""));
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);
		
		gc.gridx = 0;
		gc.gridy = 0; pnlMain.add(lblType, gc);
		gc.gridy = 1; pnlMain.add(lblSubject, gc);
		gc.gridy = 2; pnlMain.add(lblDate, gc);
		gc.gridy = 3; pnlMain.add(lblLocation, gc);
		gc.gridy = 4; pnlMain.add(lblTime, gc);
		
		gc.gridx = 1;
		gc.gridy = 0; pnlMain.add(txtType, gc);
		gc.gridy = 1; pnlMain.add(txtSubject, gc);
		gc.gridy = 2; pnlMain.add(txtDate, gc);
		gc.gridy = 3; pnlMain.add(txtLocation, gc);
		gc.gridy = 4; pnlMain.add(txtTime, gc);
		
		pnlCmb = new JPanel(new GridLayout(1, 3, 5, 5));
		pnlCmb.add(cmbHour);
		pnlCmb.add(cmbMin);
		pnlCmb.add(cmbDur);
		
		gc.gridx = 1;
		gc.gridy = 4; pnlMain.add(pnlCmb, gc);
	}
	
	/**
	 * Setup button panel
	 */
	private void setupButtonPanel() {
		pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnSave);
		pnlButton.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Save event that user input
	 * @param dir Location of data event saved
	 */
	private void saveEvent(String dir) {
		try {
			if (radPersonal.isSelected()) {
				type = "PERSONAL";
			}
			else if (radGroup.isSelected()) {
				type = "GROUP";
			}
			String subject = txtSubject.getText();
			String location = txtLocation.getText();
			String hh = hour[cmbHour.getSelectedIndex()];
			String mm = min[cmbMin.getSelectedIndex()];
			String dd = dur[cmbDur.getSelectedIndex()];
			String time = hh+ ":" +mm+ " " +dd;
			FileOutputStream outFile = new FileOutputStream(dir);
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.writeObject(new MeetingData(type, subject, location, time));
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
	 * Display output for succuss operation
	 */
	private void displaySaveSuccess() {
		String title = "Event successfully save...";
		String msg =
				"Meeting: " +txtSubject.getText()+ 
				"\nhas been save....";
		JOptionPane.showMessageDialog(
				null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Class for action for button
	 * @author ANAS
	 *
	 */
	private class MeetingAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String dir = 
					user.getUserDir()+ "/MEETING/" +sdf.format(txtDate.getDate());
			File file = new File(dir);
			if (!file.exists())
				file.mkdirs();
			dir += "/" +txtSubject.getText()+ ".ser";
			saveEvent(dir);
		}
	}
}
