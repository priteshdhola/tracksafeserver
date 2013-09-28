package com.np.trackserver.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: pritesh
 * Date: 9/28/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String s, Throwable t) {
        super(s, t);
    }

    public AuthenticationException(Throwable t) {
        this(t.getMessage(), t);
    }

    public AuthenticationException(String s) {
        super(s);
    }
}
