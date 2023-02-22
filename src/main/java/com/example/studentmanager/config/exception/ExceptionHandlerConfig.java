package com.example.studentmanager.config.exception;

import com.example.studentmanager.constant.UserMessageConstant;
import com.example.studentmanager.base.RestData;
import com.example.studentmanager.base.VsResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

  private static final Log LOG = LogFactory.getLog(ExceptionHandlerConfig.class);

  @ExceptionHandler(value = {InvalidException.class})
  protected ResponseEntity<RestData<?>> handleVsException(InvalidException ex) {
    LOG.error(ex.getMessage(), ex);

    return VsResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(value = {DuplicateException.class})
  protected ResponseEntity<RestData<?>> handleDuplicateException(DuplicateException ex) {
    LOG.error(ex.getMessage(), ex);

    return VsResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(value = {VsException.class})
  protected ResponseEntity<RestData<?>> handleVsException(VsException ex) {
    LOG.error(ex.getMessage(), ex);

    return VsResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getDevMessage());
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  protected ResponseEntity<RestData<?>> handleAccessDeniedException(AccessDeniedException ex) {
    LOG.error(ex.getMessage(), ex);

    return VsResponseUtil.error(HttpStatus.FORBIDDEN, ex.getDevMessage());
  }

  @ExceptionHandler(value = {NotFoundException.class})
  protected ResponseEntity<RestData<?>> handleNotFoundException(NotFoundException ex) {
    LOG.error(ex.getMessage(), ex);

    return VsResponseUtil.error(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected ResponseEntity<RestData<?>> handleConstraintViolationException(ConstraintViolationException ex) {
    LOG.error(ex.getMessage(), ex);
    return VsResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getCause() != null ? ex.getCause().getMessage() :
        ex.getMessage());
  }

  @ExceptionHandler(value = {BindException.class})
  protected ResponseEntity<RestData<?>> handleBindException(BindException ex) {
    LOG.error(ex.getMessage(), ex);
    return VsResponseUtil.error(HttpStatus.BAD_REQUEST, ex.getCause() != null ? ex.getCause().getMessage() :
        ex.getMessage());
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<RestData<?>> handleException(Exception ex) {
    LOG.error(ex.getMessage(), ex);

    if (ex.getCause() instanceof VsException) {
      VsException vsException = (VsException) ex.getCause();
      return VsResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, vsException.getDevMessage());
    }
    if (ex.getCause() instanceof DuplicateException) {
      DuplicateException duplicateException = (DuplicateException) ex.getCause();
      return VsResponseUtil.error(HttpStatus.BAD_REQUEST, duplicateException.getMessage());
    }
    if (ex.getCause() instanceof AccessDeniedException) {
      return VsResponseUtil.error(HttpStatus.FORBIDDEN, UserMessageConstant.ACCESS_DENIED);
    }
    if (ex.getCause() instanceof NotFoundException) {
      return VsResponseUtil.error(HttpStatus.NOT_FOUND, UserMessageConstant.NOT_FOUND);
    }
    if (ex.getCause() instanceof InvalidException) {
      VsException invalidException = (VsException) ex.getCause();
      return VsResponseUtil.error(HttpStatus.BAD_REQUEST, invalidException.getDevMessage());
    }
    return VsResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getCause() != null ? ex.getCause().getMessage()
        : ex.getMessage());
  }

}
