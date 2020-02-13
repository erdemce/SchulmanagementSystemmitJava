package personen;

import java.util.Objects;

/**
 * ElternteildesSchueler-Class ist ein Sub-Class von Person-Class.
 * (
 * @author Erdem
 *
 */
public class ElternteildesSchueler extends Person{
	
	private String email;
	private String handyNummer;
	
	public ElternteildesSchueler(int id,String name, String nachname, String email, String handyNummer) {
		super();
		this.id=id;
		this.vorname = name;
		this.nachname = nachname;
		this.email = email;
		this.handyNummer = handyNummer;
		
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, handyNummer);
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
		if (!(obj instanceof ElternteildesSchueler)) {
			return false;
		}
		ElternteildesSchueler other = (ElternteildesSchueler) obj;
		return Objects.equals(email, other.email) && Objects.equals(handyNummer, other.handyNummer);
	}

	@Override
	public String toString() {
		return id + " " + vorname + " " + nachname + " "
				+ email + " " + handyNummer;
	}	
}
