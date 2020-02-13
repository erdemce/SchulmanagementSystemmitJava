package personen;

import java.util.Objects;

/**
 * Person-Class ist super-Class von allen Menschen-Typen
 * 
 * @author Erdem
 *
 */
public class Person {
	protected int id;
	protected String vorname;
	protected String nachname;

	protected Person() {

	}

	/**
	 * Ich brauche diese method weil manchmal nur name und nachname notwendig ist
	 * und Tostring-Method in diese Sitiuation nicht nutzlich ist.
	 * 
	 * @return als String:vorname nachname
	 */
	public String getVornameUndNachname() {
		return vorname + " " + nachname + " ";
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

	/**
	 * keine setId method weil Id, nach dem Erstellung eine Person-Objekt, nie
	 * veraendert werden darf
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nachname, vorname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person other = (Person) obj;
		return id == other.id && Objects.equals(nachname, other.nachname) && Objects.equals(vorname, other.vorname);
	}

	@Override
	public String toString() {
		return id + " " + vorname + " " + nachname +" ";
	}

}
