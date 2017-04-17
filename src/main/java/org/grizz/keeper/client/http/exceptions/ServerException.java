package org.grizz.keeper.client.http.exceptions;

import org.grizz.keeper.client.model.KeeperEntry;

public class ServerException extends KeeperApiException {
    public ServerException(KeeperEntry body) {
        super(body);
    }
}
