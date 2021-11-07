package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
	@InjectMocks
    MessageServiceImpl messageService;
	
	@Mock
    MessageRepository messageDAO;
     
    
    @Test
    public void testSave() {
    	Message message = new Message();
    	messageService.save(message);
    	verify(messageDAO, times(1)).save(message);
    }
    
    @Test
    @Transactional
    public void testSaveMessageText() {
    	Message message = new Message("test");
    	message.setType("text");
    	
    	when(messageDAO.save(message))
    	.thenReturn(message);
    	
    	Message theMessage = messageDAO.save(message);
    	
    	assertThat(theMessage).hasFieldOrPropertyWithValue("payload","test");
    	assertThat(theMessage).hasFieldOrPropertyWithValue("type", "text");
    }
    
    @Test
    @Transactional
    public void testSaveMessageEmotion() {
    	Message message = new Message("test");
    	message.setType("emotion");
    	
    	when(messageDAO.save(message))
    	.thenReturn(message);
    	
    	Message theMessage = messageDAO.save(message);
    	
    	assertThat(theMessage).hasFieldOrPropertyWithValue("payload","test");
    	assertThat(theMessage).hasFieldOrPropertyWithValue("type", "emotion");
    }
    
    @Test
    @Transactional
    public void testSaveMessageError() {
    	Message message = new Message();
    	
    	when(messageDAO.save(message)).thenThrow(new IllegalArgumentException());
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		messageDAO.save(message);
    	});
    }
}
