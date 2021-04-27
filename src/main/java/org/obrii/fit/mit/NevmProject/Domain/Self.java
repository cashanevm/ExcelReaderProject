/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.fit.mit.NevmProject.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author 38068
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Self {
    
    private String href;

    public Self() {
    }

    
    @Override
    public String toString() {
        return "Self{" + "href=" + href + '}';
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    
    
    
}
