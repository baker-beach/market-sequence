package com.bakerbeach.market.sequence.service;

import org.hibernate.JDBCException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bakerbeach.market.sequence.model.Sequence;

public class SequneceMySqlDao implements SequenceDao{
	
	private static final Long DEFAULT_INITIAL_VALUE = 0L;

	private SessionFactory sessionFactory;

	private PlatformTransactionManager transactionManager;

	public Long generateId(String key) throws JDBCException {
		Session session = null;
		TransactionStatus txs = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			session = SessionFactoryUtils.getSession(sessionFactory, true);
			Sequence seq = (Sequence) session.get(Sequence.class, key, LockOptions.UPGRADE);
			if (seq != null) {
				seq.value = ++seq.value;
			} else {
				seq = new Sequence(key, DEFAULT_INITIAL_VALUE);
			}
			session.save(seq);
			transactionManager.commit(txs);
			return seq.value;
		} catch (JDBCException e) {
			transactionManager.rollback(txs);
			throw e;
		} finally {
			if (session.isOpen()) {
				SessionFactoryUtils.releaseSession(session, sessionFactory);				
			}
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


}
