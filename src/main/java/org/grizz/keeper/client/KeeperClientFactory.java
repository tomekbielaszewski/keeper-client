package org.grizz.keeper.client;

import lombok.NonNull;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.resources.EntriesResourceProvider;
import org.grizz.keeper.client.resources.GroupsResourceProvider;
import org.grizz.keeper.client.resources.UsersResourceProvider;

public class KeeperClientFactory {
    public static KeeperClient create(@NonNull String keeperUrl) {
        HttpAdapter http = new HttpAdapter(keeperUrl);

        return KeeperClient.builder()
          .http(http)
          .entriesResourceProvider(createEntriesResourceProvider(http))
          .groupsResourceProvider(createGroupsResourceProvider(http))
          .usersResourceProvider(createUsersResourceProvider(http))
          .build();
    }

    private static EntriesResourceProvider createEntriesResourceProvider(HttpAdapter http) {
        return EntriesResourceProvider.builder()
          .http(http)
          .build();
    }

    private static GroupsResourceProvider createGroupsResourceProvider(HttpAdapter http) {
        return GroupsResourceProvider.builder()
          .http(http)
          .build();
    }

    private static UsersResourceProvider createUsersResourceProvider(HttpAdapter http) {
        return UsersResourceProvider.builder()
          .http(http)
          .build();
    }
}
