package org.grizz.keeper.client.http.exceptions;

import lombok.Getter;
import org.grizz.keeper.client.model.KeeperEntry;

public class KeeperApiException extends RuntimeException {
    @Getter
    protected final KeeperEntry body;

    public KeeperApiException(Throwable cause) {
        super(cause);
        this.body = null;
    }

    public KeeperApiException(KeeperEntry body) {
        super(body.toString());
        this.body = body;
    }
}
