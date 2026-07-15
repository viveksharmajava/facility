package com.playpro.playpro.facility.util;

import com.playpro.playpro.facility.entity.InventoryItem;

import java.math.BigDecimal;
import java.util.Collection;

public final class InventoryTotals {

    private InventoryTotals() {
    }

    public static BigDecimal safe(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public static BigDecimal reservedForItem(InventoryItem item) {
        BigDecimal onHand = safe(item.getQuantityOnHandTotal());
        BigDecimal atp = safe(item.getAvailableToPromiseTotal());
        BigDecimal reserved = onHand.subtract(atp);
        return reserved.signum() > 0 ? reserved : BigDecimal.ZERO;
    }

    public static BigDecimal sumOnHand(Collection<InventoryItem> items) {
        return items.stream()
                .map(item -> safe(item.getQuantityOnHandTotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal sumAtp(Collection<InventoryItem> items) {
        return items.stream()
                .map(item -> safe(item.getAvailableToPromiseTotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal sumReserved(Collection<InventoryItem> items) {
        return items.stream()
                .map(InventoryTotals::reservedForItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
