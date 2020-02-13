package form.manager;

import java.awt.Color;
import javax.swing.JPanel;
import form.haupt.PersonFenster;

/**
 * Managerfenster fuer Manager-Zugang. Sub-Class von PersonFenster
 * 
 * @author Erdem
 *
 */
public class ManagerFenster extends PersonFenster {

	public ManagerFenster() {
		anzahlAufgaben = 6;
		bauenMittePnl();
		bauenObenPanel();
		bauenLinkPanel();
		super.bauenBorderLayout();
	}

	@Override
	public void bauenMittePnl() {
		super.bauenMittePnl();
		mittePanel[0] = new JPanel();
		mittePanel[1] = new AbwesenheitEintretenForm();
		mittePanel[2] = new NeuSchuelerRegistrieren();
		mittePanel[3] = new NeuLehrerRegistrieren();
		mittePanel[4] = new BestimmenFachundLehrerForm();
		mittePanel[5] = new SchuelerInformationenSchauenPanel();
		for (int i = 0; i < anzahlAufgaben; i++) {
			mittePanel[i].setBackground(Color.CYAN);
			mittePnl.add(mittePanel[i]);
			aufgaben.addLayoutComponent(mittePanel[i], ("" + i));
		}
	}

	@Override
	public void bauenObenPanel() {
		super.bauenObenPanel();
		super.wilkommen.setText("WILKOMMEN UNSEREN SCHULMANEGEMENTSYSTEM");
	}

	@Override
	public void bauenLinkPanel() {
		super.bauenLinkPanel();
		buttons[0].setText("Abwesenheit Daten Eintreten");
		buttons[1].setText("Neu Schueler Form");
		buttons[2].setText("Neu Lehrer Form");
		buttons[3].setText("Faecher und Lehrer Bestimmen");
		buttons[4].setText("Schueler Information Sehen");
	}
}