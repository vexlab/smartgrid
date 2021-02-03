package ml.vexlab.smartgrid.service.exception;

public class DataLoadingException extends Exception {

  private static final long serialVersionUID = 1L;

  public DataLoadingException() {
    super();
  }

  public DataLoadingException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public DataLoadingException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataLoadingException(String message) {
    super(message);
  }

  public DataLoadingException(Throwable cause) {
    super(cause);
  }
}
