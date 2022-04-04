/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.User;
import ensimag.voiture.view.View;

/**
 *
 * @author almounah
 */
public class Controller {
    private ButtonHandler butHandler;
    private QueriesRunner queriesRunner;
    private ViewUpdater viewUpdater;
    private User user;
    private View view;

    public Controller(View view) {
        this.viewUpdater = new ViewUpdater(view);
        this.butHandler = new ButtonHandler(viewUpdater);
        this.queriesRunner = new QueriesRunner();
        this.user = new User();
        this.view = view;
    }
    
    public void run() {
        view.getLoginPage().show();
        
    }
    
}
