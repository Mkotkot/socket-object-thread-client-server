package com.handeler.service;

import com.constants.Commands;
import com.constants.Response;
import com.jab.JabberDatabase;
import com.jab.JabberMessage;

import java.util.ArrayList;
import java.util.Date;

public class JabberServiceImpl implements JabberService {


    @Override
    public JabberMessage populateSignInProcess(String userName) {
        int result = new JabberDatabase().getUserID(userName);
        if (result == -1) {
            return new JabberMessage("");
        }
        return new JabberMessage(Response.SING_IN_OK);
    }

    @Override
    public JabberMessage populateSignOutProcess() {

        return new JabberMessage(Response.SIGNOUT_OK);
    }

    @Override
    public JabberMessage populateRegisterProcess(String userName) {
        //todo what about the Email is it send on the request from client !
        new JabberDatabase().addUser(userName, new Date().getTime() + "demo.com");
        return new JabberMessage(Response.REGISTER_OK);
    }

    @Override
    public JabberMessage populateTimelineProcess(String userName) {

        ArrayList<ArrayList<String>> response = new ArrayList();
        ArrayList usersTimeLines;
        usersTimeLines = new JabberDatabase().getTimelineOfUserEx(userName);
        response.add(usersTimeLines);
        return new JabberMessage(Response.TIMELINE, response);
    }

    @Override
    public JabberMessage populatePostingJabProcess(String userName, String jabText) {
        new JabberDatabase().addJab(userName, jabText);
        return new JabberMessage(Response.POSTING);
    }

    @Override
    public JabberMessage populateLikeProcess(int userId, int jabId) {
        new JabberDatabase().addLike(userId, jabId);
        return new JabberMessage(Response.LIKE);
    }

    @Override
    public JabberMessage populateFollowProcess(String username) {
        // todo get followers form DB
        ArrayList<ArrayList<String>> response = new ArrayList();
        ArrayList usersFollowers;
        int userID = new JabberDatabase().getUserID(username);
        usersFollowers = new JabberDatabase().getFollowerUserIDs(userID);
        response.add(usersFollowers);
        return new JabberMessage(Response.FOLLOW, response);
    }

    @Override
    public JabberMessage inputProcessText(String messageData) {
        //todo some process on the messageData then call the function on it such as signIn
        String messageDataArray[] = messageData.split(" ");
        if (messageDataArray.length >= 1) {
            switch (messageDataArray[0]) {
                case Commands.SING_IN:
                    if (messageDataArray.length >= 2)
                        return populateSignInProcess(messageDataArray[1]);
                case Commands.SIGN_OUT:
                    return populateSignOutProcess();
                case Commands.REGISTER:
                    if (messageDataArray.length >= 2)
                        return populateRegisterProcess(messageDataArray[1]);
                case Commands.FOLLOW:
                    if (messageDataArray.length >= 2)
                        return populateFollowProcess(messageDataArray[1]);

                case Commands.TIMELINE:
                    if (messageDataArray.length >= 2)
                        return populateTimelineProcess(messageDataArray[1]);
                case Commands.POSTING:
                    if (messageDataArray.length >= 3)
                        return populatePostingJabProcess(messageDataArray[1], messageDataArray[2]);
                case Commands.LIKE:
                    if (messageDataArray.length >= 3)
                        return populateLikeProcess(Integer.parseInt(messageDataArray[1]), Integer.parseInt(messageDataArray[2]));
                default:
                    return new JabberMessage("-Not Right Command");

            }
        }
        return null;
    }
}
