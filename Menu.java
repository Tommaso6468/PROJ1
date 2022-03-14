import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private boolean isGeslaagdExamen;

    public void startMenu(){
        Scanner scanner = new Scanner(System.in);

        //constructors
        ExamensController examensController = new ExamensController();
        StudentenController studentenController = new StudentenController();

        int keuze;
        while(true) {
            System.out.println("1) Lijst met examens");
            System.out.println("2) Lijst met studenten");
            System.out.println("3) Nieuwe student inschrijven");
            System.out.println("4) Student verwijderen");
            System.out.println("5) Examen afnemen");
            System.out.println("6) Is student geslaagd voor test?");
            System.out.println("7) Welke examens heeft student gehaald?");
            System.out.println("8) Welke student heeft de meeste examens gehaald?");
            System.out.println("0) Exit");

            ArrayList<Student> studenten = studentenController.studentLijst();
            int aantalStudenten = studenten.size();

            ArrayList<Examen> examen = examensController.getExamens();
            int aantalExamen = examen.size();

            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    System.out.println("Examens:");

                    for(int i = 0; i < aantalExamen; i++){
                        System.out.println(examen.get(i));
                    }
                    break;
                case 2:
                    System.out.println("Studenten:");

                    for(int i = 0; i < aantalStudenten; i++){
                        System.out.println(studenten.get(i));
                    }
                    break;
                case 3:
                    studentenController.studentToevoegen();
                    break;
                case 4:
                    studentenController.studentVerwijderen();
                    break;
                case 5:
                    //check of student in lijst staat
                    System.out.println("Voer je leerlingennummer in:");
                    int studentenNummer = scanner.nextInt();

                    //loopt door studentenLijst om te zoeken naar het leerlingnummer
                    for (int i = 0; i < aantalStudenten; i++) {
                        if (studenten.get(i).getStudentennummer() == studentenNummer) {
                            isGeslaagdExamen = false;
                            Examen examen = examensController.kiesExamen(scanner);
                            isGeslaagdExamen = examen.neemAf();
                        } else if (i == aantalStudenten) {
                            studentenController.studentToevoegen();
                        }
                    }
                    break;
                case 6:
                    if(isGeslaagdExamen){
                        System.out.println("Student is geslaagd voor het examen!");
                    } else {
                        System.out.println("Student is niet geslaagd voor het examen");
                    }
                    break;
                case 7:
                    System.out.println("Student heeft volgende examens gehaald:");
                    System.out.println(studentenController.studentWelkeExamensGehaald());
                    break;
                case 8:
                    System.out.println("Deze Student heeft de meeste examens gehaald:");
                    System.out.println(studentenController.studentMeesteExamensGehaald());
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Dit is geen menu optie!");
                    break;
            }
        }
    }
}


