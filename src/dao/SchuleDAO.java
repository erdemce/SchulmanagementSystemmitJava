package dao;

import java.util.ArrayList;
import java.util.List;

import dto.ElternteilDesSchuelerDTO;
import dto.FachUndLehrerDTO;
import dto.LehrerDTO;
import dto.NoteDTO;
import dto.SchuelerDTO;
import personen.ElternteildesSchueler;
import personen.Lehrer;
import personen.Schueler;
import tasken.Abwesenheit;
import tasken.FachUndLehrer;
import tasken.Note;

/**
 * SchuelerDAO interface wird benutzt um im unsere Database: neu Informationen
 * zu addieren, eine Information zu aktualiesieren oder zu entfernen, eine
 * Information zu lesen (oder bekommen).
 * 
 * @author Erdem
 *
 */
public interface SchuleDAO {

	/**
	 * Man soll id von gewünschte Eltern-Objekt geben. Mit getEltern-Method kann
	 * man Eltern-Objekt mit angegebenen Id bekommen
	 * 
	 * @param id: id von gewuenschte Eltern Objekt
	 * @return :gewünschte Eltern-Objekt
	 */
	public ElternteildesSchueler getEltern(int id);

	/**
	 * Mit getAlleEltern-Method kann man List aller Eltern-Objekten bekommen.
	 * 
	 * @return :List aller Eltern-Objekten
	 */
	public List<ElternteildesSchueler> getAlleEltern();

	/**
	 * Mit addEltern-Method kann man eine neuen Zeile in Eltern-Table in Database
	 * hinzufügen.
	 * 
	 * @param elternTransfer:neu ElternDTO-Objekt hinzuzufügen
	 * @param schuelerId:Id      von Schueler dieses Eltern-Objekts
	 */
	public void addEltern(ElternteilDesSchuelerDTO elternTransfer, int schuelerId);

	/**
	 * Man kann List der Abwesenheit-Objekten einer Schueler-Objekt bekommen.
	 * 
	 * @param id:Id von gewünschte Schueler-Objekt
	 * @return :List der Abwesenheit-Objekten
	 */
	public List<Abwesenheit> getAlleAbwesenheitVomSchueler(int id);

	/**
	 * Man kann List aller Abwesenheit-Objekten bekommen.
	 * 
	 * @return :List aller Abwesenheit-Objekten
	 */
	public List<Abwesenheit> getAlleAbwesenheit();

	/**
	 * man kann eine neuen Zeile in Abwesenheit-Table in Database hinzufügen.
	 * 
	 * @param abw:Abwesenheit-Objekt
	 */
	public void addAbwesenHeit(Abwesenheit abw);

	/**
	 * Man kann Schueler-Objekt mit angegebene Id bekommen
	 * 
	 * @param id:Id vom gewünschten Schueler-Objekt
	 * @return :Schueler-Objekt
	 */
	public Schueler getSchueler(int id);

	/**
	 * Man kann List aller Schueler-Objekten bekommen.
	 * 
	 * @return List aller Schueler-Objekten
	 */
	public List<Schueler> getAlleSchueler();

	/**
	 * Man kann List der Schueler-Objekten (die in gewünschte Klassenzimmer und
	 * Stufe sind) bekommen.
	 * 
	 * @param stufe:         gewünschte Stufe
	 * @param Klassenzimmer: gewünschte Klassenzimmer
	 * @return :List der Schueler-Objekten mit gewünschte Stufe und Klassenzimmer
	 */
	public List<Schueler> getMancheSchueler(int stufe, String Klassenzimmer);

	/**
	 * Man kann eine neuen Zeile in Schueler-Table und eine neuen Zeile in
	 * Eltern-Table in Database hinzufügen.
	 * 
	 * @param schuelerTransfer:SchuelerDTO-Objekt
	 * @param elternTransfer:                     ElternDTO-Objekt
	 */
	public void addSchueler(SchuelerDTO schuelerTransfer, ElternteilDesSchuelerDTO elternTransfer);

	/**
	 * Man kann Lehrer-Objekt mit angegebenem Id bekommen
	 * 
	 * @param id:id vom gewuenschten Lehrer-Objekt
	 * @return :Lehrer-Objekt mit angegebenem Id
	 */
	public Lehrer getLehrer(int id);

	/**
	 * Man kann List aller Lehrer-Objekten bekommen.
	 * 
	 * @return :aller Lehrer-Objekten
	 */
	public List<Lehrer> getAlleLehrer();

	/**
	 * Man kann eine neuen Zeile in Lehrer-Table in Database hinzufügen.
	 * 
	 * @param lehrerTransfer: LehrerDTO-Objekt
	 */
	public void addLehrer(LehrerDTO lehrerTransfer);

	/**
	 * Man kann List bestimmten FachUndLehrer-Objekten (deren schuelerId mit der
	 * angegebenen ID identisch ist) bekommen.
	 * 
	 * @param schuelerId:id von gewünschte Schueler
	 * @return :List bestimmten FachUndLehrer-Objekten
	 */
	public List<FachUndLehrer> getAlleGelerntenFachdesSchueler(int schuelerId);

	/**
	 * Man kann eine neuen Zeile in fachundlehrer-Table in Database hinzufügen.
	 * 
	 * @param fachUndLehrer:FachUndLehrerDTO-Objekt
	 */
	public void addFachUndLehrer(FachUndLehrerDTO fachUndLehrer);

	/**
	 * Man kann eine neuen Zeile für je gelernten Schueler in Arraylist in
	 * fachundlehrer-Table in Database hinzufügen.
	 * 
	 * @param klassenzimmer: List von gelernte SchuelerInnen
	 * @param fachId:        Id des gelehrten Faches.
	 * @param lehr:          Lehrer dieser Fach
	 */
	public void addFachUndLehrerfuerEinerKlassenZimmer(ArrayList<Schueler> klassenzimmer, int fachId, Lehrer lehr);

	/**
	 * Man kann List der Schueler-Objekten (die dieser Fach von diesem Lehrer
	 * lernen) bekommen.
	 * 
	 * @param lehrerId: id vom Lehrer dieser Fach
	 * @param fachId:   Id des gelehrten Faches.
	 * @return :List von gelernte SchuelerInnen
	 */
	public List<Schueler> getAlleSchuelerVomLehrerInBestimmtenFach(int lehrerId, int fachId);

	/**
	 * Man kann List aller Noten fuer gewuenschten FachUndLehrer-Objekt bekommen.
	 * 
	 * @param fachUndLehrer: gewuenschte FachUndLehrer-Objekt
	 * @return :List aller Noten fuer gewuenschte FachUndLehrer-Objekt
	 */
	public List<Note> getAlleNotendesGelerntenFachs(FachUndLehrer fachUndLehrer);

	/**
	 * Man kann eine neuen Zeile in noten-Table in Database hinzufügen.
	 * 
	 * @param bewertung: NoteDTO-Objekt
	 */
	public void addNote(NoteDTO bewertung);

	/**
	 * Man kann id vom relevanten fachUndLehrer-Objekte als int bekommen.
	 * 
	 * @param lehrerId:   Lehrer-Id
	 * @param fachId:     Fach-Id
	 * @param schuelerId: Schueler-Id
	 * @return int id vom relevanten fachUndLehrer-Objekte
	 */
	public int findFachUndLehrerId(int lehrerId, int fachId, int schuelerId);

	/**
	 * um die Elternteil Des Schueler und Schueler gleiche id zu haben brauche ich
	 * dieser Method wahrend Erstellen Elternteil Des Schueler
	 * 
	 * @return last erstellene Schuelers' Id
	 */
	public int getLastSchuelerId();
}
