package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {
	@MockBean
    MessageService messageService;
 
    @Autowired
    MockMvc mockMvc;
 
    @Test
    public void testsaveText() throws Exception {
        
        mockMvc.perform(post("/messages/send_text")
        		.content("{\"payload\": \"test text\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void testSaveEmotion() throws Exception {
    	mockMvc.perform(post("/messages/send_emotion")
    			.content("{\"payload\":\"test text\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isCreated());
    }
    
    @Test
    public void testSaveTextError() throws Exception {
    	mockMvc.perform(post("/messages/send_text")
    			.content("{\"payload\": \"\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isPreconditionFailed());
    }
    
    @Test
    public void testSaveEmotionError() throws Exception {
    	mockMvc.perform(post("/messages/send_emotion")
    			.content("{\"payload\":\"\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isPreconditionFailed());
    }
    
    @Test
    public void testSaveEmotionBadData() throws Exception {
    	mockMvc.perform(post("/messages/send_emotion")
    			.content("{\"payload\":\"test 1\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isPreconditionFailed());
    }
    
    @Test
    public void testSaveEmotionLargerText() throws Exception {
    	mockMvc.perform(post("/messages/send_emotion")
    			.content("{\"payload\":\"test text: 1\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isPreconditionFailed());
    }
    
    @Test
    public void testSaveTextLargerText() throws Exception {
    	mockMvc.perform(post("/messages/send_emotion")
    			.content("{\"payload\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit,"
				    					+ " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
				    					+ " Ut enim ad minim veniam, quis nostrud exercitation ullamco "
				    					+ "laboris nisi ut aliquip ex ea commodo consequat. Duis aute"
				    					+ " irure dolor in reprehenderit in voluptate velit esse cillum dolore\"}")
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(status().isPreconditionFailed());
    }
}
