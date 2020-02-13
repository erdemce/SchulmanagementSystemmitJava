package form.lehrer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import dto.NoteDTO;
import personen.Lehrer;
import personen.Schueler;
import tasken.Fach;
import tasken.Notentype;

/**
 * einer Panel von mittePanel in einer LehrerFenster. Mit dieser Panel kann
 * Lehrer neue note fuer eigene Schueler eintreten.
 * 
 * @author Erdem
 *
 */
public class NeuNoteEintretenForm extends JPanel {
	private JPanel mitte;
	private JPanel unten;
	private List<Schueler> schuelernzimmer;
	private JLabel[] lbls;
	private JComboBox[] combos;
	private JButton speichern;
	private Lehrer lehr;
	private Schueler ausgewaehlteSchueler;
	private Fach ausgewaehlteFach;

	public NeuNoteEintretenForm(Lehrer lehr) {
		this.lehr = lehr;
		setLayout(new BorderLayout(3, 3));
		bauen();
	}

	/**
	 * um die NeuNoteEintretenForm zu bauen.
	 */
	private void bauen() {
		mitte = new JPanel();
		mitte.setBackground(Color.CYAN);
		mitte.setBorder(new EmptyBorder(5, 5, 5, 5));
		mitte.setLayout(new GridLayout(8, 2, 5, 5));
		lbls = new JLabel[31];
		lbls[0] = new JLabel("Neu Note Eintreten");
		mitte.add(lbls[0]);
		lbls[1] = new JLabel();
		mitte.add(lbls[1]);
		lbls[2] = new JLabel("Fach:");
		mitte.add(lbls[2]);
		combos = new JComboBox[4];
		combos[0] = new JComboBox<Object>(lehr.getMeineFaecher().toArray());
		combos[0].setSelectedItem(null);
		mitte.add(combos[0]);
		combos[0].addActionListener(event -> {
			if (combos[0].getSelectedItem() != null) {
				ausgewaehlteFach = (Fach) combos[0].getSelectedItem();
				combos[1].removeAllItems();
				schuelernzimmer = new SchuleDAOImplDB().getAlleSchuelerVomLehrerInBestimmtenFach(lehr.getId(),
						((Fach) combos[0].getSelectedItem()).ordinal() + 1);

				for (Schueler sch : schuelernzimmer) {
					combos[1].addItem(sch.getVorname() + " " + sch.getNachname());
				}
			}
		});
		mitte.add(combos[0]);
		lbls[4] = new JLabel("SchulerInnen: ");
		mitte.add(lbls[4]);
		combos[1] = new JComboBox<String>();
		combos[1].addActionListener(event -> {
			if (combos[1].getSelectedItem() != null) {
				ausgewaehlteSchueler = schuelernzimmer.get(combos[1].getSelectedIndex());
			}
		});
		mitte.add(combos[1]);
		lbls[5] = new JLabel("Neu Notetyp:");
		mitte.add(lbls[5]);
		combos[2] = new JComboBox<Notentype>(Notentype.values());
		combos[2].setSelectedItem(null);
		mitte.add(combos[2]);
		lbls[6] = new JLabel("Neu Note:");
		mitte.add(lbls[6]);
		Integer[] noten = new Integer[101];
		for (int i = 0; i < noten.length; i++) {
			noten[i] = i;
		}
		combos[3] = new JComboBox<Integer>(noten);
		combos[3].setSelectedItem(null);
		mitte.add(combos[3]);
		speichern = new JButton("Neu Note Speichern");
		speichern.addActionListener(event -> neuNoteEintreten());
		mitte.add(speichern);
		add(mitte, BorderLayout.CENTER);
		unten = new JPanel();
		unten.setBackground(Color.CYAN);
		unten.setLayout(new FlowLayout(5, 5, 5));
		lbls[7] = new JLabel();
		lbls[7].setForeground(Color.RED);
		unten.add(lbls[7]);
		add(unten, BorderLayout.SOUTH);
	}

	/**
	 * um neu note einzutreten.
	 */
	public void neuNoteEintreten() {
		{
			try {
				int fachUndLehrerId = new SchuleDAOImplDB().findFachUndLehrerId(lehr.getId(),
						ausgewaehlteFach.ordinal() + 1, ausgewaehlteSchueler.getId());
				NoteDTO neuNote = new NoteDTO(fachUndLehrerId, combos[2].getSelectedIndex(),
						(int) combos[3].getSelectedItem());
				new SchuleDAOImplDB().addNote(neuNote);
				lbls[7].setText(
						ausgewaehlteSchueler.getVorname() + " " + ausgewaehlteSchueler.getNachname() + " hat von "
								+ ((Fach) combos[0].getSelectedItem()).name() + " " + neuNote.getNote() + " bekommen.");
				combos[2].setSelectedItem(null);
				combos[3].setSelectedItem(null);
			} catch (Exception ungeeigneteFelder) {
				JOptionPane.showMessageDialog(this, "Bitte fuellen Sie alle Felder entsprechend aus");
				combos[2].setSelectedItem(null);
				combos[3].setSelectedItem(null);
			}
		}
	}
}