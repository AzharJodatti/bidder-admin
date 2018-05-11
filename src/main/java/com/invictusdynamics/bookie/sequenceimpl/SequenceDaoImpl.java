package com.invictusdynamics.bookie.sequenceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.invictusdynamics.bookie.entity.TransactionsDetails;
import com.invictusdynamics.bookie.exception.SequenceException;
import com.invictusdynamics.bookie.sequence.BookieSequence;
import com.invictusdynamics.bookie.sequence.CoinSequence;
import com.invictusdynamics.bookie.sequence.LuckyNumberSequence;
import com.invictusdynamics.bookie.sequence.RechargeSequence;
import com.invictusdynamics.bookie.sequence.SequenceCounter;
import com.invictusdynamics.bookie.sequence.TimeSequence;
import com.invictusdynamics.bookie.sequence.TransactionSequence;
import com.invictusdynamics.bookie.service.SequenceDao;

public class SequenceDaoImpl implements SequenceDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long getNextSequenceId(String key, Class className) {
		//Query query = new Query(Criteria.where("_id").is(key));
		Query query = new Query(Criteria.where("id").is(key));
		Integer sequenceNumber = null;

		/* Update object increase sequence id by 1 */
		Update update = new Update();

		/* return new increased id */
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		if (className != null && className.getName().equals(CoinSequence.class.getCanonicalName())) {
			update.inc("coinSeq", 1);
			CoinSequence coinSequence = (CoinSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = coinSequence.getCoinSeq();
		} else if (className != null && className.getName().equals(SequenceCounter.class.getCanonicalName())) {
			update.inc("seq", 1);
			SequenceCounter sequenceCounter = (SequenceCounter) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = sequenceCounter.getSeq();
		} else if (className != null && className.getName().equals(LuckyNumberSequence.class.getCanonicalName())) {
			update.inc("luckyNumSeq", 1);
			LuckyNumberSequence luckyNumberSequence = (LuckyNumberSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = luckyNumberSequence.getLuckyNumSeq();
		} else if (className != null && className.getName().equals(BookieSequence.class.getCanonicalName())) {
			update.inc("bookieSequence", 1);
			BookieSequence bookieSequence = (BookieSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = bookieSequence.getBookieSequence();
		} else if (className != null && className.getName().equals(TimeSequence.class.getCanonicalName())) {
			update.inc("timeSeq", 1);
			TimeSequence timeSequence= (TimeSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = timeSequence.getTimeSeq();
		} else if (className != null && className.getName().equals(RechargeSequence.class.getCanonicalName())) {
			update.inc("rechargeSeq", 1);
			RechargeSequence rechargeSequence= (RechargeSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = rechargeSequence.getRechargeSeq();
		} else if (className != null && className.getName().equals(TransactionSequence.class.getCanonicalName())) {
			update.inc("transactSeq", 1);
			TransactionSequence transactionSequence= (TransactionSequence) mongoTemplate.findAndModify(query, update, options, className);
			sequenceNumber = transactionSequence.getTransactSeq();
		} 

		/* if no id, throws SequenceException optional, just a way to tell user when the sequence id is failed to generate. */
		if (sequenceNumber == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		}
		return sequenceNumber;
	}

}
