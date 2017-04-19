package org.grizz.keeper.client.http.exceptions;

import org.grizz.keeper.client.model.KeeperEntry;

public class ConflictException extends KeeperApiException {
    public ConflictException(KeeperEntry body) {
        super(body);
    }
}
