## To Do
- [x] write IllegalRomanNumeralException class
-  [x] Write TreeMap... wait, should it be a class file? (nah, just made it a class variable for the GUI)
- [ ] Write the GUI
  - [ ] Write the 3 event listeners
  - [x] Write the JFileChooser object
- [ ] Write the RomanNumeral class
- [ ] Write the Exception Handling (for the Open and Convert event listeners)
- [ ] Write Javadoc for everything


## Change List
- Added a listeners method to `RomanNumeralGUI` that creates, set up, and registers event listeners with the 3 `JMenuItems`
  - method takes in `JMenuItems` as parameters since those aren't class variables of `RomanNumeralGUI`
  - Event listener for `open` has a more compact, slimmer way of adding key-value pairs to `treemap`, 
  :
  ```java
  treemap.put(new RomanNumeral(numeralInLine), new Object());
  ```
  

