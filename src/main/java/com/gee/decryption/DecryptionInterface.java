/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee.decryption;

/**
 *
 * @author yann-
 */
public interface DecryptionInterface {
    boolean decrypt(String content);
    String findEmail(String validatedText);
    void sendEmail(String email);
}
