import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Class for clock interface
 * @author ANAS
 *
 */
public class DigitalClock extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create clock interface
	 */
	public DigitalClock() {

		ClockLabel date = new ClockLabel("date");
		ClockLabel time = new ClockLabel("time");
		ClockLabel day = new ClockLabel("day");
		
		JPanel pnlClock = new JPanel(new GridLayout(3, 1, 10, 10));
		pnlClock.add(date);
		pnlClock.add(time);
		pnlClock.add(day);
		pnlClock.setBackground(Color.BLACK);
		
		setLayout(new GridLayout(1, 0, 5, 5));
		add(pnlClock);
	}
}
