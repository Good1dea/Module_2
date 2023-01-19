package com.sydoruk.service;

import com.sydoruk.model.Customer;
import com.sydoruk.model.Goods;
import com.sydoruk.model.Invoice;
import com.sydoruk.model.ProductType;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {

    private final ArrayList<Invoice> invoices;

    public AnalyticsService(final ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Map<ProductType, Long> quantitySoldTypeGoods() {
        return invoices.stream()
                .flatMap(g -> g.getGoods().stream())
                .collect(Collectors.groupingBy(Goods::getType, Collectors.counting()));
    }

    public Map<Integer, Customer> minSumInvoice() {
        return invoices.stream()
                .min(Comparator.comparingInt(invoice -> invoice.getGoods().stream().mapToInt(Goods::getPrice).sum()))
                .stream()
                .collect(Collectors.toMap(invoice -> invoice.getGoods().stream().mapToInt(Goods::getPrice).sum(),
                        Invoice::getCustomer, (key1, key2) -> key1, HashMap::new));
    }

    public void printMinSum (Map<Integer, Customer> minSumInvoice) {
        System.out.print("Min sum: " + minSumInvoice.keySet().toArray()[0] + "; ");
        Customer c = minSumInvoice.get(minSumInvoice.keySet().toArray()[0]);
        System.out.println("Customer's ID: " + c.getId() + "; e-mail: " + c.getEmail());
    }

    public int sumAllSales() {
        return invoices.stream()
                .flatMap(g -> g.getGoods().stream())
                .mapToInt(Goods::getPrice)
                .sum();
    }

    public long quantityInvoiceRetail() {
        return invoices.stream()
                .filter(t -> t.getType().equals("retail"))
                .count();
    }

    public List<Invoice> invoicesOneProductType() {
        ArrayList<Invoice> onlyOneType = new ArrayList<>();
        invoices.stream().forEach(invoice -> {
            if (invoice.getGoods().stream().allMatch(goodsTV -> goodsTV.getType() == ProductType.TV ||
                    invoice.getGoods().stream().allMatch(goodsPhone -> goodsPhone.getType() == ProductType.TELEPHONE)))
                onlyOneType.add(invoice);
        });
        return onlyOneType;
    }

    public List<Invoice> firstThreeInvoices() {
        return invoices.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Invoice> invoicesCustomerLowAge() {
        return invoices.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < 18)
                .peek(i -> i.setType("low_age"))
                .collect(Collectors.toList());
    }

    public List<Invoice> invoicesSort() {
        Comparator<Invoice> compareByAgeCustomer = Comparator.comparing((invoice -> invoice.getCustomer().getAge()));
        Comparator<Invoice> compareQuantityGoods = Comparator.comparing(invoice -> invoice.getGoods().size());
        Comparator<Invoice> compareSum = Comparator.comparing(invoice -> invoice.getGoods().stream()
                .mapToInt(Goods::getPrice).sum());
        Comparator<Invoice> compare = compareByAgeCustomer.reversed().thenComparing(compareQuantityGoods)
                .thenComparing(compareSum);
        return invoices.stream()
                .sorted(compare)
                .collect(Collectors.toList());
    }

    public void printAllAnalytics(){
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Total quantity sold goods " + quantitySoldTypeGoods());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Min invoices' sum and customer:");
        printMinSum(minSumInvoice());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Total sale sum " + sumAllSales() + " $");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Numbers invoices with type 'retail': " + quantityInvoiceRetail());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Invoices with one product type:");
        printListInvoices(invoicesOneProductType());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("First three invoices:");
        printListInvoices(firstThreeInvoices());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Invoices with customers under 18 years: ");
        printListInvoices(invoicesCustomerLowAge());
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Sorted invoices: ");
        printListInvoices(invoicesSort());
    }

    public void printListInvoices(List<Invoice> invoices) {
        if (invoices.isEmpty() || invoices == null) {
            System.out.println("no invoices");
        } else {
            for (Invoice invoice : invoices) {
                invoice.printInvoice();
            }
        }
    }
}