package personen;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 
 * Schueler-Class ist ein Sub-Class von Person-Class.
 * 
 * @author Erdem
 *
 */
public class Schueler extends Person {

	private int stufe;
	private String klassenzimmer;
	private LocalDate geburtsdatum;

	/**
	 * jede Schueler-Object hat eine ElternteildesSchueler-Objekt
	 */
	private ElternteildesSchueler meinEltern;

	public Schueler(int id, String vorname, String nachname, int stufe, String klassenzimmer, LocalDate geburtsdatum,
			ElternteildesSchueler meinEltern) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.stufe = stufe;
		this.klassenzimmer = klassenzimmer;
		this.geburtsdatum = geburtsdatum;
		this.meinEltern = meinEltern;
	}

	public int getStufe() {
		return stufe;
	}

	public void setStufe(int stufe) {
		this.stufe = stufe;
	}

	public String getKlassenzimmer() {
		return klassenzimmer;
	}

	public void setKlassenzimmer(String klassezimmer) {
		this.klassenzimmer = klassezimmer;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(geburtsdatum);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Schueler)) {
			return false;
		}
		Schueler other = (Schueler) obj;
		return Objects.equals(geburtsdatum, other.geburtsdatum);
	}

	@Override
	public String toString() {
		return  id + " " + vorname + " " + nachname + " " + stufe
				+ "-" + klassenzimmer + " " + geburtsdatum;
	}
}