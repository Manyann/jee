/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.persistence.TypedQuery;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.gee.data.DictionnaireBean;
import com.gee.data.entity.Dictionnaire;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author yann-
 */
@Stateless
@Named
@RequestScoped
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DataAccess {
    
    //@PersistenceContext(unitName = "genPU")
    private EntityManagerFactory em;
        
    private EntityManager getEntityManager(){
        if(em == null){
            em = Persistence.createEntityManagerFactory("genPU");
        }
        return em.createEntityManager();    
    }
    
    /**
     * 
     * Sauvegarder un mot
     *
     */
    public void saveWord() {
        /*DictionnaireBean db =new DictionnaireBean();
        String word = db.getWord();
        System.out.println(word);*/
        EntityManager em = getEntityManager();
        Dictionnaire dico = new Dictionnaire();
        dico.setWord("yann");
        System.out.println("dico.getWord() :");
        System.out.println(dico.getWord());
	em.persist(dico);
    }
    
    // Méthode pour retrouver un mot a partir d'une chaine de caractères 
    public String[] findWord(String slice)
    {
        String words[] = null;
        int i = 0;
        EntityManager em = getEntityManager();
        
        TypedQuery<Dictionnaire> query = em.createNamedQuery("Dictionnaire.findBySlice",Dictionnaire.class)
                            .setParameter("slice", slice);       
        
        List<Dictionnaire> results = query.getResultList();
        System.out.println(results);
        for (Dictionnaire d : results) {
            i++;
            words[i] = d.getWord();
            System.out.println(words[i]);
        }           
        
        return words;        
    }
    
    // Fonction qui retourne tout les mots du dico
    public static String[] getFullDicoAndFormat(){
        String[] toChange = new String[20];
        return toChange;
    }

    
}
