import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentenController {

    private ArrayList<Student> studenten = new ArrayList<>();

    public void studentToevoegen() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Voer studenten naam in:");
        String naam = scanner.nextLine();
        student.setNaam(naam);
        System.out.println("Voer studentennummer in:");
        int studentnummer = scanner.nextInt();
        student.setStudentennummer(studentnummer);
        studenten.add(student);
    }

    public void studentVerwijderen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer student naam in:");
        int naam = scanner.nextInt();
        for (Student st : studenten) {
            if (st.getNaam().equals(naam)) {
                studenten.remove(st);
            }
        }
    }

    public void studentLijst() {
        for (int i = 0; i < studenten.size(); i++) {
            System.out.println(studenten.get(i).getNaam());
            System.out.println(studenten.get(i).getStudentennummer());
        }
    }

    public Student studentMeesteExamensGehaald() {
        Student maxex = new Student();
        for (Student st : studenten) {
            if (st.getGehaald().size() > maxex.getGehaald().size()) {
                maxex = st;
            }
        }
        return maxex;
    }


    public void studentWelkeExamensGehaald() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer student naam in:");
        String naam = scanner.nextLine();
        for (Student st : studenten) {
            if (st.getNaam().equals(naam)) {
                st.getGehaald();
            }
        }
    }

}
