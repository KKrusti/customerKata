package com.customer.controller.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
  private int status;
  private String error;
  private String exception;
  private String message;
  private String path;
}
