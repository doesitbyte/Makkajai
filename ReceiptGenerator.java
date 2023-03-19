import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceiptGenerator {

    private List<Item> itemInput;
    private Receipt receipt;

    public ReceiptGenerator() {
        this.itemInput = new ArrayList<>();
        this.receipt = new Receipt();
    }

    void generateReceipt(Scanner scanner) {

        IO io = new IO(scanner);
        itemInput = io.readItemInput();

        for (Item item : itemInput) {
            receipt.addItem(item);
        }

        receipt.printReceipt();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        receiptGenerator.generateReceipt(scanner);
    }
}