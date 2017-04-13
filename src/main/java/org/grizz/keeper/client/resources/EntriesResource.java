package org.grizz.keeper.client.resources;

import org.grizz.keeper.client.model.KeeperEntry;

import java.util.Date;
import java.util.List;

public class EntriesResource {
    public KeeperEntry add(KeeperEntry keeperEntry) {
        return null;
    }

    public List<KeeperEntry> add(List<KeeperEntry> keeperEntries) {
        return null;
    }

    public int deleteAll(String key) {
        return 0;
    }

    public int delete(KeeperEntry entry) {
        return 0;
    }

    public int deleteAllSince(String key, long timestamp) {
        return 0;
    }

    public int deleteAllSince(String key, Date date) {
        return this.deleteAllSince(key, date.getTime());
    }

    public List<KeeperEntry> getHistory(String key) {
        return null;
    }

    public List<KeeperEntry> getHistory(String key, long timestamp) {
        return null;
    }

    public List<KeeperEntry> getHistory(String key, Date date) {
        return this.getHistory(key, date.getTime());
    }

    public KeeperEntry getLast(String key) {
        return null;
    }
}
