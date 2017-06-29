/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.data.metier;

import com.gee.project_gen.Verif;

/**
 *
 * @author yann-
 * 
 * Fichier obsolète , à supprimer 
 */
public interface VerifServiceLocal {
    void sendMessage(Verif verif);
    boolean decrypt(Verif verif);
    String findEmail(String validatedText);
    void sendEmail(String email);
}
