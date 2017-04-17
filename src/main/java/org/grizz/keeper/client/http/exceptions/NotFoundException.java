package org.grizz.keeper.client.http.exceptions;

import org.grizz.keeper.client.model.KeeperEntry;

public class NotFoundException extends KeeperApiException {
    public NotFoundException(KeeperEntry body) {
        super(body);
    }
}
