package fr.exos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestJdbc {
	
	public static void main(String[] args) throws Exception{
		ArrayList<Article> articles = new ArrayList<Article>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mariadb://localhost:3308/shop";
		String login = "root";
		String password = "Bi3rs077ZBGSv4lM98w8";
		
		try(Connection connection = DriverManager.getConnection(url, login, password)){
			String strSql = "select * from t_articles";
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(strSql)){
					while(resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}
				}
			}
			
			for(Article a : articles)
				System.out.println(a.getId() + " - " + a.getDescription() + " - " + a.getBrand() + " - "  + a.getPrice());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
