package org.grizz.keeper.client.resources;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.model.KeeperKeysGroup;
import org.grizz.keeper.client.model.KeeperEntriesGroup;

@Builder
public class GroupsResourceProvider {
    private final HttpAdapter http;

    public KeeperKeysGroup get(String groupName) {
        return null;
    }

    public KeeperKeysGroup add(KeeperKeysGroup group) {
        return null;
    }

    public KeeperKeysGroup update(KeeperKeysGroup group) {
        return null;
    }

    public KeeperEntriesGroup getEntries(String groupName) {
        return null;
    }

    public KeeperEntriesGroup getEntries(KeeperKeysGroup group) {
        return null;
    }
}
