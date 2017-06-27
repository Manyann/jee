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
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


/**
 *
 * @author yann-
 */
@Named(value = "dicoModel")
@SessionScoped
public class DicoBean implements Serializable {
    
    private String word;
    private List<Dictionnaire> listOfWord;
    private int id;
    
    @Inject
    private DicoServiceLocal dicoLocal;

    public String create(){
        System.out.println("création du mot " + word);
        dicoLocal.addWord(word);
        HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "index"; 
    }
    
    public String findWord(){
        System.out.println("recherche du mot " + word);
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
