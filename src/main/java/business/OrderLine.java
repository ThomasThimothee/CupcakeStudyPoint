/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author thomasthimothee
 */
public class OrderLine {
    private final Cupcake cupcake;
    private final int quantity;
    private final double totalPrice;

    public OrderLine(Cupcake cupcake, int quantity, double totalPrice) {
        this.cupcake = cupcake;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    
    
}
