package form.manager;

import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.SchuleDAOImplDB;
import dto.ElternteilDesSchuelerDTO;
import dto.SchuelerDTO;

/**
 * einer Panel von mittePanel einer ManagerFenster. Mit dieser Panel kann
 * Manager neue Schueler und Eltern von diesem Schueler in Database addieren.
 * @author Erdem
 *
 */
public class NeuSchuelerRegistrieren extends JPanel {
	private JButton speichern;
	private JLabel labelSchueler;
	private JLabel labelEltern;
	private JLabel[] labels;
	private JTextField[] texts;
	private JFormattedTextField geburtsdatum;

	public NeuSchuelerRegistrieren() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(8, 4, 5, 10));
		bauen();
	}

	
	/**
	 * um die NeuSchuelerRegistrierenForm zu bauen.
	 */
	private void bauen() {
		speichern = new JButton("SPEICHERN");
		labelSchueler = new JLabel("SCHUELER");
		labelEltern = new JLabel("ELTERN");
		labels=new JLabel[9];
		labels[0]=	new JLabel("Vorname:");
		labels[1]=	new JLabel("Vorname:");
		labels[2]=	new JLabel("Nachname:");
		labels[3]=	new JLabel("Nachname:");
		labels[4]=	new JLabel("GeburtsDatum:");
		labels[5]=	new JLabel("e-Mail:");
		labels[6]=	new JLabel("Stufe:");
		labels[7]=	new JLabel("Handynummer:");
		labels[8]=	new JLabel("Klasse:");
		texts = new JTextField[9];
		geburtsdatum = new JFormattedTextField(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
		labelEltern.setFont(new Font("Arial", Font.BOLD, 20));
		labelSchueler.setFont(new Font("Arial", Font.BOLD, 20));
		add(labelSchueler);
		add(new JLabel());
		add(labelEltern);
		add(new JLabel());
		for (int i = 0; i < labels.length; i++) {
			if (i == 4) {
				geburtsdatum.setText("2000-01-01");
				texts[i] = geburtsdatum;
				labels[i].setAlignmentX(20);
				add(labels[i]);
				add(texts[i]);
			} else {
				texts[i] = new JTextField(10);
				labels[i].setAlignmentX(20);
				add(labels[i]);
				add(texts[i]);
			}
		}
		for (int i = 0; i < 9; i++) {
			add(new JLabel());
		}
		speichern.addActionListener(event -> {
			if(isAllenotwendigeFelderRictigAusgefuellt()) {
			ElternteilDesSchuelerDTO tempsEltern = new ElternteilDesSchuelerDTO(texts[1].getText(), texts[3].getText(), texts[5].getText(),
					texts[7].getText());
			SchuelerDTO tempSchueler = new SchuelerDTO(texts[0].getText(), texts[2].getText(),
					Integer.parseInt(texts[6].getText()), texts[8].getText(),LocalDate.parse(geburtsdatum.getText()));
			for (JTextField text : texts) {
				text.setText("");
			}
			geburtsdatum.setText("01.01.2000");
			new SchuleDAOImplDB().addSchueler(tempSchueler, tempsEltern);
			}
		});
		add(speichern);
	}
	/**
	 * konrollieren ob alle Felder mit geeignete Angaben ausgefuellt werden.
	 * @return boolean value 
	 */ 
	public boolean isAllenotwendigeFelderRictigAusgefuellt() {
		for (int i = 0; i < texts.length; i++) {
			if(texts[i].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bitte fuellen Sie alle Felder entsprechend aus");
				return false;
			}
			
		}
		if(Integer.parseInt(texts[6].getText())>12||Integer.parseInt(texts[6].getText())<5){
			JOptionPane.showMessageDialog(this, "Bitte geben Sie geeignete Stufe(zwischen 5-12) an");
			return false;
		}
		
		return true;
	}
}