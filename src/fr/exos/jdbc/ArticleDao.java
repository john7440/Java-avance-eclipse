package fr.exos.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
	private static final String URL = "jdbc:mariadb://localhost:3308/shop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Bi3rs077ZBGSv4lM98w8";
    
    // Le create
    public void create(Article article) throws SQLException{
    	String sql = "insert into t_articles (Description, Brand, UnitaryPrice) values(?,?,?)";
    	
    	try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    				
    				statement.setString(1, article.getDescription());
    				statement.setString(2, article.getBrand());
    	            statement.setDouble(3, article.getPrice());
    	            
    	            statement.executeUpdate();
    	            
    	            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
    	                if (generatedKeys.next()) {
    	                    article.setId(generatedKeys.getInt(1));
    	                }
    	            }
    	            
    			}
    		}
    
	 // read d'un seul article
	    public Article findById(int id) throws SQLException {
	        String sql = "select * from t_articles where IdArticle = ?";
	        Article article = null;
	        
	        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            
	            statement.setInt(1, id);
	            
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    article = new Article(
	                        resultSet.getInt("IdArticle"),
	                        resultSet.getString("description"),
	                        resultSet.getString("brand"),
	                        resultSet.getDouble("UnitaryPrice")
	                    );
	                }
	            }
	        }
	        
	        return article;
	    }
	    
	    //read all
	    public List<Article> findAll() throws SQLException {
	        String sql = "select * from t_articles";
	        List<Article> articles = new ArrayList<>();
	        
	        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	            
	            while (resultSet.next()) {
	                Article article = new Article(
	                    resultSet.getInt("IdArticle"),
	                    resultSet.getString("description"),
	                    resultSet.getString("brand"),
	                    resultSet.getDouble("UnitaryPrice")
	                );
	                articles.add(article);
	            }
	        }
	        
	        return articles;
	    }
    
	   // delete
	   public void delete(int id) throws SQLException {
	        String sql = "delete from t_articles where IdArticle = ?";
	        
	       try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            
	            statement.setInt(1, id);
	            statement.executeUpdate();
	        }
	    }
	  
	    //update
	    public void update(Article article) throws SQLException {
	        String sql = "update t_articles set description = ?, brand = ?, UnitaryPrice = ? WHERE IdArticle = ?";
	        
	        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            
	            statement.setString(1, article.getDescription());
	            statement.setString(2, article.getBrand());
	            statement.setDouble(3, article.getPrice());
	            statement.setInt(4, article.getId());
	            
	            statement.executeUpdate();
	        }
	    }
	    
	    //affichage des articles au propre
	    public void displayAll() throws SQLException {
	        List<Article> articles = findAll();
	        
	        System.out.println("===============================================================================");
	        System.out.printf("| %-5s | %-30s | %-20s | %-10s |%n", "ID", "Description", "Brand", "Price");
	        System.out.println("===============================================================================");
	        
	        for (Article article : articles) {
	            System.out.printf("| %-5d | %-30s | %-20s | %10.2f€ |%n",
	                article.getId(),
	                article.getDescription(),
	                article.getBrand(),
	                article.getPrice()
	            );
	        }
	        
	        System.out.println("==============================================================================");
	    }
	
}


