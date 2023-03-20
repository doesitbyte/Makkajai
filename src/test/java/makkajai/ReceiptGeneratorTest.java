package makkajai;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class ReceiptGeneratorTest {

        @Test
        public void test1() {
                String input = "1 book at 12.49\n" +
                                "1 music CD at 14.99\n" +
                                "1 chocolate bar at 0.85\n";

                String expectedOutput = "1 book: 12.49\n" +
                                "1 music CD: 16.49\n" +
                                "1 chocolate bar: 0.85\n" +
                                "Sales Taxes: 1.50\n" +
                                "Total: 29.83";

                assertEquals(expectedOutput.trim(), generateReceipt(input).trim());
        }

        @Test
        public void test2() {
                String input = "1 imported box of chocolates at 10.00\n" +
                                "1 imported bottle of perfume at 47.50\n";

                String expectedOutput = "1 imported box of chocolates: 10.50\n" +
                                "1 imported bottle of perfume: 54.65\n" +
                                "Sales Taxes: 7.65\n" +
                                "Total: 65.15";

                assertEquals(expectedOutput.trim(), generateReceipt(input).trim());
        }

        @Test
        public void test3() {
                String input = "1 imported bottle of perfume at 27.99\n" +
                                "1 bottle of perfume at 18.99\n" +
                                "1 packet of headache pills at 9.75\n" +
                                "1 box of imported chocolates at 11.25\n";

                String expectedOutput = "1 imported bottle of perfume: 32.19\n" +
                                "1 bottle of perfume: 20.89\n" +
                                "1 packet of headache pills: 9.75\n" +
                                "1 box of imported chocolates: 11.85\n" +
                                "Sales Taxes: 6.70\n" +
                                "Total: 74.68";

                assertEquals(expectedOutput.trim(), generateReceipt(input).trim());
        }

        @Test
        public void test4() {
                String input = "2 imported bottle of perfume at 27.99\n" +
                                "2 bottle of perfume at 18.99\n" +
                                "2 packet of headache pills at 9.75\n" +
                                "2 box of imported chocolates at 11.25\n";

                String expectedOutput = "2 imported bottle of perfume: 64.38\n" +
                                "2 bottle of perfume: 41.78\n" +
                                "2 packet of headache pills: 19.50\n" +
                                "2 box of imported chocolates: 23.70\n" +
                                "Sales Taxes: 13.40\n" +
                                "Total: 149.36";

                assertEquals(expectedOutput.trim(), generateReceipt(input).trim());
        }

        private String generateReceipt(String input) {
                System.setIn(new ByteArrayInputStream(input.getBytes()));
                ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
                System.setOut(new PrintStream(newConsole));
                Scanner scanner = new Scanner(System.in);
                ReceiptGenerator receiptGenerator = new ReceiptGenerator();
                receiptGenerator.generateReceipt(scanner);
                return newConsole.toString();
        }
}
