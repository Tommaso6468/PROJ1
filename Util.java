import java.util.Scanner;

public class Util {
    // leest een int in en vraagt opnieuw als het ongeldig is
    public static int leesInt(Scanner scanner) {
        while (true) {
            String invoer = scanner.nextLine();
            try {
                return Integer.parseInt(invoer);
            }
            catch (NumberFormatException exception) {
                System.out.println("Ongeldig getal; probeer opnieuw.");
            }
        }
    }
}
