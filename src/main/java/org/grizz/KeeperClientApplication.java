package org.grizz;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.grizz.keeper.client.KeeperClient;
import org.grizz.keeper.client.KeeperClientFactory;
import org.grizz.keeper.client.model.KeeperEntriesGroup;
import org.grizz.keeper.client.model.KeeperEntry;
import org.grizz.keeper.client.model.KeeperKeysGroup;
import org.grizz.keeper.client.model.KeeperUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
        List<KeeperEntry> entries = null;
        KeeperUser user = null;
        KeeperKeysGroup group = null;

        KeeperClient keeperClient = KeeperClientFactory.create(keeperUrl);

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

        int amountOfDeletedEntries_hereAlwaysOne = keeperClient.entries().delete(entry);
        int amountOfDeletedEntries_byExactTimestamp = keeperClient.entries().deleteExact(key, timestamp);
        int amountOfDeletedEntries_byExactDate = keeperClient.entries().deleteExact(key, date);
        int amountOfDeletedEntriesFromKey = keeperClient.entries().deleteAll(key);
        int amountOfDeletedEntriesFromKeyOlderThanTimestamp = keeperClient.entries().deleteAllSince(key, timestamp);
        int amountOfDeletedEntriesFromKeyOlderThanDate = keeperClient.entries().deleteAllSince(key, date);

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

    public static void main(String[] args) throws IOException {
        String keeper = args[0];
        String login = args[1];
        String password = args[2];

//        BasicCookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//
//        HttpUriRequest request = RequestBuilder.post()
//          .setUri(keeper + "/login")
//          .addParameter("username", login)
//          .addParameter("password", password)
//          .build();
//
//        CloseableHttpResponse response = httpClient.execute(request);
//
//        request = RequestBuilder.get(keeper + "/users").build();
//
//        response = httpClient.execute(request);
//
//        InputStream content = response.getEntity().getContent();
//        String contentFromKeeper = IOUtils.toString(content, "UTF-8");
//
//        System.out.println(contentFromKeeper);



        KeeperClient keeperClient = KeeperClientFactory.create(keeper);
        keeperClient.login(login, password);

        KeeperEntry vab = keeperClient.entries().getLast("vab");

//        System.out.println(vab);
    }
}
