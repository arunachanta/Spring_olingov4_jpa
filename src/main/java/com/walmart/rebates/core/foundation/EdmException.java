package com.walmart.rebates.core.foundation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason = "Error Processing Edm")
public class EdmException extends RuntimeException {

}
