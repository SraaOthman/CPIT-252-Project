/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication120;

//State Pattern
interface OrderState {
    void processOrder();
}

class PendingState implements OrderState {
    @Override
    public void processOrder() {
        System.out.println("Order is pending processing.");
    }
}
class CompletedState implements OrderState {
    @Override
    public void processOrder() {
        System.out.println("Order is completed.");
    }
}
