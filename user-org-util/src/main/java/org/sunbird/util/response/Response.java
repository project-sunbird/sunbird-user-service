package org.sunbird.util.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a common response class for all the layer. All layer will send same response object.
 *
 * @author anmolgupta
 */
public class Response implements Serializable, Cloneable {

  private static final long serialVersionUID = -3773253896160786443L;
  private Map<String, Object> result = new HashMap<>();

  /** @return Map<String, Object> */
  public Map<String, Object> getResult() {
    return result;
  }

  /**
   * @param key String
   * @return Object
   */
  public Object get(String key) {
    return result.get(key);
  }

  /**
   * @param key String
   * @param vo Object
   */
  public void put(String key, Object vo) {
    result.put(key, vo);
  }

  /** @param map Map<String, Object> */
  public void putAll(Map<String, Object> map) {
    result.putAll(map);
  }

  public boolean containsKey(String key) {
    return result.containsKey(key);
  }

  public Response clone(Response response) {
    try {
      return (Response) response.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }
}
