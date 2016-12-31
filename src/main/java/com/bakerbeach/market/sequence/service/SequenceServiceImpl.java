package com.bakerbeach.market.sequence.service;

import com.bakerbeach.market.sequence.service.SequenceService;
import com.bakerbeach.market.sequence.service.SequenceServiceException;

public class SequenceServiceImpl implements SequenceService {
	
	private SequenceDao sequenceDao;

	@Override
	public Long generateId(String key) throws SequenceServiceException{
		try{
			return sequenceDao.generateId(key);
		}catch(SequenceDaoException e){
			throw new SequenceServiceException(e);
		}
	}

	/**
	 * @return the sequenceDao
	 */
	public SequenceDao getSequenceDao() {
		return sequenceDao;
	}

	/**
	 * @param sequenceDao the sequenceDao to set
	 */
	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}
