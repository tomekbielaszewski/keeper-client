package org.grizz.keeper.client.resources;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.http.JsonCall;
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
    private final JsonCall jsonCall = new JsonCall();

    public List<KeeperUser> getAll() {
        KeeperUser[] keeperUsers = jsonCall.of(() -> http.get(ALL_USERS))
          .executeWithResultAs(KeeperUser[].class);
        return Arrays.asList(keeperUsers);
    }

    public KeeperUser get(String login) {
        return jsonCall.of(() -> http.get(format(USER_BY_LOGIN, login)))
          .executeWithResultAs(KeeperUser.class);
    }

    public KeeperUser create(KeeperUser user) {
        return jsonCall.of((userJson) -> http.post(CREATE_USER, userJson))
          .with(user)
          .executeWithResultAs(KeeperUser.class);
    }
}
