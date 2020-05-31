package oop.ex6.variable;

/**
 * This class represents an exception class in case a final variable is declared but not initialized
 * at the same line.
 */
public class UninitializedFinalVariableException extends VariableException {

    private final String msg = "uninitialized final variable";

    public String getMessage(){
        return msg;
    }

}
