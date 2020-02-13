package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
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
import tasken.Fach;
import tasken.FachUndLehrer;
import tasken.Note;
import tasken.Notentype;

public class SchuleDAOImplDB implements SchuleDAO {
	String VERBINDUNG = "jdbc:mysql://localhost:3306/schule";
	String USER = "root";
	String PW = "";// in meine Laptop "1357908642", in alfatraining "";

	@Override
	public ElternteildesSchueler getEltern(int gesuchteid) {
		ElternteildesSchueler elt = null;
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM eltern WHERE schuelerId='" + gesuchteid + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("schuelerId");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String email = rs.getString("email");
				String handynummer = rs.getString("handynummer");
				elt = new ElternteildesSchueler(id, vorname, nachname, email, handynummer);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return elt;
	}

	@Override
	public List<ElternteildesSchueler> getAlleEltern() {
		List<ElternteildesSchueler> alleEltern = new ArrayList<ElternteildesSchueler>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT* FROM eltern";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String email = rs.getString("email");
				String handynummer = rs.getString("handynummer");
				ElternteildesSchueler elt = new ElternteildesSchueler(id, vorname, nachname, email, handynummer);
				alleEltern.add(elt);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleEltern;
	}

	@Override
	public void addEltern(ElternteilDesSchuelerDTO elternTransfer, int schuelerId) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO eltern VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schuelerId);
			pstmt.setString(2, elternTransfer.getVorname());
			pstmt.setString(3, elternTransfer.getNachname());
			pstmt.setString(4, elternTransfer.getEmail());
			pstmt.setString(5, elternTransfer.getHandyNummer());
			pstmt.execute();
			System.out.println("Neu Elterns' Informationen wurde Database addiert.");
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public List<Abwesenheit> getAlleAbwesenheitVomSchueler(int gesuchteid) {
		List<Abwesenheit> alleAbwesenheitVomSchueler = new ArrayList<Abwesenheit>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM abwesenheit WHERE schuelerId='" + gesuchteid + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
					int schuelerId = rs.getInt("schuelerId");
					LocalDate datum = rs.getDate("datum").toLocalDate();
					Abwesenheit temp = new Abwesenheit(schuelerId, datum);
					alleAbwesenheitVomSchueler.add(temp);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleAbwesenheitVomSchueler;
	}

	@Override
	public List<Abwesenheit> getAlleAbwesenheit() {
		List<Abwesenheit> alleAbwDaten = new ArrayList<Abwesenheit>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT* FROM abwesenheit";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int schuelerId = rs.getInt("schuelerId");
				LocalDate datum = rs.getDate("datum").toLocalDate();
				Abwesenheit temp = new Abwesenheit(schuelerId, datum);
				alleAbwDaten.add(temp);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleAbwDaten;
	}

	@Override
	public void addAbwesenHeit(Abwesenheit abw) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO abwesenheit VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, abw.getSchuelerId());
			pstmt.setDate(2, Date.valueOf(abw.getDatum()));
			pstmt.execute();
			System.out.println("Neu Abwesenheits' Informationen wurde Database addiert.");
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public Schueler getSchueler(int gesuchteid) {
		Schueler sch = null;
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM schueler WHERE id='" + gesuchteid + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				LocalDate geburtsdatum = rs.getDate("geburtsdatum").toLocalDate();
				int stufe = rs.getInt("stufe");
				String klassenzimmer = rs.getString("klassenzimmer");
				sch = new Schueler(id, vorname, nachname, stufe, klassenzimmer, geburtsdatum, getEltern(id));
			}

		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return sch;
	}

	@Override
	public List<Schueler> getAlleSchueler() {
		List<Schueler> alleSchueler = new ArrayList<Schueler>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT* FROM schueler";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				LocalDate geburtsdatum = rs.getDate("geburtsdatum").toLocalDate();
				int stufe = rs.getInt("stufe");
				String klassenzimmer = rs.getString("klassenzimmer");
				Schueler sch = new Schueler(id, vorname, nachname, stufe, klassenzimmer, geburtsdatum, getEltern(id));
				alleSchueler.add(sch);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleSchueler;
	}

	@Override
	public List<Schueler> getMancheSchueler(int gesuchteStufe, String gesuchteKlassenzimmer) {
		List<Schueler> mancheSchueler = new ArrayList<Schueler>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT* FROM schueler where stufe='" + gesuchteStufe + "' and klassenzimmer='" + gesuchteKlassenzimmer + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
					int id = rs.getInt("id");
					String vorname = rs.getString("vorname");
					String nachname = rs.getString("nachname");
					LocalDate geburtsdatum = rs.getDate("geburtsdatum").toLocalDate();
					int stufe = rs.getInt("stufe");
					String klassenzimmer = rs.getString("klassenzimmer");
					Schueler sch = new Schueler(id, vorname, nachname, stufe, klassenzimmer, geburtsdatum,
							getEltern(id));
					mancheSchueler.add(sch);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return mancheSchueler;
	}

	@Override
	public void addSchueler(SchuelerDTO schuelerTransfer, ElternteilDesSchuelerDTO elternTransfer) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "INSERT INTO schueler VALUES(null,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schuelerTransfer.getVorname());
			pstmt.setString(2, schuelerTransfer.getNachname());
			pstmt.setInt(3, schuelerTransfer.getStufe());
			pstmt.setString(4, schuelerTransfer.getKlassenzimmer());
			pstmt.setDate(5, Date.valueOf(schuelerTransfer.getGeburtsdatum()));
			pstmt.execute();
			addEltern(elternTransfer, getLastSchuelerId());
			System.out.println("Neu Schuelers' Informationen wurde Database addiert.");
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}

	}
	@Override
	public int getLastSchuelerId() {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM schueler ORDER BY id DESC LIMIT 1";
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				int id = rs.getInt("id");
				return id;
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}
		return -1;
	}

	@Override
	public Lehrer getLehrer(int gesuchteid) {
		Lehrer leh = null;
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM lehrer WHERE id='" + gesuchteid + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String email = rs.getString("email");
				String handynummer = rs.getString("handynummer");
				ArrayList<Fach> meineFaecher = new ArrayList<Fach>();
				int fachid1 = rs.getInt("gelehrtefach1");
				if (fachid1 > 0) {
					Fach tempFach = Fach.values()[fachid1 - 1];
					meineFaecher.add(tempFach);
				}
				;
				int fachid2 = rs.getInt("gelehrtefach2");
				if (fachid2 > 0) {
					Fach tempFach = Fach.values()[fachid2 - 1];
					meineFaecher.add(tempFach);
				}
				;
				int fachid3 = rs.getInt("gelehrtefach3");
				if (fachid3 > 0) {
					Fach tempFach = Fach.values()[fachid3 - 1];
					meineFaecher.add(tempFach);
				}
				;
				leh = new Lehrer(id, vorname, nachname, email, handynummer, meineFaecher);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return leh;
	}

	@Override
	public List<Lehrer> getAlleLehrer() {
		List<Lehrer> alleLehrer = new ArrayList<Lehrer>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM lehrer";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String email = rs.getString("email");
				String handynummer = rs.getString("handynummer");
				ArrayList<Fach> meineFaecher = new ArrayList<Fach>();
				int fachid1 = rs.getInt("gelehrtefach1");
				if (fachid1 > 0) {
					Fach tempFach = Fach.values()[fachid1 - 1];
					meineFaecher.add(tempFach);
				}
				;
				int fachid2 = rs.getInt("gelehrtefach2");
				if (fachid2 > 0) {
					Fach tempFach = Fach.values()[fachid2 - 1];
					meineFaecher.add(tempFach);
				}
				;
				int fachid3 = rs.getInt("gelehrtefach3");
				if (fachid3 > 0) {
					Fach tempFach = Fach.values()[fachid3 - 1];
					meineFaecher.add(tempFach);
				}
				Lehrer leh = new Lehrer(id, vorname, nachname, email, handynummer, meineFaecher);
				alleLehrer.add(leh);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}
		return alleLehrer;
	}

	@Override
	public void addLehrer(LehrerDTO lehrerTransfer) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "INSERT INTO lehrer VALUES(null,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lehrerTransfer.getVorname());
			pstmt.setString(2, lehrerTransfer.getNachname());
			pstmt.setString(3, lehrerTransfer.getEmail());
			pstmt.setString(4, lehrerTransfer.getHandyNummer());
			if (lehrerTransfer.getFach1id() > 0) {
				pstmt.setInt(5, lehrerTransfer.getFach1id());
			} else {
				pstmt.setNull(5, Types.INTEGER);
			}
			if (lehrerTransfer.getFach2id() > 0) {
				pstmt.setInt(6, lehrerTransfer.getFach2id());
			} else {
				pstmt.setNull(6, Types.INTEGER);
			}
			if (lehrerTransfer.getFach3id() > 0) {
				pstmt.setInt(7, lehrerTransfer.getFach3id());
			} else {
				pstmt.setNull(7, Types.INTEGER);
			}
			pstmt.execute();
			System.out.println("Neu Lehrers' Informationen wurde Database addiert.");
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public List<FachUndLehrer> getAlleGelerntenFachdesSchueler(int gesuchteSchuelerId) {
		List<FachUndLehrer> gelernteFaecherdesSchueler = new ArrayList<FachUndLehrer>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "SELECT* FROM fachundlehrer where schuelerId='" + gesuchteSchuelerId + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
					int id = rs.getInt("id");
					int schuelerid = rs.getInt("schuelerId");
					int fachId = rs.getInt("fachId");
					int lehrerId = rs.getInt("lehrerId");
					FachUndLehrer lernendeFach = new FachUndLehrer(id, getSchueler(schuelerid),
							Fach.values()[fachId - 1], getLehrer(lehrerId));
					gelernteFaecherdesSchueler.add(lernendeFach);
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return gelernteFaecherdesSchueler;
	}

	@Override
	public void addFachUndLehrer(FachUndLehrerDTO fachUndLehrer) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "INSERT INTO fachundlehrer VALUES(null,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fachUndLehrer.getLernendeSchuelerId());
			pstmt.setInt(2, fachUndLehrer.getGelernteFachId());
			pstmt.setInt(3, fachUndLehrer.getLehrerIdVomFach());
			pstmt.execute();
			System.out.println("Informationen ueber Neue gelernten Fach eines Schueler wurde Database addiert.");

		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzdem:");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void addFachUndLehrerfuerEinerKlassenZimmer(ArrayList<Schueler> klassenzimmer, int fachId, Lehrer lehr) {
		for (int i = 0; i < klassenzimmer.size(); i++) {
			FachUndLehrerDTO fachundlehrerTemp = new FachUndLehrerDTO(klassenzimmer.get(i).getId(), fachId,
					lehr.getId());
			addFachUndLehrer(fachundlehrerTemp);
		}
	}

	@Override
	public List<Note> getAlleNotendesGelerntenFachs(FachUndLehrer fachUndLehrer) {
		List<Note> alleNotendesGelerntenFachs = new ArrayList<Note>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "SELECT* FROM noten";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {

				if (rs.getInt("fachundlehrerId") == fachUndLehrer.getId()) {
					int typId = rs.getInt("typId");
					int note = rs.getInt("note");
					Note bewertungen = new Note(fachUndLehrer, Notentype.values()[typId], note);
					alleNotendesGelerntenFachs.add(bewertungen);
				}
			}

		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleNotendesGelerntenFachs;
	}

	@Override
	public void addNote(NoteDTO bewertung) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {
			String sql = "INSERT INTO noten VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bewertung.getNotegehoertdesFachundLehrerId());
			pstmt.setInt(2, bewertung.getNotetyp());
			pstmt.setInt(3, bewertung.getNote());
			pstmt.execute();
			System.out.println("Alle Daten geschrieben");

		} catch (SQLException exc) {
			System.out.println("Informationen �ber neuen Note wurde Database addiert.");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public int findFachUndLehrerId(int lehrerId, int fachId, int schuelerId) {
		int meinId = -1;
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "SELECT * FROM fachundlehrer WHERE lehrerId='" + lehrerId + "' and fachId='" + fachId
					+ "' and schuelerId='" + schuelerId + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				meinId = rs.getInt("id");
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return meinId;
	}

	@Override
	public List<Schueler> getAlleSchuelerVomLehrerInBestimmtenFach(int lehrerId, int fachId) {

		List<Schueler> alleSchuelerVomLehrer = new ArrayList<Schueler>();
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {

			String sql = "SELECT * FROM fachundlehrer WHERE lehrerId='" + lehrerId + "' and fachId='" + fachId + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int schuelerId = rs.getInt("schuelerId");
				alleSchuelerVomLehrer.add(getSchueler(schuelerId));
			}
		} catch (SQLException exc) {
			System.out.println("Connection ist geschlossen, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(exc.getMessage());
		}
		return alleSchuelerVomLehrer;
	}
}
