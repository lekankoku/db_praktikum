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
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class Persons
{
	private Connection connection; // Verbindung zum Datenbanksystem

	public Persons( Connection connection )
	{
		this.connection = connection;
	}

	private void showQueryResult( ResultSet result ) throws SQLException
	{
		// Tabellenkopf ausgeben
		ResultSetMetaData metaData = result.getMetaData();
		int numColumns = metaData.getColumnCount(); // Anzahl der Tabellenspalten

		for( int i = 1; i <= numColumns; i++ ) // Tabellenspalten ausgeben
		{
			String name = metaData.getColumnName( i );
			System.out.format( "%-20s ", name );
		}
		System.out.println( "\n================================================================================" );

		// Tabelleninhalt ausgeben
		while( result.next() ) // Tabelleninhalt zeilenweise ausgeben
		{
			for( int i = 1; i <= numColumns; ++i )
			{
				String value = result.getString( i );
				System.out.format( "%-20s ", value );
			}
			System.out.println();
		}
	}

	public void createPersonsTable()
	{
		// Erstellt die Tabelle 'Person'.
	}

	public void showAllPersons()
	{
		// Listet alle Personen mit allen Attributen auf.
	}

	public void findPerson()
	{
		// Gibt alle Personen mit bestimmtem Vornamen aus.
		String sql = "SELECT * FROM Person WHERE vorname = ?";
		String name = "";

		// Eingaben sammeln
		Scanner scanner = new Scanner( new InputStreamReader( System.in ) );
		try
		{
			System.out.print( "Gesuchten Vornamen eingeben: " );
			name = scanner.nextLine();
		}
		catch( Exception e )
		{
			System.out.println( "Fehlerhafte Eingabe!" );
			return;
		}

		try
		{
			PreparedStatement statement = connection.prepareStatement( sql );
			statement.setString( 1, name );
			ResultSet result = statement.executeQuery();
			showQueryResult( result );
		}
		catch( SQLException e ) // Fehler bei der Ausfuehrung
		{
			System.out.println( "ERROR: " + e.getMessage() );
			return;
		}
	}

	public void addPerson()
	{
		// Fuegt eine neue Person hinzu.
		// Die Attribute PID, Vorname, Nachname und Lieblingscocktail werden vom Nutzer eingegeben.
	}

	public void deleteAllPersons()
	{
		// Loescht alle Personen.
	}

	public void deletePerson()
	{
		// Loescht eine Person mit bestimmtem Vornamen.
	}

	public void dropPersonsTable()
	{
		// Loescht die Tabelle 'Person'.
		String sql = "DROP TABLE Person";

		// Befehl ausfuehren
		try
		{
			Statement statement = connection.createStatement();
			statement.execute( sql );
			System.out.println( "Tabelle erfolgreich entfernt!" );
		}
		catch( SQLException e ) // Fehler bei der Ausfuehrung
		{
			System.out.println( "ERROR: " + e.getMessage() );
			return;
		}
	}
}
