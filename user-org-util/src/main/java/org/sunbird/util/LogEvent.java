package org.sunbird.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will log the api request , response , and error message insdie log file .in predefine
 * structure.
 *
 * @author Manzarul
 */
public class LogEvent {

  private String eid;
  private long ets;
  private String mid;
  private String ver;
  private Map<String, Object> context;
  private Map<String, Object> edata;

  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }

  public long getEts() {
    return ets;
  }

  public void setEts(long ets) {
    this.ets = ets;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getVer() {
    return ver;
  }

  public void setVer(String ver) {
    this.ver = ver;
  }

  public Map<String, Object> getContext() {
    return context;
  }

  public void setContext(Map<String, Object> context) {
    this.context = context;
  }

  public Map<String, Object> getEdata() {
    return edata;
  }

  public void setEdata(Map<String, Object> eks) {
    this.edata = new HashMap<String, Object>();
    edata.put(UserOrgJsonKey.EKS, eks);
  }

  public void setContext(String id, String ver) {
    this.context = new HashMap<String, Object>();
    Map<String, String> pdata = new HashMap<String, String>();
    pdata.put(UserOrgJsonKey.ID, id);
    pdata.put(UserOrgJsonKey.VER, ver);
    this.context.put(UserOrgJsonKey.PDATA, pdata);
  }

  /**
   * Set the error data with this method
   *
   * @param level String
   * @param className String
   * @param method String
   * @param data Object
   * @param stackTrace Object
   * @param exception Object
   */
  public void setEdata(
      String level,
      String className,
      String method,
      Object data,
      Object stackTrace,
      Object exception) {
    this.edata = new HashMap<String, Object>();
    Map<String, Object> eks = new HashMap<String, Object>();
    eks.put(UserOrgJsonKey.LEVEL, level);
    eks.put(UserOrgJsonKey.CLASS, className);
    eks.put(UserOrgJsonKey.METHOD, method);
    eks.put(UserOrgJsonKey.DATA, data);
    eks.put(UserOrgJsonKey.STACKTRACE, stackTrace);
    edata.put(UserOrgJsonKey.EKS, eks);
  }
}
