/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.auctionmanager.entity;

import com.master2.datascale.directorymanager.entity.Objet;
import com.master2.datascale.directorymanager.entity.Utilisateur;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *La classe implementant les caracteristiques de nos encheres
 * @author joy
 */
@Entity
public class Enchere implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * L'id de notre enchere
     */
    @Id
    private int id;
    /**
     * Le prix initial de l'objet mis en enchere
     */
    private double startPrice;
    /**
     * La période maximale sur laquelle s'effectuera l'enchere
     */
    private Duration maxBidPeriod;
    /**
     * La valeur a partir de laquelle le prix des encheres sera augmente
     */
    private double automaticBid;
    /**
     * Le statut de l'enchere: not started,active or closed.
     */
    private String statut;
    /**
     * le prix de vente de l'objet lorsqu'il est aux encheres
     */    
    private double bid;
    /**
     * L'utilisateur ayant deposer l'objet aux encheres
     */
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID")
    private Utilisateur utilisateur;
    /**
     * L'objet propose a l'enchere
     */
    @OneToOne
    @JoinColumn(name = "OBJET_ID")
    private Objet objet;
    /**
     * L'utilisateur proposant une offre pour l'enchere
     */
    private int acquereur;
    /**
     * La date a laquelle la premiere offre sur cette enchere a ete enregistre
     */
    private Date FirstUpdate;

    /**
     * Get the value of FirstUpdate
     *
     * @return the value of FirstUpdate
     */
    public Date getFirstUpdate() {
        return FirstUpdate;
    }

    /**
     * Set the value of FirstUpdate
     *
     * @param FirstUpdate new value of FirstUpdate
     */
    public void setFirstUpdate(Date FirstUpdate) {
        this.FirstUpdate = FirstUpdate;
    }


    /**
     * Get the value of acquereur
     *
     * @return the value of acquereur
     */
    public int getAcquereur() {
        return acquereur;
    }

    /**
     * Set the value of acquereur
     *
     * @param acquereur new value of acquereur
     */
    public void setAcquereur(int acquereur) {
        this.acquereur = acquereur;
    }


    /**
     * Get the value of objet
     *
     * @return the value of objet
     */
    public Objet getObjet() {
        return objet;
    }

    /**
     * Set the value of objet
     *
     * @param objet new value of objet
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }


    /**
     * Get the value of utilisateur
     *
     * @return the value of utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Set the value of utilisateur
     *
     * @param utilisateur new value of utilisateur
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    /**
     * Get the value of bid
     *
     * @return the value of bid
     */
    public double getBid() {
        return bid;
    }

    /**
     * Set the value of bid
     *
     * @param bid new value of bid
     */
    public void setBid(double bid) {
        this.bid = bid;
    }

    /**
     * Get the value of statut
     *
     * @return the value of statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Set the value of statut
     *
     * @param statut new value of statut
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }


    /**
     * Get the value of automaticBid
     *
     * @return the value of automaticBid
     */
    public double getautomaticBid() {
        return automaticBid;
    }

    /**
     * Set the value of automaticBid
     *
     * @param automaticBid new value of automaticBid
     */
    public void setautomaticBid(double automaticBid) {
        this.automaticBid = automaticBid;
    }


    /**
     * Get the value of maxBidPeriod
     *
     * @return the value of maxBidPeriod
     */
    public Duration getmaxBidPeriod() {
        return maxBidPeriod;
    }

    /**
     * Set the value of maxBidPeriod
     *
     * @param maxBidPeriod new value of maxBidPeriod
     */
    public void setmaxBidPeriod(Duration maxBidPeriod) {
        this.maxBidPeriod = maxBidPeriod;
    }


    /**
     * Get the value of startPrice
     *
     * @return the value of startPrice
     */
    public double getStartPrice() {
        return startPrice;
    }

    /**
     * Set the value of startPrice
     *
     * @param startPrice new value of startPrice
     */
    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    /**
    * gets the identifier.
    * 
    * @return the identifier.
    */
    public int getId() {
        return id;
    }
    /**
    * sets the identifier.
    * 
    * @param id
    *            the new identifier.
    */
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enchere)) {
            return false;
        }
        Enchere other = (Enchere) object;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Enchere{" + "id=" + id + ", startPrice=" + startPrice + ", maxBidPeriod=" + maxBidPeriod + ", automaticBid=" + automaticBid + ", statut=" + statut + ", bid=" + bid + ", utilisateur=" + utilisateur + ", objet=" + objet + ", acquereur=" + acquereur + '}';
    }
    
    

}
