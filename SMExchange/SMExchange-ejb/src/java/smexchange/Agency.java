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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "AGENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agency.findAll", query = "SELECT a FROM Agency a")
    , @NamedQuery(name = "Agency.findById", query = "SELECT a FROM Agency a WHERE a.id = :id")
    , @NamedQuery(name = "Agency.findByAgencyid", query = "SELECT a FROM Agency a WHERE a.agencyid = :agencyid")})
public class Agency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENCYID")
    private int agencyid;

    public Agency() {
    }

    public Agency(Integer id) {
        this.id = id;
    }

    public Agency(Integer id, int agencyid) {
        this.id = id;
        this.agencyid = agencyid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Agency)) {
            return false;
        }
        Agency other = (Agency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smexchange.Agency[ id=" + id + " ]";
    }
    
}
