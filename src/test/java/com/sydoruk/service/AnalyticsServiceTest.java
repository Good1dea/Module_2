package com.sydoruk.service;

import com.sydoruk.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AnalyticsServiceTest {
    private final ArrayList<Invoice> invoices = new ArrayList<>(3);
    private final PersonService personService = new PersonService();
    private final Customer customerOne = personService.createPerson();
    private final Customer customerTwo = personService.createPerson();
    private final Customer customerThree = personService.createPerson();
    private final Invoice invoiceOne = new Invoice(450);
    private final Invoice invoiceTwo = new Invoice(450);
    private final Invoice invoiceThree = new Invoice(450);
    private final ArrayList<Telephone> goodsOne = new ArrayList<>(2);
    private final ArrayList<TV> goodsTwo = new ArrayList<>(1);
    private final ArrayList<TV> goodsThree = new ArrayList<>(1);
    private final Telephone telephoneOne = new Telephone();
    private final TV tvOne = new TV();
    private final Telephone telephoneTwo = new Telephone();
    private final TV tvTwo = new TV();
    private final AnalyticsService target = new AnalyticsService(invoices);

    @BeforeEach
    void setUp() {
        telephoneOne.setPrice(100);
        telephoneTwo.setPrice(200);
        tvOne.setPrice(400);
        tvTwo.setPrice(500);
        customerOne.setAge(16);
        customerTwo.setAge(35);
        customerThree.setAge(35);
        goodsOne.add(telephoneOne);
        goodsOne.add(telephoneTwo);
        goodsTwo.add(tvOne);
        goodsThree.add(tvTwo);
        invoiceOne.setGoods(goodsOne);
        invoiceOne.setType();
        invoiceOne.setCustomer(customerOne);
        invoiceTwo.setGoods(goodsTwo);
        invoiceTwo.setType();
        invoiceTwo.setCustomer(customerTwo);
        invoiceThree.setGoods(goodsThree);
        invoiceThree.setCustomer(customerThree);
        invoiceThree.setType();
        invoices.add(0,invoiceOne);
        invoices.add(1,invoiceTwo);
        invoices.add(2,invoiceThree);
    }

    @Test
    void quantitySoldGoodsPositive() {
        Map<ProductType, Long> expected = new HashMap<>();
        expected.put(ProductType.TV, 2L);
        expected.put(ProductType.TELEPHONE, 2L);
        Assertions.assertEquals(expected, target.quantitySoldTypeGoods());
    }

    @Test
    void minSumInvoicePositive() {
        Map<Integer, Customer> expected = new HashMap<>();
        expected.put(300, customerOne);
        Assertions.assertEquals(expected, target.minSumInvoice());
    }

    @Test
    void sumAllSalesPositive() {
        int expected = 1200;
        Assertions.assertEquals(expected, target.sumAllSales());
    }

    @Test
    void quantityInvoiceRetailPositive() {
        long expected = 2L;
        Assertions.assertEquals(expected, target.quantityInvoiceRetail());
    }

    @Test
    void invoicesOneProductTypePositive() {
        List<Invoice> expected = new ArrayList<>(3);
        expected.add(invoiceOne);
        expected.add(invoiceTwo);
        expected.add(invoiceThree);
        Assertions.assertEquals(expected, target.invoicesOneProductType());
    }

    @Test
    void firstThreeInvoicesPositive() {
        List<Invoice> expected = new ArrayList<>(3);
        invoices.add(3, new Invoice(450));
        invoices.add(4, new Invoice(450));
        expected.add(invoiceOne);
        expected.add(invoiceTwo);
        expected.add(invoiceThree);
        Assertions.assertEquals(expected, target.firstThreeInvoices());
    }

    @Test
    void invoicesCustomerLowAgePositive() {
        List<Invoice> expected = new ArrayList<>(1);
        expected.add(invoiceOne);
        Assertions.assertEquals(expected, target.invoicesCustomerLowAge());
    }

    @Test
    void invoicesSortPositive() {
        List<Invoice> expected = new ArrayList<>(3);
        expected.add(0, invoiceTwo);
        expected.add(1, invoiceThree);
        expected.add(2,invoiceOne);
        Assertions.assertEquals(expected, target.invoicesSort());
    }
}