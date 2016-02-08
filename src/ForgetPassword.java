import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Class for interface to reset the password of user
 * @author ANAS
 *
 */
public class ForgetPassword extends JFrame {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUserID, lblName, lblEmail, lblContactNo, lblPassword, lblConfirm;
	private JTextField txtUserID, txtName, txtEmail, txtContactNo;
	private JPasswordField txtPassword, txtConfirm;
	private JButton btnCheck, btnVerify, btnReset;
	private GridBagConstraints gc = new GridBagConstraints();
	private JPanel pnlMain;
	private UserDatabase database = new UserDatabase();
	private User user;
	
	/**
	 * Creating the interface
	 */
	public ForgetPassword() {
		init();
	}
	
	/**
	 * Initiate building the interface
	 */
	private void init() {
		createLabel();
		createTextField();
		createButton();
		setupLabelTextField();
		
		setTitle("Forget Password");
		setLayout(new BorderLayout());

		add(pnlMain, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Create label components for interface
	 */
	private void createLabel() {
		lblUserID = new JLabel("User ID");
		lblName = new JLabel("Name");
		lblEmail = new JLabel("Email");
		lblContactNo = new JLabel("Contact No");
		lblPassword = new JLabel("Change Password");
		lblConfirm = new JLabel("Confirm Password");
	}
	
	/**
	 * Create text field components for interface
	 */
	private void createTextField() {
		txtUserID = new JTextField(20);
		txtName = new JTextField(20);
		txtEmail = new JTextField(20);
		txtContactNo = new JTextField(20);

		txtPassword = new JPasswordField(20);
		txtConfirm = new JPasswordField(20);

		txtName.setEditable(false);
		txtEmail.setEditable(false);
		txtContactNo.setEditable(false);
		txtPassword.setEditable(false);
		txtConfirm.setEditable(false);
	}
	
	/**
	 * Create buttons for interface and 
	 * link them to action listener and
	 * set property
	 */
	private void createButton() {
		btnCheck = new JButton("Check");
		btnVerify = new JButton("Verify");
		btnReset = new JButton("Reset");

		btnCheck.addActionListener(new ForgetPasswordAction());
		btnVerify.addActionListener(new ForgetPasswordAction());
		btnReset.addActionListener(new ForgetPasswordAction());

		btnVerify.setEnabled(false);
		btnReset.setEnabled(false);
	}
	
	/**
	 * Setup label and text field components for interface
	 */
	private void setupLabelTextField() {
		pnlMain = new JPanel(new GridBagLayout());
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 10, 10, 10);

		gc.gridx = 0;
		gc.gridy = 0; pnlMain.add(lblUserID, gc);
		gc.gridy = 1; pnlMain.add(lblName, gc);
		gc.gridy = 2; pnlMain.add(lblEmail, gc);
		gc.gridy = 3; pnlMain.add(lblContactNo, gc);
		gc.gridy = 4; pnlMain.add(lblPassword, gc);
		gc.gridy = 5; pnlMain.add(lblConfirm, gc);

		gc.gridx = 1;
		gc.gridy = 0; pnlMain.add(txtUserID, gc);
		gc.gridy = 1; pnlMain.add(txtName, gc);
		gc.gridy = 2; pnlMain.add(txtEmail, gc);
		gc.gridy = 3; pnlMain.add(txtContactNo, gc);
		gc.gridy = 4; pnlMain.add(txtPassword, gc);
		gc.gridy = 5; pnlMain.add(txtConfirm, gc);

		gc.gridx = 2;
		gc.gridy = 0; pnlMain.add(btnCheck, gc);
		gc.gridy = 3; pnlMain.add(btnVerify, gc);
		gc.gridy = 5; pnlMain.add(btnReset, gc);
	}
	
	/**
	 * Class for button for interaction of user
	 * @author ANAS
	 *
	 */
	private class ForgetPasswordAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCheck) {
				String userID = txtUserID.getText();
				if (database.checkUser(userID)) {
					user = database.getUserData(userID);
					txtUserID.setEditable(false);
					btnCheck.setEnabled(false);

					txtName.setText(user.getName());;
					txtEmail.setEditable(true);
					txtContactNo.setEditable(true);
					btnVerify.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(
						null, "User ID not found!", "NOT FOUND!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (e.getSource() == btnVerify) {
				String email = txtEmail.getText();
				String contactNo = txtContactNo.getText();
				if (user.getEmail().equals(email) && user.getContactNo().equals(contactNo)) {
					txtEmail.setEditable(false);
					txtContactNo.setEditable(false);
					btnVerify.setEnabled(false);

					txtPassword.setEditable(true);
					txtConfirm.setEditable(true);
					btnReset.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(
						null, "Verification failed!\nPlease re-enter.", "Verify Failure!", JOptionPane.ERROR_MESSAGE);
					txtEmail.setText("");
					txtContactNo.setText("");
					txtEmail.requestFocus();
				}
			}
			else if (e.getSource() == btnReset) {
				String pass1 = new String(txtPassword.getPassword());
				String pass2 = new String(txtConfirm.getPassword());
				if (pass1.equals(pass2)) {
					user.setPass(pass1);
					database.replaceUser(user.getUserID(), user);
					database.register();

					JOptionPane.showMessageDialog(
						null, "Password has been changed successfully.\nPlease re-login.", "Change Successfully", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(
						null, "Please re-enter the password.", "Password Confirmation Error", JOptionPane.ERROR_MESSAGE);

					txtPassword.setText("");
					txtConfirm.setText("");
					txtPassword.requestFocus();
				}
			}
		}
	}
}
