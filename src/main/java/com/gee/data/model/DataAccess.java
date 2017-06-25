/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data.model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.TypedQuery;
import javax.faces.bean.ManagedProperty;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.gee.data.DictionnaireBean;
import com.gee.data.entity.Dictionnaire;

/**
 *
 * @author yann-
 */
@Stateless
@ManagedBean(name="DataAccess")
@RequestScoped
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class DataAccess {
    
    @PersistenceContext(unitName = "genPU")
    private EntityManager em;
    
    
    @ManagedProperty(value="#{Dictionnaire}")
    private DictionnaireBean dictionnaireBean ;
    
    
    
        /**
     * 
     * Sauvegarder un mot
     * @param dictionnaire mot nouvellement créé
     */
    public void saveWord() {
        String word = dictionnaireBean.getWord();
        Dictionnaire dico = new Dictionnaire();
        dico.setWord(word);
        em.persist(dico) ;
    }
    
    // Méthode pour retrouver un mot a partir d'une chaine de caractères 
    public String[] findWord(String slice)
    {
        String words[] = null;
        int i = 0;
        
        TypedQuery<Dictionnaire> query = em.createNamedQuery("Dictionnaire.findBySplice",Dictionnaire.class)
                            .setParameter("slice", slice);
        List<Dictionnaire> results = query.getResultList();
        
        for (Dictionnaire d : results) {
            i++;
            words[i] = d.getWord();
        }
        
        return words;        
    }
    
    // Fonction qui retourne tout les mots du dico
    public String[] getFullDicoAndFormat(){
        String[] toChange = new String[20];
        return toChange;
    }
    
    public void setDictionnaireBean(DictionnaireBean dictionnaireBean) {
        this.dictionnaireBean = dictionnaireBean ;
    }

    public DictionnaireBean getMarinBean() {
        return this.dictionnaireBean ;
    }
    
}
