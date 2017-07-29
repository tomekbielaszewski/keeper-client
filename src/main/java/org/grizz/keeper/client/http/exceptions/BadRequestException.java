package org.grizz.keeper.client.http.exceptions;

import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString(callSuper = true)
public class BadRequestException extends KeeperApiException {
    public BadRequestException(KeeperEntry body) {
        super(body);
    }
}
