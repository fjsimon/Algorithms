package Kata.supermarket.model;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Receipt {

    private List<ReceiptItem> items = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    public Double getTotalPrice() {
        double total = 0.0;
        for (ReceiptItem item : this.items) {
            total += item.getTotalPrice();
        }
        for (Discount discount : this.discounts) {
            total += discount.getDiscountAmount();
        }
        return total;
    }

    public void addProduct(Product p, double quantity, double price, double totalPrice) {
        this.items.add(new ReceiptItem(p, price, totalPrice, quantity));
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

}