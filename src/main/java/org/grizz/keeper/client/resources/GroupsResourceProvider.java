package org.grizz.keeper.client.resources;

import com.google.gson.Gson;
import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
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
    private final Gson gson = new Gson();

    public KeeperKeysGroup get(String groupName) {
        String keysGroupJson = http.get(format(GROUP_BY_NAME, groupName));
        return gson.fromJson(keysGroupJson, KeeperKeysGroup.class);
    }

    public KeeperKeysGroup add(KeeperKeysGroup group) {
        String groupJson = gson.toJson(group);
        String createdKeysGroupJson = http.post(CREATE_GROUP, groupJson);
        return gson.fromJson(createdKeysGroupJson, KeeperKeysGroup.class);
    }

    public KeeperKeysGroup update(KeeperKeysGroup group) {
        String groupJson = gson.toJson(group);
        String updatedKeysGroupJson = http.put(UPDATE_GROUP, groupJson);
        return gson.fromJson(updatedKeysGroupJson, KeeperKeysGroup.class);
    }

    public KeeperEntriesGroup getEntries(String groupName) {
        String entriesGroupJson = http.get(format(GROUPED_ENTRIES, groupName));
        return gson.fromJson(entriesGroupJson, KeeperEntriesGroup.class);
    }
}
