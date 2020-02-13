package dto;

import java.util.Objects;

/**
 * ElternteilDesSchuelerDTO-Class ist eine Class zum Datentransport von ElternteilDesSchueler-CLass
 * @author Erdem
 *
 */
public class ElternteilDesSchuelerDTO {
	private String vorname;
	private String nachname;
	private String email;
	private String handyNummer;
	
	public ElternteilDesSchuelerDTO(String vorname, String nachname, String email, String handyNummer) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.handyNummer = handyNummer;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(email, handyNummer, nachname, vorname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ElternteilDesSchuelerDTO)) {
			return false;
		}
		ElternteilDesSchuelerDTO other = (ElternteilDesSchuelerDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(handyNummer, other.handyNummer)
				&& Objects.equals(nachname, other.nachname) && Objects.equals(vorname, other.vorname);
	}
	@Override
	public String toString() {
		return "ElternteilDesSchuelerDTO [vorname=" + vorname + ", nachname=" + nachname + ", email=" + email
				+ ", handyNummer=" + handyNummer + "]";
	}
	
	
}
