/**
 * Created by Elisabeth on 02.03.2017.
 */
public class BadFormatException extends Exception {
    BadFormatException()
    {
        // Aufruf des übergeordneten Konstruktors mit dem zu
        // erscheinenden Fehlertext
        super("This website does not contain an empty line after the head.Can not appear coherently.");
    }
}
