package org.grizz.keeper.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KeeperKeysGroup {
    private String id;
    private String name;
    private String owner;
    private List<String> keys;
}
