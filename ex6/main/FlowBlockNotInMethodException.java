package oop.ex6.main;

/**
 * This class represents an exception class in case of the flow block is not within a method definition.
 */
public class FlowBlockNotInMethodException extends MainScopeException{

    private final String ERROR_CAUSE = "if or while block are not within a method";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}
