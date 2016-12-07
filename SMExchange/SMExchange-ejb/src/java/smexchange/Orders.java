/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smexchange;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id")
    , @NamedQuery(name = "Orders.findByClientid", query = "SELECT o FROM Orders o WHERE o.clientid = :clientid")
    //, @NamedQuery(name = "Orders.findByOfferid", query = "SELECT o FROM Orders o WHERE o.offerid = :offerid")
    , @NamedQuery(name = "Orders.findByOrderstatus", query = "SELECT o FROM Orders o WHERE o.orderstatus = :orderstatus")
    , @NamedQuery(name = "Orders.findBySpeccond", query = "SELECT o FROM Orders o WHERE o.speccond = :speccond")})

//SELECT o FROM (Orders JOIN Offer ON Orders.offerid = Offer.id AND Offer.performerid = :performerid)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENTID")
    private int clientid;
    //@Column(name = "OFFERID")
    //private Integer offerid;
    @Size(max = 10)
    @Column(name = "ORDERSTATUS")
    private String orderstatus;
    @Size(max = 30)
    @Column(name = "SPECCOND")
    private String speccond;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, int clientid) {
        this.id = id;
        this.clientid = clientid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    /*public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }*/

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getSpeccond() {
        return speccond;
    }

    public void setSpeccond(String speccond) {
        this.speccond = speccond;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smexchange.Orders[ id=" + id + " ]";
    }
    
    @OneToOne //(cascade=CascadeType.ALL)
    @JoinColumn(name="OFFERID", referencedColumnName = "ID", updatable = false)//unique= true, nullable=true, insertable=true, updatable=true)
    private Offer offer;
    public Offer getOffer() {
        return offer;
    }
    
    public void setOffer(Offer offer) {
        this.offer = offer;
    }
    
}
