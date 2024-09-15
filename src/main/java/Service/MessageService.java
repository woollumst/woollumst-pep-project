package Service;

import Model.Message;
import DAO.*;

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
    
    public Message createNewMessage(Message message){ // <255char, not blank, posted by real acc
        if ((message.getMessage_text().length() < 255) && (message.getMessage_text().length() > 0)) { //0<length<255
            //check real account
            // call acc validate method to check for real account
            AccountService accountService = new AccountService();
            if (accountService.validateAccNum(message.getPosted_by())){
                return messageDAO.createNewMessage(message); //run msg creation (success)
            }
        }
        return null; //msg creation failure
    }

    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }

    public void deleteMessageByID(int message_id){
        messageDAO.deleteMessageByID(message_id);
        return;
    }

    public Message updateMessageByID(int messID, String newMessageText){
        if (newMessageText.length() < 255 && newMessageText.length() > 0){
            if (getMessageByID(messID) != null){
                Message tempMessage = messageDAO.getMessageByID(messID);
                tempMessage.setMessage_text(newMessageText);
                return messageDAO.updateMessageByID(tempMessage);
            }
        }
        return null;
    }

    public List<Message> getAllMessagesByAccID(int accID){
        return messageDAO.getAllMessagesByAccID(accID);
    }
}
