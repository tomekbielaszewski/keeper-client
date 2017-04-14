package org.grizz.keeper.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeeperPasswordChange {
    private String oldPassword;
    private String newPassword;
}
