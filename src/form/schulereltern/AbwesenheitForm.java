package form.schulereltern;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import personen.Schueler;
import tasken.Abwesenheit;

/**
 * einer Panel von mittePanel einer ElternSchuelerFenster. Mit dieser Panel kann
 * Schueler oder Eltern sich eigene Abwesenheit datum informieren.
 * 
 * @author Erdem
 *
 */
public class AbwesenheitForm extends JPanel {
	private JLabel lbls[];
	private Schueler sch;

	public AbwesenheitForm(Schueler sch) {
		this.sch = sch; 
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout(5, 10, 10));
		setBackground(Color.CYAN);
		bauen();
	}

	/**
	 * um die AbwesenheitForm zu bauen.
	 */
	private void bauen() {

		List<Abwesenheit> alleAbwesenheitVomSchueler = new SchuleDAOImplDB().getAlleAbwesenheitVomSchueler(sch.getId());
		int anzahl = alleAbwesenheitVomSchueler.size();
		lbls = new JLabel[10];
		for (int i=0;i<lbls.length;i++) {
			lbls[i]=new JLabel("");
			add(lbls[i]);
		}
		lbls[0].setText("Du hast ingesamt " + anzahl + " tage Abwesenheit.");
		lbls[0].setForeground(Color.RED);

		/**
		 * Maximal fuenf Abwesenheit Datum werden in jede label geschrieben. Wenn Anzahl
		 * der Abwesenheit ist grosser als 45,dann werden nur 45 von Allen geschrieben.
		 */
		for (int i = 0; i < anzahl; i++) {
			lbls[1 + i / 5].setText(
					lbls[1 + i / 5].getText() + " / " + alleAbwesenheitVomSchueler.get(i).getDatum().toString());
			if (anzahl == 45) {
				break;
			}
		}
	}
}
