/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.project_gen;


import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import javax.json.*;
import java.io.StringWriter;
import javax.jms.Queue;
import javax.annotation.Resource;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * REST Web Service
 *
 * @author yann-
 * 
 * Notre Web Service Rest qui permet de récup les infos qu'envoit wcf en json
 * 
 * 
 */
@Path("messaging")
@RequestScoped
public class MessagingResource {
    /**
     * Creates a new instance of MessagingResource
     */
    public MessagingResource() {
    }
    
    
    @Inject  
    private JMSContext context;      
    @Resource(lookup = "jms/queueFile")
    private Queue messageQueue; // Noter file d attente dans le server payara jms
    
    // Simple get de test pour vérifier que le server est bien fonctionnel
    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }

    // Méthode pour récupérer les infos envoyées par wcf
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response message(String message) {
        Verif verif = new Verif(); // on créer un objet dans lequel on stocke les infos
        StringReader reader = new StringReader(message);
        String key,file,content;
        try (JsonReader jreader = Json.createReader(reader)) {
            JsonObject jo = jreader.readObject();
            key = jo.getString("key");
            file = jo.getString("file");
            content = jo.getString("text");
        }
        System.out.println(key);
        verif.setContent(content); // On set les attributs de l'objet
        verif.setKey(key);
        verif.setFile(file);

        if(content.length() < 8){ 
            return Response.status(400).entity("Texte trop court : ignoré").build();
        }
        
        sendMessage(verif);
        return Response.accepted().build(); // 202  message reçu et accepté
    }
    
    // On envoit notre objet à la file d attente de jms, au format xml
    private void sendMessage(Verif verif){
        JAXBContext jaxbContext;         
        try {             
            jaxbContext = JAXBContext.newInstance(Verif.class);                
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();                          
            StringWriter writer = new StringWriter();                                    
            jaxbMarshaller.marshal(verif, writer);             
            String xmlMessage = writer.toString(); 
            
            TextMessage msg = context.createTextMessage(xmlMessage);       
            context.createProducer().send(messageQueue, msg); 
 
        } catch (JAXBException ex) { 
            System.out.println("rate");       
        }     
    } 
}
