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

            keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    System.out.println("Examens:");
                    System.out.println(examensController.getExamens());
                    break;
                case 2:
                    System.out.println("Studenten:");
                    System.out.println(studentenController.studentLijst());
                    break;
                case 3:
                    studentenController.studentToevoegen();
                    break;
                case 4:
                    studentenController.studentVerwijderen();
                    break;
                case 5:
                    //check of student in lijst staat
                    ArrayList<Student> studenten = StudentenController.studentLijst();
                    int aantal = studenten.size();

                    System.out.println("Voer je leerlingennummer in:");
                    int studentenNummer = scanner.nextInt();

                    for (int i = 0; i < aantal; i++) {
                        if (studenten.get(i).getStudentennummer() == studentenNummer) {
                            isGeslaagdExamen = false;
                            Examen examen = examensController.kiesExamen(scanner);
                            isGeslaagdExamen = examen.neemAf();
                        } else if (i == aantal) {
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
                    System.out.println("Student heeft volgende Exmanens gehaald:");
                    System.out.println(studentenController.studentWelkeExamensGehaald());
                    break;
                case 8:
                    studentenController.studentMeesteExamensGehaald();
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


