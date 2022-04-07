import com.github.cliftonlabs.json_simple.JsonObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Testen {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testStudentToString() throws Exception {
        StudentenController studentenController = new StudentenController();
        Student student = new Student();
        student.setNaam("Kees");
        student.setStudentennummer(12314);

        String expected = "Kees (12314)";

        Assert.assertEquals(expected, student.toString());
    }

    @Test
    public void testUtilLeesInt() throws Exception {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Student> studentenArrayList = new ArrayList<>();
        studentenArrayList.add(new Student(12314, "Kees"));
        studentenArrayList.add(new Student(346534, "Piet"));

        Util.printArrayList(studentenArrayList, "student", "studenten");

        String expected = "Er zijn 2 studenten:\n" +
                "1) Kees (12314)\n" +
                "2) Piet (346534)\n";

        Assert.assertEquals(expected, out.toString());

    }


    @Test(expected = InvalidJsonFormatException.class)
    public void testInvalidJsonFormatException() {
        JsonObject jsonObject = new JsonObject();
        Examen.leesVanJson(jsonObject);
    }


}
