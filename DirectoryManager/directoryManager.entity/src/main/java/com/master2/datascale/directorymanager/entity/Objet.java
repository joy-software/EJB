/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.directorymanager.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author joy
 * 
 */
@Entity
public class Objet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    /**
     * La description de l'objet
     */
    private String description;
    /**
     * La cat?gorie de l'objet
     */
    private String categorie;
    /**
     * L'utilisateur qui poss?de l'objet
     */
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID")
    private Utilisateur utilisateur;

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
     * Get the value of categorie
     *
     * @return the value of categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Set the value of categorie
     *
     * @param categorie new value of categorie
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    
    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Objet)) {
            return false;
        }
        Objet other = (Objet) object;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Objet{" + "id=" + id + ", description=" + description + ", categorie=" + categorie +  "}\n";
    }

    
    
}
