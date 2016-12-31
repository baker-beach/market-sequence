package com.bakerbeach.market.sequence.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/sequence_service.xml", "classpath:spring/resources.xml" })
public class SequenceServiceTest {

	@Autowired
	private SequenceService sequenceService;

	@Test
	public void testGenerateId() {
		try {
			Long id1 = sequenceService.generateId("junit_test");
			Long id2 = sequenceService.generateId("junit_test");
			Assert.isTrue(id1 < id2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
