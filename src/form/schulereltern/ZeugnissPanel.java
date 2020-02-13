package form.schulereltern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.SchuleDAOImplDB;
import tasken.FachUndLehrer;
import tasken.Note;
import tasken.Notentype;

/**
 * Zeugniss-Panel Objekt wird benutzt um alle Noten von Schueler für einen
 * Unterricht zu schauen.
 * 
 * @author Erdem
 *
 */
public class ZeugnissPanel extends JPanel {
	private FachUndLehrer fachUndLehrer;
	private JLabel[] lbls;
	private JPanel oben;
	private JPanel link;
	private JPanel recht;

	public ZeugnissPanel(FachUndLehrer fachUndLehrer) {
		this.fachUndLehrer = fachUndLehrer;
		bauen();
	}

	/**
	 * Bauen die Zeugniss-Panel fuer eine FachundLehrer
	 */
	private void bauen() {
		lbls = new JLabel[12];
		oben = new JPanel();
		link = new JPanel();
		recht = new JPanel();
		setLayout(new BorderLayout(5, 5));
		oben = new JPanel(new FlowLayout(5, 5, 5));
		oben.setBackground(Color.CYAN);
		link = new JPanel(new GridLayout(5, 1, 5, 5));
		link.setBackground(Color.CYAN);
		recht = new JPanel(new GridLayout(5, 1, 5, 5));
		recht.setBackground(Color.CYAN);
		lbls[0] = new JLabel(fachUndLehrer.getGelernteFach().name());
		lbls[1] = new JLabel("von " + fachUndLehrer.getLehrerVomFach().getVornameUndNachname());
		lbls[0].setFont(new Font("Arial", Font.BOLD, 16));
		lbls[1].setFont(new Font("Arial", Font.BOLD, 16));
		oben.add(lbls[0]);
		oben.add(lbls[1]);
		lbls[2] = new JLabel("Schulaufgaben:     ", JLabel.RIGHT);
		lbls[3] = new JLabel("Muendliche Leistungen:     ", JLabel.RIGHT);
		lbls[4] = new JLabel("Kleineschriftliche Leistungen:    ", JLabel.RIGHT);
		lbls[5] = new JLabel("Andere Leistungen:     ", JLabel.RIGHT);
		lbls[6] = new JLabel("Durschnitt:     ", JLabel.RIGHT);
		for (int i = 2; i < 7; i++) {
			link.add(lbls[i]);
		}
		for (int i = 7; i < 12; i++) {
			lbls[i] = new JLabel();
			recht.add(lbls[i]);
		}
		ArrayList<Note> bewertungen = (ArrayList) new SchuleDAOImplDB().getAlleNotendesGelerntenFachs(fachUndLehrer);
		for (Note note : bewertungen) {
			if (note.getTyp() == Notentype.SCHULAUFGABE) {
				lbls[7].setText(lbls[7].getText() + note.getNote() + " / ");
			}
			if (note.getTyp() == Notentype.MUNDLICH) {
				lbls[8].setText(lbls[8].getText() + note.getNote() + " / ");
			}
			if (note.getTyp() == Notentype.KLEINESCHRIFTLICHENLEISTUNG) {
				lbls[+9].setText(lbls[9].getText() + note.getNote() + " / ");
			}
			if (note.getTyp() == Notentype.ANDERE) {
				lbls[10].setText(lbls[10].getText() + note.getNote() + " / ");
			}
		}
		lbls[11].setText(fachUndLehrer.rechnenDurschnittAlsString());
		add(oben, BorderLayout.NORTH);
		add(recht, BorderLayout.CENTER);
		add(link, BorderLayout.WEST);
		setBackground(Color.CYAN);
	}
}
