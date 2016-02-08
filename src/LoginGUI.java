import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class for login interface
 * @author ANAS
 *
 */
public class LoginGUI extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JLabel lblUser, lblPass;
	private JButton btnLogin, btnForget;
	private JPanel pnlLogin, pnlButton;
	private UserDatabase database;
	
	/**
	 * Creating the interface
	 */
	public LoginGUI() {
		setBorder(BorderFactory.createTitledBorder("Login Form"));
		setLayout(new BorderLayout());
		init();
	}
	
	/**
	 * Initiate the building of interface
	 */
	private void init() {
		createLabel();
		createField();
		createButton();
		createLoginForm();
		setButtonAction();
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				getRootPane().setDefaultButton(btnLogin);
			}
		});
		
		add(pnlLogin, BorderLayout.CENTER);
		add(pnlButton, BorderLayout.SOUTH);
	}
	
	/**
	 * Create label components for interface
	 */
	private void createLabel() {
		lblUser = new JLabel("User ID");
		lblPass = new JLabel("Password");
	}
	
	/**
	 * Create text field components for interface
	 */
	private void createField() {
		int size = 20;
		txtUser = new JTextField(size);
		txtPass = new JPasswordField(size);
	}
	
	/**
	 * Create buttons for interface and
	 * setup panel for button
	 */
	private void createButton() {
		btnLogin = new JButton("Login");
		btnForget = new JButton("Forget Password");

		pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnLogin);
		pnlButton.add(Box.createRigidArea(new Dimension(7, 0)));
		pnlButton.add(btnForget);
		pnlButton.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Create login form interface 
	 */
	private void createLoginForm() {
		pnlLogin = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);

		// label
		gc.gridx = 0;
		gc.gridy = 0; pnlLogin.add(lblUser, gc);
		gc.gridy = 1; pnlLogin.add(lblPass, gc);

		// field
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.gridy = 0; pnlLogin.add(txtUser, gc);
		gc.gridy = 1; pnlLogin.add(txtPass, gc);
	}

	/**
	 * Setup action listener for button components
	 */
	private void setButtonAction() {
		btnLogin.setFocusable(true);
		btnLogin.addActionListener(new LoginExe());
		btnLogin.addKeyListener(new LoginKey());

		btnForget.addActionListener(new LoginExe());
	}

	/**
	 * Class for action performed when user interact with buttons
	 * @author ANAS
	 *
	 */
	private class LoginExe implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogin)
				performLogin();
			else if (e.getSource() == btnForget) 
				new ForgetPassword();
		}
	}
	
	/**
	 * Class for action performed when key input is received
	 * @author ANAS
	 *
	 */
	private class LoginKey implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER)
				performLogin();
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {}
	}
	
	/**
	 * Validate user login input
	 */
	private void performLogin() {
		database = new UserDatabase();
		String user = txtUser.getText();
		String pass = new String(txtPass.getPassword());
		if (database.validateUser(user, pass)) {
			new PlannerPage(database.getUserData(user));
			JFrame frame = (JFrame)this.getTopLevelAncestor();
			frame.dispose();
		}
			
		else {
			JOptionPane.showMessageDialog(
				null, "Please re-enter user ID, password", "Wrong user ID / password", JOptionPane.ERROR_MESSAGE);
			txtUser.setText("");
			txtPass.setText("");
			txtUser.requestFocus();
		}
	}
}
