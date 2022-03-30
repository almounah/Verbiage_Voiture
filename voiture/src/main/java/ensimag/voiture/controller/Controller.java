/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

/**
 *
 * @author almounah
 */
public class Controller {
    private buttonHandler butHandler;
    private QueriesRunner queriesRunner;
    private User user;

    public Controller() {
        this.butHandler = new buttonHandler();
        this.queriesRunner = new QueriesRunner();
        this.user = new User();
    }
    
    
    
}
