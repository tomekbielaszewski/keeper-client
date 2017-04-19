package org.grizz.keeper.client.resources;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.http.JsonCall;
import org.grizz.keeper.client.model.KeeperEntriesGroup;
import org.grizz.keeper.client.model.KeeperKeysGroup;

import static java.text.MessageFormat.format;

@Builder
public class GroupsResourceProvider {
    private static final String GROUP_BY_NAME = "/groups/{0}";
    private static final String CREATE_GROUP = "/groups";
    private static final String UPDATE_GROUP = "/groups";
    private static final String GROUPED_ENTRIES = "/groups/entries/{0}";

    private final HttpAdapter http;
    private final JsonCall jsonCall = new JsonCall();

    public KeeperKeysGroup get(String groupName) {
        return jsonCall.of(() -> http.get(format(GROUP_BY_NAME, groupName)))
          .executeWithResultAs(KeeperKeysGroup.class);
    }

    public KeeperKeysGroup add(KeeperKeysGroup group) {
        return jsonCall.of((groupJson) -> http.post(CREATE_GROUP, groupJson))
          .with(group)
          .executeWithResultAs(KeeperKeysGroup.class);
    }

    public KeeperKeysGroup update(KeeperKeysGroup group) {
        return jsonCall.of((groupJson) -> http.put(UPDATE_GROUP, groupJson))
          .with(group)
          .executeWithResultAs(KeeperKeysGroup.class);
    }

    public KeeperEntriesGroup getEntries(String groupName) {
        return jsonCall.of(() -> http.get(format(GROUPED_ENTRIES, groupName)))
          .executeWithResultAs(KeeperEntriesGroup.class);
    }
}
