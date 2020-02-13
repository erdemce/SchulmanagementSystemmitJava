package form.haupt;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import form.lehrer.Lehrersfenster;
import form.manager.ManagerFenster;
import form.schulereltern.ElternSchuelersfenster;
import personen.Lehrer;
import personen.Schueler;

/**
 * Frame fuer gesammte Projekt mit menu
 * 
 * @author Erdem
 *
 */
public class Hauptfenster extends JFrame {
	private ElternSchuelersfenster elternSchuelerForm;
	private Lehrersfenster lehrerForm;
	private ManagerFenster schulleitungsForm;
	private LoginPanel login;
	private JMenuBar menubar;
	private JMenu schuleM;
	private JMenu viewM;
	private JMenuItem menuMinimize;
	private JMenuItem menuExit;

	public Hauptfenster() {
		setSize(300, 200);
		login = new LoginPanel(this);
		schauenLoginForm();
		
	}

	/**
	 * Erstellt JMenu und alle Kompenenten der JMenu.
	 * addiert alle in Kompenenten in JMenu.
	 * addiert JMenu in dieser Frame.
	 */
	private void menuZusammenstellen() {
		menubar = new JMenuBar();
		schuleM = new JMenu("Schule Management");
		menuMinimize = new JMenuItem("Minimize");
		menuExit = new JMenuItem("Exit");
		menubar.add(schuleM);
		schuleM.add(menuMinimize);
		schuleM.add(menuExit);
		setJMenuBar(menubar);
		menuExit.addActionListener(event -> System.exit(0));
		menuMinimize.addActionListener(event -> this.setState(ICONIFIED));
	}

	/**
	 * Stellt Login-Objekte in "ContetntPane" ein.
	 */
	public void schauenLoginForm() {
		setContentPane(login);
		setTitle("Login");
	}

	/**
	 * Erstellt ein neue Schulleitungsfenster-Objekt. Stellt diese Objekte in
	 * "ContetntPane" ein.
	 */
	public void schauenManagerForm() {
		menuZusammenstellen();
		schulleitungsForm = new ManagerFenster();
		setTitle("Manager Fenster");
		setSize(850, 450);
		setContentPane(schulleitungsForm);
		setResizable(false);
	}

	/**
	 * Erstellt ein neue ElternSchuelersfenster-Objekt. Stellt diese Objekte in
	 * "ContetntPane" ein.
	 * 
	 * @param sch der Schueler der das System zugreifft
	 */
	public void schauenElternForm(Schueler sch) {
		menuZusammenstellen();
		elternSchuelerForm = new ElternSchuelersfenster(sch);
		setTitle("Eltern Fenster");
		setSize(850, 450);
		setContentPane(elternSchuelerForm);
		setResizable(false);
	}

	/**
	 * Erstellt ein neue ElternSchuelersfenster-Objekt. Stellt diese Objekte in
	 * "ContetntPane" ein.
	 * 
	 * @param sch der Schueler der das System zugreifft
	 */
	public void schauenSchuelerForm(Schueler sch) {
		menuZusammenstellen();
		elternSchuelerForm = new ElternSchuelersfenster(sch);
		setTitle("Schueler Fenster");
		setSize(850, 450);
		setContentPane(elternSchuelerForm);
		setResizable(false);
	}

	/**
	 * erstellen ein neue ElternSchuelersfenster-Objekt. Stell diese Objekte in
	 * "ContetntPane" ein.
	 * 
	 * @param lehr:der Lehrer der das System zugreifft
	 */
	public void schauenLehrerForm(Lehrer lehr) {
		menuZusammenstellen();
		lehrerForm = new Lehrersfenster(lehr);
		setTitle("Lehrer Fenster");
		setSize(850, 450);
		setContentPane(lehrerForm);
		setResizable(false);
	}
}
