package br.com.sapecasmt.views;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Model
public class ViewActionBean {
    
    @Inject Logger log;
    
    private String flag="page1";
    
    public String init(){
        log.info("call init");
        switch(flag){
            case "page1":
                return "page1";
            default:
                return "page2";  
        } 
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
}