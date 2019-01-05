/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author freddo
 */
@Embeddable
public class IdxScrapPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "kode_saham")
    private String kodeSaham;
    @Basic(optional = false)
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    public IdxScrapPK() {
    }

    public IdxScrapPK(String kodeSaham, Date tanggal) {
        this.kodeSaham = kodeSaham;
        this.tanggal = tanggal;
    }

    public String getKodeSaham() {
        return kodeSaham;
    }

    public void setKodeSaham(String kodeSaham) {
        this.kodeSaham = kodeSaham;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeSaham != null ? kodeSaham.hashCode() : 0);
        hash += (tanggal != null ? tanggal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdxScrapPK)) {
            return false;
        }
        IdxScrapPK other = (IdxScrapPK) object;
        if ((this.kodeSaham == null && other.kodeSaham != null) || (this.kodeSaham != null && !this.kodeSaham.equals(other.kodeSaham))) {
            return false;
        }
        if ((this.tanggal == null && other.tanggal != null) || (this.tanggal != null && !this.tanggal.equals(other.tanggal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IdxScrapPK[ kodeSaham=" + kodeSaham + ", tanggal=" + tanggal + " ]";
    }

}
