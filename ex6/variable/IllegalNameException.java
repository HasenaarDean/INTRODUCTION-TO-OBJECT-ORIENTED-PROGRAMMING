package oop.ex6.variable;

/**
 * This class represents an exception class in case there is an illegal name of variable
 */
public class IllegalNameException extends VariableException {

    private final String msg = "name of variable is illegal";

    public String getMessage(){
        return msg;
    }

}
