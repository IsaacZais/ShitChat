package com.ianofferdahl.shitchat;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Isaac on 4/17/2015.
 */
public class Utils {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private static String uuid;

    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static String resetUUID()
    {
        Utils.uuid = UUID.randomUUID().toString();
        return Utils.getUUID();
    }

    public static String getUUID()
    {
        if (uuid == null) {
            Utils.uuid = UUID.randomUUID().toString();
            return Utils.getUUID();
        } else {
            return uuid;
        }
    }

}
