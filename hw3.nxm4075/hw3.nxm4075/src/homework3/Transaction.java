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
public class Transaction {
    
    int pennies, dimes, nickels, quaters, ones, fives, tens, twenties;

    //Calculate the amount the customer has paid
    public double calAmountPaid(){

        double money = (this.pennies / 100) + (this.nickels / 20) + (this.dimes / 10) + (this.quaters / 4) + this.ones + this.fives*5 + this.tens*10 + this.twenties*20;

        return money;
    }
}
