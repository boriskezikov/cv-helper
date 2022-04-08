package com.olim.cvhelper.backoffice.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class UserCredsConfig {


    @Value("${admin.first.user}")
    private String username1;
    @Value("${admin.first.login}")
    private String login1;
    @Value("${admin.first.pass}")
    private String password1;

    @Value("${admin.second.user}")
    private String username2;
    @Value("${admin.second.login}")
    private String login2;
    @Value("${admin.second.pass}")
    private String password2;

    @Value("${admin.third.user}")
    private String username3;
    @Value("${admin.third.login}")
    private String login3;
    @Value("${admin.third.pass}")
    private String password3;

}
