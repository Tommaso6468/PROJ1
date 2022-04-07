import com.sun.tools.javac.Main;
import org.junit.Assert;
import org.junit.Test;

public class testStudentenController {
    @Test
    public void testGetStudentMetNummer() throws Exception {
        StudentenController studentenController = new StudentenController();

        Student student = studentenController.getStudentMetNummer(123456789);
        String expectedNaam = "Fritz Blitz";

        Assert.assertEquals(student.getNaam(), expectedNaam);
    }
}
