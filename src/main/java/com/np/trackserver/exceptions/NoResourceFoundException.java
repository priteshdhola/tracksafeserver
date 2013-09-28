package com.np.trackserver.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: pritesh
 * Date: 9/28/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoResourceFoundException extends RuntimeException{
    public NoResourceFoundException(String s, Throwable t) {
        super(s, t);
    }

    public NoResourceFoundException(Throwable t) {
        this(t.getMessage(), t);
    }

    public NoResourceFoundException(String s) {
        super(s);
    }
}
