package com.spring_market.domain;

import java.time.LocalDateTime;

public class PurchaseItem {
    private int porductId;
    private int quantity;
    private double total;
    private boolean active;
    public int getPorductId() {
        return porductId;
    }

    public void setPorductId(int porductId) {
        this.porductId = porductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
