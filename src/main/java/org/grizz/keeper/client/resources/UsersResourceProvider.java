package org.grizz.keeper.client.resources;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.model.KeeperUser;

import java.util.List;

@Builder
public class UsersResourceProvider {
    private final HttpAdapter http;

    public List<KeeperUser> getAll() {
        return null;
    }

    public KeeperUser getCurrent() {
        return null;
    }

    public KeeperUser get(String login) {
        return null;
    }

    public KeeperUser add(KeeperUser user) {
        return null;
    }
}
