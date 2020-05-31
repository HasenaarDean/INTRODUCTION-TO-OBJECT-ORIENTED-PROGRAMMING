package oop.ex6.variable;

/**
 * This class represents an exception class in case of trying to reassign the value of a final variable
 */
public class ReassignmentOfFinalVariableException extends VariableException {

    private final String msg = "reassignment of final variable";

    public String getMessage(){
        return msg;
    }

}
