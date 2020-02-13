package dto;

import java.util.Objects;

/**
 * LehrerDTO-Class ist eine Class zum Datentransport von Lehrer-CLass
 * @author Erdem
 *
 */
public class LehrerDTO {

	private String vorname;
	private String nachname;
	private String email;
	private String handyNummer;
	private int Fach1id;
	private int Fach2id;
	private int Fach3id;

	public LehrerDTO(String vorname, String nachname, String email, String handyNummer, int fach1id, int fach2id,
			int fach3id) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.handyNummer = handyNummer;
		this.Fach1id = fach1id;
		this.Fach2id = fach2id;
		this.Fach3id = fach3id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHandyNummer() {
		return handyNummer;
	}

	public void setHandyNummer(String handyNummer) {
		this.handyNummer = handyNummer;
	}

	public int getFach1id() {
		return Fach1id;
	}

	public void setFach1id(int fach1id) {
		Fach1id = fach1id;
	}

	public int getFach2id() {
		return Fach2id;
	}

	public void setFach2id(int fach2id) {
		Fach2id = fach2id;
	}

	public int getFach3id() {
		return Fach3id;
	}

	public void setFach3id(int fach3id) {
		Fach3id = fach3id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Fach1id, Fach2id, Fach3id, email, handyNummer, nachname, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LehrerDTO)) {
			return false;
		}
		LehrerDTO other = (LehrerDTO) obj;
		return Fach1id == other.Fach1id && Fach2id == other.Fach2id && Fach3id == other.Fach3id
				&& Objects.equals(email, other.email) && Objects.equals(handyNummer, other.handyNummer)
				&& Objects.equals(nachname, other.nachname) && Objects.equals(vorname, other.vorname);
	}

	@Override
	public String toString() {
		return "LehrerDTO [vorname=" + vorname + ", nachname=" + nachname + ", email=" + email + ", handyNummer="
				+ handyNummer + ", Fach1id=" + Fach1id + ", Fach2id=" + Fach2id + ", Fach3id=" + Fach3id + "]";
	}

}