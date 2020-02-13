package form.haupt;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import personen.Lehrer;
import personen.Schueler;

/**
 * Login Panel um die username und password zu kontrollieren und wenn richtig,
 * relevante Fenster weiterleiten.
 * 
 * @author Erdem
 *
 */
public class LoginPanel extends JPanel {
	private JLabel lbl_username = new JLabel("Username");
	private JLabel lbl_pass = new JLabel("Password");
	private JTextField username = new JTextField(20);
	private JTextField pass = new JTextField(20);
	private JButton anmelden = new JButton("Login");
	private Hauptfenster fenster;

	public LoginPanel(Hauptfenster fenster) {
		this.fenster = fenster;
		setLayout(new GridLayout(3, 2, 30, 30));
		bauen();
	}

	/**
	 * Bauen die Login Panel. Addieren actionListener fuer Anmelden-Button
	 */
	private void bauen() {
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(lbl_username);
		add(username);
		add(lbl_pass);
		add(pass);
		add(anmelden);
		anmelden.addActionListener(event -> login());

	}

	/**
	 * Kontrollieren die username und password(jetzt password is id) Wenn richtig
	 * schauen die relevante Panel Wenn nicht, throws Exception: falschePassword
	 */
	public void login() {
		if (username.getText().equals("Manager")) {
			fenster.schauenManagerForm();
		} else if (username.getText().equals("Lehrer")) {
			try {
				Lehrer lehr = new SchuleDAOImplDB().getLehrer(Integer.parseInt(pass.getText()));
				fenster.schauenLehrerForm(lehr);
			} catch (Exception falschePassword) {
				JOptionPane.showMessageDialog(this, "falsche password");
				username.setText("");
				pass.setText("");
			}
		} else if (username.getText().equals("Eltern")) {
			try {
				Schueler sch = new SchuleDAOImplDB().getSchueler(Integer.parseInt(pass.getText()));
				fenster.schauenElternForm(sch);
			} catch (Exception falschePassword) {
				JOptionPane.showMessageDialog(this, "falsche password");
				username.setText("");
				pass.setText("");
			}
		}

		else if (username.getText().equals("Schueler")) {
			try {
				Schueler sch = new SchuleDAOImplDB().getSchueler(Integer.parseInt(pass.getText()));
				fenster.schauenSchuelerForm(sch);
			} catch (Exception falschePassword) {
				JOptionPane.showMessageDialog(this, "falsche password");
				username.setText("");
				pass.setText("");
			}
		} else {
			JOptionPane.showMessageDialog(this, "falsche Username");
			username.setText("");
			pass.setText("");
		}
	}
}
