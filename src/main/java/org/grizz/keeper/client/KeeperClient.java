package org.grizz.keeper.client;

import com.google.gson.Gson;
import lombok.Builder;
import org.apache.http.message.BasicNameValuePair;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.resources.EntriesResourceProvider;
import org.grizz.keeper.client.resources.GroupsResourceProvider;
import org.grizz.keeper.client.resources.UsersResourceProvider;

import java.util.List;

@Builder
public class KeeperClient {
    private static final String LOGIN = "/login";

    private final EntriesResourceProvider entriesResourceProvider;
    private final UsersResourceProvider usersResourceProvider;
    private final GroupsResourceProvider groupsResourceProvider;
    private final HttpAdapter http;
    private final Gson gson = new Gson();

    public KeeperClient login(String login, String password) {
        http.post(LOGIN, null,
          new BasicNameValuePair("username", login),
          new BasicNameValuePair("password", password));
        return this;
    }

    public KeeperClient logout() {
        return this;
    }

    public KeeperClient changePassword(String oldPassword, String password) {
        return null;
    }

    public List<String> getCurrentUserKeys() {
        return null;
    }

    public List<String> getCurrentUserGroups() {
        return null;
    }

    public EntriesResourceProvider entries() {
        return entriesResourceProvider;
    }

    public GroupsResourceProvider groups() {
        return groupsResourceProvider;
    }

    public UsersResourceProvider users() {
        return usersResourceProvider;
    }
}
