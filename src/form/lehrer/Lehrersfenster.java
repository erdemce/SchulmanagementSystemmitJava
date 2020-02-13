package form.lehrer;

import java.awt.Color;
import javax.swing.JPanel;
import form.haupt.PersonFenster;
import personen.Lehrer;

/**
 * Lehrer-Panel fuer Lehrer-Zugang.
 * Sub-Class von PersonFenster
 * 
 * @author Erdem
 *
 */
public class Lehrersfenster extends PersonFenster {

	private Lehrer lehr;

	public Lehrersfenster(Lehrer lehr) {
		this.lehr = lehr;
		anzahlAufgaben=3;
		bauenMittePnl();
		bauenObenPanel();
		bauenLinkPanel();
		super.bauenBorderLayout();
	}
	@Override
	public void bauenMittePnl() {
		super.bauenMittePnl();
		mittePanel[0] = new JPanel();		
		mittePanel[1] = new NeuNoteEintretenForm(lehr);		
		mittePanel[2]=new JPanel();
		
		for (int i = 0; i < anzahlAufgaben; i++) {
			mittePanel[i].setBackground(Color.CYAN);
			mittePnl.add(mittePanel[i]);
			aufgaben.addLayoutComponent(mittePanel[i], ("" + i));
		}
	}
	
	@Override
	public void bauenObenPanel() {
		super.bauenObenPanel();
		super.wilkommen.setText(lehr.getVornameUndNachname() + "          WILKOMMEN UNSEREN SCHULMANEGEMENTSYSTEM");
	}
	
	@Override
	public void bauenLinkPanel() {
		super.bauenLinkPanel();
		buttons[0].setText("Neu note eintreten");
		buttons[1].setText("Meine Klassen");
	}
}