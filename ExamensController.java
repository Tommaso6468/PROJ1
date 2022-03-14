import java.util.ArrayList;
import java.util.Scanner;

public class ExamensController {

    ArrayList<Examen> examens = new ArrayList<>();

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
        int aantal = this.getAantalExamens();
        switch (aantal) {
            case 0 -> System.out.println("Er zijn geen examens beschikbaar.%n");
            case 1 -> System.out.println("Er is 1 examen beschikbaar:%n");
            default -> System.out.printf("Er zijn %d examens beschikbaar:%n", aantal);
        }
        for (int i = 0; i < aantal; i++) {
            System.out.printf("%d. %s%n", i+1, examens.get(i).getNaam());
        }
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
        return examens.get(Util.leesInt(scanner, 1, aantal) - 1);
    }
}
