package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.service.AddressServiceImpl;
import org.sunbird.user.service.IAddressService;

/**
 * this actor class is used to process the user Address entity
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"insertUserAddress", "updateUserAddress"},
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
            case "insertUserAddress":
                insertAddress(request);
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

    private void insertAddress(Request request) {
        startTrace("insertUserAddress");
        try {
            addressService = new AddressServiceImpl();
            response = addressService.insertAddress(request);
            sender().tell(response, self());
        } catch (BaseException ex) {
            sender().tell(ex, self());
        }
        endTrace("insertUserAddress");
    }

}
