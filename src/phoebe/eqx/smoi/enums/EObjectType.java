/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phoebe.eqx.smoi.enums;

/**
 *
 * @author pavarisa
 */
public enum EObjectType {
    DESTINATION("Destination")
    ;

    String text;
    EObjectType(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }
}
