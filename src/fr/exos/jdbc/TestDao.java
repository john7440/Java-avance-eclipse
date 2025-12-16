package fr.exos.jdbc;

import java.sql.SQLException;
import java.util.List;

public class TestDao {
	public static void main(String[] args) {
        ArticleDao dao = new ArticleDao();
        
        try {
        	// create
        	//Article newArticle = new Article (0,"Laptop", "DELL", 899.99);
        	//dao.create(newArticle);
        	
        	//read all
        	//List<Article> articles = dao.findAll();
           //articles.forEach(a -> System.out.println(a.getId() + " - " + a.getDescription()));
            
            //update
            //newArticle.setPrice(799.99);
            //dao.update(newArticle);
            
            //delete
            //dao.delete(newArticle.getId());
            
            //affichage
            dao.displayAll();
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }

	}
}