package oop.ex6.main;

/**
 * This class represents an exception class in case there is a usage of a variable or a method
 * which does not exist
 */
public class CannotResolveSymbolException extends MainScopeException{

    private final String ERROR_CAUSE = "cannot resolve name of method or variable";

    public String getMessage(){
        return ERROR_CAUSE;
    }
}

