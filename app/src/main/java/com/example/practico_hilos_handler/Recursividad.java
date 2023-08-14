package com.example.practico_hilos_handler;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;

public class Recursividad implements Callable<Recursividad.Functions> {
    private int number;
    private int numbertwo;
    private String text;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumbertwo() {
        return numbertwo;
    }

    public void setNumbertwo(int numbertwo) {
        this.numbertwo = numbertwo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public  String invertText(String text) {
        if (text.isEmpty()) {
            return text;
        } else {
            char firstChar = text.charAt(0);
            String remainingText = text.substring(1);
            return invertText(remainingText) + firstChar;
        }
    }
    public int calculeMCD(int number, int numbertwo){
        int mayor=number>numbertwo?number:numbertwo;
        int menor=number<numbertwo?number:numbertwo;
        if(menor==0){
            return mayor;
        }else {
            int resto = mayor % menor;
            return calculeMCD(menor,resto);
        }
    }
    @Override
    public Functions call() throws Exception {
        return new Functions();
    }

    public class Functions{
        public String getInvertText(){
            return invertText(text);
        }
        public int getCalculeMCD(){return calculeMCD(number,numbertwo);}
    }
}
