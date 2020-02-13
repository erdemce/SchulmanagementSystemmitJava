package form.schulereltern;

import java.awt.Color;
import javax.swing.JPanel;
import form.haupt.PersonFenster;
import personen.Schueler;

/**
 * ElternSchuelersfenster fuer Éltern oder Schueler-Zugang.
 * Sub-Class von PersonFenster
 * 
 * @author Erdem
 *
 */
public class ElternSchuelersfenster extends PersonFenster {
	private Schueler sch;

	public ElternSchuelersfenster(Schueler sch) {
		this.sch = sch;
		anzahlAufgaben=4;
		bauenMittePnl();
		bauenObenPanel();
		bauenLinkPanel();
		super.bauenBorderLayout();
	}
	
	@Override
	public void bauenMittePnl() {
		super.bauenMittePnl();
		mittePanel[0] = new JPanel();
		mittePanel[1] = new AbwesenheitForm(this.sch);
		mittePanel[2] = new GelernteFaecherMitLehrerForm(this.sch);
		mittePanel[3] = new SchuelerFensterfuerNoten(this.sch);
		for (int i = 0; i < anzahlAufgaben; i++) {
			mittePanel[i].setBackground(Color.CYAN);
			mittePnl.add(mittePanel[i]);
			aufgaben.addLayoutComponent(mittePanel[i], ("" + i));
		}
	}
	@Override
	public void bauenObenPanel() {
		super.bauenObenPanel();
		super.wilkommen.setText(sch.getVornameUndNachname() + "          WILKOMMEN UNSEREN SCHULMANEGEMENTSYSTEM");
	}
	
	@Override
	public void bauenLinkPanel() {
		super.bauenLinkPanel();
		buttons[0].setText("Abwesenheit Daten:");
		buttons[1].setText("gelernte Faecher mit Lehrer");
		buttons[2].setText("Noten Schauen");
	}
}
