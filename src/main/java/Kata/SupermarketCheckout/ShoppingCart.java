package Kata.SupermarketCheckout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();

    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {

                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
                int quantityAsInt = (int) quantity;

                if (offer.offerType == SpecialOfferType.ThreeForTwo) {

                    double discountTotal = quantity * unitPrice - (( (quantityAsInt / 3) * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
                    receipt.addDiscount(new Discount(p,"3 for 2", -discountTotal));
                }

                if (offer.offerType == SpecialOfferType.TwoForAmount && quantityAsInt >= 2) {

                    double discountTotal = unitPrice * quantity - (offer.argument * (quantityAsInt / 2) + quantityAsInt % 2 * unitPrice);
                    receipt.addDiscount(new Discount(p,"2 for " + offer.argument, -discountTotal));
                }

                if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {

                    double discountTotal = unitPrice * quantity - (offer.argument * (quantityAsInt / 5) + quantityAsInt % 5 * unitPrice);
                    receipt.addDiscount(new Discount(p,"5 for " + offer.argument, -discountTotal));
                }

                if (offer.offerType == SpecialOfferType.TenPercentDiscount) {

                    double discountTotal = quantity * unitPrice * offer.argument / 100.0;
                    receipt.addDiscount(new Discount(p, offer.argument + "% off", -discountTotal));
                }
            }
        }
    }
}