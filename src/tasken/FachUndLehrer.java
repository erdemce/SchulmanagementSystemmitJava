package tasken;

import java.util.ArrayList;

import dao.SchuleDAOImplDB;
import personen.Lehrer;
import personen.Schueler;

/**
 * 
 * FachUndLehrer-Objekt besteht aus teilgenommene Schueler, lehrende Lehrer un
 * gelernte Fach.
 * 
 * @author Erdem
 *
 */
public class FachUndLehrer {

	private int id;
	private Schueler lernendeSchueler;
	private Fach gelernteFach;
	private Lehrer lehrerVomFach; 
	private String durschnittAlsString;

	public FachUndLehrer(int id, Schueler lernendeSchueler, Fach gelernteFach, Lehrer lehrerVomFach) {
		this.id = id;
		this.lernendeSchueler = lernendeSchueler;
		this.gelernteFach = gelernteFach;
		this.lehrerVomFach = lehrerVomFach;
		this.durschnittAlsString = rechnenDurschnittAlsString();
	}

	public Lehrer getLehrerVomFach() {
		return lehrerVomFach;
	}

	public void setLehrerVomFach(Lehrer lehrerVomFach) {
		this.lehrerVomFach = lehrerVomFach;
	}

	public int getId() {
		return id;
	}

	public Schueler getLernendeSchueler() {
		return lernendeSchueler;
	}

	public Fach getGelernteFach() {
		return gelernteFach;
	}

	/**
	 * rechnenDurschnittAlsString-Method ist um die durschnittliche Note von Dieser
	 * Unterricht kalkulieren
	 * 
	 * @return durschnittNote von dieser Unterricht
	 */
	public String rechnenDurschnittAlsString() {
		ArrayList<Note> bewertungen = (ArrayList) new SchuleDAOImplDB().getAlleNotendesGelerntenFachs(this);
		int temp = 0;
		int anzahl = bewertungen.size();
		if (bewertungen.size() == 0) {
			return "";
		} else {
			for (Note note : bewertungen) {
				if (note.getTyp() == Notentype.SCHULAUFGABE) {
					anzahl++;
					temp = temp + 2 * note.getNote();
				} else {
					temp = temp + note.getNote();
				}
			}
		}
		double durschnittNote = temp / anzahl;
		return "" + durschnittNote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gelernteFach == null) ? 0 : gelernteFach.hashCode());
		result = prime * result + id;
		result = prime * result + ((lehrerVomFach == null) ? 0 : lehrerVomFach.hashCode());
		result = prime * result + ((lernendeSchueler == null) ? 0 : lernendeSchueler.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FachUndLehrer other = (FachUndLehrer) obj;
		if (gelernteFach != other.gelernteFach)
			return false;
		if (id != other.id)
			return false;
		if (lehrerVomFach == null) {
			if (other.lehrerVomFach != null)
				return false;
		} else if (!lehrerVomFach.equals(other.lehrerVomFach))
			return false;
		if (lernendeSchueler == null) {
			if (other.lernendeSchueler != null)
				return false;
		} else if (!lernendeSchueler.equals(other.lernendeSchueler))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return lernendeSchueler + " lernt " + gelernteFach + " von " + lehrerVomFach + ".";
	}

}
