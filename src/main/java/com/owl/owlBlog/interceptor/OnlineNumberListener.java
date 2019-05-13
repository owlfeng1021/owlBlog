package com.owl.owlBlog.interceptor;

import com.owl.owlBlog.util.SessionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineNumberListener implements HttpSessionListener {

    private SessionContext myc = SessionContext.getInstance();


    @Override
    public void sessionCreated(HttpSessionEvent e) {
        // 获取进入监听的用户的sessionid
        HttpSession session = e.getSession();
        myc.addSession(session);

        ServletContext application = e.getSession().getServletContext();

        Integer online_number = (Integer) application.getAttribute("online_number");

        if (null == online_number)
            online_number = 0;
        online_number++;
        application.setAttribute("online_number", online_number);
        System.out.println("新增一位在线用户");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        ServletContext application = e.getSession().getServletContext();

        Integer online_number = (Integer) application.getAttribute("online_number");

        if(null==online_number){
            online_number = 0;
        }
        else
            online_number--;
        application.setAttribute("online_number", online_number);
        System.out.println("一位用户离线");

        HttpSession session = e.getSession();
        myc.delSession(session);

    }
}
