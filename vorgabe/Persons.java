/*
 * Praktikum Datenbanken/Datenmanagement
 * Lehrstuhl Datenbank- und Informationssysteme
 * BTU Cottbus - Senftenberg
 *
 */

import java.util.Scanner;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class Persons {
	private Connection connection; // Verbindung zum Datenbanksystem

	public Persons(Connection connection) {
		this.connection = connection;
	}

	private void showQueryResult(ResultSet result) throws SQLException {
		// Tabellenkopf ausgeben
		ResultSetMetaData metaData = result.getMetaData();
		int numColumns = metaData.getColumnCount(); // Anzahl der Tabellenspalten

		for (int i = 1; i <= numColumns; i++) // Tabellenspalten ausgeben
		{
			String name = metaData.getColumnName(i);
			System.out.format("%-20s ", name);
		}
		System.out.println("\n================================================================================");

		// Tabelleninhalt ausgeben
		while (result.next()) // Tabelleninhalt zeilenweise ausgeben
		{
			for (int i = 1; i <= numColumns; ++i) {
				String value = result.getString(i);
				System.out.format("%-20s ", value);
			}
			System.out.println();
		}
	}

	public void createPersonsTable() {
		// Erstellt die Tabelle 'Person'.

		String sql = "create table PERSON ( pid number primary key, vorname varchar(20), nachname varchar(20))";
		// Befehl ausfuehren
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Tabelle erfolgreich erstellt!");
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void showAllPersons() {
		// Listet alle Personen mit allen Attributen auf.
		String sql = "select * from PERSON";
		// Befehl ausfuehren
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			showQueryResult(result);
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void findPerson() {
		// Gibt alle Personen mit bestimmtem Vornamen aus.
		String sql = "SELECT * FROM Person WHERE vorname = ?";
		String vorname = "";

		// Eingaben sammeln
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		try {
			System.out.print("Gesuchten Vornamen eingeben: ");
			vorname = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return;
		}

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vorname);
			ResultSet result = statement.executeQuery();
			showQueryResult(result);
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void addPerson() {
		// Fuegt eine neue Person hinzu.
		// Die Attribute PID, Vorname, Nachname und Lieblingscocktail werden vom Nutzer
		// eingegeben.
		String sql = "insert into PERSON values (?, ?, ?)";
		int pid;
		String nachname, vorname;

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		try {
			System.out.print("Geben Sie PID ein: ");
			pid = Integer.parseInt(scanner.nextLine());
			System.out.print("Geben Sie Vornamen ein: ");
			vorname = scanner.nextLine().trim();
			System.out.print("Geben Sie Nachnamen ein: ");
			nachname = scanner.nextLine().trim();
			System.out.println();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return;
		}

		// Ausführen
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, String.valueOf(pid));
			statement.setString(2, vorname);
			statement.setString(3, nachname);
			statement.executeQuery();
			System.out.println(vorname + " " + nachname + " erfolgreich hinzugefügt!");
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void deleteAllPersons() {
		String sql = "DELETE FROM Person";

		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Tabelle inhalte erfolgreich gelöscht!");
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void deletePerson() {
		// Loescht eine Person mit bestimmtem Vornamen.
		String sql = "DELETE FROM Person WHERE vorname = ?";
		String vorname = "";

		// Eingaben sammeln
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		try {
			System.out.print("Geben Sie einen Vorname ein: ");
			vorname = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return;
		}

		// Ausführen
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vorname);
			statement.executeQuery();
			System.out.println(vorname + " gelöscht!");
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}

	public void dropPersonsTable() {
		// Loescht die Tabelle 'Person'.
		String sql = "DROP TABLE Person cascade constraints";

		// Befehl ausfuehren
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("Tabelle erfolgreich entfernt!");
		} catch (SQLException e) // Fehler bei der Ausfuehrung
		{
			System.out.println("ERROR: " + e.getMessage());
			return;
		}
	}
}
