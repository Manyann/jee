/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import com.gee.data.entity.Dictionnaire;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


/**
 *
 * @author yann-
 * 
 * Fichier valable pendant toute une session
 * Il permet de gérer les actions fait depuis une page jsf
 * Il appelle notre DAO qui lui gérer l'insertion / recherche / etc en bdd
 * 
 * 
 */
@Named(value = "dicoModel")
@SessionScoped
public class DicoBean implements Serializable {
    
    private String word;
    private List<Dictionnaire> listOfWord;
    private int id;
    
    @Inject
    private DicoServiceLocal dicoLocal; // On injecte le service qui nous permet d'appeler les méthodes DAO

    // Méthode pour ajouter un mot dans la bdd
    public String create(){
        dicoLocal.addWord(word);
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "index"; 
    }
    
    // Méthode pour trouver une liste de mot en base celon un pattern donné
    public String findWord(){
        listOfWord = dicoLocal.findWord(word);
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "search"; 
    }
        
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Dictionnaire>  getListOfWord() {
        return listOfWord;
    }
    public void setListOfWord(List<Dictionnaire> listOfWord) {
        this.listOfWord = listOfWord;
    }


    /**
     * Creates a new instance of DicoBean
     */
    public DicoBean() {
    }
    
}
