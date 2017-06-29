/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import com.gee.data.entity.Dictionnaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;


/**
 *
 * @author yann-
 * 
 * Nous permet d'intéragir avec la base de données
 */
@Stateless
public class DicoDAO {
    
    @PersistenceContext(unitName = "genPU") // pour savoir dans quelle base agir
    private EntityManager em;

    // Insérer mot en base
    public void insert(Dictionnaire dico){
        em.persist(dico);
    } 
    
    // récupérer liste de mot
    public List<Dictionnaire> find(String slice){
        TypedQuery<Dictionnaire> query = em.createNamedQuery("Dictionnaire.findBySlice",Dictionnaire.class)
                            .setParameter("slice", slice);       
        
        List<Dictionnaire> results = query.getResultList();
        System.out.println(results);
        for (Dictionnaire d : results) {
            System.out.println(d.getWord());
        }           
        
        return results;   
    }
    
    // Récupérer tous les mots
    public List<Dictionnaire> findAllDico(){
        TypedQuery<Dictionnaire> query = em.createNamedQuery("Dictionnaire.findAllDico",Dictionnaire.class);
        List<Dictionnaire> results = query.getResultList();

        return results;   
    }

}
