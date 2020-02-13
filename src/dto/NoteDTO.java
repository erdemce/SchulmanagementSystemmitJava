package dto;


/**
 * NoteDTO-Class ist eine Class zum Datentransport von Note-CLass
 * @author Erdem
 *
 */
public class NoteDTO {
	private int notegehoertdesFachundLehrerId;
	private int notetyp;
	private int note;

	public NoteDTO(int notegehoertdesFachundLehrerId, int notetyp, int note) {
		this.notegehoertdesFachundLehrerId = notegehoertdesFachundLehrerId;
		this.notetyp = notetyp;
		this.note = note;
	}

	public int getNotetyp() {
		return notetyp;
	}

	public void setNotetyp(int notetyp) {
		this.notetyp = notetyp;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getNotegehoertdesFachundLehrerId() {
		return notegehoertdesFachundLehrerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + note;
		result = prime * result + notegehoertdesFachundLehrerId;
		result = prime * result + notetyp;
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
		NoteDTO other = (NoteDTO) obj;
		if (note != other.note)
			return false;
		if (notegehoertdesFachundLehrerId != other.notegehoertdesFachundLehrerId)
			return false;
		if (notetyp != other.notetyp)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NoteDTO [notegehoertdesFachundLehrerId=" + notegehoertdesFachundLehrerId + ", notetyp=" + notetyp
				+ ", note=" + note + "]";
	}

}
