package org.sunbird.actor.core;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import akka.routing.FromConfig;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ResponseCode;
import org.sunbird.exception.actorservice.ActorServiceException;
import org.sunbird.request.Request;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * @author Amit Kumar
 */
public abstract class BaseActor extends UntypedAbstractActor {

    public abstract void onReceive(Request request) throws Throwable;

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
        BaseException exception =
                new ActorServiceException.InvalidOperationName(
                        ResponseCode.invalidOperationName.getErrorCode(),
                        ResponseCode.invalidOperationName.getErrorMessage(),
                        ResponseCode.CLIENT_ERROR.getResponseCode());
        sender().tell(exception, self());
    }

    private SupervisorStrategy strategy = new OneForOneStrategy(false, DeciderBuilder.
            match(ArithmeticException.class, e -> {
                return SupervisorStrategy.restart();
            }).
            match(ArithmeticException.class, e -> {
                notifyConsumerFailure("operation", e);
                return SupervisorStrategy.stop();
            }).
            match(Throwable.class, e -> {
                notifyConsumerFailure("operation", e);
                return SupervisorStrategy.stop();
            }).build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    private void notifyConsumerFailure(String operation, Throwable failure) {
        ActorRef actorRef = ActorCache.getActorRef(operation);
        if (actorRef != null) {
            actorRef.tell(new Status.Failure(failure), self());
        }
    }

    protected ActorRef getChildActor(Class<? extends BaseActor> actor, String dispatcher, ActorContext context){
        Props props;
        if (StringUtils.isNotBlank(dispatcher)) {
            props = Props.create(actor).withDispatcher(dispatcher);
        } else {
            props = Props.create(actor);
        }
        return context.actorOf(FromConfig.getInstance().props(props), actor.getSimpleName());
    }
}
