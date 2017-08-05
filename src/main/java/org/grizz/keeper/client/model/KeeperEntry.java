package org.grizz.keeper.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class KeeperEntry<T> {
    private String id;
    private String key;
    private Map<String, T> value;
    private String owner;
    private Long date;
}
