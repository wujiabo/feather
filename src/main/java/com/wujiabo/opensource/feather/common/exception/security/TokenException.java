package com.wujiabo.opensource.feather.common.exception.security;

/**
 * 描述：
 * <p>
 *
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
