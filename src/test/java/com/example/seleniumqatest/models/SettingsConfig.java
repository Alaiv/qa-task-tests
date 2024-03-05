package com.example.seleniumqatest.models;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:proj.properties")
public interface SettingsConfig extends Config{
    @Key("base_url")
    String baseUrl();
    @Key("email")
    String email();
    @Key("password")
    String password();
}
