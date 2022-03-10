import java.util.ArrayList;

public class ExamensController {

    ArrayList<Examen> examens = new ArrayList<Examen>();

    public ExamensController() {

    }

    public void lijstExamens() {
        switch (examens.size()) {
            case 0 -> System.out.println("Er zijn geen examens beschikbaar.%n");
            case 1 -> System.out.println("Er is 1 examen beschikbaar:%n");
            default -> System.out.printf("Er zijn %d examens beschikbaar:%n", examens.size());
        }
        for (int i = 0; i < examens.size(); i++) {
            System.out.printf("%d. %s%n", i+1, examens.get(i).getNaam());
        }
    }

    public void examenAfnemen() {

    }

}
