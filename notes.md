## To Do
- [x ] write IllegalRomanNumeralException class
-  [x ] Write TreeMap... wait, should it be a class file? (nah, just made it a class variable for the GUI)
- [ x] Write the GUI
  - [x ] Write the 3 event listeners (try to make them anonymous class, not an anon class saved to a variable )
  - [x] Write the JFileChooser object
- [x ] Write the RomanNumeral class
- [x ] Write the Exception Handling (for the Open and Convert event listeners)
- [x ] Write Javadoc for everything

Finished!

## Change List

5/13/2023
- Added a listeners method to `RomanNumeralGUI` that creates, set up, and registers event listeners with the 3 `JMenuItems`
  - method takes in `JMenuItems` as parameters since those aren't class variables of `RomanNumeralGUI`
  - Event listener for `open` has a more compact, slimmer way of adding key-value pairs to `treemap`, Initially didn't work because my Roman
  Numeral object constructor for some reason had an exception handler in the constructor rather than throwing the error, which was wrong. Removed it, and below works.
  :
  ```java
  treemap.put(new RomanNumeral(numeralInLine), new Object());
  ```
  - Removed the named local anonymous classes for the 3 listeners in the `listeners()` method and just made them actual anonymous classes
  - 
  
5/13/2023
Done.
