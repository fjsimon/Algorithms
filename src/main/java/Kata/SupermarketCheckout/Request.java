package Kata.SupermarketCheckout;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Request {

    Offer offer;
    double unitPrice;
    double quantity;
}
