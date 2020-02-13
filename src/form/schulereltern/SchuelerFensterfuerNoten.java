package form.schulereltern;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.SchuleDAOImplDB;
import personen.Schueler;
import tasken.FachUndLehrer;

/**
 * einer Panel von mittePanel einer ElternSchuelerFenster. Mit dieser Panel kann
 * Schueler/Eltern alle eigenen erhaltenen Noten teilgenommender Unterricht
 * sehen. Fuer Jede Unterricht erstellt eine neuPanel. Mit CardLayout Schauen.
 * 
 * @author Erdem
 *
 */
public class SchuelerFensterfuerNoten extends JPanel {
	private Schueler sch;
	private CardLayout faecherLayout;

	public SchuelerFensterfuerNoten(Schueler sch) {
		this.sch = sch;
		faecherLayout = new CardLayout(1, 1);
		setLayout(faecherLayout);
		setBackground(Color.CYAN);
		bauen();
	}

	/**
	 * um die SchuelerFensterfuerNoten zu bauen.
	 */
	private void bauen() {
		int schuelerId = this.sch.getId();
		ArrayList<FachUndLehrer> alleGelerntenFaecher = (ArrayList) new SchuleDAOImplDB()
				.getAlleGelerntenFachdesSchueler(schuelerId);
		int anzahlDerlernendenFaecher = alleGelerntenFaecher.size();
		JPanel zwischenzeugniss = new JPanel();

		/**
		 * Wenn schueler weniger als 8 Fach hat , um die ZeugnissPanel gut struktieren,
		 * arrangieren wir GridLayout.
		 */
		if (anzahlDerlernendenFaecher > 8) {
			zwischenzeugniss.setLayout(new GridLayout(anzahlDerlernendenFaecher + 1, 3, 5, 5));
		} else {
			zwischenzeugniss.setLayout(new GridLayout(9, 3, 5, 5));
		}
		zwischenzeugniss.setBorder(new EmptyBorder(10, 10, 10, 10));
		zwischenzeugniss.setBackground(Color.CYAN);
		zwischenzeugniss.add(new JLabel("Fach:", JLabel.CENTER));
		zwischenzeugniss.add(new JLabel("Lehrer:", JLabel.CENTER));
		zwischenzeugniss.add(new JLabel("Durschnitt: ", JLabel.CENTER));

		for (int i = 0; i < anzahlDerlernendenFaecher; i++) {
			JButton temp = new JButton(alleGelerntenFaecher.get(i).getGelernteFach().name());
			temp.setFont(new Font("Arial", Font.BOLD, 14));
			temp.setBackground(new Color(224, 255, 255));
			temp.setToolTipText("klicken Sie bitte hier um alle Noten zu sehen");
			temp.setOpaque(true);
			temp.setBorder(new LineBorder(Color.BLUE, 1, true));
			String a = "" + i;
			temp.addActionListener(event -> faecherLayout.show(this, a));
			zwischenzeugniss.add(temp);
			zwischenzeugniss.add(
					new JLabel(alleGelerntenFaecher.get(i).getLehrerVomFach().getVornameUndNachname(), JLabel.CENTER));
			zwischenzeugniss.add(new JLabel(alleGelerntenFaecher.get(i).rechnenDurschnittAlsString(), JLabel.CENTER));
		}

		/**
		 * Wenn schueler weniger als 8 Fach hat , um die ZeugnissPanel gut struktieren,
		 * addieren wir fehlende Faecher als Empty JLabel
		 */
		if (anzahlDerlernendenFaecher < 8) {
			for (int i = 0; i < 3 * (8 - anzahlDerlernendenFaecher); i++) {
				zwischenzeugniss.add(new JLabel());
			}
		}
		add(zwischenzeugniss);
		faecherLayout.addLayoutComponent(zwischenzeugniss, ("alle"));

		for (int i = 0; i < anzahlDerlernendenFaecher; i++) {
			ZeugnissPanel zeugnissPanel = new ZeugnissPanel(alleGelerntenFaecher.get(i));
			JButton zurueck = new JButton("Zurueck");
			zurueck.addActionListener(event -> faecherLayout.show(this, "alle"));
			zeugnissPanel.add(zurueck, BorderLayout.SOUTH);
			add(zeugnissPanel);
			faecherLayout.addLayoutComponent(zeugnissPanel, ("" + i));
		}
	}
}
