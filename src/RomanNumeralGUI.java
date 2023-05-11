import javax.swing.*;

public class RomanNumeralGUI extends JFrame{

    /**
     * Basic scaffolding. Since we're extending JFrame, no need to use the dot operator since this class *inherits* all of the superclass' fields and vars
     */
    public RomanNumeralGUI()
    {
        JTextArea sortedKeys = new JTextArea();
        JTextArea sortedValuesOfKeys = new JTextArea();
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }



}
