package oop.ex6.variable;

/**
 * This class represents an exception class in case of trying to use an uninitialized variable
 */
public class UsingUninitializedVariableException extends VariableException {

    private final String msg = "using an uninitialized variable";

    public String getMessage(){
        return msg;
    }

}
