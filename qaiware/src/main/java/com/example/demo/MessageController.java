package com.example.demo;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
	private MessageService messageService;

	@Autowired
	public MessageController(MessageService theMessageService) {
		messageService = theMessageService;
	}
	
	@PostMapping("/send_text")
	public ResponseEntity<MessageEmpty> saveText(@RequestBody Message payload) {
		if(payload.getPayload().length() >= 1 && payload.getPayload().length() <= 160) {
			payload.setType("text");
			payload.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			messageService.save(payload);			
			
			return new ResponseEntity<>(new MessageEmpty(), HttpStatus.CREATED);
		} else {
			throw new MessageErrorException();
		}
	}
	
	@PostMapping("/send_emotion")
	public ResponseEntity<MessageEmpty> saveEmotion(@RequestBody Message payload) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(payload.getPayload());
		boolean hasSpecialChars = matcher.find();

		if(payload.getPayload().length()>=2 && payload.getPayload().length()<10 && !hasSpecialChars) {
			payload.setType("emotion");
			payload.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			messageService.save(payload);
			
			return new ResponseEntity<>(new MessageEmpty(), HttpStatus.CREATED);
		} else {
			throw new MessageErrorException();
		}
	}
}
