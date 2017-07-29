package org.grizz.keeper.client.http.exceptions;

import lombok.ToString;
import org.grizz.keeper.client.model.KeeperEntry;

@ToString(callSuper = true)
public class ServerException extends KeeperApiException {
    public ServerException() {
        super(
                KeeperEntry.builder()
                        .key("ERROR")
                        .value("Unknown internal server error. Check the logs or notify The Admin.")
                        .owner("https://www.wykop.pl/ludzie/Grizwold/")
                        .date(System.currentTimeMillis())
                        .build()
        );
    }
}
