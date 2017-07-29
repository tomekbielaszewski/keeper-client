package org.grizz.keeper.client.http.exceptions;

import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString(callSuper = true)
public class AuthenticationException extends KeeperApiException {
    public AuthenticationException(KeeperEntry body) {
        super(body);
    }
}
