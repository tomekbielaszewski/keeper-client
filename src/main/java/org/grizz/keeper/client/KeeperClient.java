package org.grizz.keeper.client;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.resources.EntriesResourceProvider;
import org.grizz.keeper.client.resources.GroupsResourceProvider;
import org.grizz.keeper.client.resources.UsersResourceProvider;

import java.util.List;

@Builder
public class KeeperClient {
    private final EntriesResourceProvider entriesResourceProvider;
    private final UsersResourceProvider usersResourceProvider;
    private final GroupsResourceProvider groupsResourceProvider;
    private final HttpAdapter http;

    public EntriesResourceProvider entries() {
        return null;
    }

    public KeeperClient login(String login, String password) {
        return this;
    }

    public KeeperClient logout() {
        return this;
    }

    public UsersResourceProvider users() {
        return null;
    }

    public List<String> getCurrentUserKeys() {
        return null;
    }

    public List<String> getCurrentUserGroups() {
        return null;
    }

    public KeeperClient changePassword(String oldPassword, String password) {
        return null;
    }

    public GroupsResourceProvider groups() {
        return null;
    }
}
