package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String USER = "student";
	private static final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sqlQuery = "SELECT * FROM film WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlQuery);) {
			stmt.setInt(1, filmId);
			try (ResultSet fR = stmt.executeQuery();) {
				while (fR.next()) {
					film = new Film(fR.getInt("id"), fR.getString("title"), fR.getString("description"),
							fR.getInt("release_year"), fR.getInt("language_id"), fR.getInt("rental_duration"),
							fR.getDouble("rental_rate"), fR.getInt("length"), fR.getDouble("replacement_cost"),
							fR.getString("rating"), fR.getString("special_features"), findActorsByFilmId(filmId));

				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return film;
	}
	@Override
	public List<Film> findFilmsByText(String searchText) {
		List<Film> filmResults = new ArrayList<>();
		
		String sqlQuery = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ? ";
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlQuery);) {
			stmt.setString(1, ("%"+searchText+"%"));
			stmt.setString(2, ("%"+searchText+"%"));
			
			System.out.println(stmt);
			try (ResultSet fR = stmt.executeQuery();) {
				while (fR.next()) {
					Film film = new Film(fR.getInt("id"), fR.getString("title"), fR.getString("description"),
							fR.getInt("release_year"), fR.getInt("language_id"), fR.getInt("rental_duration"),
							fR.getDouble("rental_rate"), fR.getInt("length"), fR.getDouble("replacement_cost"),
							fR.getString("rating"), fR.getString("special_features"), findActorsByFilmId(fR.getInt("id")));
					filmResults.add(film);
					
				}
			}
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return filmResults;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sqlQuery = "SELECT * FROM actor WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlQuery);) {
			stmt.setInt(1, actorId);
			try (ResultSet rA = stmt.executeQuery();) {
				while (rA.next()) {
					actor = new Actor(rA.getInt("id"), rA.getString("first_name"), rA.getString("last_name"));

				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> castList = new ArrayList<>();
		String sqlQuery = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor"
				+ "  JOIN film_actor"
				+ "    ON actor.id = film_actor.actor_id"
				+ "  JOIN film"
				+ "    ON film_actor.film_id = film.id"
				+ "    WHERE film.id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sqlQuery);) {
			stmt.setInt(1, filmId);
			try (ResultSet rCast = stmt.executeQuery();) {
				while (rCast.next()) {
					Actor actor = new Actor(rCast.getInt("id"), rCast.getString("first_name"),
							rCast.getString("last_name"));

					castList.add(actor);

				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return castList;
	}

}
