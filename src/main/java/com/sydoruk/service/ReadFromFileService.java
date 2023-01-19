package com.sydoruk.service;

import com.sydoruk.exception.IncorrectProductLineException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ReadFromFileService {

    private final ArrayList<HashMap<String, String>> csvData = new ArrayList<>();

    public ArrayList<HashMap<String, String>> readFromFile() throws IncorrectProductLineException {
        boolean addMap = false;
        String csvLine;
        ArrayList<String> head = null;
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (final InputStream resourceAsStream = classLoader.getResourceAsStream("products.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            csvLine = br.readLine();
            if (csvLine.length() > 0) {
                head = new ArrayList<String>(Arrays.asList(csvLine.split(",")));
            }
            while ((csvLine = br.readLine()) != null) {
                String[] data = csvLine.split(",");
                HashMap<String, String> csvDataLine = new HashMap<>();
                for (int i = 0; i < data.length; i++) {
                    try {
                        if (!(data[i].isBlank())) {
                            csvDataLine.put(head.get(i), data[i]);
                            addMap = true;
                        } else {
                            throw new IncorrectProductLineException(csvLine);
                        }
                    } catch (IncorrectProductLineException ex) {
                        ex.printStackTrace();
                        addMap = false;

                        break;
                    }
                }
                if (addMap) {
                    csvData.add(csvDataLine);
                }
                addMap = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    public void printShop() {
        for (HashMap<String, String> csvDatum : csvData) {
            System.out.println(csvDatum);
        }
    }
}