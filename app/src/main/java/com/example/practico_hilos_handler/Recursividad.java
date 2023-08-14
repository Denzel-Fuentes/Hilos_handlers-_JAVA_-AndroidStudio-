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
    public int maxNumberArray(String input){
        String[] parts = input.split(","); // Dividir la cadena en partes usando la coma
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]); // Convertir cada parte a entero
        }
        return findMax(numbers,0,parts.length-1);
    }
    public static int sumOfFirstNNumbers(int n) {
        if (n <= 0) {
            return 0; // Caso base: suma de los primeros 0 números es 0
        } else {
            return n + sumOfFirstNNumbers(n - 1); // Suma de "n" más la suma de los primeros "n-1" números
        }
    }
    public static int findMax(int[] arr, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return arr[startIndex]; // Caso base: solo hay un elemento
        } else {
            int mid = (startIndex + endIndex) / 2;

            // Dividir el arreglo en dos partes y encontrar el máximo en cada parte
            int maxLeft = findMax(arr, startIndex, mid);
            int maxRight = findMax(arr, mid + 1, endIndex);

            // Combinar los resultados de las dos partes para encontrar el máximo global
            return Math.max(maxLeft, maxRight);
        }
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
        public int getMaxNumberArray(){ return maxNumberArray(text);}
        public int getSumNumberNatural(){return sumOfFirstNNumbers(number);}
    }
}
