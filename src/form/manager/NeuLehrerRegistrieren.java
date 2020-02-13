package form.manager;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import dto.LehrerDTO;
import tasken.Fach;

/**
 * einer Panel von mittePanel einer ManagerFenster. Mit dieser Panel kann
 * Manager neue Lehrer in Database addieren.
 * 
 * @author Erdem
 *
 */
public class NeuLehrerRegistrieren extends JPanel {

	private JLabel[] lbls;
	private JTextField[] texts;
	private JComboBox<Fach>[] combos;
	private JButton speichern;
	private JLabel labelLehrer;

	public NeuLehrerRegistrieren() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(8, 4, 5, 10));
		bauen();
	}

	/**
	 * um die NeuLehrerRegistrierenForm zu bauen.
	 */
	private void bauen() {
		labelLehrer = new JLabel("LEHRER");
		labelLehrer.setFont(new Font("Arial", Font.BOLD, 20));
		add(labelLehrer);
		for (int i = 0; i < 3; i++) {
			add(new JLabel());
		}
		lbls = new JLabel[7];
		texts = new JTextField[4];
		combos = new JComboBox[3];
		lbls[0] = new JLabel("Vorname:");
		lbls[1] = new JLabel("Fach 1:");
		lbls[2] = new JLabel("Nachname:");
		lbls[3] = new JLabel("Fach 2:");
		lbls[4] = new JLabel("e-Mail:");
		lbls[5] = new JLabel("Fach 3:");
		lbls[6] = new JLabel("Handynummer:");
		Fach[] faecher = Fach.values();
		for (int i = 0; i < combos.length; i++) {

			combos[i] = new JComboBox<Fach>(faecher);
			combos[i].setSelectedItem(null);

		}
		speichern = new JButton("Speichern");

		for (int i = 0; i < lbls.length; i++) {

			lbls[i].setBorder(new EmptyBorder(0, 20, 0, 0));
			add(lbls[i]);
		}
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField(10);
			add(texts[i], (3 * i + 5));
		}
		for (int i = 0; i < combos.length; i++) {
			add(combos[i], (4 * i + 7));
		}

		for (int i = 0; i < 13; i++) {
			add(new JLabel());
		}

		add(speichern);
		speichern.addActionListener(event -> {
			if (isAllenotwendigeFelderRictigAusgefuellt()) {
				LehrerDTO tempLehrer = new LehrerDTO(texts[0].getText(), texts[1].getText(), texts[3].getText(),
						texts[2].getText(), combos[0].getSelectedIndex() + 1, combos[1].getSelectedIndex() + 1,
						combos[2].getSelectedIndex() + 1);
				for (JTextField text : texts) {
					text.setText("");
				}
				for (JComboBox combo : combos) {
					combo.setSelectedItem(null);
				}
				new SchuleDAOImplDB().addLehrer(tempLehrer);
				System.out.println();
			}
		});

	}

	/**
	 * konrollieren ob alle Felder mit geeignete Angaben ausgefuellt werden.
	 * 
	 * @return boolean value
	 */
	public boolean isAllenotwendigeFelderRictigAusgefuellt() {
		for (int i = 0; i < texts.length; i++) {
			if (texts[i].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bitte fuellen Sie alle Felder entsprechend aus");
				return false;
			}

		}
		if (combos[0].getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Bitte mindestens eine Fach ausfuellen, besonders erste Fach");
			return false;
		}
		return true;
	}
}
