import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Class for interface that display information on development of this program
 * @author ANAS
 *
 */
public class AboutUs extends JPanel implements ActionListener {
	
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JTextArea txtAbout;
	private Random randNo = new Random();
	private int r, g, b;

	/**
	 * Create the interface
	 */
	public AboutUs() {
		init();
	}
	
	/**
	 * Building the interface
	 */
	private void init() {
		createAbout();
		setupPanel();
		
		setBorder(BorderFactory.createTitledBorder("About Us"));
		Timer t = new Timer(2000, this);
		t.start();
	}
	
	/**
	 * Create the text components for the information
	 */
	private void createAbout() {
		lblTitle = new JLabel("WHAT'S THE PLAN!!! - V 1.0", SwingConstants.CENTER);
		lblTitle.setFont(new Font("jokerman", Font.BOLD, 35));
		
		txtAbout = new JTextArea();
		txtAbout.setText(
				"Purpose of this project is for our OOP project CSC 1103. "
				+ "The project is based on digital planner which the user can:"
				+ "\n  - View reminder for today event"
				+ "\n  - Create event"
				+ "\n  - View created event"
				+ "\n  - View current time and calendar"
				+ "\n\nOur role for this project:"
				+ "\n  # Program manager : ANAS JUWAIDI"
				+ "\n  # Programmer 1 : MAHIR"
				+ "\n  # Programmer 2 : NURRUDIN"
				+ "\n  # Programmer 3 : NIK FIKRI"
				+ "\n\nAlso credited to:"
				+ "\n  * Madam Norsaremeh : Our lecturer"
				+ "\n  * Kai Toedter(www.toedter.com) : for provide JCalendar, JDateChooser class"
				+ "\n also for every individual who gave us the idea to this program."
				+ "\nThank you very much....");
		txtAbout.setFont(new Font("calibri", Font.PLAIN, 16));
		txtAbout.setEditable(false);
	}
	
	/**
	 * Setup panel properties
	 */
	private void setupPanel() {
		setLayout(new BorderLayout());
		add(lblTitle, BorderLayout.NORTH);
		add(txtAbout, BorderLayout.CENTER);
	}

	/**
	 * Generate random color 
	 * @return New color generate
	 */
	private Color getRandomColor() {
		r = randNo.nextInt(255);
		g = randNo.nextInt(255);
		b = randNo.nextInt(255);
		
		return new Color(r, g, b);
	}

	/**
	 * Action performed after fixed interval
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		lblTitle.setForeground(getRandomColor());
	}
}
