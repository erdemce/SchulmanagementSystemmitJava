package personen;

import java.util.ArrayList;
import java.util.Objects;

import tasken.Fach;

/**
 * Lehrer-Class ist ein Sub-Class von Person-Class.
 * 
 * @author Erdem
 *
 */
public class Lehrer extends Person{
	private String email;
	private String handyNummer;
	
	/**
	 * jede Lehrer lehrt minimal ein maximal drei Facher.
	 * meineFaecher ist ArrayList von dieser Faecher. 
	 */
	private ArrayList<Fach> meineFaecher;

	public Lehrer(int id, String vorname, String nachname,String email, String handyNummer, ArrayList<Fach> meineFaecher) {
		super();
		this.id=id;
		this.vorname=vorname;
		this.nachname=nachname;
		this.email = email;
		this.handyNummer = handyNummer;
		this.meineFaecher=meineFaecher;
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

	public ArrayList<Fach> getMeineFaecher() {
		return meineFaecher;
	}

	public void setMeineFaecher(ArrayList<Fach> meineFaecher) {
		this.meineFaecher = meineFaecher;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email, handyNummer, meineFaecher);
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
		if (!(obj instanceof Lehrer)) {
			return false;
		}
		Lehrer other = (Lehrer) obj;
		return Objects.equals(email, other.email) && Objects.equals(handyNummer, other.handyNummer)
				&& Objects.equals(meineFaecher, other.meineFaecher);
	}

	@Override
	public String toString() {
		return "" + id + " " + vorname + " " + nachname + " " + email + " " + handyNummer;
	}
}
