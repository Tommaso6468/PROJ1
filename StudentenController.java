import java.util.ArrayList;
import java.util.Scanner;

public class StudentenController {

    private ArrayList<Student> studenten = new ArrayList<>();

    public void studentToevoegen() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("Voer studenten naam in:");
        String naam = scanner.nextLine();
        Student.setNaam(naam);
        System.out.println("Voer studentennummer in:");
        int studentnummer = scanner.nextInt();
        Student.setStudentennummer(studentnummer);
        System.out.println("Voer aantal examens gehaald in:");
        int gehaald = scanner.nextInt();
        Student.setGehaald(gehaald);
        studenten.add(student);
    }

    public void studentVerwijderen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voer student naam in:");
        int naam = scanner.nextInt();
        for (Student st : studenten){
        if(st.getNaam().equals("naam")){
            studenten.remove(st);
        }
        }
    }

    public void studentLijst() {
        for (int i=0;i<studenten.size();i++) {
            System.out.println(studenten.get(i).getNaam());
            System.out.println(studenten.get(i).getStudentennummer());
        }
    }

    public void studentMeesteExamensGehaald() {

    }

    public void studentWelkeExamensGehaald() {

    }

}
