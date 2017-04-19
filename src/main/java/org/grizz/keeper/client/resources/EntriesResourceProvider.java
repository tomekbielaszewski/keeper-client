package org.grizz.keeper.client.resources;

import lombok.Builder;
import org.grizz.keeper.client.http.HttpAdapter;
import org.grizz.keeper.client.http.JsonCall;
import org.grizz.keeper.client.model.KeeperEntry;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.text.MessageFormat.format;

@Builder
public class EntriesResourceProvider {
    public static final String GET_LAST = "/entries/{0}/last";
    public static final String GET_HISTORY = "/entries/{0}";
    public static final String GET_HISTORY_SINCE = "/entries/{0}/{1}";
    public static final String ADD = "/entries";
    public static final String DELETE_BY_ID = "/entries/{0}";
    public static final String DELETE_ALL_BY_KEY = "/entries/all/{0}";
    public static final String DELETE_ALL_BY_KEY_AND_DATE = "/entries/all/{0}/exact/{1}";
    public static final String DELETE_ALL_BEFORE_BY_KEY = "/entries/all/{0}/older/than/{1}";

    private final HttpAdapter http;
    private final JsonCall jsonCall = new JsonCall();

    public List<KeeperEntry> getHistory(String key) {
        KeeperEntry[] keeperEntries = jsonCall.of(() -> http.get(format(GET_HISTORY, key)))
          .executeWithResultAs(KeeperEntry[].class);
        return Arrays.asList(keeperEntries);
    }

    public List<KeeperEntry> getHistory(String key, long timestamp) {
        KeeperEntry[] keeperEntries = jsonCall.of(() -> http.get(format(GET_HISTORY_SINCE, key, timestamp)))
          .executeWithResultAs(KeeperEntry[].class);
        return Arrays.asList(keeperEntries);
    }

    public List<KeeperEntry> getHistory(String key, Date date) {
        return this.getHistory(key, date.getTime());
    }

    public KeeperEntry getLast(String key) {
        return jsonCall.of(() -> http.get(format(GET_LAST, key)))
          .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry add(KeeperEntry keeperEntry) {
        return jsonCall.of((entryJson) -> http.post(ADD, entryJson))
          .with(keeperEntry)
          .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry delete(String id) {
        return jsonCall.of(() -> http.delete(format(DELETE_BY_ID, id)))
          .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry deleteAll(String key) {
        return jsonCall.of(() -> http.delete(format(DELETE_ALL_BY_KEY, key)))
          .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry deleteExact(String key, long timestamp) {
        return jsonCall.of(() -> http.delete(format(DELETE_ALL_BY_KEY_AND_DATE, key, timestamp)))
          .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry deleteExact(String key, Date date) {
        return this.deleteExact(key, date.getTime());
    }

    public KeeperEntry deleteAllOlderThan(String key, long timestamp) {
        return jsonCall.of(() -> http.delete(format(DELETE_ALL_BEFORE_BY_KEY, key, timestamp)))
        .executeWithResultAs(KeeperEntry.class);
    }

    public KeeperEntry deleteAllOlderThan(String key, Date date) {
        return this.deleteAllOlderThan(key, date.getTime());
    }
}
