package logic;
import java.util.ArrayList ;

public class Channel {
    // fields
    private String name ;
    private ArrayList<Message> messageList ;

    // constructors
    public Channel(String name) {
        // initialize ArrayList & define name
        this.messageList = new ArrayList<Message>() ;
        setName(name) ;
    }

    // getter - setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) this.name = "off-topic" ;
        else this.name = name;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    // another methods
    public int getMessageCount() {
        return messageList.size() ;
    }

    public void addMessage(Message message) {
        messageList.add(message) ;
    }
}
