package com.bakerbeach.market.sequence.service;

public interface SequenceDao {
	
	Long generateId(String key) throws SequenceDaoException;

}
