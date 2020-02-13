package form.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.SchuleDAOImplDB;
import form.schulereltern.SchuelerFensterfuerNoten;
import personen.Schueler;

public class SchuelerInformationenSchauenPanel extends JPanel {
	private JPanel oben;
	private JPanel unten;
	private List<Schueler> schuelernzimmer;
	private JLabel[] lbls;
	private JComboBox[] combos;
	private BorderLayout brderlyt;

	public SchuelerInformationenSchauenPanel() {
		brderlyt = new BorderLayout(3, 3);
		setLayout(brderlyt);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		bauen();
	}

	/**
	 * um die SchuelerInformationenSchauenPanel zu bauen.
	 */
	private void bauen() {
		oben = new JPanel();
		oben.setBackground(Color.CYAN);
		oben.setBorder(new EmptyBorder(0, 0, 5, 0));
		oben.setLayout(new GridLayout(1, 4, 5, 5));
		lbls = new JLabel[4];
		lbls[0] = new JLabel("Stufe:");
		oben.add(lbls[0]);
		combos = new JComboBox[3];
		Integer[] stuefen = { 5, 6, 7, 8, 9, 10, 11, 12 };
		combos[0] = new JComboBox<Integer>(stuefen);
		combos[0].setSelectedItem(null);
		oben.add(combos[0]);
		lbls[1] = new JLabel("Klassenzimmer: ");
		oben.add(lbls[1]);
		String[] klassenzimmern = { "", "A", "B", "C", "D", "E", "F" };
		combos[1] = new JComboBox<String>(klassenzimmern);
		combos[1].addActionListener(event -> {
			if (combos[1].getSelectedItem() != null && combos[0].getSelectedItem() != null) {
				schuelernzimmer = new SchuleDAOImplDB().getMancheSchueler(stuefen[combos[0].getSelectedIndex()],
						klassenzimmern[combos[1].getSelectedIndex()]);
				combos[2].removeAllItems();
				String[] fuerCombo = new String[schuelernzimmer.size()];
				for (Schueler sch : schuelernzimmer) {
					combos[2].addItem(sch.getVornameUndNachname());
				}
			}
		});
		oben.add(combos[1]);
		lbls[2] = new JLabel("Schueler:");
		oben.add(lbls[2]);
		combos[2] = new JComboBox();
		combos[2].setSelectedItem(null);
		combos[2].addActionListener(event -> {
			if (combos[2].getSelectedItem() != null) {
				if (getComponents().length == 3) {
					remove(getComponents()[2]);
				}
				Schueler sch = schuelernzimmer.get(combos[2].getSelectedIndex());
				SchuelerFensterfuerNoten mitte = new SchuelerFensterfuerNoten(sch);
				add(mitte, BorderLayout.CENTER);
				int anzahl = new SchuleDAOImplDB().getAlleAbwesenheitVomSchueler(sch.getId()).size();
				lbls[3].setText(sch.getVornameUndNachname() + " hat ingesamt " + anzahl + " tage Abwesenheit.");
				repaint();
			}
		});
		oben.add(combos[2]);
		unten = new JPanel();
		unten.setBackground(Color.CYAN);
		unten.setBorder(new LineBorder(Color.BLUE, 1));
		lbls[3] = new JLabel();
		unten.add(lbls[3]);
		add(unten, BorderLayout.SOUTH);
		add(oben, BorderLayout.NORTH);
	}
}
