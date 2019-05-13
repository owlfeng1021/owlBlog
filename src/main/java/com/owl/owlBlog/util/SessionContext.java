package com.owl.owlBlog.util;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionContext {
    private static SessionContext instance;
    private HashMap<String,HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String,HttpSession>();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

}