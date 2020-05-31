package oop.ex6.variable;

/**
 * This class represents an exception class in case the value of a variable is illegal
 */
public class IllegalValueException extends VariableException {

    private final String msg = "type of value does not match type of variable";

    public String getMessage(){
        return msg;
    }

}
