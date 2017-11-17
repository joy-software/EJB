/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.directorymanager.bean;

import com.master2.datascale.directorymanager.entity.Objet;
import com.master2.datascale.directorymanager.entity.Utilisateur;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author joy
 * The API of the DirectoryManager
 */
@Remote
public interface StatelessDirectoryManagerBeanRemote {
    
    /**
     * Ajoute un utilisateur dans la base de donnees
     * @param utilisateur l'utilisateur e ajouter
     * @return 
     *       true lorsque tout c'est bien déroulé
     */
    boolean addUser(Utilisateur utilisateur);
    /**
     * Supprime un utilisateur de la base de donnees
     * @param utilisateur l'utilisateur a supprime
     * @return 
     *      true lorsque l'utilisateur a ete bien supprime
     */
    boolean removeUser(Utilisateur utilisateur);
    /**
     * Modifie les droits d'acces d'un utilisateur
     * @param utilisateur L'utilisateur a modifie
     * @param value la valeur du droit d'acces, true lorsqu'il est autorise 
     *      e deposer de nouveaux objets
     * @return 
     *      la valeur affectee aux droits de l'utilisateur
     */
    boolean updateUserRights(Utilisateur utilisateur,boolean value);
    /**
     * Recupere le droit d'acces d'un utilisateur
     * @param utilisateur l'utilisateur dont on veut savoir le droit dd'acces
     * @return 
     *      la valeur du droit d'acces
     */
    boolean lookupUserRights(Utilisateur utilisateur);
    /**
     * Recuperer un utilisateur dans la base de donnees connaissant son ID
     * @param id l'id de l'utilisateur
     * @return 
     *         L'utilisateur d'ID "id"
     */
    Utilisateur findUser(int id);
    /**
     * Recuperer un utilisateur dans la base de donnees connaissant son email
     * @param email
     * @return 
     *         L'utilisateur correspondant à cet email, null s'il n'existe pas
     */
    Utilisateur findUser(String email);
    /**
     * Ajouter un nouvel objet dans la base de donnees
     * @param objet l'objet a ajoute
     * @param utilisateur l'utilisateur possedant l'objet
     * @return 
     *     true lorsque tout s'est bien passé
     */
    boolean addObjects(Utilisateur utilisateur,Objet objet);
    /**
     * Retirer un objet de la base de donnees
     * @param objet l'objet a retire
     * @param utilisateur l'utilisateur possedant l'objet
     * @return 
     *      true si l'operation s'est bien effectuee
     */
    boolean removeObjects(Utilisateur utilisateur,Objet objet);
    /**
     * Recuperer un objet dans la base de donnees connaissant son ID
     * @param id l'id de l'objet
     * @return 
     *         L'objet d'ID "id"
     */
    Objet findObjet(int id);
    /**
     * On recupère les objets que possède un utlisateur
     * @param idUtilisateur
     * @return 
     *      La liste des objets d'un utilisateur
     */
    List<Objet> getObjects(int idUtilisateur);
    /**
     * Fonction de permet de gérer l'authentification des utilisateurs
     * @param email l'email de l'utilisateur a authentifié
     * @param pseudonyme le pseudonyme de l'utilisateur a authentifié
     * @return 
     *      True s'il peut s'authentifié et si ces droits sont correctes.
     */
    boolean auth(String email,String pseudonyme);
    
}
