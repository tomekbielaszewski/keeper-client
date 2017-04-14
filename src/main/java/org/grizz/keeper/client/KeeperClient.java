package org.grizz.keeper.client;

import com.google.gson.Gson;
import lombok.Builder;
import org.apache.http.message.BasicNameValuePair;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.model.KeeperKeysGroup;
import org.grizz.keeper.client.model.KeeperPasswordChange;
import org.grizz.keeper.client.model.KeeperUser;
import org.grizz.keeper.client.resources.EntriesResourceProvider;
import org.grizz.keeper.client.resources.GroupsResourceProvider;
import org.grizz.keeper.client.resources.UsersResourceProvider;

import java.util.Arrays;
import java.util.List;

@Builder
public class KeeperClient {
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String CURRENT_USER = "/user";
    private static final String CURRENT_USER_KEYS = "/user/keys";
    private static final String CURRENT_USER_GROUPS = "/user/groups";
    private static final String CHANGE_PASSWORD = "/user/password";

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
        http.get(LOGOUT);
        return this;
    }

    public KeeperClient changePassword(String oldPassword, String newPassword) {
        String changePasswordRequest = gson.toJson(new KeeperPasswordChange(oldPassword, newPassword));
        http.put(CHANGE_PASSWORD, changePasswordRequest);
        return this;
    }

    public KeeperUser getCurrentUser() {
        String user = http.get(CURRENT_USER);
        return gson.fromJson(user, KeeperUser.class);
    }

    public List<String> getCurrentUserKeys() {
        String keysJson = http.get(CURRENT_USER_KEYS);
        return Arrays.asList(gson.fromJson(keysJson, String[].class));
    }

    public List<KeeperKeysGroup> getCurrentUserGroups() {
        String groupsJson = http.get(CURRENT_USER_GROUPS);
        return Arrays.asList(gson.fromJson(groupsJson, KeeperKeysGroup[].class));
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
