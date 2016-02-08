import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class for register interface
 * @author ANAS
 *
 */
public class RegisterGUI extends JPanel {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUserID, txtName, txtEmail, txtContactNo;
	private JPasswordField txtPass1, txtPass2;
	private JLabel lblUserID, lblName, lblEmail, lblContactNo, lblPass1, lblPass2;
	private JButton btnRegister;
	private JPanel pnlDetails, pnlButton;
	private ArrayList<JTextField> arrField = new ArrayList<JTextField>();
	private UserDatabase database;

	/**
	 * Creating the register interface
	 */
	public RegisterGUI() {
		setBorder(BorderFactory.createTitledBorder("Details Form"));
		setLayout(new BorderLayout());
		init();
	}
	
	/**
	 * Initiate building the interface
	 */
	private void init() {
		createTextPassField();
		createLabel();
		createButton();
		setButtonAction();
		createDetailsForm();
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				getRootPane().setDefaultButton(btnRegister);
			}
		});
		
		add(pnlButton, BorderLayout.SOUTH);
		add(pnlDetails, BorderLayout.CENTER);
	}
	
	/**
	 * Create text and password field
	 */
	private void createTextPassField() {
		int size = 20;
		txtUserID = new JTextField(size);
		txtName = new JTextField(size);
		txtEmail = new JTextField(size);
		txtContactNo = new JTextField(size);
		
		txtPass1 = new JPasswordField(size);
		txtPass2 = new JPasswordField(size);

		arrField.add(txtUserID);
		arrField.add(txtName);
		arrField.add(txtEmail);
		arrField.add(txtContactNo);
	}
	
	/**
	 * Create label for components
	 */
	private void createLabel() {
		lblUserID = new JLabel("User ID");
		lblName = new JLabel("Name");
		lblEmail = new JLabel("Email");
		lblContactNo = new JLabel("Contact No");
		lblPass1 = new JLabel("Password");
		lblPass2 = new JLabel("Confirm password");
	}
	
	/**
	 * Create button and setup the panel
	 */
	private void createButton() {
		btnRegister = new JButton("Register");
		pnlButton = new JPanel();
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
		pnlButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pnlButton.add(Box.createHorizontalGlue());
		pnlButton.add(btnRegister);
		pnlButton.add(Box.createHorizontalGlue());
	}
	
	/**
	 * Setup panel for form
	 */
	private void createDetailsForm() {
		pnlDetails = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);
		
		// label
		gc.gridx = 0;
		
		gc.gridy = 0; pnlDetails.add(lblUserID, gc);
		gc.gridy = 1; pnlDetails.add(lblPass1, gc);
		gc.gridy = 2; pnlDetails.add(lblPass2, gc);
		gc.gridy = 3; pnlDetails.add(lblName, gc);
		gc.gridy = 4; pnlDetails.add(lblEmail, gc);
		gc.gridy = 5; pnlDetails.add(lblContactNo, gc);
		
		// field
		gc.gridx = 1;
		gc.gridwidth = 2;
		
		gc.gridy = 0; pnlDetails.add(txtUserID, gc);
		gc.gridy = 1; pnlDetails.add(txtPass1, gc);
		gc.gridy = 2; pnlDetails.add(txtPass2, gc);
		gc.gridy = 3; pnlDetails.add(txtName, gc);
		gc.gridy = 4; pnlDetails.add(txtEmail, gc);
		gc.gridy = 5; pnlDetails.add(txtContactNo, gc);
	}
	
	/**
	 * Link button to listener
	 */
	private void setButtonAction() {
		btnRegister.setFocusable(true);
		btnRegister.addActionListener(new RegisterExe());
		btnRegister.addKeyListener(new RegisterKey());
	}

	/**
	 * Class for button click action
	 */
	private class RegisterExe implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			performRegister();
		}
	}

	/**
	 * Class for keyboard press action
	 */
	private class RegisterKey implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER)
				performRegister();
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {}
	}

	/**
	 * For validate the input and register user to database
	 */
	private void performRegister() {
		char[] pass1 = txtPass1.getPassword();
		char[] pass2 = txtPass2.getPassword();
		if (!Arrays.equals(pass1, pass2)) {
			JOptionPane.showMessageDialog(
				null, "Please re-enter the password", "Wrong password!", JOptionPane.ERROR_MESSAGE);
			txtPass2.setText("");
			txtPass1.requestFocus();
			txtPass1.selectAll();
		}
		else {
			boolean store = true;
			for (JTextField field : arrField) {
				if (field.getText().isEmpty()) {
					store = false;
					break;
				}
			}
			if (!store) {
				JOptionPane.showMessageDialog(
					null, "Please enter all required details", "Empty details!", JOptionPane.WARNING_MESSAGE);
			}
			else {
				String userID = txtUserID.getText();
				String pass = new String(txtPass1.getPassword());
				String name = txtName.getText();
				String email = txtEmail.getText();
				String contactNo = txtContactNo.getText();
				database = new UserDatabase();
				database.register(userID, pass, name, email, contactNo);
				File file = new File("./dat/" +userID);
				file.mkdir();
				clearTextField();
				JOptionPane.showMessageDialog(
					null, "User registered successfully.\nPlease go back to main menu to login", "Register success!", 
					JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Clear all text field in form
	 */
	private void clearTextField() {
		for (JTextField field : arrField)
			field.setText("");
		txtPass1.setText("");
		txtPass2.setText("");
	}
}