/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.gee.data.model.DataAccess;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;


/**
 *
 * @author yann-    
 */
@ManagedBean(name ="Dictionnaire")
@SessionScoped
public class DictionnaireBean implements Serializable {
    
    @ManagedProperty(value="#{word}")
    private String word;
    
    
    private DataAccess data = new DataAccess();
    
    public void insert(){
        data.saveWord();
    }
    
    public String[] find(){
        String words[] = null;
        words = data.findWord(getWord());
        return words;
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
