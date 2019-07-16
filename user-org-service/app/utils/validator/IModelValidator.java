package utils.validator;

import org.json.JSONObject;

public interface IModelValidator {


    /**
     * this method will be implemented in every subclass to validate the play request
     * @param request
     * @param uri
     */
    void validate(JSONObject request, String uri);


}
