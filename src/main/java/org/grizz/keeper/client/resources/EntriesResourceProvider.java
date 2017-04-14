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
    public static final String ADD = "";
    public static final String ADD_MANY = ""; //TODO there shouldn't be batch requests. How about error handling and transactionality?
    public static final String DELETE = "";
    public static final String DELETE_ALL = "";
    public static final String DELETE_ALL_BEFORE = "";

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

    public List<KeeperEntry> add(List<KeeperEntry> keeperEntries) {
        String newKeeperEntriesJson = gson.toJson(keeperEntries.toArray(new KeeperEntry[]{}));
        String savedKeeperEntriesJson = http.post(ADD_MANY, newKeeperEntriesJson);
        return Arrays.asList(gson.fromJson(savedKeeperEntriesJson, KeeperEntry[].class));
    }

    public int delete(KeeperEntry entry) {
        return 0; //TODO keeper does not allow deleting by ID
    }

    public int deleteExact(String key, long timestamp) {
        return 0;
    }

    public int deleteExact(String key, Date date) {
        return this.deleteExact(key, date.getTime());
    }

    public int deleteAll(String key) {
        return 0;
    }

    public int deleteAllSince(String key, long timestamp) {
        return 0;
    }

    public int deleteAllSince(String key, Date date) {
        return this.deleteAllSince(key, date.getTime());
    }
}
