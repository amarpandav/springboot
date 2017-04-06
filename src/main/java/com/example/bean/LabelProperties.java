package com.example.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by amarpandav on 06/04/17.
 */
@Component
@ConfigurationProperties("lbl")
public class LabelProperties {

    private String myNameIs;

    public String getMyNameIs() {
        return myNameIs;
    }

    public void setMyNameIs(String myNameIs) {
        this.myNameIs = myNameIs;
    }
}
