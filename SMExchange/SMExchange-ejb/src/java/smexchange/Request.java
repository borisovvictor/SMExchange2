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
@Table(name = "REQUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id")
    , @NamedQuery(name = "Request.findByClientid", query = "SELECT r FROM Request r WHERE r.clientid = :clientid")
    , @NamedQuery(name = "Request.findByPrice", query = "SELECT r FROM Request r WHERE r.price = :price")
    , @NamedQuery(name = "Request.findByPeriod", query = "SELECT r FROM Request r WHERE r.period = :period")
    , @NamedQuery(name = "Request.findBySocialmedia", query = "SELECT r FROM Request r WHERE r.socialmedia = :socialmedia")
    , @NamedQuery(name = "Request.findByDescription", query = "SELECT r FROM Request r WHERE r.description = :description")
    , @NamedQuery(name = "Request.findByStatus", query = "SELECT r FROM Request r WHERE r.status = :status")
    , @NamedQuery(name = "Request.findByOffersids", query = "SELECT r FROM Request r WHERE r.offersids = :offersids")})
public class Request implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "PERIOD")
    private Integer period;
    @Size(max = 10)
    @Column(name = "SOCIALMEDIA")
    private String socialmedia;
    @Size(max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 50)
    @Column(name = "OFFERSIDS")
    private String offersids;

    public Request() {
    }

    public Request(Integer id) {
        this.id = id;
    }

    public Request(Integer id, int clientid) {
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

    public String getSocialmedia() {
        return socialmedia;
    }

    public void setSocialmedia(String socialmedia) {
        this.socialmedia = socialmedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOffersids() {
        return offersids;
    }

    public void setOffersids(String offersids) {
        this.offersids = offersids;
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
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smexchange.Request[ id=" + id + " ]";
    }
    
}
