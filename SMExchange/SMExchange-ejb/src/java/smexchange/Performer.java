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
@Table(name = "PERFORMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Performer.findAll", query = "SELECT p FROM Performer p")
    , @NamedQuery(name = "Performer.findById", query = "SELECT p FROM Performer p WHERE p.id = :id")
    , @NamedQuery(name = "Performer.findByPerformerid", query = "SELECT p FROM Performer p WHERE p.performerid = :performerid")
    , @NamedQuery(name = "Performer.findByPhonenumber", query = "SELECT p FROM Performer p WHERE p.phonenumber = :phonenumber")
    , @NamedQuery(name = "Performer.findByMoneyamount", query = "SELECT p FROM Performer p WHERE p.moneyamount = :moneyamount")
    , @NamedQuery(name = "Performer.findByAgencyid", query = "SELECT p FROM Performer p WHERE p.agencyid = :agencyid")})
public class Performer implements Serializable {

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
    @Size(max = 30)
    @Column(name = "PHONENUMBER")
    private String phonenumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONEYAMOUNT")
    private Float moneyamount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENCYID")
    private int agencyid;

    public Performer() {
    }

    public Performer(Integer id) {
        this.id = id;
    }

    public Performer(Integer id, int performerid, int agencyid) {
        this.id = id;
        this.performerid = performerid;
        this.agencyid = agencyid;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Float getMoneyamount() {
        return moneyamount;
    }

    public void setMoneyamount(Float moneyamount) {
        this.moneyamount = moneyamount;
    }

    public int getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(int agencyid) {
        this.agencyid = agencyid;
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
        if (!(object instanceof Performer)) {
            return false;
        }
        Performer other = (Performer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smexchange.Performer[ id=" + id + " ]";
    }
    
}
