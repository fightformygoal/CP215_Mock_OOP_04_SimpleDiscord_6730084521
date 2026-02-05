package logic;

import java.util.ArrayList;

public class Server {
    // fields
    String name ;
    User owner ;
    ArrayList<Channel> channelList ;
    ArrayList<User> memberList; // owner included in memberList

    // constructors
    public Server(String name , User owner , TemplateType templateType) {
        // Don't forget order of initialization
        this.owner = owner ; // set owner
        this.channelList = new ArrayList<>() ; // initialize memberList
        this.memberList = new ArrayList<>() ; // initialize channelList

        // Add owner to memberList , add server to owners' joined serverList (recommended using addUser method)
        memberList.add(owner) ;
        owner.addJoinedServersList(this); // add this server to owner's server

        // set name of the server
        setName(name);

        // add 1 channel to serverList depends on templateType
            // check templateType via switch case
        switch (templateType) { // use addChannel method instead
            case BASIC -> {
                addChannel(owner , "general") ;
            }
            case GAMING -> {
                addChannel(owner , "gaming") ;
            }
            case STUDY -> {
                addChannel(owner , "homework-help") ;
            }
        }
    }

    // getter - setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        // If the name is blank, set the Server’s name to <owner’s name> + “ home”. (For example, “Citron home”)
        // Otherwise, set it to the specified name
        if (name.isBlank() == true) this.name = owner.getName() + " home" ;
        else this.name = name ;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(ArrayList<Channel> channelList) {
        this.channelList = channelList;
    }

    public ArrayList<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }

    // methods
    public Channel addChannel(User user , String channelName) {
        if (user.equals(owner)) { // check by equals (same class)
            // create new channel then add to channelList then return channel
            Channel newChannel = new Channel(channelName) ; // call Channel class
            channelList.add(newChannel) ;
            return newChannel ;
        }
        else return null ;
    }
    public User addUser(User user) {
        // check if user in memeberList or not
        if (memberList.contains(user) == false) {
            // add user to memberList
            memberList.add(user) ;
            // Also, add this server to the user's joinedServersList then return the newly added user.
            user.addJoinedServersList( this ); // use addServer method then add "this" server
            return user ;
        }
        else return null ;
    }
    public boolean kickUser(User kicker , User kicked) throws Exception {
        // check if kicker is owner or not
            // case 1 : kicker isn't Server's owner
        if (!kicker.equals(owner)) { // check User class with User class
            throw new Exception() ;
        }
            // case 2 : kicker is owner and kicked is either (not in memberList) or (kicked is server owner)
        else if ( kicker.equals(owner) && ( (!memberList.contains(kicked)) || (kicked.equals(owner)) ) ) {
            return false ;
        }

            // case 3 : general case
        else {
            // Remove this Server from kicked’s joinedServersList.
            // Then, remove kicked from the Server’s memberList and return true
            kicked.getJoinedServersList().remove(this) ;
            memberList.remove(kicked) ;
            return true ;
        }
    }
    public boolean isMemberInServer(User userToCheck) {
        // check if member is in this server
        return memberList.contains(userToCheck) ;
    }
}

