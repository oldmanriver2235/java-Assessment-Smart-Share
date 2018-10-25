package com.ftd.smartshare.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ftd.smartshare.server.entity.File;

public class FileDao {
	    private static final String URL = "jdbc:postgresql://localhost:5432/postgres/smartshare";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "bondstone";

	public FileDao() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load the postgreSQL Driver.");
		}

	}


	public boolean save(File entity) {
		try (Connection connection = DriverManager.getConnection("pom.xml");) {
			// Create and populate a prepared statement
			String sql = "INSERT INTO public.user (file_name, file) VALUES (?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getFilename());
			preparedStatement.setBytes(2, entity.getFile());

			// Execute the prepared statement to save the user to the database
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			
		}
		return false;
	}


	public static File getByName(String filename) {
		File file = null;
		try (Connection connection = DriverManager.getConnection("pom.xml");) {
			// Create and populate a prepared statement
			String sql = "SELECT * FROM smartshare.files WHERE file_name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, filename);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				file = new File(rs.getInt("id"), rs.getString("file_name"), rs.getBytes("file"));
			}
		} catch (SQLException e) {
			
		}
		return file;
	}
	
	
}
