package org.grizz.keeper.client.resources;

import com.google.gson.Gson;
import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.model.KeeperEntry;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Builder
public class EntriesResourceProvider {
    public static final String GET_LAST = "/entries/last/{0}"; //TODO Change to '/entries/{0}/last' - what if we have key 'last' and under {0} would went a number? Conflicts with history.
    public static final String GET_HISTORY = "/entries/{0}";
    public static final String GET_HISTORY_SINCE = "/entries/{0}/{1}";
    public static final String ADD = "/entries";
    public static final String DELETE_BY_ID = "/entries/{0}";
    public static final String DELETE_ALL_BY_KEY = "/entries/all/{0}";
    public static final String DELETE_ALL_BY_KEY_AND_DATE = "/entries/all/{0}/exact/{1}";
    public static final String DELETE_ALL_BEFORE_BY_KEY = "/entries/all/{0}/older/than/{1}";

    private final HttpAdapter http;
    private final Gson gson = new Gson();

    public List<KeeperEntry> getHistory(String key) {
        String keeperEntriesJson = http.get(format(GET_HISTORY, key));
        KeeperEntry[] keeperEntries = gson.fromJson(keeperEntriesJson, KeeperEntry[].class);
        return Arrays.asList(keeperEntries);
    }

    public List<KeeperEntry> getHistory(String key, long timestamp) {
        String keeperEntriesJson = http.get(format(GET_HISTORY_SINCE, key, timestamp));
        KeeperEntry[] keeperEntries = gson.fromJson(keeperEntriesJson, KeeperEntry[].class);
        return Arrays.asList(keeperEntries);
    }

    public List<KeeperEntry> getHistory(String key, Date date) {
        return this.getHistory(key, date.getTime());
    }

    public KeeperEntry getLast(String key) {
        String keeperEntryJson = http.get(format(GET_LAST, key));
        return gson.fromJson(keeperEntryJson, KeeperEntry.class);
    }

    public KeeperEntry add(KeeperEntry keeperEntry) {
        String newKeeperEntryJson = gson.toJson(keeperEntry);
        String savedKeeperEntryJson = http.post(ADD, newKeeperEntryJson);
        return gson.fromJson(savedKeeperEntryJson, KeeperEntry.class);
    }

    public KeeperEntry delete(String id) {
        String deletionNotification = http.delete(format(DELETE_BY_ID, id));
        return gson.fromJson(deletionNotification, KeeperEntry.class);
    }

    public KeeperEntry deleteAll(String key) {
        String deletionNotification = http.delete(format(DELETE_ALL_BY_KEY, key));
        return gson.fromJson(deletionNotification, KeeperEntry.class);
    }

    public KeeperEntry deleteExact(String key, long timestamp) {
        String deletionNotification = http.delete(format(DELETE_ALL_BY_KEY_AND_DATE, key, timestamp));
        return gson.fromJson(deletionNotification, KeeperEntry.class);
    }

    public KeeperEntry deleteExact(String key, Date date) {
        return this.deleteExact(key, date.getTime());
    }

    public KeeperEntry deleteAllOlderThan(String key, long timestamp) {
        String deletionNotification = http.delete(format(DELETE_ALL_BEFORE_BY_KEY, key, timestamp));
        return gson.fromJson(deletionNotification, KeeperEntry.class);
    }

    public KeeperEntry deleteAllOlderThan(String key, Date date) {
        return this.deleteAllOlderThan(key, date.getTime());
    }
}
