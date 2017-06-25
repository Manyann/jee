/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.project_gen;

import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import javax.json.*;


/**
 * REST Web Service
 *
 * @author yann-
 */
@Path("messaging")
public class MessagingResource {
    /**
     * Creates a new instance of MessagingResource
     */
    public MessagingResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(String message) {
        
        StringReader reader = new StringReader(message);
        String content;
        try (JsonReader jreader = Json.createReader(reader)) {
            JsonObject jo = jreader.readObject();
            content = jo.getString("content");
        }
        
        System.out.println(content);
        
        if(content.length() < 30){ 
            return Response.status(400).entity("Texte trop court : ignoré").build();
        }
        return Response.accepted().build();
    }
}
