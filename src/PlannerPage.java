import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class for main frame of program
 * @author ANAS
 *
 */
public class PlannerPage extends JFrame {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private DigitalClockCalendar clockCal;
	private User user;
	
	/**
	 * Creating the interface while store user data
	 * @param user User login data
	 */
	public PlannerPage(User user) {
		this.user = user;
		setTitle("What's the Plan!!!");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				int close = JOptionPane.showConfirmDialog(
						null, "Are you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (close == JOptionPane.YES_OPTION) {
					new StartPage();
					dispose();
				}
			}

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
		});
				
		init();
				
		pack();
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		welcomeUser();
	}
	
	/**
	 * Initiate building the interface
	 */
	private void init() {
		createMainInterface();
		addComponent();
	}
	
	/**
	 * Create static interface of program
	 */
	private void createMainInterface() {
		clockCal = new DigitalClockCalendar();
	}
	
	/**
	 * Add components into main frame
	 */
	private void addComponent() {
		add(clockCal, BorderLayout.WEST);
		add(new ContentPanel(user), BorderLayout.CENTER);
	}

	/**
	 * Display output for welcoming user
	 */
	private void welcomeUser() {
		JOptionPane.showMessageDialog(
			null, "Welcome, " +user.getName(), "Hello...", JOptionPane.INFORMATION_MESSAGE);
	}
}
