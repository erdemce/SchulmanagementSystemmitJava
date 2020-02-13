package form.haupt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * eine Algemeine Panel fuer Frame.
 * Super-Panel fuer Lehrer-, Schueler- oder Manager-Panel.
 * 
 * @author Erdem
 *
 */
public class PersonFenster extends JPanel {
	
	protected JPanel linkPanel;
	protected JPanel obenPanel;
	protected JPanel[] mittePanel;
	protected JPanel mittePnl;
	protected JLabel wilkommen;
	protected JButton[] buttons;
	protected BorderLayout meinLayout;
	protected CardLayout aufgaben;
	protected int anzahlAufgaben = 20;

	public PersonFenster() {
		
	}

	/**
	 * bauen die Mittepanel
	 */
	public void bauenMittePnl() {
		mittePnl = new JPanel();
		aufgaben = new CardLayout();
		mittePnl.setLayout(aufgaben);
		mittePanel = new JPanel[anzahlAufgaben];
		
	}

	/**
	 * Bauen die Oben-Panel
	 */
	public void bauenObenPanel() {
		obenPanel = new JPanel();
		obenPanel.setBackground(new Color(50, 50, 255));
		wilkommen = new JLabel(".................");
		wilkommen.setForeground(Color.WHITE);
		wilkommen.setFont(new Font("Arial", Font.BOLD, 20));
		obenPanel.add(wilkommen);
	}

	/**
	 * Bauen die Link-Panel
	 */
	public void bauenLinkPanel() {
		buttons = new JButton[anzahlAufgaben-1];
		linkPanel = new JPanel();
		linkPanel.setBackground(new Color(50, 50, 255));
		linkPanel.setBorder(new EmptyBorder(30, 20, 100, 20));
		linkPanel.setLayout(new GridLayout(anzahlAufgaben, 1, 20, 20));
		for (int i = 0; i < anzahlAufgaben-1; i++) {
			buttons[i] = new JButton("..........");
			String temp = "" + (i+1);
			buttons[i].addActionListener(e -> aufgaben.show(mittePnl, temp));
			buttons[i].setFont(new Font("Arial", Font.BOLD, 14));
			buttons[i].setBorder(new EmptyBorder(15, 10, 15, 10));
			linkPanel.add(buttons[i]);
		}
	}

	/**
	 * Erstellen neue Border-Layout. Arrangieren die BorderLayout. Stellen diese
	 * Layout als Layout von dieser Panel ein.
	 */
	public void bauenBorderLayout() {
		meinLayout = new BorderLayout(10, 10);
		setLayout(meinLayout);
		add(linkPanel, BorderLayout.WEST);
		add(obenPanel, BorderLayout.NORTH);
		add(mittePnl, BorderLayout.CENTER);
	}
}