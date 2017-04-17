package org.grizz.keeper.client.http.exceptions;

import org.grizz.keeper.client.model.KeeperEntry;

public class BadRequestException extends KeeperApiException {
    public BadRequestException(KeeperEntry body) {
        super(body);
    }
}
