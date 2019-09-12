/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novi.edward.sudokusolver;

/**
 *
 * @author edwar
 */
public class Main {
    public static void main(String[] args) {
                String PuzzleString =  "1.......6" 
                + "..6.2.7.."
                + "78945.1.3"
                + "...8.7..4"
                + "....3...."
                + ".9...42.1"
                + "31297..4."
                + ".4..12.78"
                + "9.8......";
        if (PuzzleString.length() == 81) {
            SudokuPuzzle testPuzzle = new SudokuPuzzle(PuzzleString);
            System.out.println("Deze Puzzel gaat erin");
            System.out.println(testPuzzle.toString4());
            System.out.println("Nu vragen we hem om te solven");
            testPuzzle.solve();
        }

    }
}
