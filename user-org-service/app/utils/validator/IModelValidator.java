package utils.validator;


/**
 * Each validator class needs to extends this interface class
 *
 * @author anmolgupta
 */
@FunctionalInterface
public interface IModelValidator {
  /** this method will be implemented in every subclass to validate the play request */
  void validate();
}
