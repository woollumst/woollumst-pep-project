package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import Service.*;
import Model.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        ObjectMapper om = new ObjectMapper();
        AccountService accountService = new AccountService();
        MessageService messageService = new MessageService();

        app.get("/", ctx -> ctx.result("Hello World"));
        
        app.post("/register", ctx -> { //register account
            Account tempAcc = ctx.bodyAsClass(Account.class);
            try{
                Account newAcc = accountService.registerAccount(tempAcc);
                ctx.json(newAcc);
                ctx.status(200);
            } catch (Exception e){
                ctx.status(400);
            }
        });

        app.post("/login", ctx -> { //login to account
            Account tempAcc = om.readValue(ctx.body(), Account.class);
            try{
                Account newAcc = accountService.accountLogin(tempAcc);
                ctx.json(newAcc);
                ctx.status(200);
            } catch(Exception e){
                ctx.status(401);
            }
        });

        app.post("/messages", ctx -> { //create a new message
            Message tempMessage = om.readValue(ctx.body(), Message.class);
            if (messageService.createNewMessage(tempMessage) == false){
                ctx.status(400);
            } else {
                ctx.json(tempMessage);
                ctx.status(200);
            }
        });

        app.get("/messages", ctx -> { //get all messages
            ctx.json(messageService.getAllMessages());
            ctx.status(200);
        });

        app.get("/messages/{message_id}", ctx -> { //get message by ID
            try{
                int newNum = Integer.parseInt(ctx.pathParam("message_id"));
                ctx.status(200);
                ctx.json(messageService.getMessageByID(newNum));
            } catch(Exception e) {
                ctx.status(200);
            }
        });

        app.delete("/messages/{message_id}", ctx -> { //delete message by ID

        });

        app.patch("/messages/{message_id}", ctx -> { //update message by message ID
            int temp = Integer.parseInt(ctx.pathParam("message_id"));
            String newText = ctx.body();
            Message newMess= messageService.updateMessageByID(temp, newText);
            ctx.json(newMess);
            ctx.status(200);
        });

        app.get("/accounts/{account_id}/messages", ctx -> { //get messages by particular user

        });

        app.get("example-endpoint", this::exampleHandler);

        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    
}