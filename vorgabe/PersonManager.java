/*
 * Praktikum Datenbanken/Datenmanagement
 * Lehrstuhl Datenbank- und Informationssysteme
 * BTU Cottbus - Senftenberg
 *
 * @author Marcel Zierenberg & Tobias Killer
 */

import java.util.Scanner;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersonManager {

	private final String DB_URL = "jdbc:oracle:thin:@coco.informatik.tu-cottbus.de:1521:dbis"; // URL des
	// Datenbanksystems
	private final String DB_USERNAME = "STUD_DB_SS_2021_7"; // Nutzername
	private final String DB_PASSWORD = "bL2KaBJa"; // Passwort
	private final int LOGIN_TIMEOUT = 10000; // maximale Zeit fuer Verbindungsaufbau in ms

	private Connection connection = null; // Verbindung zum Datenbanksystem

	public static void main(String[] args) {
		PersonManager mngr = new PersonManager();
		mngr.start();
	}

	public void start() {
		if (!initDBConnection()) // Kein Verbindungsaufbau moeglich gewesen?
		{
			// beende Programm mit Fehlercode
			System.exit(1);
		}

		boolean done = false;
		while (!done) {
			int task = selectTask();

			if (task > 0 && task < 8) // eine Aufgabe ausgewaehlt?
			{
				// ausgewaehlte Aufgabe ausfuehren
				startTask(task);
			} else if (task == 8) // soll Programm beendet werden?
			{
				// Programm beenden
				done = true;
			}
		}

		closeDBConnection();
	}

	private boolean initDBConnection() {
		// Treiber laden
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle-Treiber laden
		} catch (ClassNotFoundException e) // Oracle-Treiber nicht gefunden
		{
			// Betriebssystem bestimmen
			final String OS = System.getProperty("os.name").toLowerCase();

			// je nach Betriebssystem unterscheidet sich die korrekte Angabe des Classpath
			// etwas
			String startOption = "-cp ojdbc14-1.jar:.";
			if (OS.indexOf("win") >= 0) {
				startOption = "-cp ojdbc14-1.jar;.";
			}

			// Fehlerausgabe und Hinweis zur Fehlerbehebung
			System.out.println(
					"Oracle-Treiber wurde nicht gefunden! Wurde Startoption '" + startOption + "' korrekt gesetzt? ");
			return false;
		}

		// Verbindung zum Datenbank-Server herstellen
		try {
			DriverManager.setLoginTimeout(LOGIN_TIMEOUT);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); // Verbindungsaufbau
		} catch (SQLException e) // Verbindungsaufbau nicht erfolgreich
		{
			System.out.println("Verbindungsaufbau nicht moeglich! Besteht eine (VPN-)Verbindung zum Uninetzwerk?");
			return false;
		}

		return true; // Verbindungsaufbau erfolgreich
	}

	private boolean closeDBConnection() {
		// bestehende Verbindung abbauen
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Verbindungsabbau nicht moeglich! Besteht eine (VPN-)Verbindung zum Uninetzwerk?");
			return false;
		}

		return true;
	}

	private int selectTask() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));

		// Menue ausgeben
		System.out.println("Personenverwaltung");
		System.out.println("==================");

		System.out.println("1. Personentabelle erstellen");
		System.out.println("2. Person hinzufuegen");
		System.out.println("3. Alle Personen anzeigen");
		System.out.println("4. Person anhand Vorname suchen");
		System.out.println("5. Alle Personen loeschen");
		System.out.println("6. Person anhand Vorname loeschen");
		System.out.println("7. Personentabelle loeschen");
		System.out.println("8. Programm beenden");
		System.out.println();
		System.out.print("> ");

		// Eingabe einlesen
		int task = 0;
		try {
			task = Integer.parseInt(scanner.nextLine()); // Nutzereingabe einlesen
		} catch (Exception e) {
			System.out.println("Fehlerhafte Eingabe!");
			// gibt 0 zurueck
		}

		return task;
	}

	private void startTask(int task) {
		System.out.println("\n--------------------------------------------------------------------------------\n");

		Persons persons = new Persons(connection);

		switch (task) {
			case 1:
				persons.createPersonsTable();
				break;
			case 2:
				persons.addPerson();
				break;
			case 3:
				persons.showAllPersons();
				break;
			case 4:
				persons.findPerson();
				break;
			case 5:
				persons.deleteAllPersons();
				break;
			case 6:
				persons.deletePerson();
				break;
			case 7:
				persons.dropPersonsTable();
				break;
			default:
				break;
		}

		System.out.println("\n--------------------------------------------------------------------------------\n");
	}
}
