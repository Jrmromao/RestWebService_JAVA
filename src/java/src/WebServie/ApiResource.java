/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.WebServie;

import classes.DBUtil;
import classes.Product;
import classes.User;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import src.classes.Login;
//import src.entities.User;

/**
 * REST Web Service
 *
 * @author jrmromao
 */
@Path("api")
public class ApiResource {

    @Context
    private UriInfo context;
    private DBUtil c = null;
    private User u = null;
    private Product p = null;
    private String message;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String loginUser(@FormParam("name") String name, @FormParam("password") String password) throws SQLException {
        //TODO return proper representation object

        c = new DBUtil();
        u = c.loginUser(name, password);

        JSONObject json = new JSONObject();
        JSONObject userJson = new JSONObject();
        if (u != null) {

            userJson.put("name", u.getName());
            userJson.put("email", u.getEmail());
            userJson.put("id", u.getId());
            // userJson.put("password", u.getPassword());

            json.put("user", userJson);
            json.put("error", false);
            json.put("message", "login successful");

            message = json.toString();
            return message;

        } else {

            json.put("error", true);
            json.put("message", "Invalid username or password");
            message = json.toString();
            return message;
        }

    }
    
    
    
        @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public String regUser(@FormParam("name") String name,
            @FormParam("email") String email,
            @FormParam("password") String password) throws SQLException {
        //TODO return proper representation object

        c = new DBUtil();
        boolean regFlag = c.regUser(name, email, password);

        JSONObject userRegJson = new JSONObject();
        JSONObject regJson = new JSONObject();

        if (regFlag) {

            u = c.loginUser(name, password);
            userRegJson.put("id", u.getId());
            userRegJson.put("name", u.getName());
            userRegJson.put("email", u.getEmail());

            regJson.put("user", userRegJson);
            regJson.put("error", false);
            regJson.put("messsage", "User Registered successfully");
            message = regJson.toString();
            return message;
        } else {
            regJson.put("error", true);
            regJson.put("messsage", "User already registered");
            message = regJson.toString();
            return message;
        }

    }//end regUser
    

    @POST
    @Path("/getProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProduct(@FormParam("upc_num") String upc_num) throws SQLException {
        //TODO return proper representation object

        c = new DBUtil();
        p = c.getProduct(upc_num);

        JSONObject json = new JSONObject();
        JSONObject prodJson = new JSONObject();

        if (p != null) {
            prodJson.put("upc_num", p.getUpc_num());
            prodJson.put("name", p.getProName());
            prodJson.put("description", p.getProdDescription());
            prodJson.put("expiry_date", p.getExpireDate());
            prodJson.put("price", p.getPrice());
            prodJson.put("brand", p.getBrand());
            
            json.put("product", prodJson);
            json.put("error", false);
            json.put("message", "Product found");

            message = json.toString();
            return message;

        } else {

            json.put("error", true);
            json.put("message", "this product is not in the system");
            message = json.toString();
            return message;
        }

    }



}
