package me.jortpepe.advancedworld;

public class ErrorHandler {

  enum ErrorType {
    PLAYER,
    ARGUMENTAMOUNT,
    ARGUMENTTYPE,
    PLACEHOLDER,
    PLACEHOLDERSUB,
    GENERATOR
  }

  /**
   * This method gets called when an error occurs.
   *
   * @param e The type of error.
   * @return A string containing a user-friendly description of the error.
   */
  static String handle(ErrorType e) {
    return switch (e) {
      case PLAYER -> "Err: Player not found";
      case PLACEHOLDER -> "Err: Unknown placeholder";
      case PLACEHOLDERSUB -> "Err: Unknown sub-placeholder";
      case ARGUMENTAMOUNT -> "Err: Invalid amount of args";
      case ARGUMENTTYPE -> "Err: Invalid arg type";
      case GENERATOR -> "Err: Generator not found";
    };
  }
}
