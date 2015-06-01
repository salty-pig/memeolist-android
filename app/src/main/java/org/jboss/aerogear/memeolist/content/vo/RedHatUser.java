/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.aerogear.memeolist.content.vo;

import org.jboss.aerogear.android.core.RecordId;

import java.io.Serializable;

/**
 *
 *  summers
 */

public class RedHatUser implements Serializable{
    @RecordId
    private Long id;

    private String username;
    
    private String displayName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    
}
