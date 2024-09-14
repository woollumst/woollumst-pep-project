package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService (){
        messageDAO = new MessageDAO();
    }

    public MessageService (MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    
    public boolean createNewMessage(Message message){ // <255char, not blank, posted by real acc
        if (message.getMessage_text().length() < 255 || message.getMessage_text().length() > 0) { //0<length<255
            //check real account
            //int tempAccID = message.getPosted_by(); //how to check real acc? link to accDAO?
            
            return messageDAO.createNewMessage(message); //run msg creation (success)
        }
        return false;
    }

    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }

    public Message deleteMessageByID(int message_id){
        return messageDAO.deleteMessageByID(message_id);
    }

    public Message updateMessageByID(int messID, String newMessageText){
        Message tempMessage = messageDAO.getMessageByID(messID);
        tempMessage.setMessage_text(newMessageText);
        return messageDAO.updateMessageByID(tempMessage);
    }

    public List<Message> getAllMessagesByAccID(int accID){
        return messageDAO.getAllMessagesByAccID(accID);
    }
}
