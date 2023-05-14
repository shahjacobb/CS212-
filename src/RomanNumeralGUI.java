import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * This class definition includes the set up for the event listeners (a method) and creation of the Treemap (as well as the Comparator interface being defined).
 * It has the definition for the 3 event listeners in a helper method called listeners()
 */
public class RomanNumeralGUI extends JFrame {

    JTextArea sortedKeys = new JTextArea("Sorted Keys\n");
    JTextArea sortedValues = new JTextArea("Sorted Values of Keys\n");

    /**
    The sort method above in the class definition checks the convertedArabicValue (which is why I used a getter method to return that value) to see where to place a new key/node (a Roman Numeral Object).
     If it returns a negative 1, it'll insert to the left subtree. If it inserts a positive 1, it'll insert the node to the right subtree. If it's the same, well, a BST wouldn't
     even allow any duplicate values so nothing gets inserted.
     *
     */
    Comparator<RomanNumeral> romanNumeralSorter = new Comparator<RomanNumeral>() {
        @Override
        public int compare(RomanNumeral o1, RomanNumeral o2) {
            if (o1.convertedArabicValue > o2.convertedArabicValue) return 1;
            else return -1;
        }
    };

    TreeMap<RomanNumeral, Object> treemap = new TreeMap<>(romanNumeralSorter);

    /**
     * This constructor just does some basic scaffolding and creates the menubar as well as all the items.
     * Since we're extending JFrame, no need to use the dot operator since this class *inherits* all of the superclass' fields and vars
     */
    public RomanNumeralGUI() {


        setLayout(new GridLayout(1, 2));
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // adding the text areas

        add(this.sortedKeys);
        add(this.sortedValues);

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
     * This is a helper method that sets up the listeners. I use an anonymous class for all of them
     * we're passing in the jmenus from the constructor as parameters since i don't want to make them class variables.
     * There is exception handling for the convert button, and exception handling for the open button since those two deal with Roman Numerals. The quit button doesn't have any
     * exception handling since it's literally a single, simple operation.
     *
     *
     * @param open
     * @param convert
     * @param quit
     */
    public void listeners(JMenuItem open, JMenuItem convert, JMenuItem quit) {
        // in the constructor of ActionListener calling listener.addActionListener

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // this 'open' listener does what it looks like. The main thing to note here is that I use an exception handler (try catch) while using TextFileInput to read a file
        open.addActionListener(new ActionListener() {

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
                    // After getting the file name, we'll use good old TextFileInput to start reading
                    TextFileInput reader = new TextFileInput(fileName);
                    while ((line = reader.readLine()) != null) {

                        String[] tokenizedArrayLine = line.split(",");
                        for (String numeral : tokenizedArrayLine) {
                        // This is the most important thing to note here. The treemap inserts a new node. The key is a Roman Numeral object, and the value is just some generic null new Object.
                            try {
                                treemap.put(new RomanNumeral(numeral), new Object());

                            // if my constructor throws an error, it'll catch it
                            } catch (IllegalRomanNumeralException exc) {
                                System.out.println(numeral + " is not a  valid character.");
                            }
                        }

                    }



                }
                /*
                This is the other important line for this open listener. it will use the KEY (not the value) to 'fill' the values for both the key and the 'value' colummn
                in the JTextAreas. Pretty simple. I find it really weird to use a Treemap. What's the point if the value in the treemap is totally useless?
                 */
                for (RomanNumeral key : treemap.keySet()) {
                    sortedKeys.append(key.getRomanNumeral() + "\n");
                    sortedValues.append(Integer.toString(key.getConvertedArabicValue()) + "\n");

                }


            }


        });

        convert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a valid value.\n");
                try
                {
                    RomanNumeral inputtedObject = new RomanNumeral(input);
                    JOptionPane.showMessageDialog(null, inputtedObject.getConvertedArabicValue());
                }
                catch (IllegalRomanNumeralException illegalRomanNumeralException)
                {
                    System.out.println(input + " isn't even a valid Roman Numeral.\n");
                }


            }
        });




    }


}
