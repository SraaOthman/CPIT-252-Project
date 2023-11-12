/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication120;

//Observer Pattern
interface OrderObserver {
    void update(String message);
}
class Cart implements OrderObserver {
    private double totalPrice = 0;

    public void addToCart(double price) {
        totalPrice += price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void update(String message) {
        System.out.println("Cart: " + message);
    }
}
