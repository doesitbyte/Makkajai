import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

public class ReceiptGenerator {
    private final List<Item> items;
    private BigDecimal salesTax;
    private BigDecimal total;

    public ReceiptGenerator() {
        items = new ArrayList<>();
        salesTax = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
    }

    public void addItem(Item item) {
        items.add(item);
        salesTax = salesTax.add(item.getPriceWithTax().subtract(item.getPrice()));
        total = total.add(item.getPriceWithTax());
    }

    public void generateReceipt() {
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("Sales Taxes: " + salesTax.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Total: " + total.setScale(2, RoundingMode.HALF_UP));
    }

    public static List<String> exemptedItems = Arrays.asList("book", "chocolate", "pill");

    public static void main(String[] args) {
        ReceiptGenerator receipt = new ReceiptGenerator();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(" ");
            BigDecimal quantity = new BigDecimal(tokens[0]);
            BigDecimal price = new BigDecimal(tokens[tokens.length - 1]);
            String name = String.join(" ", Arrays.asList(tokens).subList(1, tokens.length - 2));

            boolean isExempt = false;
            boolean isImported = false;

            for (String exemptItem : exemptedItems) {
                if (name.contains(exemptItem)) {
                    isExempt = true;
                }
            }

            if (name.contains("imported")) {
                isImported = true;
            }

            Item item = new Item(name, price, quantity, isExempt, isImported);
            receipt.addItem(item);
        }

        scanner.close();

        receipt.generateReceipt();
    }
}
