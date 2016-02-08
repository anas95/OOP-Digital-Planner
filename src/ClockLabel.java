import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Class for label for clock interface
 * @author ANAS
 *
 */
public class ClockLabel extends JLabel implements ActionListener {
	
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private final Color fontColor = new Color(19, 205, 233);
	private final String fontType = "sans-serif";

	private SimpleDateFormat sdf;

	/**
	 * Create label based on parameter provided
	 * @param type Create specified label for clock
	 */
	public ClockLabel(String type) {
		setForeground(fontColor);

		switch (type) {
			case "date" :
				sdf = new SimpleDateFormat("    MMMM, dd yyyy");
				setFont(new Font(fontType, Font.PLAIN, 20));
				setHorizontalAlignment(SwingConstants.LEFT);
				break;
			case "time" :
				sdf = new SimpleDateFormat("  hh:mm:ss a  ");
				setFont(new Font(fontType, Font.PLAIN, 40));
				setHorizontalAlignment(SwingConstants.CENTER);
				break;
			case "day" :
				sdf = new SimpleDateFormat("EEEE    ");
				setFont(new Font(fontType, Font.PLAIN, 24));
				setHorizontalAlignment(SwingConstants.RIGHT);
				break;
			default :
				sdf = new SimpleDateFormat();
				break;
		}

		Timer t = new Timer(1000, this);
		t.start();
	}

	/**
	 * Action performed after fixed interval
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Date d = new Date();
		setText(sdf.format(d));
	}
}