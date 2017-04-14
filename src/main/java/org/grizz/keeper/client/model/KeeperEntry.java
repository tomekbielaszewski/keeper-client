package org.grizz.keeper.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeeperEntry<T> {
    private String id;
    private String key;
    private T value;
    private String owner;
    private Long date;
}
