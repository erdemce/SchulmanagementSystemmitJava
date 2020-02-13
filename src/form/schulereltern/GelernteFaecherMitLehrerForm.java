package form.schulereltern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import personen.Schueler;
import tasken.FachUndLehrer;

/**
 * einer Panel von mittePanel einer ElternSchuelerFenster. Mit dieser Panel kann
 * Schueler/Eltern alle eigene teilgenommende Unterricht oder Faecher sehen.
 * 
 * @author Erdem
 *
 */
public class GelernteFaecherMitLehrerForm extends JPanel {
	private JLabel lbls[];
	private Schueler sch;
	private JPanel link;
	private JPanel mitte;

	public GelernteFaecherMitLehrerForm(Schueler sch) {
		this.sch = sch;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(10, 10));
		bauen();
	}
	
	/**
	 * um die GelernteFaecherMitLehrerForm zu bauen.
	 */
	private void bauen() {

		List<FachUndLehrer> allegelernteFaecher = new SchuleDAOImplDB().getAlleGelerntenFachdesSchueler(sch.getId());
		int anzahl = allegelernteFaecher.size();
		link = new JPanel(new GridLayout(anzahl + 1, 1, 5, 5));
		link.setBorder(new EmptyBorder(5, 5, 5, 5));
		link.setBackground(Color.CYAN);
		mitte = new JPanel(new GridLayout(anzahl + 1, 1, 5, 5));
		mitte.setBorder(new EmptyBorder(5, 5, 5, 5));
		mitte.setBackground(Color.CYAN);
		lbls = new JLabel[(anzahl + 1) * 2];
		lbls[0] = new JLabel("Fach Name:", JLabel.CENTER);
		lbls[1] = new JLabel("Lehrer:", JLabel.LEFT);
		link.add(lbls[0]);
		mitte.add(lbls[1]);
		for (int i = 0; i < anzahl; i++) {
			lbls[2 * i + 2] = new JLabel(allegelernteFaecher.get(i).getGelernteFach().name(), JLabel.CENTER);
			lbls[2 * i + 3] = new JLabel(allegelernteFaecher.get(i).getLehrerVomFach().getVorname() + " "
					+ allegelernteFaecher.get(i).getLehrerVomFach().getNachname(), JLabel.LEFT);
			link.add(lbls[2 * i + 2]);
			mitte.add(lbls[2 * i + 3]);
		}
		add(link, BorderLayout.WEST);
		add(mitte, BorderLayout.CENTER);
	}
}