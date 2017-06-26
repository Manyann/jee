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
 */
@Stateless
public class DicoDAO {
    
    @PersistenceContext(unitName = "genPU")
    private EntityManager em;

    public void insert(Dictionnaire student){
        em.persist(student);
    } 
    
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

}
