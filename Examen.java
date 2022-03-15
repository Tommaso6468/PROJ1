import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.Arrays;
import java.util.Scanner;

public class Examen {

    private final String naam;
    private final Vraag[] vragen;
    private final int minimum;

    /**
     * Construeert een Examen.
     * @param naam De naam van het examen.
     * @param vragen De lijst met vragen in dit examen.
     * @param minimum Het minimum aantal vragen dat goed moeten zijn om dit examen te halen.
     */
    public Examen(String naam, Vraag[] vragen, int minimum) {
        if (naam == null || vragen == null) throw new IllegalArgumentException();
        this.naam = naam;
        this.vragen = vragen;
        this.minimum = minimum;
    }

    /**
     * Leest de info over een examen uit een JSON-object.
     * @param object Het object met de info over dit examen.
     * @return Het Examen geconstrueerd met de info uit het object, of null als de JSON niet goed was geformatteerd.
     * @throws InvalidJsonFormatException De vraag is niet goed geformatteerd.
     */
    public static Examen leesVanJson(JsonObject object) throws InvalidJsonFormatException {
        String naam = Util.leesJsonString(object, "naam");
        Object vragenInput = Util.getJsonWaardeOfThrow(object, "vragen");
        if (!(vragenInput instanceof JsonArray vragenArray)) {
            throw new InvalidJsonFormatException("vragen", "moet een array zijn");
        }
        Vraag[] vragen = new Vraag[vragenArray.size()];
        for (int i = 0; i < vragen.length; i++) {
            if (!(vragenArray.get(i) instanceof JsonObject vraagObject)) {
                throw new InvalidJsonFormatException("vragen", "moet objecten bevatten");
            }
            vragen[i] = Vraag.leesVanJson(vraagObject);
        }
        int minimum = Util.leesJsonInt(object, "minimum", 0, vragen.length);
        return new Examen(naam, vragen, minimum);
    }

    /**
     * Controleert of een aantal vragen goed voldoende is om dit examen te slagen.
     * @param aantalGoed Het aantal vragen dat goed is.
     * @return True als het voldoende is, anders false.
     */
    public boolean isVoldoende(int aantalGoed) {
        return aantalGoed >= minimum;
    }

    /**
     * Krijgt de naam van dit examen.
     * @return De naam van dit examen.
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Neemt het examen af; stelt alle vragen in het examen en checkt of het minimum aantal goed is gehaald.
     * @param scanner De scanner om de gebruikersinvoer uit te lezen.
     * @return True als het minimum aantal goed is gehaald; anders false.
     */
    public boolean neemAf(Scanner scanner) {
        int aantalGoed = 0;
        for (int i = 0; i < vragen.length; i++) {
            System.out.println();
            System.out.printf("Vraag %d:%n", i+1);
            boolean isGoed = vragen[i].stelVraag(scanner);
            if (isGoed) {
                aantalGoed++;
            }
        }
        System.out.println();
        System.out.printf("Het examen is voltooid. U heeft %d vragen goed.%n", aantalGoed);
        return isVoldoende(aantalGoed);
    }

    @Override
    public String toString() {
        return naam;
    }
}
