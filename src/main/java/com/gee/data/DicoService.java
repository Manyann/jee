/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import javax.ejb.Stateful;;
import javax.ejb.Remove;

import com.gee.data.entity.Dictionnaire;
import com.gee.data.entity.Dictionnaire;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author yann-
 */
@Stateful
public class DicoService implements DicoServiceLocal {

    private Dictionnaire dic = new Dictionnaire();
     
    @Inject
    DicoDAO dicoDAO;
    
    @Override
    public void addWord(String word) {
        dic.setWord(word);
        System.out.println("Mot ajouté "+word);
        save();
    }
    
    @Override
    public List<Dictionnaire> findWord(String word) {
        System.out.println("Mots trouvés ");
        List<Dictionnaire> words = dicoDAO.find(word);         
        return words;
    }
    
    @Override
    @Remove
    public void save() {
        dicoDAO.insert(dic);
        System.out.println("sauvegarde du mot");
    } 

}
