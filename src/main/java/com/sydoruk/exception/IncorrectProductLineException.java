package com.sydoruk.exception;

public class IncorrectProductLineException extends RuntimeException {

    private final String csvLine;

    public IncorrectProductLineException(String csvLine) {
        System.out.println(IncorrectProductLineException.class.getName());
        this.csvLine = csvLine;
    }

    @Override
    public String toString(){
         return  " -- Incorrect product's parameter in csv-file: {" + csvLine + "}";
    }
}