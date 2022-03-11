import java.util.ArrayList;
import java.util.Scanner;

public class ExamensController {

    ArrayList<Examen> examens = new ArrayList<Examen>();

    public ArrayList<Examen> getExamens() {
        return examens;
    }

    public int getAantalExamens() {
        return examens.size();
    }

    public void voegExamenToe(String naam, Vraag[] vragen, int minimum) {
        examens.add(new Examen(naam, vragen, minimum));
    }

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

    public boolean kiesEnStartExamen(Scanner scanner) {
        lijstExamens();
        int aantal = this.getAantalExamens();
        if (aantal == 0) return;

        while (true) {
            System.out.println("Geef het nummer van het examen dat u wilt afnemen.");
            int index = Util.leesInt(scanner) - 1;
            if (index < 0 || index > aantal) {
                System.out.println("Er is geen examen met dat nummer; probeer opnieuw.");
            }
            else {
                return examens.get(index).neemAf();
            }
        }
    }
}
