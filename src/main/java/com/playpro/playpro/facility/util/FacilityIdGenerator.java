package com.playpro.playpro.facility.util;

import java.util.concurrent.ThreadLocalRandom;

public final class FacilityIdGenerator {

    private FacilityIdGenerator() {
    }

    public static String nextFacilityId() {
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        return "FAC" + System.currentTimeMillis() + random;
    }

    public static String nextInventoryItemId() {
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        return "INV" + System.currentTimeMillis() + random;
    }

    public static String nextReceiptId() {
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        return "RCP" + System.currentTimeMillis() + random;
    }

    public static String nextInventoryTransferId() {
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        return "XFR" + System.currentTimeMillis() + random;
    }

    public static String nextDetailSeqId(int sequence) {
        return String.format("%05d", sequence);
    }
}
