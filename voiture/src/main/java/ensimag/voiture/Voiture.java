/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ensimag.voiture;

import ensimag.voiture.controller.Controller;
import ensimag.voiture.view.HomePage;

/**
 *
 * @author almounah
 */
public class Voiture {

    public static void main(String[] args) {
        Controller controller = new Controller();
        HomePage page = new HomePage();
        page.show();
    }
}
