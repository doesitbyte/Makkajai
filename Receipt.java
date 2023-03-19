import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Receipt {
    private final List<Item> items;
    private BigDecimal salesTax;
    private BigDecimal total;

    public Receipt() {
        items = new ArrayList<>();
        salesTax = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    public void addItem(Item item) {
        items.add(item);
        salesTax = salesTax.add(item.getPriceWithTax().subtract(item.getPrice()));
        total = total.add(item.getPriceWithTax());
    }

    public void printReceipt() {
        for (Item item : items) {
            System.out.print(item.toString() + "\n");
        }
        System.out.print("Sales Taxes: " + salesTax.setScale(2, RoundingMode.HALF_UP) + "\n");
        System.out.print("Total: " + total.setScale(2, RoundingMode.HALF_UP) + "\n");
    }
}