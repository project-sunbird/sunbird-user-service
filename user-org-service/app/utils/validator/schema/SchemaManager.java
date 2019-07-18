package utils.validator.schema;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * this class is responsible to manage the schemaObject cache
 *
 * @author anmolgupta
 */
public class SchemaManager {

  private static Map<String, Schema> schemaInfoMap = new HashMap<>();
  private static final String schemaParentDir = "conf/";
  public static final String schemaDir = "schemas/";

  public SchemaManager() {}

  /**
   * this method is used to init the schemas and save them in cache map name schemaInfoMap on
   * Application startup
   */
  public void initSchemas() {
    List<String> schemas = walkOnSchema();
    schemas
        .stream()
        .forEach(
            jsonFile -> schemaInfoMap.put(jsonFile, getSchemaObject(schemaDir.concat(jsonFile))));
    ProjectLogger.log(
        String.format(
            "%s:%s: schema object available for files  %s",
            this.getClass().getSimpleName(), "initSchemas", schemaInfoMap.keySet()),
        LoggerEnum.DEBUG.name());
  }

  private Schema getSchemaObject(String filePath) {
    try (InputStream schemaStream =
        this.getClass().getClassLoader().getResourceAsStream(filePath)) {
      JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
      Schema schema =
          SchemaLoader.builder()
              .useDefaults(true)
              .schemaJson(rawSchema)
              .draftV7Support()
              .build()
              .load()
              .build();
      return schema;
    } catch (Exception e) {
      ProjectLogger.log(
          String.format(
              "%s:%s: error occurred in loading schema for %s",
              this.getClass().getSimpleName(), "getSchemaObject", filePath));
      System.exit(-1);
      return null;
    }
  }

  /**
   * this method should be used to get the schema object by passing API uri.
   *
   * @param fileName
   * @return SchemaMapper
   */
  public Schema getSchemaOrNull(String fileName) {
    if (schemaInfoMap.containsKey(fileName)) {
      return schemaInfoMap.get(fileName);
    }
    ProjectLogger.log(
        String.format(
            "%s:%s:schema not configured with the Given json file %s",
            this.getClass().getSimpleName(), "getSchemaOrNull", fileName),
        LoggerEnum.INFO.name());
    return null;
  }

  private List<String> walkOnSchema() {
    Collection<File> files =
        FileUtils.listFiles(new File(schemaParentDir.concat(schemaDir)), null, false);
    List<String> schemaFileLists = new ArrayList<>();
    files.stream().forEach(s -> schemaFileLists.add(s.getName()));
    return schemaFileLists;
  }
}
