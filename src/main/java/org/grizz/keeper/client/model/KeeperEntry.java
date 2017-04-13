package org.grizz.keeper.client.model;

import lombok.Data;

@Data
public class KeeperEntry<T> {
    private String id;
    private String key;
    private T value;
    private String owner;
    private long date;
}
