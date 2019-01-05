/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author freddo
 */
@Entity
@Table(name = "idx_scrap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdxScrap.findAll", query = "SELECT i FROM IdxScrap i")
    , @NamedQuery(name = "IdxScrap.findByKodeSaham", query = "SELECT i FROM IdxScrap i WHERE i.idxScrapPK.kodeSaham = :kodeSaham")
    , @NamedQuery(name = "IdxScrap.findByRemarks", query = "SELECT i FROM IdxScrap i WHERE i.remarks = :remarks")
    , @NamedQuery(name = "IdxScrap.findBySebelumnya", query = "SELECT i FROM IdxScrap i WHERE i.sebelumnya = :sebelumnya")
    , @NamedQuery(name = "IdxScrap.findByOpenPrice", query = "SELECT i FROM IdxScrap i WHERE i.openPrice = :openPrice")
    , @NamedQuery(name = "IdxScrap.findByFirstTrade", query = "SELECT i FROM IdxScrap i WHERE i.firstTrade = :firstTrade")
    , @NamedQuery(name = "IdxScrap.findByTertinggi", query = "SELECT i FROM IdxScrap i WHERE i.tertinggi = :tertinggi")
    , @NamedQuery(name = "IdxScrap.findByTerendah", query = "SELECT i FROM IdxScrap i WHERE i.terendah = :terendah")
    , @NamedQuery(name = "IdxScrap.findByPenutupan", query = "SELECT i FROM IdxScrap i WHERE i.penutupan = :penutupan")
    , @NamedQuery(name = "IdxScrap.findBySelisih", query = "SELECT i FROM IdxScrap i WHERE i.selisih = :selisih")
    , @NamedQuery(name = "IdxScrap.findByVolume", query = "SELECT i FROM IdxScrap i WHERE i.volume = :volume")
    , @NamedQuery(name = "IdxScrap.findByNilai", query = "SELECT i FROM IdxScrap i WHERE i.nilai = :nilai")
    , @NamedQuery(name = "IdxScrap.findByFrekuensi", query = "SELECT i FROM IdxScrap i WHERE i.frekuensi = :frekuensi")
    , @NamedQuery(name = "IdxScrap.findByIndexIndividual", query = "SELECT i FROM IdxScrap i WHERE i.indexIndividual = :indexIndividual")
    , @NamedQuery(name = "IdxScrap.findByListedShare", query = "SELECT i FROM IdxScrap i WHERE i.listedShare = :listedShare")
    , @NamedQuery(name = "IdxScrap.findByOffer", query = "SELECT i FROM IdxScrap i WHERE i.offer = :offer")
    , @NamedQuery(name = "IdxScrap.findByOfferVolume", query = "SELECT i FROM IdxScrap i WHERE i.offerVolume = :offerVolume")
    , @NamedQuery(name = "IdxScrap.findByBid", query = "SELECT i FROM IdxScrap i WHERE i.bid = :bid")
    , @NamedQuery(name = "IdxScrap.findByBidVolume", query = "SELECT i FROM IdxScrap i WHERE i.bidVolume = :bidVolume")
    , @NamedQuery(name = "IdxScrap.findByLastTradingDate", query = "SELECT i FROM IdxScrap i WHERE i.lastTradingDate = :lastTradingDate")
    , @NamedQuery(name = "IdxScrap.findByTradeableShare", query = "SELECT i FROM IdxScrap i WHERE i.tradeableShare = :tradeableShare")
    , @NamedQuery(name = "IdxScrap.findByWeightForIndex", query = "SELECT i FROM IdxScrap i WHERE i.weightForIndex = :weightForIndex")
    , @NamedQuery(name = "IdxScrap.findByForeignSell", query = "SELECT i FROM IdxScrap i WHERE i.foreignSell = :foreignSell")
    , @NamedQuery(name = "IdxScrap.findByForeignBuy", query = "SELECT i FROM IdxScrap i WHERE i.foreignBuy = :foreignBuy")
    , @NamedQuery(name = "IdxScrap.findByNonRegularVolume", query = "SELECT i FROM IdxScrap i WHERE i.nonRegularVolume = :nonRegularVolume")
    , @NamedQuery(name = "IdxScrap.findByNonRegularValue", query = "SELECT i FROM IdxScrap i WHERE i.nonRegularValue = :nonRegularValue")
    , @NamedQuery(name = "IdxScrap.findByNonRegularFrequency", query = "SELECT i FROM IdxScrap i WHERE i.nonRegularFrequency = :nonRegularFrequency")
    , @NamedQuery(name = "IdxScrap.findByTanggal", query = "SELECT i FROM IdxScrap i WHERE i.idxScrapPK.tanggal = :tanggal")})
public class IdxScrap implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IdxScrapPK idxScrapPK;
    @Lob
    @Column(name = "nama_perusahaan")
    private String namaPerusahaan;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "sebelumnya")
    private String sebelumnya;
    @Column(name = "open_price")
    private String openPrice;
    @Column(name = "first_trade")
    private String firstTrade;
    @Column(name = "tertinggi")
    private String tertinggi;
    @Column(name = "terendah")
    private String terendah;
    @Column(name = "penutupan")
    private String penutupan;
    @Column(name = "selisih")
    private String selisih;
    @Column(name = "volume")
    private String volume;
    @Column(name = "nilai")
    private String nilai;
    @Column(name = "frekuensi")
    private String frekuensi;
    @Column(name = "index_individual")
    private String indexIndividual;
    @Column(name = "listed_share")
    private String listedShare;
    @Column(name = "offer")
    private String offer;
    @Column(name = "offer_volume")
    private String offerVolume;
    @Column(name = "bid")
    private String bid;
    @Column(name = "bid_volume")
    private String bidVolume;
    @Column(name = "last_trading_date")
    private String lastTradingDate;
    @Column(name = "tradeable_share")
    private String tradeableShare;
    @Column(name = "weight_for_index")
    private String weightForIndex;
    @Column(name = "foreign_sell")
    private String foreignSell;
    @Column(name = "foreign_buy")
    private String foreignBuy;
    @Column(name = "non_regular_volume")
    private String nonRegularVolume;
    @Column(name = "non_regular_value")
    private String nonRegularValue;
    @Column(name = "non_regular_frequency")
    private String nonRegularFrequency;

    public IdxScrap() {
    }

    public IdxScrap(IdxScrapPK idxScrapPK) {
        this.idxScrapPK = idxScrapPK;
    }

    public IdxScrap(String kodeSaham, Date tanggal) {
        this.idxScrapPK = new IdxScrapPK(kodeSaham, tanggal);
    }

    public IdxScrapPK getIdxScrapPK() {
        return idxScrapPK;
    }

    public void setIdxScrapPK(IdxScrapPK idxScrapPK) {
        this.idxScrapPK = idxScrapPK;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSebelumnya() {
        return sebelumnya;
    }

    public void setSebelumnya(String sebelumnya) {
        this.sebelumnya = sebelumnya;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getFirstTrade() {
        return firstTrade;
    }

    public void setFirstTrade(String firstTrade) {
        this.firstTrade = firstTrade;
    }

    public String getTertinggi() {
        return tertinggi;
    }

    public void setTertinggi(String tertinggi) {
        this.tertinggi = tertinggi;
    }

    public String getTerendah() {
        return terendah;
    }

    public void setTerendah(String terendah) {
        this.terendah = terendah;
    }

    public String getPenutupan() {
        return penutupan;
    }

    public void setPenutupan(String penutupan) {
        this.penutupan = penutupan;
    }

    public String getSelisih() {
        return selisih;
    }

    public void setSelisih(String selisih) {
        this.selisih = selisih;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getFrekuensi() {
        return frekuensi;
    }

    public void setFrekuensi(String frekuensi) {
        this.frekuensi = frekuensi;
    }

    public String getIndexIndividual() {
        return indexIndividual;
    }

    public void setIndexIndividual(String indexIndividual) {
        this.indexIndividual = indexIndividual;
    }

    public String getListedShare() {
        return listedShare;
    }

    public void setListedShare(String listedShare) {
        this.listedShare = listedShare;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getOfferVolume() {
        return offerVolume;
    }

    public void setOfferVolume(String offerVolume) {
        this.offerVolume = offerVolume;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBidVolume() {
        return bidVolume;
    }

    public void setBidVolume(String bidVolume) {
        this.bidVolume = bidVolume;
    }

    public String getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(String lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public String getTradeableShare() {
        return tradeableShare;
    }

    public void setTradeableShare(String tradeableShare) {
        this.tradeableShare = tradeableShare;
    }

    public String getWeightForIndex() {
        return weightForIndex;
    }

    public void setWeightForIndex(String weightForIndex) {
        this.weightForIndex = weightForIndex;
    }

    public String getForeignSell() {
        return foreignSell;
    }

    public void setForeignSell(String foreignSell) {
        this.foreignSell = foreignSell;
    }

    public String getForeignBuy() {
        return foreignBuy;
    }

    public void setForeignBuy(String foreignBuy) {
        this.foreignBuy = foreignBuy;
    }

    public String getNonRegularVolume() {
        return nonRegularVolume;
    }

    public void setNonRegularVolume(String nonRegularVolume) {
        this.nonRegularVolume = nonRegularVolume;
    }

    public String getNonRegularValue() {
        return nonRegularValue;
    }

    public void setNonRegularValue(String nonRegularValue) {
        this.nonRegularValue = nonRegularValue;
    }

    public String getNonRegularFrequency() {
        return nonRegularFrequency;
    }

    public void setNonRegularFrequency(String nonRegularFrequency) {
        this.nonRegularFrequency = nonRegularFrequency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idxScrapPK != null ? idxScrapPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdxScrap)) {
            return false;
        }
        IdxScrap other = (IdxScrap) object;
        if ((this.idxScrapPK == null && other.idxScrapPK != null) || (this.idxScrapPK != null && !this.idxScrapPK.equals(other.idxScrapPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IdxScrap[ idxScrapPK=" + idxScrapPK + " ]";
    }

}
