package form.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import personen.Schueler;
import tasken.Abwesenheit;

/**
 * einer Panel von mittePanel einer ManagerFenster.
 * Mit dieser Panel kann Manager neue Abwesenheit datum fuer eine Schueler  eintreten.
 * @author Erdem
 *
 */
public class AbwesenheitEintretenForm extends JPanel{
	private JPanel mitte;
	private JPanel recht;
	private List<Schueler> schuelernzimmer;
	private JLabel[] lbls;
	private JComboBox[] combos;
	private JButton abwesenheit;
	private JFormattedTextField datum = new JFormattedTextField();
	private Schueler sch;

	public AbwesenheitEintretenForm() {
		setLayout(new BorderLayout(3, 3));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		bauen();
	}

	/**
	 * um die AbwesenheitEintretenForm zu bauen.
	 */
	private void bauen() {
		mitte = new JPanel();
		mitte.setBackground(Color.CYAN);
		mitte.setBorder(new EmptyBorder(10, 10, 10, 10));
		mitte.setLayout(new GridLayout(8, 2, 5, 5));
		lbls = new JLabel[29];
		lbls[0] = new JLabel("ABWESENHEIT");
		mitte.add(lbls[0]);
		lbls[1] = new JLabel();
		mitte.add(lbls[1]);
		lbls[2] = new JLabel("Datum");
		mitte.add(lbls[2]);
		datum.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
		mitte.add(datum);
		lbls[3] = new JLabel("Stufe:");
		mitte.add(lbls[3]);
		combos = new JComboBox[3];
		Integer[] stuefen = { 5, 6, 7, 8, 9, 10, 11, 12 };
		combos[0] = new JComboBox<Integer>(stuefen);
		combos[0].setSelectedItem(null);
		mitte.add(combos[0]);
		lbls[4] = new JLabel("Klassenzimmer: ");
		mitte.add(lbls[4]);
		String[] klassenzimmern = { "", "A", "B", "C", "D", "E", "F" };
		combos[1] = new JComboBox<String>(klassenzimmern);
		combos[1].addActionListener(event -> {
			if (combos[1].getSelectedItem()!= null) {
				schuelernzimmer = new SchuleDAOImplDB().getMancheSchueler(stuefen[combos[0].getSelectedIndex()],
						klassenzimmern[combos[1].getSelectedIndex()]);
				combos[2].removeAllItems();
				String[] fuerCombo = new String[schuelernzimmer.size()];
				for (Schueler sch : schuelernzimmer) {
					combos[2].addItem(sch.getVornameUndNachname());
				}
			}
		});
		mitte.add(combos[1]);
		lbls[5] = new JLabel("SchulerInnen: ");
		mitte.add(lbls[5]);
		combos[2] = new JComboBox<String>();
		combos[2].addActionListener(event->{
			if(combos[2].getSelectedItem()!=null) {
				sch = schuelernzimmer.get(combos[2].getSelectedIndex());
				schreibenRechtAbwesenheitDatenFuerAusgewaehltenSchueler();
			}
		}
		);
		mitte.add(combos[2]);
		for (int i = 0; i < 5; i++) {
			mitte.add(new JLabel());
		}
		abwesenheit = new JButton("Speichern");
		abwesenheit.addActionListener(event->neuAbwesenheitEintreten());
		mitte.add(abwesenheit);
		add(mitte, BorderLayout.CENTER);
		recht = new JPanel();
		recht.setBackground(Color.CYAN);
		recht.setBorder(new EmptyBorder(10, 10, 10, 10));
		recht.setLayout(new GridLayout(21, 1, 4, 4));
		lbls[6] = new JLabel("         *ABWESENHEIT*         ");
		lbls[6].setForeground(Color.RED);
		recht.add(lbls[6]);
		for (int i = 7; i < 29; i++) {
			lbls[i] = new JLabel();
		}
		add(recht, BorderLayout.EAST);
	} 
	
	/**
	 * um AbwesenheitDaten fuer ausgewaehlten Schueler in recht-Panel zu schreiben.
	 */
	public void schreibenRechtAbwesenheitDatenFuerAusgewaehltenSchueler() {
		List<Abwesenheit> alleAbwesenheitVomSchueler = new SchuleDAOImplDB()
				.getAlleAbwesenheitVomSchueler(sch.getId());
		int anzahl = alleAbwesenheitVomSchueler.size();
		recht.removeAll();
		recht.add(lbls[6]);
		lbls[7].setText(sch.getId() + " " + sch.getVornameUndNachname());
		recht.add(lbls[7]);
		lbls[8].setText(anzahl + "tage:");
		recht.add(lbls[8]);
		for (int i = 0; i < anzahl; i++) {
			lbls[9 + i].setText(alleAbwesenheitVomSchueler.get(i).getDatum().toString());
			recht.add(lbls[9 + i]);
			if (i > 16) {
				break;
			}
		}
		recht.repaint();
	}

	/**
	 * um neue Abwesenheit Datum fuer einer Schueler einzutreten.
	 */
	public void neuAbwesenheitEintreten() {
			Abwesenheit abw = new Abwesenheit(sch.getId(), LocalDate.parse(datum.getText()));
			new SchuleDAOImplDB().addAbwesenHeit(abw);
			schreibenRechtAbwesenheitDatenFuerAusgewaehltenSchueler();
			combos[0].setSelectedItem(null);
			combos[1].setSelectedItem(null);
			combos[2].removeAllItems();
	}
}