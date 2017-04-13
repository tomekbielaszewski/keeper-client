package org.grizz;

import org.grizz.keeper.client.KeeperClient;
import org.grizz.keeper.client.model.KeeperEntry;
import org.grizz.keeper.client.model.KeeperKeysGroup;
import org.grizz.keeper.client.model.KeeperEntriesGroup;
import org.grizz.keeper.client.model.KeeperUser;

import java.util.Date;
import java.util.List;

public class KeeperClientApplication {

    public static void main(String[] args) {
        String login = "";
        String password = "";
        String key = "";
        String oldPassword = "";
        String groupName = "";
        String keeperUrl = "";
        long timestamp = 0;
        Date date = null;
        KeeperEntry entry = null;
        List<KeeperEntry> entries = null;
        KeeperUser user = null;
        KeeperKeysGroup group = null;

        KeeperClient keeperClient = new KeeperClient(keeperUrl);

        KeeperClient loggedInState = keeperClient.login(login, password);
        KeeperClient loggedOutState = keeperClient.logout();

        KeeperClient changedPasswordButStillLoggedIn = keeperClient.changePassword(oldPassword, password);
        List<String> currentUserKeys = keeperClient.getCurrentUserKeys();
        List<String> currentUserGroups = keeperClient.getCurrentUserGroups();

        KeeperEntry addedEntry = keeperClient.entries().add(entry);
        List<KeeperEntry> addedEntries = keeperClient.entries().add(entries);

        List<KeeperEntry> entriesHistoryFromKey = keeperClient.entries().getHistory(key);
        List<KeeperEntry> entriesHistoryFromKeySinceTimestamp = keeperClient.entries().getHistory(key, timestamp);
        List<KeeperEntry> entriesHistoryFromKeySinceDate = keeperClient.entries().getHistory(key, date);
        KeeperEntry lastEntryFromKey = keeperClient.entries().getLast(key);

        int amountOfDeletedEntriesFromKey = keeperClient.entries().deleteAll(key);
        int amountOfDeletedEntriesFromKeyOlderThanTimestamp = keeperClient.entries().deleteAllSince(key, timestamp);
        int amountOfDeletedEntriesFromKeyOlderThanDate = keeperClient.entries().deleteAllSince(key, date);
        int amountOfDeletedEntries_hereUsuallyOne = keeperClient.entries().delete(entry);

        List<KeeperUser> allUsers = keeperClient.users().getAll();
        KeeperUser currentUser = keeperClient.users().getCurrent();
        KeeperUser userByLogin = keeperClient.users().get(login);

        KeeperUser addedUser = keeperClient.users().add(user);

        KeeperKeysGroup groupByGroupName = keeperClient.groups().get(groupName);
        KeeperKeysGroup addedGroup = keeperClient.groups().add(group);
        KeeperKeysGroup updatedGroup = keeperClient.groups().update(group);
        KeeperEntriesGroup latestEntriesFromGroupByGroupName = keeperClient.groups().getEntries(groupName);
        KeeperEntriesGroup latestEntriesFromGroupByGroup = keeperClient.groups().getEntries(group);

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
}
