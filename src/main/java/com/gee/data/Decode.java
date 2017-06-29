/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import com.gee.data.entity.Dictionnaire;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author yann-
 * 
 * Fichier de tests , obsolète , à supprimer 
 * 
 * 
 */
public class Decode {
     private List<Dictionnaire> dico;
     
     @Inject
    DicoDAO dicoDAO;
     
    public void decrypt(String content){
        int frenchWordCount = 0;
        
        dico = dicoDAO.findAllDico();
        
        for(Dictionnaire d : dico){
            if(content.contains(d.getWord())){
                frenchWordCount ++;
            }
        }
        
        System.out.println("*******************************************");
        System.out.println(frenchWordCount);
        System.out.println("*******************************************");
        
    }
}
