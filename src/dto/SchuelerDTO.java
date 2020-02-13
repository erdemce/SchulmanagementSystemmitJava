package dto;

import java.time.LocalDate;
import java.util.Objects;



/**
 * SchuelerDTO-Class ist eine Class zum Datentransport von Schueler-CLass
 * @author Erdem
 *
 */
public class SchuelerDTO {
	private String vorname;
	private String nachname;
	private int stufe;
	private String klassenzimmer;
	private LocalDate geburtsdatum;
	private int elternId;

	public SchuelerDTO(String vorname, String nachname, int stufe, String klassenzimmer, LocalDate geburtsdatum) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.stufe = stufe;
		this.klassenzimmer = klassenzimmer;
		this.geburtsdatum = geburtsdatum;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
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

	public void setKlassenzimmer(String klassenzimmer) {
		this.klassenzimmer = klassenzimmer;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(elternId, geburtsdatum, klassenzimmer, nachname, stufe, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SchuelerDTO)) {
			return false;
		}
		SchuelerDTO other = (SchuelerDTO) obj;
		return elternId == other.elternId && Objects.equals(geburtsdatum, other.geburtsdatum)
				&& Objects.equals(klassenzimmer, other.klassenzimmer) && Objects.equals(nachname, other.nachname)
				&& stufe == other.stufe && Objects.equals(vorname, other.vorname);
	}

	@Override
	public String toString() {
		return "SchuelerDTO [vorname=" + vorname + ", nachname=" + nachname + ", stufe=" + stufe + ", klassenzimmer="
				+ klassenzimmer + ", geburtsdatum=" + geburtsdatum + ", elternId=" + elternId + "]";
	}
}
