package oop.ex6.main;

/**
 * This class represents an exception class in case there is no return statement within a method
 */
public class ReturnStatementMissingException extends MainScopeException{

    private final String ERROR_CAUSE = "missing return command in method";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}

