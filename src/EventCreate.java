import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Class for creating event interface
 * @author ANAS
 *
 */
public class EventCreate extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JRadioButton radMeeting, radToDo;
	private ButtonGroup btnGroup;
	private JButton btnReset;
	private JPanel pnlOption, pnlButton, pnlMain;
	private CardLayout cards;
	
	/**
	 * Create interface while store user data
	 * @param user User login data
	 */
	public EventCreate(User user) {
		this.user = user;
		init();
	}
	
	/**
	 * Initiate building the interface
	 */
	private void init() {
		setupEventOption();
		createButton();
		setupMainPanel();
		setupPanel();
	}
	
	/**
	 * Setup radio option to change interface for different event
	 */
	private void setupEventOption() {
		radMeeting = new JRadioButton("Meeting", true);
		radToDo = new JRadioButton("To Do List");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(radMeeting);
		btnGroup.add(radToDo);
		
		radMeeting.addActionListener(new EventChoose());
		radToDo.addActionListener(new EventChoose());
		
		pnlOption = new JPanel();
		pnlOption.add(radMeeting);
		pnlOption.add(radToDo);
		pnlOption.setBorder(BorderFactory.createTitledBorder("Choose type of event"));
	}
	
	/**
	 * Create button and setup button panel
	 */
	private void createButton() {
		btnReset = new JButton("Reset");
		
		btnReset.addActionListener(new ButtonAction());
		
		pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnReset);
		pnlButton.add(Box.createRigidArea(new Dimension(10, 0)));
	}
	
	/**
	 * Setup main panel of interface
	 */
	private void setupMainPanel() {
		pnlMain = new JPanel(new CardLayout());
		pnlMain.add(new ToDoCreate(user), "TODO");
		pnlMain.add(new MeetingCreate(user), "MEETING");
		
		cards = (CardLayout)pnlMain.getLayout();
		cards.show(pnlMain, "MEETING");
	}
	
	/**
	 * Setup parent panel of interface
	 */
	private void setupPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Create Event"));
		
		add(pnlOption, BorderLayout.NORTH);
		add(pnlButton, BorderLayout.SOUTH);
		add(pnlMain, BorderLayout.CENTER);
	}
	
	/**
	 * Get user working directory
	 * @return User working directory
	 */
	public String getUserDir() {
		return user.getUserDir();
	}
	
	/**
	 * Class for implement action for component in the interface
	 * For radio components
	 * @author ANAS
	 *
	 */
	private class EventChoose implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == radToDo) {
				cards.show(pnlMain, "TODO");
			}
			else if (e.getSource() == radMeeting) {
				cards.show(pnlMain, "MEETING");
			}
		}
	}
	
	/**
	 * Class for implement action for component in the interface
	 * For button components
	 * @author ANAS
	 *
	 */
	private class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(pnlMain);
			setupMainPanel();
			add(pnlMain, BorderLayout.CENTER);
			radMeeting.setSelected(true);
			validate();
			repaint();
		}
	}
}


