package oogasalad.exceptions;

public class BlueprintParseException extends Exception{
  public BlueprintParseException(String message) {
    super(message);
  }

  public BlueprintParseException(String message, Throwable cause) {
    super(message, cause);
  }
}
