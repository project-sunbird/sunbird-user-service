package utils.validator.schema;

import org.everit.json.schema.Schema;

/**
 * this class will be used to have schema object and filePath in one single object os SchemaMapper...
 *
 * @author anmolgupta
 */
public class SchemaMapper {

    private String filePath;
    private Schema schema;

    public SchemaMapper() {

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }


}
