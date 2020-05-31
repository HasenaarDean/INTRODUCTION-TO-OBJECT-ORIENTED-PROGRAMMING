package oop.ex6.variable;

/**
 * This class represents an exception class in case there is a usage of an illegal type of variable
 */
public class IllegalTypeException extends VariableException {

    private final String msg = "type of variable is illegal";

    public String getMessage(){
        return msg;
    }

}
