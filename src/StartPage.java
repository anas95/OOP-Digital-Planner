import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JCalendar;

/**
 * Main inteface when running the program
 * @author ANAS
 *
 */
public class StartPage extends JFrame {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnBack, btnLogin, btnRegister;
	private JPanel pnlButton, pnlRadio, pnlMain;
	private JRadioButton radCalendar, radClock;
	private ButtonGroup btnGroup;
	private CardLayout cards;

	/**
	 * Building the interface
	 */
	public StartPage() {
		createMenuButton();
		createMainMenu();
		createMainPage();
		createPanelCard();

		setTitle("Welcome");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);

		showMainClock();
			
		add(pnlMain, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);
		add(pnlRadio, BorderLayout.NORTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Create menu button
	 */
	private void createMenuButton() {
		Dimension size = new Dimension(120, 25);
		btnLogin = new JButton("Login");
		btnLogin.setPreferredSize(size); btnLogin.setMinimumSize(size); btnLogin.setMaximumSize(size);
		btnLogin.addActionListener(new ButtonMenu());
		btnRegister = new JButton("Register");
		btnRegister.setPreferredSize(size); btnRegister.setMinimumSize(size); btnRegister.setMaximumSize(size);
		btnRegister.addActionListener(new ButtonMenu());
		btnBack = new JButton(new ImageIcon("./img/back_menu.png"));
		btnBack.setPreferredSize(size);	btnBack.setMinimumSize(size); btnBack.setMaximumSize(size);
		btnBack.addActionListener(new ButtonMenu());
	}

	/**
	 * Create main menu
	 */
	private void createMainMenu() {
		pnlButton = new JPanel();
		Dimension space = new Dimension(5, 0);
		pnlButton.setBorder(BorderFactory.createTitledBorder("Menu"));
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnLogin);
		pnlButton.add(Box.createRigidArea(space));
		pnlButton.add(btnRegister);
		pnlButton.add(Box.createRigidArea(space));
		pnlButton.add(btnBack);
		pnlButton.add(Box.createHorizontalGlue());
	}

	/**
	 * Create main page
	 */
	private void createMainPage() {
		pnlRadio = new JPanel();
		radClock = new JRadioButton("Digital Clock", true);
		radCalendar = new JRadioButton("Calendar");
		btnGroup = new ButtonGroup();
		btnGroup.add(radClock);
		btnGroup.add(radCalendar);
		pnlRadio.add(radClock);
		pnlRadio.add(radCalendar);
		radClock.addActionListener(new RadioMenu());
		radCalendar.addActionListener(new RadioMenu());
	}

	/**
	 * Create panel card
	 */
	private void createPanelCard() {
		pnlMain = new JPanel(new CardLayout());
		pnlMain.add(new JCalendar(), "CALENDAR");
		pnlMain.add(new DigitalClock(), "CLOCK");
		pnlMain.add(new LoginGUI(), "LOGIN");
		pnlMain.add(new RegisterGUI(), "REGISTER");
		cards = (CardLayout)pnlMain.getLayout();
		cards.show(pnlMain, "CLOCK");
	}
	
	/**
	 * Display clock interface
	 */
	private void showMainClock() {
		if (radClock.isSelected() == true) 
			cards.show(pnlMain, "CLOCK");
		else
			cards.show(pnlMain, "CALENDAR");
		btnBack.setVisible(false);
		btnLogin.setVisible(true);
		btnRegister.setVisible(true);
		pnlRadio.setVisible(true);
	}

	/**
	 * Class for button listener
	 */
	private class ButtonMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogin) {
				cards.show(pnlMain, "LOGIN");
				btnBack.setVisible(true);
				btnLogin.setVisible(false);
				btnRegister.setVisible(false);
				pnlRadio.setVisible(false);
			}
			else if (e.getSource() == btnRegister){
				cards.show(pnlMain, "REGISTER");
				btnBack.setVisible(true);
				btnLogin.setVisible(false);
				btnRegister.setVisible(false);
				pnlRadio.setVisible(false);
			}
			else if (e.getSource() == btnBack) 
				showMainClock();
		}
	}

	/**
	 * Class for radio listener
	 */
	private class RadioMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == radClock)
				cards.show(pnlMain, "CLOCK");
			else if (e.getSource() == radCalendar) 
				cards.show(pnlMain, "CALENDAR");
		}
	}
}