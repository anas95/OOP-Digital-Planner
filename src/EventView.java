import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

/**
 * Class for viewing event interface
 * @author ANAS
 *
 */
public class EventView extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JLabel lblDate;
	private JDateChooser txtDate;
	private JButton btnSearch, btnView;
	private JRadioButton radMeeting, radToDo;
	private ButtonGroup btnGroup;
	private JPanel pnlOption, pnlMain = new JPanel(new GridLayout(1, 1, 5, 5));
	private File file;
	private File[] fileList;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JScrollPane scroll;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Create the interface (without user data)
	 */
	public EventView() {
		init();
	}
	
	/**
	 * Create the interface while store user data
	 * @param user User login data
	 */
	public EventView(User user) {
		this.user = user;
		init();
	}
	
	/**
	 * Initiate the building of interface
	 */
	private void init() {
		createDateLabelSearch();
		createEventOption();
		createButton();
		createList();
		setupOptionPanel();
		
		scroll.setMinimumSize(new Dimension(240, 240));
		scroll.setPreferredSize(new Dimension(240, 240));
		scroll.setMaximumSize(new Dimension(240, 240));
		
		setBorder(BorderFactory.createTitledBorder("View Event"));
		setLayout(new BorderLayout());
		add(pnlOption, BorderLayout.NORTH);
		add(scroll, BorderLayout.WEST);
	}
	
	/**
	 * Create components for date 
	 */
	private void createDateLabelSearch() {
		lblDate = new JLabel("Date");
		txtDate = new JDateChooser();
	}
	
	/**
	 * Create radio option and link to listener
	 */
	private void createEventOption() {
		radMeeting = new JRadioButton("Meeting", true);
		radToDo = new JRadioButton("To Do List");
		
		radMeeting.addActionListener(new RadioAction());
		radToDo.addActionListener(new RadioAction());
		
		btnGroup = new ButtonGroup();
		btnGroup.add(radMeeting);
		btnGroup.add(radToDo);
	}
	
	/**
	 * Create button and setup the action listener
	 */
	private void createButton() {
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setupListDir();
			}
		});
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEvent();
			}
		});
	}
	
	/**
	 * Create list for subject inteface and
	 * setup the action related
	 */
	private void createList() {
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		scroll = new JScrollPane(list);
		
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getEvent();
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		list.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode())
					getEvent();
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	/**
	 * Setup option panel of interface
	 */
	private void setupOptionPanel() {
		pnlOption = new JPanel();
		pnlOption.setLayout(new BoxLayout(pnlOption, BoxLayout.X_AXIS));
		pnlOption.setBorder(BorderFactory.createTitledBorder(""));
		
		Dimension space = new Dimension(5, 0);
		pnlOption.add(Box.createHorizontalGlue());
		pnlOption.add(radMeeting);
		pnlOption.add(Box.createRigidArea(space));
		pnlOption.add(radToDo);
		pnlOption.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlOption.add(lblDate);
		pnlOption.add(Box.createRigidArea(space));
		pnlOption.add(txtDate);
		pnlOption.add(Box.createRigidArea(space));
		pnlOption.add(btnSearch);
		pnlOption.add(Box.createRigidArea(space));
		pnlOption.add(btnView);
		pnlOption.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Generate list of subject of event created
	 * based on type of event and date
	 */
	private void setupListDir() {
		String type = new String();
		if (radMeeting.isSelected())
			type = "MEETING";
		else if (radToDo.isSelected())
			type = "TODO";
		String dir = 
				"./dat/" +user.getUserID()+ "/" +type+ "/" +sdf.format(txtDate.getDate())+ "/";
		file = new File(dir);
		if (file.exists()) {
			fileList = file.listFiles();
			listModel.clear();
			for (File f : fileList) {
				int p = f.getName().lastIndexOf(".");
				listModel.addElement(f.getName().substring(0, p));
			}
		}
		else {
			JOptionPane.showMessageDialog(
					null, "File not found!", "NOT FOUND!!", JOptionPane.ERROR_MESSAGE);
					
		}
	}
	
	/**
	 * Get the event object store in file
	 * @param in Object of event store in file
	 */
	private void viewEvent(Object in) {
		remove(pnlMain);
		pnlMain.removeAll();
		if (radMeeting.isSelected()){
			pnlMain.add(new MeetingCreate(user, (MeetingData)in, txtDate.getDate()), BorderLayout.CENTER);
		}
		else if (radToDo.isSelected())
			pnlMain.add(new ToDoCreate(user, (ToDoData)in, txtDate.getDate()), BorderLayout.CENTER);
		add(pnlMain, BorderLayout.CENTER);
	}
	
	/**
	 * Get event to display in main panel
	 */
	private void getEvent() {
		int index = list.getSelectedIndex();
		try {
			String dir = fileList[index].getPath();
			FileInputStream inFile = new FileInputStream(dir);
			ObjectInputStream in = new ObjectInputStream(inFile);
			viewEvent(in.readObject());
			in.close();
			inFile.close();
		} catch (ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(
					null, "Please select subject before view...", "ERROR!!!", JOptionPane.WARNING_MESSAGE);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
	}
	
	/**
	 * Action performed when radio button property change
	 * @author ANAS
	 *
	 */
	private class RadioAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			listModel.clear();
		}
	}
}
