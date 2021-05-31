package com.handeler.service;

import com.jab.JabberMessage;

public interface JabberService {
    JabberMessage populateSignInProcess(String userName);

    JabberMessage populateFollowProcess(String userName);

    JabberMessage populateRegisterProcess(String userName);

    JabberMessage populateTimelineProcess(String userName);

    JabberMessage populatePostingJabProcess(String userName, String jabText);

    JabberMessage populateLikeProcess(int userId, int jabId);

    JabberMessage populateSignOutProcess();

    JabberMessage inputProcessText(String messageData);
}
