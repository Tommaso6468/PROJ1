import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;

public class Student {

    private ArrayList<Examen> gehaald = new ArrayList<>();
    private String naam;
    private int studentennummer;

    public Student(){}

    public Student(int studentennummer, String naam) {
        this.naam = naam;
        this.studentennummer = studentennummer;
    }

    public static Student leesVanJson(JsonObject object) {
        int studentennummer = Util.leesJsonInt(object, "studentennummer", 0, Integer.MAX_VALUE);
        String naam = Util.leesJsonString(object, "naam");
        return new Student(studentennummer,naam);
    }

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

    public void setGehaald(Examen examen) {
        if (!this.gehaald.contains(examen)) {
            this.gehaald.add(examen);
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", naam, studentennummer);
    }
}
