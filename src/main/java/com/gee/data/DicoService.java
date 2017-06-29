/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;


import com.gee.data.entity.Dictionnaire;
import com.gee.project_gen.Verif;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author yann-
 * 
 * Service appelant les méthodes du DAO
 */
@Stateless
public class DicoService implements DicoServiceLocal {

    

     
    @Inject
    DicoDAO dicoDAO;
    
    // On peuple notre objet verif avec l info a ajouter en bdd
    @Override
    public void addWord(String word) {
        Dictionnaire dic = new Dictionnaire();
        Verif verif = new Verif();
        verif.setContent(word);
        dic.setWord(word);
        save(dic);
    }
    
    // récupération d'une liste
    @Override
    public List<Dictionnaire> findWord(String word) {
        List<Dictionnaire> words = dicoDAO.find(word);         
        return words;
    }
    
    // on enregistre en bdd un mot
    private void save(Dictionnaire dic) {
        dicoDAO.insert(dic);
        System.out.println("sauvegarde du mot");
    } 
    
    // on récupère tous les mots de la bdd
    @Override
    public List<Dictionnaire> findAllDico() {
        System.out.println("Mots trouvés ");
        List<Dictionnaire> words = dicoDAO.findAllDico();         
        return words;
    }

}
