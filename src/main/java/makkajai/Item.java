package makkajai;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Item {
    private final String name;
    private final BigDecimal price;
    private final boolean isExempt;
    private final boolean isImported;
    private final BigDecimal quantity;

    public Item(String name, BigDecimal price, BigDecimal quantity, boolean isExempt, boolean isImported) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isExempt = isExempt;
        this.isImported = isImported;
    }

    public BigDecimal getPrice() {
        return price.multiply(quantity);
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (!isExempt) {
            tax = tax.add(price.multiply(new BigDecimal("0.1")));
        }
        if (isImported) {
            tax = tax.add(price.multiply(new BigDecimal("0.05")));
        }
        return price.add(roundTax(tax)).multiply(quantity);
    }

    private BigDecimal roundTax(BigDecimal tax) {
        BigDecimal roundedTax = tax.divide(new BigDecimal("0.05"), 0, RoundingMode.UP)
                .multiply(new BigDecimal("0.05"));
        return roundedTax.setScale(2, RoundingMode.HALF_UP);
    }

    public String toString() {
        return quantity + " " + name + ": " + getPriceWithTax();
    }
}