package com.daeyun.exception;

import java.io.Serializable;

public class NoResultException extends ApiException implements Serializable {

    private static final long serialVersionUID = -5215004073847208833L;

    public NoResultException(){this.code = ErrorCode.NoResult;}
}
