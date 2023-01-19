package com.sydoruk.service;

import com.sydoruk.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ShopService {

    private final Random random = new Random();
    private final ArrayList<Invoice> invoices = new ArrayList<>();
    private final ArrayList<HashMap<String, String>> csvData;

    public ShopService(final ArrayList<HashMap<String, String>> csvData) {
        this.csvData = csvData;
    }

    public ArrayList<? extends Goods> createGoods(final int count) {
        ArrayList<Goods> goods = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Goods good = null;
            final HashMap<String, String> product = csvData.get(random.nextInt(0, (csvData.size())));
            if (product.get("type").equals("Telephone")) {
                good = new Telephone();
                ((Telephone) good).setModel(product.get("model"));
            } else if (product.get("type").equals("TV")) {
                good = new TV();
                ((TV) good).setDiagonal(Integer.parseInt(product.get("diagonal")));
                ((TV) good).setCountry(product.get("country"));
            }
            good.setSeries(product.get("series"));
            good.setScreenType(product.get("screen type"));
            good.setPrice(Integer.parseInt(product.get("price")));
            goods.add(good);
        }
        return goods;
    }

    public ArrayList<Invoice> createInvoices(int count) {
        for (int i = 0; i < count; i++) {
            Invoice invoice = new Invoice(5000);
            PersonService personService = new PersonService();
            invoice.setCustomer(personService.createPerson());
            invoice.setGoods(createGoods(random.nextInt(1, 6)));
            invoice.setType();
            invoice.printInvoice();
            invoices.add(invoice);
        }
        return invoices;
    }
}