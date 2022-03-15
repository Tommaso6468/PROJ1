import java.util.ArrayList;

public class Student {

    private ArrayList<Examen> gehaald = new ArrayList<>();
    private String naam;
    private int studentennummer;

    public ArrayList<Examen> getGehaald() {
        return gehaald;
    }

    public int getStudentennummer() {
        return studentennummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setStudentennummer(int studentennummer) {
        this.studentennummer = studentennummer;
    }

    public void setGehaald(ArrayList<Examen> gehaald) {
        this.gehaald = gehaald;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", naam, studentennummer);
    }
}
