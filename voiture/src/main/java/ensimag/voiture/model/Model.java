/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

/**
 *
 * @author almounah
 */
public class Model {
    private QueriesRunner queriesRunner;
    private TimeCalculator timeCalculator;
    private Search search;

    public Model() {
        this.queriesRunner = new QueriesRunner();
        this.timeCalculator = new TimeCalculator();
        this.search = new Search();
    }
    
}
