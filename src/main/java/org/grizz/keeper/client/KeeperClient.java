package org.grizz.keeper.client;

import org.grizz.keeper.client.resources.EntriesResource;
import org.grizz.keeper.client.resources.GroupsResource;
import org.grizz.keeper.client.resources.UsersResource;

import java.util.List;

public class KeeperClient {
    public KeeperClient(String keeperUrl) {

    }

    public EntriesResource entries() {
        return null;
    }

    public KeeperClient login(String login, String password) {
        return this;
    }

    public KeeperClient logout() {
        return this;
    }

    public UsersResource users() {
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

    public GroupsResource groups() {
        return null;
    }
}
