package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    // create new message 
    public Message createNewMessage(Message message){

    }

    // retrieve all messages 
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt) //FINISH THIS LINE
                messages.add(message);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage(e));
        }
        return messages;
    }

    // retrieve message by message ID 
    public Message getMessageByID(){

    }

    // Delete message by message ID
    public Message deleteMessageByID(){

    }

    // Update message text by message ID
    public Message updateMessageByID(){

    }

    // retrieve all messages by account ID
    public List<Message> getAllMessagesByAccID(){

    }
}
