package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepo;
	
	@Autowired
	public MessageServiceImpl(MessageRepository theMessageRepo) {
		messageRepo = theMessageRepo;
	}

	@Override
	@Transactional
	public void save(Message theMessage) {
		messageRepo.save(theMessage);
	}

}
