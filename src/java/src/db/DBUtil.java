/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jrmromao
 */

public class DBUtil {
    
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web_PU");


    public static EntityManagerFactory getEmf() {
        return emf;
    } 
}