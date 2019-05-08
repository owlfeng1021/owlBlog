package com.owl.owlBlog.config;

import com.owl.owlBlog.pojo.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "default")
public class DefaultData {
    private User user;

    public DefaultData(User user) {
        this.user = user;
    }

    public DefaultData() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
