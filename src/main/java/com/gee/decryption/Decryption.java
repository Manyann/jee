/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.decryption;

import com.gee.data.model.DataAccess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yann-
 */
public class Decryption implements DecryptionInterface {
    
    @Override
    public boolean decrypt(String content){
        String[] dico = new String[30000];
        int frenchWordCount = 0;
        
        dico = DataAccess.getFullDicoAndFormat();
        
        for(String word : dico){
            if(content.contains(word)){
                frenchWordCount ++;
            }
        }
        
        if(frenchWordCount < 15){
            return false;
        }
        
        String email = findEmail(content);
        sendEmail(email);
        
        return true;

    }
    
    @Override
    public String findEmail(String validatedText){
        
        String email="";
        
        Pattern  pattern = Pattern.compile("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}", Pattern.MULTILINE);
        Matcher  matcher = pattern.matcher(validatedText);
        
        while (matcher.find()){
            email = validatedText.substring(matcher.start(), matcher.end());
        }
        
        return email;
    }
    
    @Override
    public void sendEmail(String email){
        // For now 
        System.out.println(email);
    }
}
