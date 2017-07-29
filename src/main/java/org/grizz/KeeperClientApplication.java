package org.grizz;

import org.grizz.keeper.client.KeeperClient;
import org.grizz.keeper.client.KeeperClientFactory;
import org.grizz.keeper.client.model.KeeperEntriesGroup;
import org.grizz.keeper.client.model.KeeperEntry;
import org.grizz.keeper.client.model.KeeperKeysGroup;
import org.grizz.keeper.client.model.KeeperUser;

import java.io.IOException;
import java.util.*;

public class KeeperClientApplication {

    public static void clientSampleUsage() {
        String login = "";
        String password = "";
        String key = "";
        String oldPassword = "";
        String groupName = "";
        String keeperUrl = "";
        long timestamp = 0;
        Date date = null;
        KeeperEntry entry = null;
        KeeperUser user = null;
        KeeperKeysGroup group = null;

        KeeperClient keeperClient = KeeperClientFactory.create(keeperUrl);

        KeeperClient loggedInState = keeperClient.login(login, password);
        KeeperClient loggedOutState = keeperClient.logout();

        KeeperClient changedPasswordButStillLoggedIn = keeperClient.changePassword(oldPassword, password);
        List<String> currentUserKeys = keeperClient.getCurrentUserKeys();
        List<KeeperKeysGroup> currentUserGroups = keeperClient.getCurrentUserGroups();

        KeeperEntry addedEntry = keeperClient.entries().add(entry);

        List<KeeperEntry> entriesHistoryFromKey = keeperClient.entries().getHistory(key);
        List<KeeperEntry> entriesHistoryFromKeySinceTimestamp = keeperClient.entries().getHistory(key, timestamp);
        List<KeeperEntry> entriesHistoryFromKeySinceDate = keeperClient.entries().getHistory(key, date);
        KeeperEntry lastEntryFromKey = keeperClient.entries().getLast(key);

        KeeperEntry amountOfDeletedEntries_hereAlwaysOne = keeperClient.entries().delete(entry.getId());
        KeeperEntry amountOfDeletedEntries_byExactTimestamp = keeperClient.entries().deleteExact(key, timestamp);
        KeeperEntry amountOfDeletedEntries_byExactDate = keeperClient.entries().deleteExact(key, date);
        KeeperEntry amountOfDeletedEntriesFromKey = keeperClient.entries().deleteAll(key);
        KeeperEntry amountOfDeletedEntriesFromKeyOlderThanTimestamp = keeperClient.entries().deleteAllOlderThan(key, timestamp);
        KeeperEntry amountOfDeletedEntriesFromKeyOlderThanDate = keeperClient.entries().deleteAllOlderThan(key, date);

        List<KeeperUser> allUsers = keeperClient.users().getAll();
        KeeperUser userByLogin = keeperClient.users().get(login);

        KeeperUser addedUser = keeperClient.users().create(user);

        KeeperKeysGroup groupByGroupName = keeperClient.groups().get(groupName);
        KeeperKeysGroup addedGroup = keeperClient.groups().add(group);
        KeeperKeysGroup updatedGroup = keeperClient.groups().update(group);
        KeeperEntriesGroup latestEntriesFromGroupByGroupName = keeperClient.groups().getEntries(groupName);
        KeeperEntriesGroup latestEntriesFromGroupByGroup = keeperClient.groups().getEntries(group.getName());

        List<KeeperEntry> mirkoonline = keeperClient
          .login(login, password)
          .changePassword(oldPassword, password)
          .logout()
          .entries()
          .getHistory("mirkoonline");

        List<String> currentUserKeys_ = keeperClient
          .login(login, password)
          .getCurrentUserKeys();
    }

    public static void main(String[] args) throws IOException {
        String keeper = args[0];
        String login = args[1];
        String password = args[2];

        KeeperClient keeperClient = KeeperClientFactory.create(keeper);
        keeperClient.login(login, password);

        keeperClient.entries().add(dummyEntry("key", "value"));
        keeperClient.entries().add(dummyEntry("key", "value"));
        keeperClient.entries().add(dummyEntry("key", "value"));
        keeperClient.entries().add(dummyEntry("key", "latest"));

        keeperClient.entries().add(dummyEntry("another-key", "value"));
        keeperClient.entries().add(dummyEntry("another-key", "value"));
        keeperClient.entries().add(dummyEntry("another-key", "value"));
        keeperClient.entries().add(dummyEntry("another-key", "latest"));

//        keeperClient.groups().add(dummyGroup("dummyGroup", "key", "another-key"));

        KeeperEntriesGroup dummyGroup = keeperClient.groups().getEntries("dummyGroup");

        System.out.println(dummyGroup);

        keeperClient.entries().add(KeeperEntry.builder().build());
    }

    private static KeeperEntry dummyEntry(String key, String value) {
        HashMap hashMap = new HashMap();
        hashMap.put(key, value);
        return KeeperEntry.builder()
                .key(key)
                .value(hashMap)
                .build();
    }

    private static KeeperKeysGroup dummyGroup(String name, String... keys) {
        ArrayList keysList = new ArrayList();
        for (String key : keys) {
            keysList.add(key);
        }
        return KeeperKeysGroup.builder()
                .name(name)
                .keys(keysList)
                .build();
    }
}
