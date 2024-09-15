package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.azul.crs.client.Result;

public class MessageDAO {
    // create new message 
    public Message createNewMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        Message createdMessage = null;
        try{
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set methods
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.execute();

            String sql2 = "SELECT * FROM Message;";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();
            rs.last();
            createdMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            return createdMessage;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return createdMessage;
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
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch")); //FINISH THIS LINE
                messages.add(message);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    // retrieve message by message ID 
    public Message getMessageByID(int messID){
        Connection connection = ConnectionUtil.getConnection();
        Message newMess = null;
        try{
            String sql = "SELECT * From Message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, messID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            newMess = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return newMess;
    }

    // Delete message by message ID
    public void deleteMessageByID(int messID){
        Connection connection = ConnectionUtil.getConnection();
        try{
            /*String sql1 = "SELECT * FROM Message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, messID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            */
            String sql = "DELETE * FROM Message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messID);
            preparedStatement.execute();
                        
            return;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return;
    }

    // Update message text by message ID
    public Message updateMessageByID(Message message){
        Connection connection = ConnectionUtil.getConnection();
        Message newMessage = null;
        try{
            String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message.getMessage_id());
            preparedStatement.execute();

            sql = "SELECT * FROM Message WHERE message_id=?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,message.getMessage_id());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            newMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return newMessage;
    }

    // retrieve all messages by account ID
    public List<Message> getAllMessagesByAccID(int accID){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message WHERE posted_by = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
