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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "OFFER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offer.findAll", query = "SELECT o FROM Offer o")
    , @NamedQuery(name = "Offer.findById", query = "SELECT o FROM Offer o WHERE o.id = :id")
    , @NamedQuery(name = "Offer.findByPerformerid", query = "SELECT o FROM Offer o WHERE o.performerid = :performerid")
    , @NamedQuery(name = "Offer.findByPrice", query = "SELECT o FROM Offer o WHERE o.price = :price")
    , @NamedQuery(name = "Offer.findByPeriod", query = "SELECT o FROM Offer o WHERE o.period = :period")
    , @NamedQuery(name = "Offer.findByDescription", query = "SELECT o FROM Offer o WHERE o.description = :description")
    , @NamedQuery(name = "Offer.findBySocialmedia", query = "SELECT o FROM Offer o WHERE o.socialmedia = :socialmedia")})
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERFORMERID")
    private int performerid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "PERIOD")
    private Integer period;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "SOCIALMEDIA")
    private String socialmedia;

    public Offer() {
    }

    public Offer(Integer id) {
        this.id = id;
    }

    public Offer(Integer id, int performerid) {
        this.id = id;
        this.performerid = performerid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPerformerid() {
        return performerid;
    }

    public void setPerformerid(int performerid) {
        this.performerid = performerid;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSocialmedia() {
        return socialmedia;
    }

    public void setSocialmedia(String socialmedia) {
        this.socialmedia = socialmedia;
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
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smexchange.Offer[ id=" + id + " ]";
    }
    
}
