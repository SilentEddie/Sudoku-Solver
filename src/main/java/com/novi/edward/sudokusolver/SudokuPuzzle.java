
package com.novi.edward.sudokusolver;

import java.util.Arrays;

class SudokuPuzzle {
    char[] board;
    

    SudokuPuzzle(String puzzle) {
        this.board = puzzle.replace('.', ' ').toCharArray();
    }
    
    SudokuPuzzle(char[] board) {
        this.board = board;
    }
    
    public String getPuzzleString(){
        String answer = "┌───────┬───────┬───────┐\n│ ";
        for (int i =0; i<81; i++){
            if (i>0&&i%3==0){
                answer+="│ ";
            }
            if (i>0&&i%9==0){
                if (i>0&&(i/9)%3==0){
                    answer+="\n├───────┼───────┼───────┤";
                }
                answer+="\n│ ";
            }
            answer += ""+ board[i] + " ";
        }
        answer += "│\n└───────┴───────┴───────┘";
        return answer;
    }
        
    public char[] getPossibleValues(int field){
        if (board[field] != ' '){
            return new char[]{board[field]};
        }else{
            char[] values = ("123456789").toCharArray();
            values =(difference(values, getRow(field)));
            values =(difference(values, getColumn(field)));
            values =(difference(values, getBox(field)));
            return values;
        }
    }
    
    private char newValue(int field){
        if (board[field] != ' '){
            return board[field];
        }else {
            char[] possibillities = getPossibleValues(field);
            if (possibillities.length==1){
                return possibillities[0];
            } else {
                return ' ';
            }
        }
    }
    
    public void runOnce(){
        char[] result = new char[81];
        for (int i = 0; i<81;i++){
            board[i] = newValue(i);
        }
     }
    
    private char[] difference(char[] row1, char[] row2){
        String result = "";
        for(char row1Char: row1){
            if (!contains(row2, row1Char)){
                result += ""+row1Char; 
            }
        }
        return (result.toCharArray());
    }
    
    private boolean contains(char[] row, char checkChar){
        for(char rowChar: row){
            if (rowChar == checkChar){
                return true;
            }
        }return false;
    }
    
    public String solve(){
        boolean solved = false;
        while (!solved){
            runOnce();
            solved = (difference(board, new char[]{' '}).length==81);
        }
        return getPuzzleString();
    }
    
    private char[] getRow(int field) {
        int start = (field/9)*9;
        return Arrays.copyOfRange(board, start, start+9);
    }

    private char[] getColumn(int field) {
        String result = "";
        for (int i = (field % 9); i <= (field % 9) + 72; i += 9) {
            result += "" + board[i];
        }
        return (result.toCharArray());
    }
    
    private char[] getBox(int field){
        String result = "";
        int column =(((field%9)/3)*3);
        int checkField = (field/27)*27;
        for (int i= 0; i<3;i++ ){
            result += new String(Arrays.copyOfRange(getRow(checkField), column, column+3));
            checkField +=9;
        }
        return result.toCharArray();
    }
}
