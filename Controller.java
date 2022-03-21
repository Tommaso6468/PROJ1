import java.util.ArrayList;

public class Controller<T> {
    private final ArrayList<T> lijst;

    public Controller() {
        lijst = new ArrayList<>();
    }

    protected Controller(ArrayList<T> lijst) {
        this.lijst = lijst;
    }

    /**
     * Krijgt de lijst met objecten die worden beheerd door deze Controller.
     * @return De ArrayList die de objecten bevat.
     */
    public ArrayList<T> getLijst() {
        return lijst;
    }

    /**
     * Krijgt het aantal objecten die worden beheerd door deze Controller.
     * @return Het aantal objecten.
     */
    public int getAantal() {
        return lijst.size();
    }

    /**
     * Voegt een object toe aan deze Controller.
     * @param object Het object om toe te voegen.
     */
    public void voegToe(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Een controller kan niet null bevatten.");
        }
        lijst.add(object);
    }
}
