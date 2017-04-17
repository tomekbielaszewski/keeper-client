package org.grizz.keeper.client.http.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString
public class KeeperApiException extends RuntimeException {
    private final @Getter KeeperEntry body;

    public KeeperApiException(Throwable cause) {
        super(cause);
        this.body = null;
    }

    public KeeperApiException(KeeperEntry body) {
        this.body = body;
    }
}
