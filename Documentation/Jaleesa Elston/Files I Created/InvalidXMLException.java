/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.jsu.dish;
/**
 *
 * @author Jaleesa
 */
public class InvalidXMLException extends Exception{

    //constructor that takes a string and a variable to hold that string
    InvalidXMLException (String error){
        String displayError = error;
        displayError = getMessage();
        System.out.println("This xml file is not valid because ");
        System.out.println(displayError);        
    }
}
