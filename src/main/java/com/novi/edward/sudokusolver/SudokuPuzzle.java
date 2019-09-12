/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novi.edward.sudokusolver;

import java.util.Arrays;

/**
 *
 * @author edwar
 */
class SudokuPuzzle {
    char[] bord;

    SudokuPuzzle(String PuzzleString) {
        this.bord = PuzzleString.replace('.', ' ').toCharArray();
    }
    
    public char[] getBord() {
        return bord;
    }

    public void setBord(char[] bord) {
        this.bord = bord;
    }

    public SudokuPuzzle(char[] bord) {
        this.bord = bord;
    }
    
    @Override
    public String toString(){
        String answer = "";
        for (int i =0; i<81; i++){
            if (i%9==0&&i!=0){
                answer+="\n";
            }
            answer += ""+ bord[i];
        }
        return answer;
    }
    
    public String toString2(){
        String answer = "|-----------------";
        for (int i =0; i<81; i++){
            if (i%9==0){
                answer+="|\n";
            }
            answer += "|"+ bord[i];
        }
        return answer+"|\n|---------------------|";
    }
    
        public String toString4(){
        String answer = "-----------------------\n| ";
        for (int i =0; i<81; i++){
            if (i>0&&i%3==0){
                answer+="|";
            }
            if (i>0&&i%9==0){
                answer+="\n| ";
            }
            answer += ""+ bord[i] + " ";
            
        }
        answer += "|\n---------------------";
        return answer
    }
        
    public String toString3(){
        return new String(bord);
    }
    
    public char[] getMogelijkeWaarden(int veld){
        String result = "";
        if (bord[veld] != ' '){
            return new char[]{bord[veld]};
        }else{
            char[] mogelijkheden = ("123456789").toCharArray();
            mogelijkheden =(verschil(mogelijkheden, getRij(veld)));
            mogelijkheden =(verschil(mogelijkheden, getKolom(veld)));
            mogelijkheden =(verschil(mogelijkheden, getVak(veld)));
            return mogelijkheden;
        }
    }
    
    public char watIsDeze(int veld){
        //char[] mogelijkheden = new char[];
        if (bord[veld] != ' '){
            return bord[veld];
        }else {
            char[] mogelijkheden = getMogelijkeWaarden(veld);
            if (mogelijkheden.length==1){
                return mogelijkheden[0];
            } else {
                return ' ';
            }
            
        }
    }
    
    public void runOnce(){
        char[] result = new char[81];
            for (int i = 0; i<81;i++){
                result[i] = watIsDeze(i);
            }
        bord = result;
    }
    
    private char[] verschil(char[] rij1, char[] rij2){
        String result = "";
        for(char teken: rij1){
            if (!contains(rij2, teken)){
                result += ""+teken; 
            }
        }
        return (result.toCharArray());

    }
    
    private boolean contains(char[] rij, char teken){
        for(char checkTeken: rij){
            if (checkTeken == teken){
                return true;
            }
        }return false;
    }
    
    public void solve(){
        boolean solved = false;
        while (!solved){
            runOnce();
            solved = (verschil(bord, new char[]{' '}).length==81);
        }
        System.out.println(toString2());

        
    }
    
    private int getRijNum(int veld){
        return (veld/9);
    }
    
    private int getKolomNum(int veld){
        return (veld%9);
    }
    
    private int getVakRij (int veld){
        return (getRijNum(veld)/3);
    }

    private int getVakKolom (int veld){
        return (getKolomNum(veld)/3);
    }
    
    private char[] getRij(int veld) {
        int start = (veld/9)*9;
        return Arrays.copyOfRange(bord, start, start+9);
    }

    private char[] getKolom(int veld) {
        String result = "";
        for (int i = (veld % 9); i <= (veld % 9) + 72; i += 9) {
            result += "" + bord[i];
        }
        return (result.toCharArray());
    }
    
    private char[] getVak(int veld){
        String result = "";
        int kolom =(((veld%9)/3)*3);
        int checkVeld = (veld/27)*27;
        for (int i= 0; i<3;i++ ){
            result += new String(Arrays.copyOfRange(getRij(checkVeld), kolom, kolom+3));
            checkVeld +=9;
        }


        return result.toCharArray();
    }


}
