package com.vishal.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vishal.domain.PaymentMethod;
import com.vishal.model.PaymentOrder;
import com.vishal.payload.dto.BookingDTO;
import com.vishal.payload.dto.UserDTO;
import com.vishal.payload.response.PaymentLinkResponse;

public interface paymentService {

    PaymentLinkResponse createOrder(
            UserDTO userDTO,
            BookingDTO bookingDTO,
            PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorPayPaymentLink(UserDTO userDTO, Long amount, Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws Exception;
}
