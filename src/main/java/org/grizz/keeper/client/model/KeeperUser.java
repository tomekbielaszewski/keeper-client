package org.grizz.keeper.client.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class KeeperUser {
    private String id;
    private String login;
    private String password;
    private Set<String> roles;
}
