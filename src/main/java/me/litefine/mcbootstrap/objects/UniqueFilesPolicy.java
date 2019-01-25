package me.litefine.mcbootstrap.objects;

import me.litefine.mcbootstrap.objects.booting.BootingServer;
import me.litefine.mcbootstrap.objects.booting.PrimaryBootingServer;
import me.litefine.mcbootstrap.utils.BasicUtils;

import java.io.File;

public interface UniqueFilesPolicy {

    File getUniqueFolder(PrimaryBootingServer primaryBootingServer, BootingServer forObject);

    UniqueFilesPolicy INORDER_POLICY = (primaryBootingServer, forObject) -> {
        int index = primaryBootingServer.getClonedServers().indexOf(forObject);
        return primaryBootingServer.getUniqueFilesFolder().listFiles()[index];
    };

    UniqueFilesPolicy RANDOM_POLICY = (primaryBootingServer, forObject) -> {
        int bound = primaryBootingServer.getUniqueFilesFolder().listFiles().length - 1;
        return primaryBootingServer.getUniqueFilesFolder().listFiles()[BasicUtils.RANDOM.nextInt(bound)];
    };

    static UniqueFilesPolicy getUniqueFilesPolicy(String name) {
        if (name.equalsIgnoreCase("INORDER")) return INORDER_POLICY;
        else if (name.equalsIgnoreCase("RANDOM")) return RANDOM_POLICY;
        else if (name.equalsIgnoreCase("CUSTOM")) return null;
        else return null;
    }

}