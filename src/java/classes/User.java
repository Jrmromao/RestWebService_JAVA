/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author jrmromao
 */
public class User {
    
    private int _Id;
    private String _name;
    private String _email;
    private String _password;
    private String _dateJoined;

    public String getDateJoined() {
        return _dateJoined;
    }

    public void setDateJoined(String _dateJoined) {
        this._dateJoined = _dateJoined;
    }

    public int getId() {
        return _Id;
    }

    public void setId(int _Id) {
        this._Id = _Id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
    
    
    
    
    
    
    
}
