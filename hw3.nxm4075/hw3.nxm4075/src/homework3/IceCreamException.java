/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

/**
 *
 * @author Neil
 */
public class IceCreamException extends Exception{
   
    //reply message
    @Override
    public String toString() {
        return "Not Enough Ice Cream";
    }
}
