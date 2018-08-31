/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jrmromao
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByIdproductID", query = "SELECT p FROM Product p WHERE p.idproductID = :idproductID"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByProductDescription", query = "SELECT p FROM Product p WHERE p.productDescription = :productDescription"),
    @NamedQuery(name = "Product.findByProductPhoto", query = "SELECT p FROM Product p WHERE p.productPhoto = :productPhoto")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductID")
    private Integer idproductID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_description")
    private String productDescription;
    @Size(max = 45)
    @Column(name = "product_photo")
    private String productPhoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productidproductID")
    private Collection<ShoppingList> shoppingListCollection;

    public Product() {
    }

    public Product(Integer idproductID) {
        this.idproductID = idproductID;
    }

    public Product(Integer idproductID, String productName, String productDescription) {
        this.idproductID = idproductID;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public Integer getIdproductID() {
        return idproductID;
    }

    public void setIdproductID(Integer idproductID) {
        this.idproductID = idproductID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    @XmlTransient
    public Collection<ShoppingList> getShoppingListCollection() {
        return shoppingListCollection;
    }

    public void setShoppingListCollection(Collection<ShoppingList> shoppingListCollection) {
        this.shoppingListCollection = shoppingListCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductID != null ? idproductID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idproductID == null && other.idproductID != null) || (this.idproductID != null && !this.idproductID.equals(other.idproductID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.Product[ idproductID=" + idproductID + " ]";
    }
    
}
