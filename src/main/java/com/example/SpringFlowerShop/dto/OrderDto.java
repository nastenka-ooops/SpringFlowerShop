package com.example.SpringFlowerShop.dto;

import com.example.SpringFlowerShop.entity.Status;
import jakarta.persistence.*;

import java.sql.Date;

public class OrderDto {
    private Long id;
    private Date date;
    private int customerId;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
