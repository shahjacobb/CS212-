import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.Comparator;
import java.util.TreeMap;

public class RomanNumeralGUI extends JFrame {

    JTextArea sortedKeys = new JTextArea("Sorted Keys\n");
    JTextArea sortedValues = new JTextArea("Sorted Values of Keys\n");

    Comparator<RomanNumeral> romanNumeralSorter = new Comparator<RomanNumeral>() {
        @Override
        public int compare(RomanNumeral o1, RomanNumeral o2) {
            if (o1.convertedArabicValue > o2.convertedArabicValue) return 1;
            else return -1;
        }
    };

    TreeMap<RomanNumeral, Object> treemap = new TreeMap<>(romanNumeralSorter);

    /**
     * Basic scaffolding. Since we're extending JFrame, no need to use the dot operator since this class *inherits* all of the superclass' fields and vars
     */
    public RomanNumeralGUI() {


        setLayout(new GridLayout(1, 2));
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // creating the text areas
        JTextArea sortedKeys = new JTextArea("Sorted Keys");
        JTextArea sortedValuesOfKeys = new JTextArea("Sorted Values");
        add(sortedKeys);
        add(sortedValuesOfKeys);

        // creating the menubar and menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu convertMenu = new JMenu("Convert");

        // for the first menubar
        JMenuItem open = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");
        fileMenu.add(open);
        fileMenu.add(quit);

        // for the second menubar (literally one button)
        JMenuItem convert = new JMenuItem("Convert");
        convertMenu.add(convert);

        menuBar.add(fileMenu);
        menuBar.add(convertMenu);


        // woof, we're done now
        setJMenuBar(menuBar);

        listeners(open, convert, quit);

        setVisible(true);
    }

    /**
     * This is a helper method that sets up the listeners. The open listener will have the TreeMap implementation below.
     * we're passing in the jmenus from the constructor as parameters since i don't want to make them class variables
     * we are ALSO passing in the text areas from the constructor so this code can be modular (i'm trying to really embrace encapsulation :))) I did bad in the past two projects
     *
     * @param open
     * @param convert
     * @param quit
     */
    public void listeners(JMenuItem open, JMenuItem convert, JMenuItem quit) {
        // the 'quit' listener is so simple I'm not even going to name it as a variable, we'll still use an anonymous class, but just define the entire thing
        // in the constructor of ActionListener calling listener.addActionListener

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ActionListener openListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // getting string name of file
                    if (!file.exists()) throw new NullPointerException("File not found.\n");
                    String fileName = file.getAbsolutePath();
                    String line;
                    TextFileInput reader = new TextFileInput(fileName);
                    while ((line = reader.readLine()) != null) {

                        String[] tokenizedArrayLine = line.split(",");
                        for (String numeral : tokenizedArrayLine) {

                            try {

                                treemap.put(new RomanNumeral(numeral), new Object());
                            }
                            catch (IllegalRomanNumeralException exc)
                            {
                                System.out.println(numeral + " is not a  valid character.\n");
                            }
                        }

                    }
                    for (RomanNumeral key : treemap.keySet()) {
                        sortedKeys.append(key.getRomanNumeral() + "\n");
                        sortedValues.append(Integer.toString(key.getConvertedArabicValue()) + "\n");

                    }


                }


            }


        };

        open.addActionListener(openListener);


    }


}
