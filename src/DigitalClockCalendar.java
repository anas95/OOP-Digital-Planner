import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

/**
 * Class for clock and calendar interface
 * @author ANAS
 *
 */
public class DigitalClockCalendar extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create clock and calendar interface
	 */
	public DigitalClockCalendar() {

		ClockLabel date = new ClockLabel("date");
		ClockLabel time = new ClockLabel("time");
		ClockLabel day = new ClockLabel("day");
		
		JCalendar cal = new JCalendar();
		
		JPanel pnlClock = new JPanel(new GridLayout(3, 1, 10, 10));
		pnlClock.add(date);
		pnlClock.add(time);
		pnlClock.add(day);
		pnlClock.setBackground(Color.BLACK);
		
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(2, 1, 10, 10));
		add(pnlClock);
		add(cal);
		
		setPreferredSize(new Dimension(320, 480));
	}
}
