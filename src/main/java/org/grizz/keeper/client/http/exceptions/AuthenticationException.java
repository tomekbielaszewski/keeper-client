package org.grizz.keeper.client.http.exceptions;

import org.grizz.keeper.client.model.KeeperEntry;

public class AuthenticationException extends KeeperApiException {
    public AuthenticationException(KeeperEntry body) {
        super(body);
    }
}
