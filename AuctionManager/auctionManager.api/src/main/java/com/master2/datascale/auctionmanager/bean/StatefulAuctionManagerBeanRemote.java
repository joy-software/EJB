/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.auctionmanager.bean;

import com.master2.datascale.auctionmanager.entity.Enchere;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author joy
 */
@Remote
public interface StatefulAuctionManagerBeanRemote {
    
    
    /**
     * Permet de creer une enchere sur un objet
     * @param enchere L'enchere qu'on souhaite enregistre
     * @param idUtilisateur l'identifiant de l'utilisateur
     * @param idObjet l'identifiant de l'objet
     * @return 
     *          True si l'opération s'est bien déroulee
     */ 
    public boolean createAuction(Enchere enchere,int idUtilisateur,int idObjet);
    /**
     * Permet de lancer le debut d'une enchere sur un objet
     * @param id l'identifiant de l'enchere a active
     * @return 
     *        True si l'operation s'est bien effectue
     */
    public boolean startAuction(int id);
    /**
     * fermer une enchere en cours
     * @param id l'identifiant de l'enchere en cours
     * @return 
     *          True si tout s'est bien deroule
     */
    public boolean closeAuction(int id);
    /**
     * Fermer une enchere en cours
     * @param enchere l'enchere en cours
     * @return 
     *          True si tout s'est bien deroule
     */
    public boolean closeAuction(Enchere enchere);
    /**
     * 
     * @param idUtilisateur l'identifiant de l'acquereur
     * @param enchere l'enchere en cours
     * @return
     *         Un message adressé à l'utilisateur
     */
    public String sendBid(int idUtilisateur,int enchere);
    /**
     * Recuperer les encheres en cours
     * @param id l'identifiant de l'utilisateur a qui sera renvoyé la liste
     * @return 
     *      Une liste des encheres en cours
     */
    public List<Enchere> getEncheresEnCours(int id);
    /**
     *Fais appel au module d'authentification qui se trouve chez le DirectoryManager
     * @param email l'email de l'utilisateur qui souhaite s'identifier
     * @param pseudonyme le pseudonyme de l'utilisateur qui souhaite s'identifier
     * @return 
     *          L'utilisateur correspondant a ces identifiants s'ils sont corrects.
     */
    public boolean auth(String email,String pseudonyme);
    /**
     * Faire au directoryManager pour rechercher un objet
     * @param email l'email d'un utilisateur authentifie
     * @return 
     *      L'objet correspondant a l'id s'il existe et null sinon.
     */
    public int findID(String email);
}
