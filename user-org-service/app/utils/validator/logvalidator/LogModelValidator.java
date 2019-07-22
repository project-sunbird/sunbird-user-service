package utils.validator.logvalidator;

import org.everit.json.schema.Schema;
import org.json.JSONObject;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import utils.validator.IModelValidator;
import utils.validator.schema.SchemaFactory;

/**
 * this validator class is used to validate the log request
 *
 * @author anmolgupta
 */
public class LogModelValidator implements IModelValidator {

  private static final String validatorFile = "LogUpdate.json";
  private JSONObject requestAsJson;

  public LogModelValidator(JSONObject request) {
    this.requestAsJson = request;
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
