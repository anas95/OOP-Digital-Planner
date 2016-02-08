import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Class for interface for quote, which randomly change after fixed interval
 * @author ANAS
 *
 */
public class QuoteLabel extends JPanel implements ActionListener {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private final String fontType = "calibri";
	private File file;
	private Scanner scan;
	private ArrayList<String> quote = new ArrayList<String>();
	private ArrayList<String> author = new ArrayList<String>();
	private Random rand = new Random();
	private JLabel lblQuote, lblAuthor;
	private int index;

	/**
	 * Building the interface
	 */
	public QuoteLabel() {
		loadQuote();
		createLabel();
		addLabel();

		Timer t = new Timer(120000, this);
		t.start();
	}

	/**
	 * Load list of quotes and authors into array
	 */
	private void loadQuote() {
		try {
			file = new File("./dat/quote.txt");
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				quote.add(scan.nextLine());
				author.add(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate random index number
	 * @return Random number generate
	 */
	private int getRandom() {
		int i;
		do {
			i = rand.nextInt(quote.size());
		} while (index == i);
		index = i;
		return i;
	}

	/**
	 * Create label for display quote and author
	 */
	private void createLabel() {
		int i = getRandom();
		index = i;
		String srtQuote = "<html>\"" +quote.get(i)+ "\"</html>";
		String srtAuthor = "<html>" +author.get(i)+ "\t</html>";
		lblQuote = new JLabel(srtQuote, SwingConstants.LEFT);
		lblQuote.setFont(new Font(fontType, Font.BOLD, 20));
		lblAuthor = new JLabel(srtAuthor, SwingConstants.RIGHT);
		lblAuthor.setFont(new Font(fontType, Font.ITALIC, 18));
	}

	/**
	 * Change label text
	 * @param i Index for selected quote and author
	 */
	private void refreshQuote(int i) {
		String srtQuote = "<html>\"" +quote.get(i)+ "\"</html>";
		String srtAuthor = "<html>" +author.get(i)+ "\t</html>";
		lblQuote.setText(srtQuote);
		lblAuthor.setText(srtAuthor);
	}

	/**
	 * Add label into interface
	 */
	private void addLabel() {
		setLayout(new GridLayout(2, 1, 5, 5));
		setBorder(BorderFactory.createTitledBorder("Quote of the Moments"));
		add(lblQuote);
		add(lblAuthor);
	}

	/**
	 * Execute after fixed interval
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		refreshQuote(getRandom());
	}
}
