package com.hl.p2p.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {

  private String errorCode;

  private String errorMessage;

  private Boolean succeed;

  private Object data;

  public JsonResult() {

  }

  public JsonResult(String errorCode, String errorMessage, Boolean succeed, Object data) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.succeed = succeed;
    this.data = data;
  }

  public JsonResult(String errorMessage) {
    this.errorCode = "0000000";
    this.errorMessage = errorMessage;
    this.succeed = true;
    this.data = null;
  }

  public JsonResult(Object data) {
    this.errorCode = "0000000";
    this.errorMessage = "成功";
    this.succeed = true;
    this.data = data;
  }

  public JsonResult(String errorMessage, Object data) {
    this.errorCode = "0000000";
    this.errorMessage = errorMessage;
    this.succeed = true;
    this.data = data;
  }

  public JsonResult(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.succeed = false;
    this.data = null;
  }

  public static JsonResult result(String errorCode, String errorMessage, Boolean succeed, Object data) {
    return new JsonResult(errorCode, errorMessage, succeed, data);
  }

  public static JsonResult resultSuccess(String errorMessage) {
    return new JsonResult(errorMessage);
  }

  public static JsonResult resultSuccess(Object data) {
    return new JsonResult(data);
  }

  public static JsonResult resultSuccess(String errorMessage, Object data) {
    return new JsonResult(errorMessage, data);
  }

  public static JsonResult resultError(String errorCode, String errorMessage) {
    return new JsonResult(errorCode, errorMessage);
  }
}
