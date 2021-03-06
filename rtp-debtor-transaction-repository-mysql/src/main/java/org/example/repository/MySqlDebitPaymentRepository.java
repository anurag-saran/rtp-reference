package org.example.repository;

import java.math.BigInteger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.repository.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import rtp.demo.debtor.domain.model.payment.DebitPayment;

public class MySqlDebitPaymentRepository implements DebitPaymentRepository {

	private static final Logger log = LogManager.getLogger(MySqlDebitPaymentRepository.class);

	protected Session session = HibernateUtil.getHibernateSession();

	@Override
	public void addPayment(DebitPayment payment) {
		// In a real application the full object would not be logged at info level
		log.info("Saving payment: {}", payment);

		Transaction transaction = session.beginTransaction();
		session.save(payment);

		transaction.commit();
	}

	@Override
	public List<DebitPayment> getAllPayments() {
		log.info("Retrieving all payments");
		Transaction transaction = session.beginTransaction();

		Criteria cr = session.createCriteria(DebitPayment.class);
		List<DebitPayment> results = cr.list();

		transaction.commit();
		return results;
	}

	@Override
	public DebitPayment getPayment(BigInteger id) {
		log.info("Retrieving payment with id: {}", id);
		Transaction transaction = session.beginTransaction();

		DebitPayment customer = (DebitPayment) session.get(DebitPayment.class, id);

		transaction.commit();
		return customer;
	}

	@Override
	public void updatePayment(DebitPayment payment) {
		// In a real application the full object would not be logged at info level
		log.info("Updating payment: {}", payment);
		Transaction transaction = session.beginTransaction();

		session.update(payment);

		transaction.commit();
	}

	@Override
	public void deletePayment(DebitPayment payment) {
		// In a real application the full object would not be logged at info level
		log.info("Deleting payment: {}", payment);
		Transaction transaction = session.beginTransaction();

		session.delete(payment);

		transaction.commit();
	}

	public Session getSession() {
		return session;
	}

	@Override
	public List<DebitPayment> getPayments(String accountNumber) {
		log.info("Retrieving all payments by account number");
		Transaction transaction = session.beginTransaction();

		Criteria cr = session.createCriteria(DebitPayment.class);
		cr.add(Restrictions.eq("senderAccountNumber", accountNumber));
		List<DebitPayment> results = cr.list();

		transaction.commit();
		return results;
	}

}
