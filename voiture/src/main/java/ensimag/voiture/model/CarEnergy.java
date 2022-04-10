/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ensimag.voiture.model;

/**
 *
 * @author almounah
 */
public enum CarEnergy {
    essence,
    diesel,
    hybride,
    electrique;
        
    public Double getAlpha() {
        switch (this) {
            case essence:
                return 1.5;
              
            case diesel:
                return 1.5;
                
            case hybride:
                return 1.0;
            
            case electrique:
                return 0.5;
            default:
                throw new AssertionError();
        }
    } 
}
