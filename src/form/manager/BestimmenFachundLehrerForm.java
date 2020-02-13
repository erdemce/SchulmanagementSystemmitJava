package form.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import personen.Lehrer;
import personen.Schueler;
import tasken.Fach;

/**
 * einer Panel von mittePanel einer ManagerFenster. Mit dieser Panel kann
 * Manager neue Fach und Lehrer manchmal fuer alle Schueler manchmal fuer einige
 * Schueler in einem Klassenzimmer eintreten.
 * 
 * @author Erdem
 *
 */
public class BestimmenFachundLehrerForm extends JPanel {
	private JPanel oben;
	private JComboBox<Object> alleLehrer;
	private JComboBox<String> faecherDesLehrers;
	private JLabel[] lbls;
	private JComboBox<Integer> stuefen;
	private JComboBox<String> klassenzimmern;
	private List<Schueler> schuelernzimmer = new <Schueler>ArrayList();
	private ArrayList<Fach> meineFaecher;
	private Fach selectedFach;
	private Lehrer selectedLehrer;
	private JPanel mitte;
	private JCheckBox[] alleSchuelerInDiesemKlassenzimmer;
	private JButton bestimmenDerFachundLehrerfuerDieseSchueler;

	public BestimmenFachundLehrerForm() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		bauenMitte();
		bauenOben();
	}

	/**
	 * um die oben-Panel von AbwesenheitEintretenForm zu bauen.
	 */
	private void bauenOben() {
		oben = new JPanel();
		oben.setLayout(new GridLayout(2, 4, 5, 10));
		oben.setBackground(Color.CYAN);
		lbls = new JLabel[4];
		lbls[0] = new JLabel("Lehrer:");
		lbls[1] = new JLabel("Fach:");
		lbls[2] = new JLabel("Stufe:");
		lbls[3] = new JLabel("Klassenzimmer:");
		for (int i = 0; i < lbls.length; i++) {
			oben.add(lbls[i]);
		}
		alleLehrer = new JComboBox<Object>(new SchuleDAOImplDB().getAlleLehrer().toArray());
		alleLehrer.addActionListener(event -> {
			if (alleLehrer.getSelectedItem() != null) {
				selectedLehrer = (Lehrer) alleLehrer.getSelectedItem();
				faecherDesLehrers.removeAllItems();
				meineFaecher = selectedLehrer.getMeineFaecher();
				for (int i = 0; i < meineFaecher.size(); i++) {
					faecherDesLehrers.addItem(meineFaecher.get(i).toString());
				}
			}
		});
		oben.add(alleLehrer, 1);

		faecherDesLehrers = new JComboBox<String>();
		faecherDesLehrers.addActionListener(event -> {
			if (faecherDesLehrers.getSelectedItem() != null) {
				selectedFach = meineFaecher.get(faecherDesLehrers.getSelectedIndex());
			}
		});
		oben.add(faecherDesLehrers, 3);

		Integer[] stuefenArray = { 5, 6, 7, 8, 9, 10, 11, 12 };
		stuefen = new JComboBox<Integer>(stuefenArray);
		stuefen.addActionListener(event -> klassenzimmern.setSelectedItem(null));
		oben.add(stuefen, 5);
		String[] klassenzimmernArray = { "A", "B", "C", "D", "E", "F" };
		klassenzimmern = new JComboBox<String>(klassenzimmernArray);
		klassenzimmern.setSelectedItem(null);
		klassenzimmern.addActionListener(event -> {
			if (klassenzimmern.getSelectedItem() != null) {
				schuelernzimmer = new SchuleDAOImplDB().getMancheSchueler((Integer) stuefen.getSelectedItem(),
						(String) klassenzimmern.getSelectedItem());
				for (int i = 0; i < schuelernzimmer.size(); i++) {
					alleSchuelerInDiesemKlassenzimmer[i].setText(schuelernzimmer.get(i).getId() + " "
							+ schuelernzimmer.get(i).getVorname() + " " + schuelernzimmer.get(i).getNachname());
					alleSchuelerInDiesemKlassenzimmer[i].setVisible(true);
					alleSchuelerInDiesemKlassenzimmer[i].setSelected(true);
				}
				for (int i = schuelernzimmer.size(); i < 27; i++) {
					alleSchuelerInDiesemKlassenzimmer[i].setText("");
					alleSchuelerInDiesemKlassenzimmer[i].setVisible(false);
					alleSchuelerInDiesemKlassenzimmer[i].setSelected(false);
				}
			}
		});

		oben.add(klassenzimmern, 7);
		add(oben, BorderLayout.NORTH);
	}

	/**
	 * um die mitte-Panel von AbwesenheitEintretenForm zu bauen.
	 */
	private void bauenMitte() {
		mitte = new JPanel();
		mitte.setLayout(new GridLayout(14, 2, 1, 1));
		mitte.setBackground(Color.CYAN);
		alleSchuelerInDiesemKlassenzimmer = new JCheckBox[27];
		for (int i = 0; i < 27; i++) {
			alleSchuelerInDiesemKlassenzimmer[i] = new JCheckBox("", false);
			alleSchuelerInDiesemKlassenzimmer[i].setVisible(false);
			mitte.add(alleSchuelerInDiesemKlassenzimmer[i]);
		}
		bestimmenDerFachundLehrerfuerDieseSchueler = new JButton("Fach Bestimmen");
		bestimmenDerFachundLehrerfuerDieseSchueler.addActionListener(event -> {
			ArrayList<Schueler> alleSchuelerfuerdieserFach = new ArrayList<Schueler>();

			for (int i = 0; i < alleSchuelerInDiesemKlassenzimmer.length; i++) {
				if (alleSchuelerInDiesemKlassenzimmer[i].isSelected()) {
					alleSchuelerfuerdieserFach.add(schuelernzimmer.get(i));
				}
			}
			new SchuleDAOImplDB().addFachUndLehrerfuerEinerKlassenZimmer(alleSchuelerfuerdieserFach,
					selectedFach.ordinal() + 1, selectedLehrer);
			alleLehrer.setSelectedItem(null);
			stuefen.setSelectedItem(null);
			faecherDesLehrers.removeAllItems();
			for (int i = 0; i < 27; i++) {
				alleSchuelerInDiesemKlassenzimmer[i].setText("");
				alleSchuelerInDiesemKlassenzimmer[i].setSelected(false);
				alleSchuelerInDiesemKlassenzimmer[i].setVisible(false);
			}
		});

		mitte.add(bestimmenDerFachundLehrerfuerDieseSchueler);
		add(mitte, BorderLayout.CENTER);
	}
}
