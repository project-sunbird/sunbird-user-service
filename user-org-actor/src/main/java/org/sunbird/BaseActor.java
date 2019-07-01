package org.sunbird;

import akka.actor.UntypedAbstractActor;
import org.sunbird.exception.ActorServiceException;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.request.Request;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

import java.util.Locale;

/**
 * @author Amit Kumar
 */
public abstract class BaseActor extends UntypedAbstractActor {

    public abstract void onReceive(Request request) throws Throwable;
    protected Localizer localizer = Localizer.getInstance();

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Request) {
            Request request = (Request) message;
            String operation = request.getOperation();
            ProjectLogger.log("BaseActor: onReceive called for operation: " + operation, LoggerEnum.INFO);
            try {
                onReceive(request);
            } catch (Exception e) {
                onReceiveException(operation, e);
            }
        } else {
            ProjectLogger.log("BaseActor: onReceive called with invalid type of request.", LoggerEnum.INFO);
        }
    }

    protected void onReceiveException(String callerName, Exception exception) throws Exception {
        ProjectLogger.log(
                "Exception in message processing for: "
                        + callerName
                        + " :: message: "
                        + exception.getMessage(),
                exception);
        sender().tell(exception, self());
    }

    protected void onReceiveUnsupportedMessage(String callerName) {
        ProjectLogger.log(callerName + ": unsupported operation", LoggerEnum.INFO);
        /**
         * TODO Need to replace null reference from getLocalized method and replace with requested local.
         */
        BaseException exception =
                new ActorServiceException.InvalidOperationName(
                        IResponseMessage.INVALID_OPERATION_NAME,
                        getLocalizedMessage(IResponseMessage.INVALID_OPERATION_NAME,null),
                        ResponseCode.CLIENT_ERROR.getCode());
        sender().tell(exception, self());
    }


    protected String getLocalizedMessage(String key, Locale locale){
        return localizer.getMessage(key, locale);
    }
}
