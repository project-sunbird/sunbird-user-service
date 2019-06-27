package org.sunbird.es.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestData {

  public static Map<String, Object> initializeChemistryCourse(int appendVal) {
    Map<String, Object> chemistryMap = new HashMap<>();
    chemistryMap.put("courseType", "type of the course. all , private");
    chemistryMap.put("description", "This is for chemistry");
    chemistryMap.put("size", 10);
    chemistryMap.put("objectType", "course");
    chemistryMap.put("courseId", "course id_" + appendVal);
    chemistryMap.put("courseName", "NTP course_" + appendVal);
    chemistryMap.put("courseDuration", appendVal);
    chemistryMap.put("noOfLecture", 30 + appendVal);
    chemistryMap.put("organisationId", "org id");
    chemistryMap.put("orgName", "Name of the organisation");
    chemistryMap.put("courseAddedById", "who added the course in NTP");
    chemistryMap.put("courseAddedByName", "Name of the person who added the course under sunbird");
    chemistryMap.put("coursePublishedById", "who published the course");
    chemistryMap.put("coursePublishedByName", "who published the course");
    chemistryMap.put("enrollementStartDate", new Date());
    chemistryMap.put("publishedDate", new Date());
    chemistryMap.put("updatedDate", new Date());
    chemistryMap.put("updatedById", "last updated by id");
    chemistryMap.put("updatedByName", "last updated person name");

    chemistryMap.put("facultyId", "faculty for this course");
    chemistryMap.put("facultyName", "name of the faculty");
    chemistryMap.put(
        "CoursecontentType",
        "list of course content type as comma separated , pdf, video, wordDoc");
    chemistryMap.put("availableFor", "[\"C.B.S.C\",\"I.C.S.C\",\"all\"]");
    chemistryMap.put("tutor", "[{\"id\":\"name\"},{\"id\":\"name\"}]");
    chemistryMap.put("operationType", "add/updated/delete");
    chemistryMap.put("owner", "EkStep");

    chemistryMap.put("visibility", "Default");
    chemistryMap.put(
        "downloadUrl",
        "https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/ecar_files/do_112228048362078208130/test-content-1_1493905653021_do_112228048362078208130_5.0.ecar");

    chemistryMap.put("language", "[\"Hindi\"]");
    chemistryMap.put("mediaType", "content");
    chemistryMap.put(
        "variants",
        "{\"spine\": {\"ecarUrl\": \"https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/ecar_files/do_112228048362078208130/test-content-1_1493905655272_do_112228048362078208130_5.0_spine.ecar\",\"size\": 863}}");
    chemistryMap.put("mimeType", "application/vnd.ekstep.html-archive");
    chemistryMap.put("osId", "org.ekstep.quiz.app");
    chemistryMap.put("languageCode", "hi");
    chemistryMap.put("createdOn", "2017-05-04T13:47:32.676+0000");
    chemistryMap.put("pkgVersion", appendVal);
    chemistryMap.put("versionKey", "1495646809112");

    chemistryMap.put("lastPublishedOn", "2017-05-04T13:47:33.000+0000");
    chemistryMap.put(
        "collections",
        "[{\"identifier\": \"do_1121912573615472641169\",\"name\": \"A\",\"objectType\": \"Content\",\"relation\": \"hasSequenceMember\",\"description\": \"A.\",\"index\": null}]");
    chemistryMap.put("name", "Test Content 1");
    chemistryMap.put(
        "artifactUrl",
        "https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/content/do_112228048362078208130/artifact/advancedenglishassessment1_1533_1489654074_1489653812104_1492681721669.zip");
    chemistryMap.put("lastUpdatedOn", "2017-05-24T17:26:49.112+0000");
    chemistryMap.put("contentType", "Story");
    chemistryMap.put("status", "Live");
    chemistryMap.put("channel", "NTP");
    return chemistryMap;
  }

  public static Map<String, Object> intitializePhysicsCourse(int appendVal) {
    Map<String, Object> physicsCourseMap = new HashMap<>();
    physicsCourseMap.put("courseType", "type of the course. all , private");
    physicsCourseMap.put("description", "This is for physics");
    physicsCourseMap.put("size", 20);
    physicsCourseMap.put("objectType", "course");
    physicsCourseMap.put("courseId", "course id_" + appendVal);
    physicsCourseMap.put("courseName", "NTP course_" + appendVal);
    physicsCourseMap.put("courseDuration", appendVal);
    physicsCourseMap.put("noOfLecture", 30 + appendVal);
    physicsCourseMap.put("organisationId", "org id");
    physicsCourseMap.put("orgName", "Name of the organisation");
    physicsCourseMap.put("courseAddedById", "who added the course in NTP");
    physicsCourseMap.put(
        "courseAddedByName", "Name of the person who added the course under sunbird");
    physicsCourseMap.put("coursePublishedById", "who published the course");
    physicsCourseMap.put("coursePublishedByName", "who published the course");
    physicsCourseMap.put("enrollementStartDate", new Date());
    physicsCourseMap.put("publishedDate", new Date());
    physicsCourseMap.put("updatedDate", new Date());
    physicsCourseMap.put("updatedById", "last updated by id");
    physicsCourseMap.put("updatedByName", "last updated person name");

    physicsCourseMap.put("facultyId", "faculty for this course");
    physicsCourseMap.put("facultyName", "name of the faculty");
    physicsCourseMap.put(
        "CoursecontentType",
        "list of course content type as comma separated , pdf, video, wordDoc");
    physicsCourseMap.put("availableFor", "[\"C.B.S.C\",\"I.C.S.C\",\"all\"]");
    physicsCourseMap.put("tutor", "[{\"id\":\"name\"},{\"id\":\"name\"}]");
    physicsCourseMap.put("operationType", "add/updated/delete");
    physicsCourseMap.put("owner", "EkStep");

    physicsCourseMap.put("visibility", "Default");
    physicsCourseMap.put(
        "downloadUrl",
        "https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/ecar_files/do_112228048362078208130/test-content-1_1493905653021_do_112228048362078208130_5.0.ecar");

    physicsCourseMap.put("language", "[\"Hindi\"]");
    physicsCourseMap.put("mediaType", "content");
    physicsCourseMap.put(
        "variants",
        "{\"spine\": {\"ecarUrl\": \"https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/ecar_files/do_112228048362078208130/test-content-1_1493905655272_do_112228048362078208130_5.0_spine.ecar\",\"size\": 863}}");
    physicsCourseMap.put("mimeType", "application/vnd.ekstep.html-archive");
    physicsCourseMap.put("osId", "org.ekstep.quiz.app");
    physicsCourseMap.put("languageCode", "hi");
    physicsCourseMap.put("createdOn", "2017-06-04T13:47:32.676+0000");
    physicsCourseMap.put("pkgVersion", appendVal);
    physicsCourseMap.put("versionKey", "1495646809112");

    physicsCourseMap.put("lastPublishedOn", "2017-05-04T13:47:33.000+0000");
    physicsCourseMap.put(
        "collections",
        "[{\"identifier\": \"do_1121912573615472641169\",\"name\": \"A\",\"objectType\": \"Content\",\"relation\": \"hasSequenceMember\",\"description\": \"A.\",\"index\": null}]");
    physicsCourseMap.put("name", "Test Content 1");
    physicsCourseMap.put(
        "artifactUrl",
        "https://ekstep-public-dev.s3-ap-south-1.amazonaws.com/content/do_112228048362078208130/artifact/advancedenglishassessment1_1533_1489654074_1489653812104_1492681721669.zip");
    physicsCourseMap.put("lastUpdatedOn", "2017-05-24T17:26:49.112+0000");
    physicsCourseMap.put("contentType", "Story");
    physicsCourseMap.put("status", "Live");
    physicsCourseMap.put("channel", "NTP");
    return physicsCourseMap;
  }
}
