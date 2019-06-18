package org.sunbird.util.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/** @author anmolgupta */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request implements Serializable {

  private static final long serialVersionUID = -2362783406031347676L;
  private Map<String, Object> request = new HashMap<>();

  public Request() {}

  /** @return the requestValueObjects */
  public Map<String, Object> getRequest() {
    return request;
  }

  public void setRequest(Map<String, Object> request) {
    this.request = request;
  }

  public Object get(String key) {
    return request.get(key);
  }

  public void put(String key, Object vo) {
    request.put(key, vo);
  }

  @Override
  public String toString() {
    return "Request{" + "request=" + request + '}';
  }
}
