/**
Exception doesn't have a no-argument constructor, so we need to call super() since we define a constructor in this subclass here to make sure
 we properly initialize the values in the Exception superclass. Atleast, that's the way I understand it.
  */
public class IllegalRomanNumeralException extends IllegalArgumentException{
    public IllegalRomanNumeralException(String msg)
    {
        super(msg);
    }
}
