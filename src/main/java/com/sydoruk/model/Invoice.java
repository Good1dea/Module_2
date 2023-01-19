package com.sydoruk.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Optional;

@Setter
@Getter
public class Invoice {

    private ArrayList<? extends Goods> goods;
    private Customer customer;
    @Getter
    private String type;
    private final int limit;

    public Invoice(final int limit) {
        this.limit = limit;
    }

    public void setType() {
        int sum = goods.stream()
                .map(Goods::getPrice)
                .reduce(0, Integer::sum);
        if(sum > limit){
            type = "wholesale";
        } else{
            type = "retail";
        }
    }

    public void setType(final String type) {
        if(!type.isBlank()) {
            this.type = type;
        }
    }

    public void printInvoice() {
        printTime();
        printCustomer(this.getCustomer());
        System.out.println("Invoice's type: " + this.getType());
        printGoods(this.getGoods());
        System.out.println();
    }

   private void printGoods(final ArrayList<? extends Goods> goods) {
        if(goods == null){
            System.out.println("goods is null");
        } else {
            for (Goods good : goods) {
                if (good == null) {
                    System.out.println("good is null");
                } else if (good.getType().equals(ProductType.TV)) {
                    System.out.println("TV: series - " + good.getSeries() + "; screen type - " + good.getScreenType() +
                            "; price - " + good.getPrice() + "; diagonal - " + ((TV) good).getDiagonal() + "; country - "
                            + ((TV) good).getCountry());
                } else {
                    System.out.println("Telephone: series - " + good.getSeries() + "; screen type - " + good.getScreenType() +
                            "; price - " + good.getPrice() + "; model - " + ((Telephone) good).getModel());
                }
            }
        }
    }

    private void printCustomer(final Customer customer){
        Optional.ofNullable(customer).ifPresentOrElse(print ->
                System.out.println("Customer's ID: " + customer.getId() + "; e-mail: " + customer.getEmail() +
                        "; age: " + customer.getAge()), () -> System.out.println("Customer is 'null'"));
    }

    private void printTime() {
        System.out.println (java.time.LocalDate.now() + "  " + java.time.LocalTime.now());
    }
}