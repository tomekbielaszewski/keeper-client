package org.grizz.keeper.client.http.exceptions;

import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString(callSuper = true)
public class NotFoundException extends KeeperApiException {
    public NotFoundException(KeeperEntry body) {
        super(body);
    }
}
