package com.cg.mtb.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cg.mtb.dto.PaymentDto;
import com.cg.mtb.exception.PaymentNotFoundException;

public interface PaymentService {

	// 🎟️ 1️⃣ Make a payment for a booked ticket (User)
    PaymentDto makePayment(PaymentDto paymentDto);

    // 🎟️ 2️⃣ Get payment details by payment ID (User & Admin)
    Optional<PaymentDto> getPaymentById(int paymentId) throws PaymentNotFoundException;

    // 🎟️ 3️⃣ Get all payments made by a specific user (User)
    Set<PaymentDto> getUserPayments(int userId);

    //4️ Get all payments made by all users (Admin)
    Set<PaymentDto> getAllPayments();

    // 5️ Admin views a specific payment by ID
    Optional<PaymentDto> getAdminPaymentById(int paymentId) throws PaymentNotFoundException;

    // 🛠️ 6️⃣ Admin can update payment status
    PaymentDto updatePaymentStatus(int paymentId, String newStatus) throws PaymentNotFoundException;

    void deletePayment(int paymentId) throws PaymentNotFoundException;
    PaymentDto cancelPayment(int paymentId) throws PaymentNotFoundException;

}

