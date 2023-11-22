package Kata.supermarket;

import Kata.supermarket.model.*;
import Kata.supermarket.processor.*;
import java.util.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();
    List<OfferChecker> offerCheckerList = Arrays.asList(
            new ThreeForTwoOfferChecker(),
            new TwoForAmountOfferChecker(),
            new FiveForAmountOfferChecker(),
            new TenPercentDiscountOfferChecker()
    );

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

                Request request = new Request(offers.get(p), catalog.getUnitPrice(p), quantity);
                for( OfferChecker offerChecker : offerCheckerList ) {
                    offerChecker.check(receipt, request);
                }
            }
        }
    }
}