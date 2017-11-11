/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.classes;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import src.entities.User;
import src.db.DBUtil;

/**
 *
 * @author jrmromao
 */
public class Login {
    
    
 
    public static User LginUser(String name, String password) {

        EntityManager em = null;
        User u = null;
        String q = "SELECT u FROM User u WHERE u.name = :name AND u.password = password";

        Query query = em.createQuery(q); 
        try {
            
            em = DBUtil.getEmf().createEntityManager();
            TypedQuery tq = em.createQuery(q, User.class );
            tq.setParameter("name", name);
            tq.setParameter("password", password);
            
            u = (User) tq.getSingleResult();

            
            
        } catch (Exception e) {
            System.err.println("LginUser ERROR: " + e.getMessage());
        } finally {
            em.close();
        }

        return u;
    }//end loginUser

    
    
}
