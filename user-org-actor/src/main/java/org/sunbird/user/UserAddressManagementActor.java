package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.service.AddressServiceImpl;
import org.sunbird.user.service.IAddressService;
import org.sunbird.util.ProjectLogger;

/**
 * this actor class is used to process the user Address entity
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createUserAddress", "updateUserAddress"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)
public class UserAddressManagementActor extends BaseActor {

    private Response response = null;
    private IAddressService addressService = null;

    @Override
    public void onReceive(Request request) throws Throwable {
        String operation = request.getOperation();
        switch (operation) {
            case "createUserAddress":
                createAddress(request);
                break;

            case "updateUserAddress":
                updateAddress(request);
                break;

            default:
                onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    private void updateAddress(Request request) {
    }

    private void createAddress(Request request) {
        startTrace("createUserAddress");
        try {
            addressService = new AddressServiceImpl();
            response = addressService.createAddress(request);
            sender().tell(response, self());
        } catch (BaseException ex) {
            ProjectLogger.log("UserAddressManagementActor:createAddress : Exception occurred while inserting user address : ",ex);
            sender().tell(ex, self());
        }
        endTrace("createUserAddress");
    }

}
