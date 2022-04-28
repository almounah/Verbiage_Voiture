/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.Model;
import ensimag.voiture.view.View;

/**
 *
 * @author almounah
 */
public class Controller {
    private ButtonHandler butHandler;
    private ViewUpdater viewUpdater;
    private View view;
    private Model model;
    

    public Controller(View view, Model model) {
        this.viewUpdater = new ViewUpdater(view);
        this.butHandler = new ButtonHandler(viewUpdater);
        this.model = model;
        this.view = view;
    }
    
    public void run() {
        view.getLoginPage().show();
        
    }
    
}
