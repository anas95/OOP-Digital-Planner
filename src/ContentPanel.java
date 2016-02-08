import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class for main interface of program
 * @author ANAS
 *
 */
public class ContentPanel extends JPanel implements ActionListener {
	
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JButton btnCreate, btnView, btnHome, btnAbout;
	private JPanel pnlButton, pnlMain;
	private CardLayout cards;

	/**
	 * Create interface while store user login details
	 * @param user User login data
	 */
	public ContentPanel(User user) {
		this.user = user;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		createButton();
		createMain();
		addComponent();
	}

	/**
	 * Create and adjust the buttons
	 */
	private void createButton() {
		btnHome = new JButton("Home", new ImageIcon("./img/home.png"));
		btnCreate = new JButton("Create", new ImageIcon("./img/create_event.png"));
		btnView = new JButton("View", new ImageIcon("./img/view_event.png"));
		btnAbout = new JButton("About Us", new ImageIcon("./img/about_us.png"));

		btnHome.setVerticalTextPosition(AbstractButton.BOTTOM); btnHome.setHorizontalTextPosition(AbstractButton.CENTER);
		btnCreate.setVerticalTextPosition(AbstractButton.BOTTOM); btnCreate.setHorizontalTextPosition(AbstractButton.CENTER);
		btnView.setVerticalTextPosition(AbstractButton.BOTTOM); btnView.setHorizontalTextPosition(AbstractButton.CENTER);
		btnAbout.setVerticalTextPosition(AbstractButton.BOTTOM); btnAbout.setHorizontalTextPosition(AbstractButton.CENTER);

		btnHome.setEnabled(false);

		btnHome.addActionListener(this);
		btnCreate.addActionListener(this);
		btnView.addActionListener(this);
		btnAbout.addActionListener(this);

		pnlButton = new JPanel(new GridLayout(1, 4, 5, 5));
		pnlButton.setBorder(BorderFactory.createTitledBorder("Menu"));
		pnlButton.add(btnHome);
		pnlButton.add(btnCreate);
		pnlButton.add(btnView);
		pnlButton.add(btnAbout);
	}

	/**
	 * Create main panel
	 */
	private void createMain() {
		pnlMain = new JPanel(new CardLayout());
		pnlMain.add(new Reminder(user), "HOME");
		pnlMain.add(new AboutUs(), "ABOUT");
		pnlMain.add(new EventCreate(user), "CREATE");
		pnlMain.add(new EventView(user), "VIEW");

		cards = (CardLayout)pnlMain.getLayout();
		cards.show(pnlMain, "HOME");
	}

	/**
	 * Add components to main frame
	 */
	private void addComponent() {
		add(new QuoteLabel(), BorderLayout.NORTH);
		add(pnlButton, BorderLayout.SOUTH);
		add(pnlMain, BorderLayout.CENTER);
	}

	/**
	 * Action performed when user interact with component
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHome) {
			btnHome.setEnabled(false);
			btnCreate.setEnabled(true);
			btnView.setEnabled(true);
			btnAbout.setEnabled(true);
			cards.show(pnlMain, "HOME");
		}
		else if (e.getSource() == btnCreate) {
			btnHome.setEnabled(true);
			btnCreate.setEnabled(false);
			btnView.setEnabled(true);
			btnAbout.setEnabled(true);
			cards.show(pnlMain, "CREATE");
		}
		else if (e.getSource() == btnView) {
			btnHome.setEnabled(true);
			btnCreate.setEnabled(true);
			btnView.setEnabled(false);
			btnAbout.setEnabled(true);
			cards.show(pnlMain, "VIEW");
		}
		else if (e.getSource() == btnAbout) {
			btnHome.setEnabled(true);
			btnCreate.setEnabled(true);
			btnView.setEnabled(true);
			btnAbout.setEnabled(false);
			cards.show(pnlMain, "ABOUT");
		}
	}
}
