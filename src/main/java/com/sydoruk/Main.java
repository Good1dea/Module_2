package com.sydoruk;

import com.sydoruk.model.Invoice;
import com.sydoruk.service.ReadFromFileService;
import com.sydoruk.service.AnalyticsService;
import com.sydoruk.service.ShopService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        ReadFromFileService readFromFileService = new ReadFromFileService();
        ShopService shopService = new ShopService(readFromFileService.readFromFile());
        ArrayList<Invoice> invoices = shopService.createInvoices(15);
        AnalyticsService saleAnalytics = new AnalyticsService(invoices);
        saleAnalytics.printAllAnalytics();
        }
}
