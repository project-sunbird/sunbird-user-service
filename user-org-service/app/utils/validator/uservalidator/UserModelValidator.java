package utils.validator.uservalidator;

import org.everit.json.schema.Schema;
import org.json.JSONObject;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import utils.validator.IModelValidator;
import utils.validator.schema.SchemaFactory;

public class UserModelValidator implements IModelValidator {

  @Override
  public void validate(JSONObject request, String uri) {
    Schema schema = SchemaFactory.getInstance().getSchemaObjectFromUri(uri).getSchema();
    schema.validate(request);
    ProjectLogger.log(
        String.format("%s:%s:request validated", this.getClass().getSimpleName(), "validate"),
        LoggerEnum.DEBUG.name());
  }
}
