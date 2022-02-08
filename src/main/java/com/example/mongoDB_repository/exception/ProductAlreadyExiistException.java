package com.example.mongoDB_repository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Customer already exists")
public class ProductAlreadyExiistException extends Throwable {
}
