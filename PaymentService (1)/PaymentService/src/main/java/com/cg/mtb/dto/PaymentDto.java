package com.cg.mtb.dto;

import java.util.Date;
import java.time.LocalDate;

public class PaymentDto {

    private int paymentId;
    private int userId;
    private int ticketId;
    private int amount;
    private LocalDate paymentDate;
    private String paymentStatus;

    public PaymentDto() {
    }

    public PaymentDto(int paymentId, int userId, int ticketId, int amount, LocalDate paymentDate, String paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

