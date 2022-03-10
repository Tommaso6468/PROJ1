public class Examen {

    private final String naam;
    private final Vraag[] vragen;
    private final int minimum;
    private int aantalGoed;

    public Examen(String naam, Vraag[] vragen, int minimum) {
        if (naam == null || vragen == null) throw new IllegalArgumentException();
        this.naam = naam;
        this.vragen = vragen;
        this.minimum = minimum;
    }

    public void controleerVoldoende() {

    }

    public String getNaam() {
        return naam;
    }
}
