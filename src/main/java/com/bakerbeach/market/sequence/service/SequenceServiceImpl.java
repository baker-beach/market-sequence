package com.bakerbeach.market.sequence.service;

public class SequenceServiceImpl implements SequenceService {

	private SequenceDao sequenceDao;

	@Override
	public Long generateId(String key) throws SequenceServiceException {
		try {
			return sequenceDao.generateId(key);
		} catch (SequenceDaoException e) {
			throw new SequenceServiceException(e);
		}
	}
	
	@Override
	public Long generateId(String key, Long maxRandomOffset) throws SequenceServiceException {
		try {
			Long offset = null;			
			if (maxRandomOffset != null) {
				offset = new Double(Math.random() * maxRandomOffset).longValue();
			}
			return sequenceDao.generateId(key, offset+1);
		} catch (SequenceDaoException e) {
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
	 * @param sequenceDao
	 *            the sequenceDao to set
	 */
	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}
