/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.auctionmanager.bean;

import com.master2.datascale.auctionmanager.entity.Enchere;
import com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author joy
 */
@Stateful
public class StatefulAuctionManagerBean implements StatefulAuctionManagerBeanRemote {

    /**
     * the reference to the entity manager, which persistence context is "pu1".
     */
    @PersistenceContext(unitName = "com.master2.datascale_auctionManager.entity_jar_1.0-SNAPSHOTPU")
    private EntityManager em;
    /**
     * La requete de selection des objets d'un utisateur
     */
    private static final String SELECT_ENCHERE_PAR_ID_UTILISATEUR = "SELECT e FROM Enchere e  WHERE e.utilisateur.id!=:id and e.statut=\"active\"";
    
    /**
     * Utiliser dans la requête select_par_email pour renseigner l'email de l'utilisateur à chercher
     */
    private static final String PARAM_ID           = "id";
    
    
    
    @Override
    public boolean createAuction(Enchere enchere,int idUtilisateur,int idObjet) {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        
        StatelessDirectoryManagerBeanRemote sb = null;
        InitialContext ic;
        
        try {
            ic = new InitialContext(props);
            sb = (StatelessDirectoryManagerBeanRemote)ic.lookup("com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote");
            
            enchere.setObjet(sb.findObjet(idObjet));
            enchere.setUtilisateur(sb.findUser(idUtilisateur));
            em.merge(enchere);
            return true;
        } catch (NamingException ex) {
            Logger.getLogger(StatefulAuctionManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean startAuction(int idEnchere) {
        try {
            Enchere result = em.find(Enchere.class,idEnchere);
            result.setStatut("active");
            em.merge(result);
            return true; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean closeAuction(int id) {
        Enchere enchere = em.find(Enchere.class,id);
        if(enchere != null)
        {
            try {
                enchere.setStatut("closed");
                em.merge(enchere);
                return true; 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean closeAuction(Enchere enchere) {
        try {
                enchere.setStatut("closed");
                em.merge(enchere);
                return true; 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    @Override
    public String sendBid(int utilisateur, int idenchere) {
        Enchere manip = em.find(Enchere.class,idenchere);
        if(manip.getBid() != 0)
        {
            Duration duree = Duration.between(manip.getFirstUpdate().toInstant(), new Date().toInstant());
            if(duree.compareTo(manip.getmaxBidPeriod()) > 0)
            {
                if(manip.getStatut().equalsIgnoreCase("closed"))
                {
                    return "\t Cette enchere est deja close \n";
                }
                else
                {
                    manip.setStatut("closed");
                    em.merge(manip);
                    return "\t temps maximum pour recevoir les offres dépassées, enchere deja fermee.\n";
                }
            }
            else{
                manip.setAcquereur(utilisateur);
                manip.setBid(manip.getBid() + manip.getautomaticBid());

                em.merge(manip);
                return "La valeur actuelle de l'objet: "+manip.getBid()+". Votre offre a ete bien enregistre\n";
            }
        }
        else
        {
            manip.setAcquereur(utilisateur);
            manip.setBid(manip.getStartPrice());
            manip.setFirstUpdate(new Date());
            em.merge(manip);
            return "La valeur actuelle de l'objet: "+manip.getBid()+". Votre offre a ete bien enregistre\n";
        }
    }

    @Override
    public List<Enchere> getEncheresEnCours(int id) {
        Query requete = em.createQuery( SELECT_ENCHERE_PAR_ID_UTILISATEUR );
            requete.setParameter( PARAM_ID, id);
        
            try {
                return requete.getResultList();
            } catch ( Exception e ) {
                
            System.out.println(e.getMessage());}
        return null;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean auth(String email, String pseudonyme) {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        
        StatelessDirectoryManagerBeanRemote sb = null;
        InitialContext ic;
        
        try {
            ic = new InitialContext(props);
            sb = (StatelessDirectoryManagerBeanRemote)ic.lookup("com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote");
			
        } catch (NamingException ex) {
            Logger.getLogger(StatefulAuctionManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
			
        return sb.auth(email, pseudonyme);
    }

    @Override
    public int findID(String email) {
    
    Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        
        StatelessDirectoryManagerBeanRemote sb = null;
        InitialContext ic;
        
        try {
            ic = new InitialContext(props);
            sb = (StatelessDirectoryManagerBeanRemote)ic.lookup("com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote");
			
        } catch (NamingException ex) {
            Logger.getLogger(StatefulAuctionManagerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
			
        return sb.findUser(email).getId();
    }
}
