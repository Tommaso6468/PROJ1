import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;
import java.util.Scanner;

public class ExamensController {

    private final ArrayList<Examen> examens;

    public ExamensController() {
        examens = new ArrayList<>();
    }

    private ExamensController(ArrayList<Examen> examens) {
        this.examens = examens;
    }

    public static int gekozenExamenIndex;

    /**
     * Leest de info over alle examens uit een JSON-array.
     * @param array De array met de info over alle examens.
     * @return De ExamensController geconstrueerd met de info uit de array.
     * @throws InvalidJsonFormatException De examens zijn niet goed geformatteerd.
     */
    public static ExamensController leesVanJson(JsonArray array) {
        ArrayList<Examen> examens = new ArrayList<>(array.size());
        for (Object examenInput : array) {
            if (!(examenInput instanceof JsonObject examenObject)) {
                return null;
            }
            examens.add(Examen.leesVanJson(examenObject));
        }
        return new ExamensController(examens);
    }

    /**
     * Krijgt de lijst met examens.
     * @return De ArrayList die de examens bevat.
     */
    public ArrayList<Examen> getExamens() {
        return examens;
    }

    /**
     * Krijgt het aantal examens in de lijst.
     * @return Het aantal examens in de lijst.
     */
    public int getAantalExamens() {
        return examens.size();
    }

    /**
     * Voegt een nieuw examen toe aan de lijst.
     * @param naam De naam van het examen.
     * @param vragen De vragen in het examen.
     * @param minimum Het minimum aantal vragen goed.
     */
    public void voegExamenToe(String naam, Vraag[] vragen, int minimum) {
        examens.add(new Examen(naam, vragen, minimum));
    }

    /**
     * Voegt een examen toe aan de lijst.
     * @param examen Het examen om toe te voegen.
     */
    public void voegExamenToe(Examen examen) {
        if (examen == null) {
            throw new IllegalArgumentException("Parameter examen moet niet null zijn.");
        }
        examens.add(examen);
    }

    /**
     * Drukt een lijst met alle examens af.
     */
    public void lijstExamens() {
        Util.printArrayList(examens, "examen beschikbaar", "examens beschikbaar");
    }

    /**
     * Laat de gebruiker een examen uit de lijst kiezen.
     * @param scanner De scanner om de gebruikersinvoer uit te lezen.
     * @return De gekozen examen, of null als er geen examen is gekozen.
     */
    public Examen kiesExamen(Scanner scanner) {
        lijstExamens();
        int aantal = this.getAantalExamens();
        if (aantal == 0) return null;

        System.out.println("Geef het nummer van het examen.");
        gekozenExamenIndex = Util.leesInt(scanner, 1, aantal) - 1;
        return examens.get(gekozenExamenIndex);
    }
}
