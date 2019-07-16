package utils.validator.uservalidator;

import org.everit.json.schema.Schema;
import org.json.JSONObject;
import utils.validator.IModelValidator;
import utils.validator.schema.SchemaFactory;

public class UserModelValidator implements IModelValidator {

    @Override
    public void validate(JSONObject request, String uri) {
        Schema schema = SchemaFactory.getInstance().getSchemaObjectFromUri(uri).getSchema();
        schema.validate(request);
    }

}
