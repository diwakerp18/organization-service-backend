package com.organization.record.exception;

import com.organization.record.exception.errorcode.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationServiceException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    public OrganizationServiceException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getErrorMessage();
    }


    public OrganizationServiceException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public OrganizationServiceException(String errorMessage) {
        super(errorMessage);
        this.errorCode = ErrorCode.BAD_REQUEST.getCode();
        this.errorMessage = errorMessage;
    }

    public OrganizationServiceException(Integer customErrorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = customErrorCode;
        this.errorMessage = errorMessage;
    }
}


