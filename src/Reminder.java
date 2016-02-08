import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Class for home interface, display how many events created for today
 * @author ANAS
 *
 */
public class Reminder extends JPanel implements ActionListener {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Date date = new Date();
	private String[] event = {"MEETING", "TODO"};
	private int[] total = {0, 0};
	private File file;
	private File[] files;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JLabel lblDate;
	private JTextArea txtInfo;
	private JButton btnRefresh;

	/**
	 * Create interface reminder
	 * @param user Current user login
	 */
	public Reminder(User user) {
		this.user = user;
		checkFile();
		init();
	}
	
	/**
	 * Check total events for today, by calculate total file in respective directory
	 */
	private void checkFile() {
		String dir = new String();
		for (int i = 0; i < event.length; i++) {
			dir = user.getUserDir()+ "/" +event[i]+ "/" +sdf.format(date);
			file = new File(dir);
			if (file.exists()) {
				files = file.listFiles();
				total[i] = files.length;
			}
		}
	}

	/**
	 * Building the interface
	 */
	private void init() {
		createInfoText();
		createButton();
		setupInfoPanel();
	}

	/**
	 * Create components for displaying
	 */
	private void createInfoText() {
		lblDate = new JLabel("TODAY: " +sdf.format(date), SwingConstants.RIGHT);
		lblDate.setFont(new Font("Gabriola", Font.BOLD, 40));
		
		txtInfo = new JTextArea();
		txtInfo.setText(
				"You have:"
				+ "\n   " +total[0]+ " meeting for today."
				+ "\n   " +total[1]+ " to do list for today.");
		txtInfo.setFont(new Font("Gabriola", Font.PLAIN, 30));
		txtInfo.setEditable(false);
	}
	
	/**
	 * Create button and link to listener
	 */
	private void createButton() {
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
	}
	
	/**
	 * Setup panel for displaying
	 */
	private void setupInfoPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.RED));
		add(lblDate, BorderLayout.NORTH);
		add(txtInfo, BorderLayout.CENTER);
		add(btnRefresh, BorderLayout.SOUTH);
	}
	
	/**
	 * Refresh the total events for today
	 */
	private void refreshReminder() {
		checkFile();
		txtInfo.setText(
				"You have:"
				+ "\n   " +total[0]+ " meeting for today."
				+ "\n   " +total[1]+ " to do list for today.");
	}
	
	/**
	 * Action for button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		refreshReminder();
	}
}
