package com.pathmasri.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.pathmasri.messenger.database.Database;
import com.pathmasri.messenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = Database.getMessages();
	
	public  MessageService(){
				
		Message m1 = new Message(1L, "Message1", "Author1");
		Message m2 = new Message(2L, "Message2", "Author2");
		messages.put(1L, m1);
		messages.put(2L, m2);
				
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
    public List<Message> getAllMessagesForYear(int year){
    	ArrayList<Message> messagesForYear = new ArrayList();   	
    	Calendar cal = Calendar.getInstance();
    	for(Message message:messages.values()){
    		cal.setTime(message.getCreated());
    		if(cal.get(Calendar.YEAR) == year){
    			messagesForYear.add(message);
    		}
    	}  	
    	return messagesForYear;
    }
    
    public List<Message> getAllMessagesPaginated(int start, int size){
    	ArrayList<Message> list = new ArrayList<Message>(messages.values());	
    	return list.subList(start, size);
    }
	
	public Message addMessage(Message message){
		message.setMessageId(messages.size()+1);
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		
		if(messages.containsKey(message.getMessageId())){
			messages.put(message.getMessageId(), message);
		} else {
			return null;
		}
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);		
	}

}
