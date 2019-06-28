package controllers;

import akka.actor.ActorRef;
import io.opensaber.registry.app.OpenSaberApplication;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;

import org.sunbird.response.Response;
import org.sunbird.util.jsonkey.JsonKey;
import play.Application;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({org.sunbird.Application.class, BaseController.class, ActorRef.class, Await.class, OpenSaberApplication.class,SpringApplication.class})
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})

public class BaseControllerTest {
  Localizer localizer = Localizer.getInstance();
  BaseController controllerObject;
  TestHelper testHelper;
  public static Application app;
  public static Map<String, String[]> headerMap;
  private org.sunbird.Application application;
  private static ActorRef actorRef;
  private static BaseController baseController;
  //private OpenSaberApplication openSaberApplication;

  public BaseControllerTest() {
    baseControllerTestsetUp();
  }

  public void baseControllerTestsetUp() {

    application = PowerMockito.mock(org.sunbird.Application.class);
    PowerMockito.mockStatic(org.sunbird.Application.class);
    PowerMockito.when(org.sunbird.Application.getInstance()).thenReturn(application);
    application.init();
    mockRequestHandler();
  }

  public void mockRequestHandler() {

    try {
      baseController = Mockito.mock(BaseController.class);
      actorRef = Mockito.mock(ActorRef.class);
      Mockito.when(baseController.getActorRef(Mockito.anyString())).thenReturn(actorRef);
      PowerMockito.mockStatic(Await.class);
      PowerMockito.when(Await.result(Mockito.any(Future.class), Mockito.any(FiniteDuration.class)))
              .thenReturn(getResponseObject());
      PowerMockito.mockStatic(OpenSaberApplication.class);
      SpringApplication springApplication = PowerMockito.mock(SpringApplication.class);
      springApplication.setWebApplicationType(WebApplicationType.NONE);
      PowerMockito.doNothing().when(springApplication.run(new String[0]));
    } catch (Exception ex) {
    }
  }

  private Response getResponseObject() {

    Response response = new Response();
    response.put("userId", "123");
    return response;
  }


  @Test
  public void testJsonifyResponseSuccess() {
    Response response = new Response();
    BaseController controller = new BaseController();
    response.put(JsonKey.MESSAGE, localizer.getMessage(IResponseMessage.INTERNAL_ERROR,null));
    String jsonifyResponse = controller.jsonifyResponseObject(response);
    assertEquals(
            "{\"id\":null,\"ver\":null,\"ts\":null,\"params\":null,\"responseCode\":\"OK\",\"result\":{\"message\":\"Process failed,please try again later.\"}}", jsonifyResponse);
  }

  @Test
  public void testJsonifyResponseFailure() {
    Response response = new Response();
    BaseController controller = new BaseController();
    response.put(JsonKey.MESSAGE, response.getResult());
    String jsonifyResponse = controller.jsonifyResponseObject(response);
    assertEquals(StringUtils.EMPTY, jsonifyResponse);
  }
}