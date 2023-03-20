package makkajai;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class IO {
    // private final Receipt receipt;
    private final List<String> exemptedItems;
    private final List<Item> inputItems;
    private final Scanner scanner;

    public IO(Scanner scanner) {
        this.scanner = scanner;
        this.inputItems = new ArrayList<>();
        this.exemptedItems = Arrays.asList("book", "chocolate", "pill");
    }

    public List<Item> readItemInput() {

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                break;
            }

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
            inputItems.add(item);
        }

        scanner.close();

        return inputItems;
    }
}
