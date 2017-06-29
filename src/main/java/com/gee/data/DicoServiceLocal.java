/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data;

import com.gee.data.entity.Dictionnaire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yann-
 * 
 * Interface de dicoService
 * 
 */
@Local
public interface DicoServiceLocal {
    public void addWord(String word);
    public List<Dictionnaire> findAllDico();
    public List<Dictionnaire> findWord(String word);

}
