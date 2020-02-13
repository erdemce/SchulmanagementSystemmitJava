package tasken;

import java.time.LocalDate;

/**
 * Wenn ein Schueler in einem Tag nicht Schule kommt, erstellen wir eine
 * Abwesenheit Objekt mit Datum und SchuelerId
 * 
 * @author Erdem
 *
 */
public class Abwesenheit {
	private int schuelerId;
	private LocalDate datum;

	public Abwesenheit(int schuelerId, LocalDate datum) {
		super();
		this.schuelerId = schuelerId;
		this.datum = datum;
	}

	public int getSchuelerId() {
		return schuelerId;
	}

	public LocalDate getDatum() {
		return datum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + schuelerId;
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
		Abwesenheit other = (Abwesenheit) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (schuelerId != other.schuelerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Der Schueler mit Id " + schuelerId + "war am " + datum + "nicht in die Schule.";
	}

}