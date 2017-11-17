/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.directorymanager.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author joy
 * 
 * Le mod?le de la table Utilisateur de notre base de donn?es
 */
@Entity
public class Utilisateur implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * L'id de l'utilisateur
     */
    @Id
    private int id;
    /**
     * Le pseudonyme de l'utilisateur
     */
    private String pseudonyme;
    /**
     * Le nom de l'utilisateur
     */
    private String nom;
    /**
     * Le prenom de l'utilisateur
     */
    private String prenom;
    /**
     * L'adresse postale de l'utilisateur
     */
    @Column(name="postale")
    private String adressPostale;
    /**
     * L'email de l'utilisateur
     */
    private String email;
    /**
     * Le droit d'acc?s de l'utilisateur. Pour v?rifier s'il peut encore
     * soumettre des objects aux ench?res.
     */
    private boolean enchere;
    
    /**
     * La liste des objets de l'utilisateur
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur", fetch = FetchType.EAGER)
    private List<Objet> objets = new ArrayList<Objet>();

    /**
    * gets the collection of objets.
    * 
    * @return the collection.
    */
    public List<Objet> getObjets() {
        
        return objets;
    }

    /**
    * sets the collection of objets.
    * 
    * @param objets 
    *            the new collection.
    */

    public void setObjets(List<Objet> objets) {
        this.objets = objets;
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

    /**
     * Get the value of enchere
     *
     * @return the value of enchere
     */
    public boolean isEnchere() {
        return enchere;
    }

    /**
     * Set the value of enchere
     *
     * @param enchere new value of enchere
     */
    public void setEnchere(boolean enchere) {
        this.enchere = enchere;
    }

    
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * Get the value of adressPostale
     *
     * @return the value of adressPostale
     */
    public String getAdressPostale() {
        return adressPostale;
    }

    /**
     * Get the value of prenom
     *
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the value of prenom
     *
     * @param prenom new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * Get the value of pseudonyme
     *
     * @return the value of pseudonyme
     */
    public String getPseudonyme() {
        return pseudonyme;
    }

    /**
     * Set the value of pseudonyme
     *
     * @param pseudonyme new value of pseudonyme
     */
    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    /**
     * Modifier l'adresse postale
     * @param adressPostale  l'adresse postale à sauvegarder
     */
    public void setAdressPostale(String adressPostale) {
        this.adressPostale = adressPostale;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        return this.id == other.id;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        
        return "[Utilisateur{" + "id=" + id + ", pseudonyme=" + pseudonyme + ", nom=" + nom + ", prenom=" + prenom + ", adressPostale=" + adressPostale + ", email=" + email + ", enchere=" + enchere + " ]\n";
    }

    
    
    public String toString_() {
        
        String objetStings = "";
       
        if(this.objets != null)
        {
             for(int i = 0; i < objets.size(); i++)
            {
                objetStings += objets.get(i).toString()+"\t";
            }
       
        }
        
        return "[\n\tUtilisateur{" + "id=" + id + ", pseudonyme=" + pseudonyme + ", nom=" + nom + ", prenom=" + prenom + ", adressPostale=" + adressPostale + ", email=" + email + ", enchere=" + enchere + ", \n  \tobjets=" 
                + objetStings+ "]\n";
    }
    
    
}
