package com.bakerbeach.market.sequence.service;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class SequneceMongoDao implements SequenceDao {

	private static final Long DEFAULT_INCREMENT_VALUE = 1L;

	private MongoTemplate mongoTemplate;

	private String collectionName;

	@Override
	public Long generateId(String key) throws SequenceDaoException {
		return generateId(key, DEFAULT_INCREMENT_VALUE);
	}

	@Override
	public Long generateId(String key, Long offset) throws SequenceDaoException {
		try {
			DBObject dbo = getDBCollection().findAndModify(new BasicDBObject("key", key), null, null, false,
					new BasicDBObject("$inc", new BasicDBObject("value", offset)), true, true);

			return (Long) dbo.get("value");
		} catch (Exception e) {
			throw new SequenceDaoException(e);
		}
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	private DBCollection getDBCollection() {
		return mongoTemplate.getCollection(collectionName);
	}

}
