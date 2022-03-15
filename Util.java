import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.Scanner;

public class Util {
    /**
     * Leest een int van een scanner en vraagt opnieuw als het ongeldig is.
     * @param scanner De scanner om de invoer uit te lezen.
     * @param min Het laagst toegestane getal.
     * @param max Het hoogst toegestane getal.
     * @return Het getal dat is uitgelezen.
     */
    public static int leesInt(Scanner scanner, int min, int max) {
        while (true) {
            String invoer = scanner.nextLine();
            int getal;
            try {
                getal = Integer.parseInt(invoer);
            }
            catch (NumberFormatException exception) {
                System.out.println("Ongeldig getal; probeer opnieuw.");
                continue;
            }
            if (getal < min || getal > max) {
                System.out.printf("Het moet minstens %d en hoogstens %d zijn; probeer opnieuw.%n", min, max);
                continue;
            }
            return getal;
        }
    }

    /**
     * Verkrijgt een waarde uit een JSON-object, en genereert een foutmelding als het niet bestaat.
     * @param object Het JSON-object dat de waarde moet bevatten.
     * @param key De naam van de eigenschap.
     * @return De waarde uit het JSON-object.
     * @throws InvalidJsonFormatException Er is geen waarde.
     */
    public static Object getJsonWaardeOfThrow(JsonObject object, String key) throws InvalidJsonFormatException {
        if (!object.containsKey(key)) {
            throw new InvalidJsonFormatException(key, "mist");
        }
        return object.get(key);
    }

    /**
     * Verkrijgt een string uit een JSON-object.
     * @param object Het JSON-object dat de string moet bevatten.
     * @param key De naam van de eigenschap.
     * @return De string uit het JSON-object.
     * @throws InvalidJsonFormatException Er is geen waarde of het is niet een string.
     */
    public static String leesJsonString(JsonObject object, String key) throws InvalidJsonFormatException {
        Object value = getJsonWaardeOfThrow(object, key);
        if (value == null) {
            throw new InvalidJsonFormatException(key, "moet niet null zijn");
        }
        return value.toString();
    }

    /**
     * Verkrijgt een getal uit een JSON-object.
     * @param object Het JSON-object dat het getal moet bevatten.
     * @param key De naam van de eigenschap.
     * @param min De kleinst toegestane waarde.
     * @param max De hoogst toegestane waarde.
     * @return De waarde uit het JSON-object.
     * @throws InvalidJsonFormatException Er is geen waarde of het is niet een getal binnen het toegestane bereik.
     */
    public static int leesJsonInt(JsonObject object, String key, int min, int max) throws InvalidJsonFormatException {
        Object value = getJsonWaardeOfThrow(object, key);
        if (!(value instanceof Number number)) {
            throw new InvalidJsonFormatException(key, "met een getal zijn");
        }
        long numberValue = number.longValue();
        if (numberValue < min) {
            throw new InvalidJsonFormatException(key, "is te klein");
        }
        if (numberValue > max) {
            throw new InvalidJsonFormatException(key, "is te groot");
        }
        return (int)numberValue;
    }
}
