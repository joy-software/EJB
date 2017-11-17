/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master2.datascale.auctionmanager.client;

import com.master2.datascale.auctionmanager.bean.StatefulAuctionManagerBeanRemote;
import com.master2.datascale.auctionmanager.entity.Enchere;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *La classe cliente de l'auctionManager
 * @author joy
 */
public class Client {
    private static Scanner scan = new Scanner(System.in);
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		      StatefulAuctionManagerBeanRemote stb;
		
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
			stb = (StatefulAuctionManagerBeanRemote)ic.lookup("com.master2.datascale.auctionmanager.bean.StatefulAuctionManagerBeanRemote");
			
                        System.out.println("Authentification de joyjedid@gmail.com");
                        boolean auth = stb.auth("joyjedid@gmail.com", "joyjedid");
                        System.out.println("success :"+auth);
                        int id1=0;
                        if(auth)
                        {
                            id1 = stb.findID("joyjedid@gmail.com");
                        }
                        
                        System.out.println("creation d'une enchere");
                        Enchere enchere = new Enchere();
                        enchere.setId(1);
                        enchere.setStartPrice(200);
                        enchere.setautomaticBid(10);
                        enchere.setmaxBidPeriod(Duration.ofMinutes(1));
                        enchere.setStatut("not started");
                        System.out.println("success: "+stb.createAuction(enchere,1,1));//*/
                        
                        System.out.println("Activation d'une enchere");
                        System.out.println("success :"+stb.startAuction(1));
                        
                        
                        System.out.println("creation d'une enchere");
                        enchere = new Enchere();
                        enchere.setId(2);
                        enchere.setStartPrice(100);
                        enchere.setautomaticBid(10);
                        enchere.setmaxBidPeriod(Duration.ofSeconds(30));
                        enchere.setStatut("not started");
                        System.out.println("success: "+stb.createAuction(enchere,1,2));//*/
                        
                        System.out.println("Activation d'une enchere");
                        System.out.println("success :"+stb.startAuction(1));
                        
                        
                        System.out.println("Authentification de joy@gmail.com");
                        auth = stb.auth("joy@gmail.com", "joyjedid");
                        System.out.println("success :"+auth);
                        int id2=0;
                        if(auth)
                        {
                            id2 = stb.findID("joy@gmail.com");
                        }
                        
                        System.out.println("Soumission d'une offre:");
                        System.out.println(stb.sendBid(id2, 1));
                        
                          System.out.println("Authentification de joy@gmail.com");
                        auth = stb.auth("joyjed@gmail.com", "joyjedid");
                        System.out.println("success :"+auth);
                        int id3=0;
                        if(auth)
                        {
                            id3 = stb.findID("joyjed@gmail.com");
                        }
                        
                        System.out.println("Soumission d'une offre:");
                        System.out.println(stb.sendBid(id3, 1));
                        System.out.println("Soumission d'une offre:");
                         System.out.println(stb.sendBid(id2, 2));
                         System.out.println("Soumission d'une offre:");
                          System.out.println(stb.sendBid(id3, 2));
                          System.out.println("Soumission d'une offre:");
                           System.out.println(stb.sendBid(id2, 1));
                           System.out.println("Soumission d'une offre:");
                            System.out.println(stb.sendBid(id3, 1));
                          System.out.println("Soumission d'une offre:");
                           System.out.println(stb.sendBid(id2, 2));
                           System.out.println("Soumission d'une offre:");
                            System.out.println(stb.sendBid(id3, 2));
                            
                        System.out.println("Fermeture de l'enchere 1 par son proprietaire");
                        System.out.println("success: "+stb.closeAuction(1));
                      
                          System.out.println("Soumission d'une offre:");
                           System.out.println(stb.sendBid(id2, 2));
                           System.out.println("Soumission d'une offre:");
                            System.out.println(stb.sendBid(id3, 2));
                            
                          System.out.println("Soumission d'une offre:");
                           System.out.println(stb.sendBid(id2, 2));
                           System.out.println("Soumission d'une offre:");
                            System.out.println(stb.sendBid(id3, 2));
                            
                          System.out.println("Soumission d'une offre:");
                           System.out.println(stb.sendBid(id2, 2));
                           System.out.println("Soumission d'une offre:");
                            System.out.println(stb.sendBid(id3, 2));
                        
                } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
