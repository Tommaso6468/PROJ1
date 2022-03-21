import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;
import java.util.Scanner;

public class ExamensController extends Controller<Examen> {

    public ExamensController() {
        super();
    }

    private ExamensController(ArrayList<Examen> examens) {
        super(examens);
    }

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
     * Voegt een nieuw examen toe aan de lijst.
     * @param naam De naam van het examen.
     * @param vragen De vragen in het examen.
     * @param minimum Het minimum aantal vragen goed.
     */
    public void voegExamenToe(String naam, Vraag[] vragen, int minimum) {
        voegToe(new Examen(naam, vragen, minimum));
    }

    /**
     * Drukt een lijst met alle examens af.
     */
    public void lijstExamens() {
        Util.printArrayList(getLijst(), "examen beschikbaar", "examens beschikbaar");
    }

    /**
     * Laat de gebruiker een examen uit de lijst kiezen.
     * @param scanner De scanner om de gebruikersinvoer uit te lezen.
     * @return De gekozen examen, of null als er geen examen is gekozen.
     */
    public Examen kiesExamen(Scanner scanner) {
        lijstExamens();
        int aantal = this.getAantal();
        if (aantal == 0) return null;

        System.out.println("Geef het nummer van het examen.");
        return getLijst().get(Util.leesInt(scanner, 1, aantal) - 1);
    }
}
