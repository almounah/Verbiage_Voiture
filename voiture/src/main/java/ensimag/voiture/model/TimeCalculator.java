/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import java.time.LocalDateTime;

/**
 *
 * @author almounah
 */
public class TimeCalculator {
    
    private static LocalDateTime previousChunckEndDate;
    
    public static void calculateNextChunckStartTime(Integer travDur, Integer waitDelay) {
        previousChunckEndDate = previousChunckEndDate.plusMinutes(travDur);
        previousChunckEndDate = previousChunckEndDate.plusMinutes(waitDelay);
        System.out.println(travDur + " " + waitDelay + previousChunckEndDate);
    }

    public static LocalDateTime getPreviousChunckEndDate() {
        return previousChunckEndDate;
    }

    public static void setPreviousChunckEndDate(LocalDateTime previousChunckEndDate) {
        TimeCalculator.previousChunckEndDate = previousChunckEndDate;
    }
    
    
}
