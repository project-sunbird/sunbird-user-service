package utils.validator.schema;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SchemaManager {

    private static Map<String, SchemaMapper> schemaInfoMap = new HashMap<>();
    private static final String schemaMapperFile="schemaMapper.properties";

    public SchemaManager() {
    }


    private Properties getSchemaRoutesFileMap() {
        Properties prop = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(schemaMapperFile)) {
            prop.load(inputStream);
            ProjectLogger.log(String.format("%s:%s: Properties Populated", this.getClass().getSimpleName(), "getSchemaRoutesFileMap"), LoggerEnum.DEBUG.name());
            return prop;
        } catch (Exception e) {
            ProjectLogger.log(String.format("%s:%s: error occurred while loading schemaMapperFile", this.getClass().getSimpleName(), "getSchemaRoutesFileMap"));
            System.exit(-1);
        }
        return prop;
    }

    /**
     * this method is used to init the schemas and save them in cache map name schemaInfoMap
     */
    public void initSchemas() {
        Properties properties = getSchemaRoutesFileMap();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            schemaInfoMap.put((String) entry.getKey(), getSchemaMapperObject((String) entry.getValue()));
        }
        ProjectLogger.log(String.format("%s:%s: schemaInfoMap Populated", this.getClass().getSimpleName(), "initSchemas"), LoggerEnum.DEBUG.name());
    }

    private Schema getSchemaObject(String filePath) {
        try (InputStream schemaStream = this.getClass().getClassLoader().getResourceAsStream(filePath)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.builder()
                    .useDefaults(true)
                    .schemaJson(rawSchema)
                    .draftV7Support()
                    .build()
                    .load().build();
            return schema;

        } catch (Exception e) {
            System.out.println("the error in creating json schema is +++++++++++++=" +e.getMessage());
            e.printStackTrace();
            ProjectLogger.log(String.format("%s:%s: error in creating schema object with file path %s wut", this.getClass().getSimpleName(), "getSchemaObject",filePath), LoggerEnum.DEBUG.name());
            return null;
        }
    }

    private SchemaMapper getSchemaMapperObject(String filePath) {
        SchemaMapper schemaMapper = new SchemaMapper();
        schemaMapper.setFilePath(filePath);
        schemaMapper.setSchema(getSchemaObject(filePath));
        return schemaMapper;

    }

    /**
     * this method should be used to get the schema object by passing API uri.
     *
     * @param uri
     * @return SchemaMapper
     */
    public SchemaMapper getSchemaObjectFromUri(String uri) {
        if (schemaInfoMap.containsKey(uri)) {
            return schemaInfoMap.get(uri);
        }
        return null;
    }
}