package org.grizz.keeper.client.resources;

import com.google.gson.Gson;
import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.model.KeeperUser;

import java.util.Arrays;
import java.util.List;

import static java.text.MessageFormat.format;

@Builder
public class UsersResourceProvider {
    private static final String ALL_USERS = "/users";
    private static final String USER_BY_LOGIN = "/users/{0}";
    private static final String CREATE_USER = "/users";

    private final HttpAdapter http;
    private final Gson gson = new Gson();

    public List<KeeperUser> getAll() {
        String users = http.get(ALL_USERS);
        return Arrays.asList(gson.fromJson(users, KeeperUser[].class));
    }

    public KeeperUser get(String login) {
        String users = http.get(format(USER_BY_LOGIN, login));
        return gson.fromJson(users, KeeperUser.class);
    }

    public KeeperUser create(KeeperUser user) {
        String userJson = gson.toJson(user);
        String createdUserJson = http.post(CREATE_USER, userJson);
        return gson.fromJson(createdUserJson, KeeperUser.class);
    }
}
