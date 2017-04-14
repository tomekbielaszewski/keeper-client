package org.grizz.keeper.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class KeeperEntriesGroup {
    private String name;
    private List<KeeperEntry> entries;
}
