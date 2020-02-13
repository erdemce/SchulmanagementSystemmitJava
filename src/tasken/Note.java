package tasken;

/**
 * Note-Objekt besteht aus ein FachUndLehrer-Objekt, Notentype und note.
 * @author Erdem
 *
 */
public class Note {
	private FachUndLehrer notegehoertdesFachundLehrer;
	private Notentype typ;
	private int note;

	public Note(FachUndLehrer notegehoertdesFachundLehrer, Notentype typ, int note) {
		super();
		this.notegehoertdesFachundLehrer = notegehoertdesFachundLehrer;
		this.typ = typ;
		this.note = note;
		
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Notentype getTyp() {
		return typ;
	}

	public FachUndLehrer getNotegehoertdesFachundLehrer() {
		return notegehoertdesFachundLehrer;
	}

	@Override
	public String toString() {
		return notegehoertdesFachundLehrer + " " + typ + "-Note ist" + note + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + note;
		result = prime * result + ((notegehoertdesFachundLehrer == null) ? 0 : notegehoertdesFachundLehrer.hashCode());
		result = prime * result + ((typ == null) ? 0 : typ.hashCode());
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
		Note other = (Note) obj;
		if (note != other.note)
			return false;
		if (notegehoertdesFachundLehrer == null) {
			if (other.notegehoertdesFachundLehrer != null)
				return false;
		} else if (!notegehoertdesFachundLehrer.equals(other.notegehoertdesFachundLehrer))
			return false;
		if (typ != other.typ)
			return false;
		return true;
	}

}
