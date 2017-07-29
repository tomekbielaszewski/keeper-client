package org.grizz.keeper.client.http.exceptions;

import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString(callSuper = true)
public class ConflictException extends KeeperApiException {
    public ConflictException(KeeperEntry body) {
        super(body);
    }
}
