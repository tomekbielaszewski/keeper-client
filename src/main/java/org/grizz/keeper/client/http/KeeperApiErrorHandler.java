package org.grizz.keeper.client.http;

import com.google.gson.Gson;
import org.grizz.keeper.client.http.exceptions.*;
import org.grizz.keeper.client.model.KeeperEntry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KeeperApiErrorHandler {
    private Map<Integer, Consumer<KeeperEntry>> errors = new HashMap<>(4);
    {
        this.errors.put(400, keeperEntry -> {throw new BadRequestException(keeperEntry);});
        this.errors.put(401, keeperEntry -> {throw new AuthenticationException(keeperEntry);});
        this.errors.put(404, keeperEntry -> {throw new NotFoundException(keeperEntry);});
        this.errors.put(409, keeperEntry -> {throw new ConflictException(keeperEntry);});
        this.errors.put(500, keeperEntry -> {throw new ServerException();});
    }

    public KeeperApiErrorHandler() {}

    public void handle(String content, int statusCode) {
        KeeperEntry errorContent = new Gson().fromJson(content, KeeperEntry.class);

        if(errors.containsKey(statusCode)) {
            Consumer<KeeperEntry> error = errors.get(statusCode);
            error.accept(errorContent);
        } else {
            throw new KeeperApiException(errorContent);
        }
    }
}
