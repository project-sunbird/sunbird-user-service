package utils.validator.uservalidator;

import org.everit.json.schema.Schema;
import org.json.JSONObject;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import utils.validator.IModelValidator;
import utils.validator.schema.SchemaFactory;

/**
 * this validator class is used to validate the create user request.
 *
 * @author anmolgupta
 */
public class UserCreateModelValidator implements IModelValidator {
  private static final String validatorFile = "UserCreate.json";
  private JSONObject requestAsJson;

  public UserCreateModelValidator(JSONObject jsonObject) {
    this.requestAsJson = jsonObject;
  }

  @Override
  public void validate() {
    Schema schema = SchemaFactory.getInstance().getSchemaOrNull(validatorFile);
    schema.validate(requestAsJson);
    ProjectLogger.log(
        String.format(
            "%s:%s:request validated against validator file %s",
            this.getClass().getSimpleName(), "validate", validatorFile),
        LoggerEnum.DEBUG.name());
  }
}
