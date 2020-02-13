package dto;
/**
 * FachUndLehrerDTO-Class ist eine Class zum Datentransport von FachUndLehrer-CLass
 * @author Erdem
 *
 */
public class FachUndLehrerDTO {
	private int lernendeSchuelerId;
	private int gelernteFachId;
	private int lehrerIdVomFach;
	
	public FachUndLehrerDTO(int lernendeSchuelerID, int gelernteFachId, int lehrerIdVomFach) {
		this.lernendeSchuelerId = lernendeSchuelerID;
		this.gelernteFachId = gelernteFachId;
		this.lehrerIdVomFach = lehrerIdVomFach;
	}
	
	public int getLernendeSchuelerId() {
		return lernendeSchuelerId;
	}
	public int getGelernteFachId() {
		return gelernteFachId;
	}
	public int getLehrerIdVomFach() {
		return lehrerIdVomFach;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gelernteFachId;
		result = prime * result + lehrerIdVomFach;
		result = prime * result + lernendeSchuelerId;
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
		FachUndLehrerDTO other = (FachUndLehrerDTO) obj;
		if (gelernteFachId != other.gelernteFachId)
			return false;
		if (lehrerIdVomFach != other.lehrerIdVomFach)
			return false;
		if (lernendeSchuelerId != other.lernendeSchuelerId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FachUndLehrerDTO [lernendeSchuelerId=" + lernendeSchuelerId + ", gelernteFachId=" + gelernteFachId
				+ ", lehrerIdVomFach=" + lehrerIdVomFach + "]";
	}	
}
