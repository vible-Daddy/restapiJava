package com.daeyun.exception;

import java.io.Serializable;

public class ParameterMissedException extends ApiException implements Serializable {

    private static final long serialVersionUID = -7140020405121413982L;
    public ParameterMissedException(){this.code = ErrorCode.ParameterMissed;}
}
