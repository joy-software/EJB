/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.directorymanager.bean;

import com.master2.datascale.directorymanager.entity.Objet;
import com.master2.datascale.directorymanager.entity.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author joy
 * The stateless Bean of our DirectoryManager
 */
@Stateless
public class StatelessDirectoryManagerBean implements StatelessDirectoryManagerBeanRemote {

    /**
     * Notre requête sql qui sera execute 
     * Ce mecaisme nous permettra d'eviter les injections de code dans nos requêtes
     */
    private static final String SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";
    /**
     * La requete de selection des objets d'un utisateur
     */
    private static final String SELECT_OBJET_PAR_ID_UTILISATEUR = "SELECT o FROM Objet o  WHERE o.utilisateur.id=:id";
    
    /**
     * Utiliser dans la requête select_par_email pour renseigner l'email de l'utilisateur à chercher
     */
    private static final String PARAM_EMAIL           = "email";
    /**
     * paramètre utlisé dans la requete select_objet
     */
    private static final String PARAM_ID = "id";

    /**
     * the reference to the entity manager, which persistence context is "pu1".
     */
    @PersistenceContext(unitName = "com.master2.datascale_directoryManager.entity_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    
    @Override
    public boolean addUser(Utilisateur utilisateur) {
        try {
            em.merge(utilisateur);
            return true; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
   
    }

    @Override
    public boolean removeUser(Utilisateur utilisateur) {
        try {
            Utilisateur temp = em.merge(utilisateur);
                em.remove(temp);
                return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUserRights(Utilisateur utilisateur, boolean value) {
        utilisateur.setEnchere(value);
        Utilisateur result = em.merge(utilisateur);
        return result.isEnchere();
    }

    @Override
    public boolean lookupUserRights(Utilisateur utilisateur) {
        return utilisateur.isEnchere();
    }

    @Override
    public Utilisateur findUser(int id) {
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        return utilisateur;
    }

    
    @Override
    public Objet findObjet(int id) {
        Objet objet = em.find(Objet.class, id);
       
        return objet;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean addObjects(Utilisateur utilisateur, Objet objet) {
        if(getObjects(utilisateur.getId()).size() < 0)
        {
                //em.persist(objet);
            try {
                utilisateur.getObjets().add(objet);
                objet.setUtilisateur(utilisateur);
                em.merge(utilisateur);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        else
        {
            updateUserRights(utilisateur, false);
            return false;
        }
            
    }

    @Override
    public boolean removeObjects(Utilisateur utilisateur, Objet objet) {
       try {
           utilisateur.getObjets().remove(objet);
           
           em.merge(utilisateur);
           //objet.setUtilisateur(null);
           Objet temp = em.merge(objet);
           //em.getTransaction().begin();
            em.remove(temp);
           //em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }

    @Override
    public Utilisateur findUser(String email) {
        Utilisateur utilisateur = null;
        Query requete = em.createQuery( SELECT_PAR_EMAIL );
        requete.setParameter( PARAM_EMAIL, email );
        try {
            utilisateur = (Utilisateur) requete.getSingleResult();
            utilisateur = em.merge(utilisateur);
        } catch ( NoResultException e ) {
            return null;
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
        return utilisateur;
    }

    @Override
    public List<Objet> getObjects(int idUtilisateur) {
            Query requete = em.createQuery( SELECT_OBJET_PAR_ID_UTILISATEUR );
            requete.setParameter( PARAM_ID, idUtilisateur );
        
            try {
                return requete.getResultList();
            } catch ( Exception e ) {
                
            System.out.println(e.getMessage());}
        return null;
    }

    @Override
    public boolean auth(String email, String pseudonyme) {
        Utilisateur utilisateur = this.findUser(email);
            
            if(utilisateur != null)
            {
                if(utilisateur.getPseudonyme().equals(pseudonyme))
                {
                        return true;
                }
            }
            return false;
    }
}
