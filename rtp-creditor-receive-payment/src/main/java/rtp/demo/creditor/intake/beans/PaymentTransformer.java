package rtp.demo.creditor.intake.beans;

import rtp.demo.creditor.domain.payments.Payment;
import rtp.demo.creditor.validation.PaymentValidationRequest;

public class PaymentTransformer {

	public Payment toPayment(PaymentValidationRequest paymentValidationRequest) {
		Payment payment = new Payment();

		payment.setCreationDateTime(paymentValidationRequest.getCreditTransferMessage().getCreationDateTime());
		payment.setCreditorAccountNumber(
				paymentValidationRequest.getCreditTransferMessage().getCreditorAccountNumber());
		payment.setCreditorId(paymentValidationRequest.getCreditTransferMessage().getCreditorId());
		payment.setCreditTransferMessageId(
				paymentValidationRequest.getCreditTransferMessage().getCreditTransferMessageId());
		payment.setDebtorAccountNumber(paymentValidationRequest.getCreditTransferMessage().getDebtorAccountNumber());
		payment.setDebtorId(paymentValidationRequest.getCreditTransferMessage().getDebtorId());
		payment.setEndToEndId(paymentValidationRequest.getCreditTransferMessage().getEndToEndId());
		payment.setNumberOfTransactions(paymentValidationRequest.getCreditTransferMessage().getNumberOfTransactions());
		payment.setPaymentAmount(paymentValidationRequest.getCreditTransferMessage().getPaymentAmount());
		payment.setPaymentCurrency(paymentValidationRequest.getCreditTransferMessage().getPaymentCurrency());
		payment.setPaymentInstructionId(paymentValidationRequest.getCreditTransferMessage().getPaymentInstructionId());
		payment.setSettlementMethod(paymentValidationRequest.getCreditTransferMessage().getSettlementMethod());

		if (paymentValidationRequest.getErrors().size() == 0) {
			payment.setIsValidated(true);
			payment.setStatus("ACCEPTED");
		}

		paymentValidationRequest.getErrors().forEach(error -> {
			payment.getErrors().add(error);
			payment.setStatus("REJECTED");
		});

		return payment;
	}

}
