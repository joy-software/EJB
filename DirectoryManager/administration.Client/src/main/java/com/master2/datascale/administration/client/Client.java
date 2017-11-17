/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.administration.client;

import com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote;
import com.master2.datascale.directorymanager.entity.Objet;
import com.master2.datascale.directorymanager.entity.Utilisateur;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author joy
 * Le client de notre application
 */
public class Client {
    
     private static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		      StatelessDirectoryManagerBeanRemote sb;
		
		      InitialContext ic;
		try {
			
                        Properties props = new Properties();
                        props.load(new FileInputStream("jndi.properties"));
                        System.out.println("Entrer l'adresse du serveur glassfish (default: localhost)[Touche entrer pour passer]");
                        String address = scan.nextLine();
                        System.out.println("Entrer le port du CORBA: (default: 3700)[Touche entrer pour passer]");
                        String port = scan.nextLine();
                        
                        if(!address.isEmpty())
                        {
                            props.setProperty("org.omg.CORBA.ORBInitialHost", address);
                        }
                        
                        if(!port.isEmpty())
                        {
                            props.setProperty("org.omg.CORBA.ORBInitialPort", port);
                        }
                        //*/
                        ic = new InitialContext(props);
			sb = (StatelessDirectoryManagerBeanRemote)ic.lookup("com.master2.datascale.directorymanager.bean.StatelessDirectoryManagerBeanRemote");
			
                        System.out.println("Nouvel utilisateur");
                        
                        Utilisateur utilisateur = new Utilisateur();
                        utilisateur.setId(1);
                        utilisateur.setAdressPostale("45 Avenue des Etats Unis, 75000 Versailles");
                        utilisateur.setEmail("joyjedid@gmail.com");
                        utilisateur.setEnchere(true);
                        utilisateur.setNom("Joy");
                        utilisateur.setPrenom("Jedidja");
                        utilisateur.setPseudonyme("joyjedid");
                        
                        System.out.println("Utilisateur ajoutée : "+sb.addUser(utilisateur));
                        
                        System.out.println("Nouvel objet");
  
                        Objet objet = new Objet();
                        objet.setId(1);
                        objet.setCategorie("laptop");
                        objet.setDescription("les ordinateurs portables");
                        
                        System.out.println("Objet ajouté: " + sb.addObjects(utilisateur, objet));
                        
                        Objet objet1 = new Objet();
                        objet1.setId(2);
                        objet1.setCategorie("nutella");
                        objet1.setDescription("tartine pour pain");
                        
                        System.out.println("Objet2 ajouté: "+sb.addObjects(utilisateur, objet1));
                        
                        Objet objet2 = new Objet();
                        objet2.setId(3);
                        objet2.setCategorie("nutella1");
                        objet2.setDescription("tartine pour pain");
                        
                        System.out.println("Objet3 ajouté: "+sb.addObjects(utilisateur, objet2));
                        
                        System.out.println("object 3 supprimé: "+sb.removeObjects(utilisateur, objet2));
//*/                   //*/
                        
                        utilisateur.setObjets(sb.getObjects(1));
                        System.out.println(utilisateur);
                        

                        //System.out.println(utilisateur.getObjets());
                        
                        
                       
                        System.out.println("Nouvel utilisateur");
                        
                        Utilisateur utilisateur1 = new Utilisateur();
                        utilisateur1.setId(2);
                        utilisateur1.setEmail("1joyjedid@gmail.com");
                        utilisateur1.setEnchere(true);
                        utilisateur1.setNom("Joy1");
                        utilisateur1.setPrenom("Jedidja");
                        utilisateur1.setPseudonyme("joyjedid");
//                        utilisateur1 = sb.addUser(utilisateur1);
                        
                        System.out.println("Utilisateur ajoutée : " + sb.addUser(utilisateur1));
                        
                        System.out.println("suppresion utilisateur: "+sb.removeUser(utilisateur1));
                        
                        
                        System.out.println("Nouvel utilisateur");
                        
                        utilisateur = new Utilisateur();
                        utilisateur.setId(2);
                        utilisateur.setAdressPostale("45 Avenue des Etats Unis, 75000 Versailles");
                        utilisateur.setEmail("joyjed@gmail.com");
                        utilisateur.setEnchere(true);
                        utilisateur.setNom("Joy");
                        utilisateur.setPrenom("Jedidja");
                        utilisateur.setPseudonyme("joyjedid");
                        
                        System.out.println("Utilisateur ajoutée : "+sb.addUser(utilisateur));
                        
                        
                        System.out.println("Nouvel utilisateur");
                        
                        utilisateur = new Utilisateur();
                        utilisateur.setId(3);
                        utilisateur.setAdressPostale("45 Avenue des Etats Unis, 75000 Versailles");
                        utilisateur.setEmail("joy@gmail.com");
                        utilisateur.setEnchere(true);
                        utilisateur.setNom("Joy");
                        utilisateur.setPrenom("Jedidja");
                        utilisateur.setPseudonyme("joyjedid");
                        
                        System.out.println("Utilisateur ajoutée : "+sb.addUser(utilisateur));
                //*/        
                        
                } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

    
}
