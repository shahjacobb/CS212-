import java.util.HashMap;

/**
 * I go over it down below, but the convert() method has the logic for conversion + the HashMap needed to convert.
 */
public class RomanNumeral {
    String romanNumeral;
    int convertedArabicValue = 0;

    public String getRomanNumeral() {
        return romanNumeral;
    }

    public int getConvertedArabicValue() {
        return convertedArabicValue;
    }


    public RomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
        try {
            this.convertedArabicValue = convert(romanNumeral);
        } catch (IllegalRomanNumeralException e) {
            System.out.println(romanNumeral + " is not a valid Roman Numeral.");
        }

    }

    // normally I'd make this static so I can use it later, but we only really need to use this method once

    /**
     * Neat little helper method to make the code look a little cleaner and be more modular
     * This is where you'll find the HashMap required for the Project. And this time, it's better written. Rather than storing the value of the last value in the string inside a variable,
     * I just test to see if i == the last value and break.
     * It has the conversion logic for converting the string member of a RomanNumeral object. It could be type void since we're *in the class*, but just so that I can reuse this, I'm making
     * it return int. And since it's returning int, it also makes sense for it to be static if I want to reuse this in another class.
     *
     * @param romanNumeral the string value to be converted into the arabic number
     */
    private static int convert(String romanNumeral) {
        int convertedValue = 0;
        HashMap<Character, Integer> codex = new HashMap<>();
        codex.put('I', 1);
        codex.put('V', 5);
        codex.put('X', 10);
        codex.put('L', 50);
        codex.put('C', 100);
        codex.put('D', 500);
        codex.put('M', 1000);

        // tests if the numeral is valid or not
        for (int i = 0; i < romanNumeral.length(); i++)
        {
            // Immediately testing for any invalid characters
            if (!codex.containsKey(romanNumeral.charAt(i)))
                throw new IllegalRomanNumeralException("Not a valid Roman Numeral");

        }

        for (int i = 0; i < romanNumeral.length(); i++) {

            // this if statement is here because we obviously can't compare the last digit to the right most digit since it'll be out of bounds.
            // so it'll check if it's the last, add this last value, and then break execution out of the loop
            if (i == romanNumeral.length() - 1) {
                convertedValue += codex.get(romanNumeral.charAt(i));
                break;
            }
            // otherwise, compare i and i+1 and subtract if necessary
            else {
                int firstChar = codex.get(romanNumeral.charAt(i));
                int secondChar = codex.get(romanNumeral.charAt(i + 1));
                if (firstChar < secondChar) {
                    convertedValue += secondChar - firstChar;
                } else {
                    convertedValue += firstChar;
                }

            }
        }

        return convertedValue;

        //


    }
}