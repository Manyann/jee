/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data.metier;

import com.gee.data.DicoServiceLocal;
import com.gee.data.entity.Dictionnaire;
import com.gee.project_gen.Verif;
import java.io.StringWriter;
import javax.jms.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author yann-
 * 
 * Fichier inutile , à supprimer 
 * 
 * 
 * 
 */
import java.util.List;
@Stateless
public class VerifService implements VerifServiceLocal {
    
    
    
    private List<Dictionnaire> dico;
    
    @Inject //paquetage javax.inject     
    private JMSContext context; //paquetage javax.jms          
    @Resource(lookup = "jms/queueFile") //paquetage javax.annotation     
    private Queue messageQueue; //paquetage javax.jms  
     
    @Inject
    private DicoServiceLocal dicoLocal;
    
     @Override
     public void sendMessage(Verif verif){
        JAXBContext jaxbContext;         
        try {             
            jaxbContext = JAXBContext.newInstance(Verif.class);                
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();                          
            StringWriter writer = new StringWriter();                                    
            jaxbMarshaller.marshal(verif, writer);             
            String xmlMessage = writer.toString();             //          
            System.out.println(xmlMessage); 
            
            TextMessage msg = context.createTextMessage(xmlMessage);       
            context.createProducer().send((Destination) messageQueue, msg); 
 
        } catch (JAXBException ex) { 
            System.out.println("rate");       
        }     
    } 
     
     
    @Override
    public boolean decrypt(Verif verif){
        int frenchWordCount = 0;
        
        dico = dicoLocal.findAllDico();
        
        for(Dictionnaire d : dico){
            if(verif.getContent().contains(d.getWord())){
                frenchWordCount ++;
            }
        }
        
        
        if(frenchWordCount < 15){
            return false;
        }
        System.out.println(frenchWordCount);
        String email = findEmail(verif.getContent());
        sendEmail(email);
        
        return true;

    }
    
    @Override
    public String findEmail(String validatedText){
        
        String email="";
        
        Pattern  pattern = Pattern.compile("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}", Pattern.MULTILINE);
        Matcher  matcher = pattern.matcher(validatedText);
        
        while (matcher.find()){
            email = validatedText.substring(matcher.start(), matcher.end());
        }
        
        return email;
    }
    
    @Override
    public void sendEmail(String email){
        // For now 
        System.out.println(email);
    }
     
}
