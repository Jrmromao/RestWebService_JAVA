/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jrmromao
 */
@Entity
@Table(name = "shopping_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShoppingList.findAll", query = "SELECT s FROM ShoppingList s"),
    @NamedQuery(name = "ShoppingList.findByIdshoppinglistID", query = "SELECT s FROM ShoppingList s WHERE s.idshoppinglistID = :idshoppinglistID")})
public class ShoppingList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idshopping_list_ID")
    private Integer idshoppinglistID;
    @JoinColumn(name = "product_idproductID", referencedColumnName = "idproductID")
    @ManyToOne(optional = false)
    private Product productidproductID;
    @JoinColumn(name = "user_userID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User useruserID;

    public ShoppingList() {
    }

    public ShoppingList(Integer idshoppinglistID) {
        this.idshoppinglistID = idshoppinglistID;
    }

    public Integer getIdshoppinglistID() {
        return idshoppinglistID;
    }

    public void setIdshoppinglistID(Integer idshoppinglistID) {
        this.idshoppinglistID = idshoppinglistID;
    }

    public Product getProductidproductID() {
        return productidproductID;
    }

    public void setProductidproductID(Product productidproductID) {
        this.productidproductID = productidproductID;
    }

    public User getUseruserID() {
        return useruserID;
    }

    public void setUseruserID(User useruserID) {
        this.useruserID = useruserID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idshoppinglistID != null ? idshoppinglistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShoppingList)) {
            return false;
        }
        ShoppingList other = (ShoppingList) object;
        if ((this.idshoppinglistID == null && other.idshoppinglistID != null) || (this.idshoppinglistID != null && !this.idshoppinglistID.equals(other.idshoppinglistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.ShoppingList[ idshoppinglistID=" + idshoppinglistID + " ]";
    }
    
}
