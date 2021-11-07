package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
	@InjectMocks
    MessageServiceImpl messageService;
	
	@Mock
    MessageRepository messageDAO;
     
    
    @Test
    public void testSave() {
    	Message message = new Message("");
    	messageService.save(message);
    	verify(messageDAO, times(1)).save(message);
    }
}
