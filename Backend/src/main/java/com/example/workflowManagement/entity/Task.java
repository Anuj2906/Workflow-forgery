package com.example.workflowManagement.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Task {


    private String api_check;
    private String condition;
    private String action;
    private String status;

    public Task() {
    }

    public Task(String api_check, String condition, String action, String status) {

        this.api_check = api_check;
        this.condition = condition;
        this.action = action;
        this.status = status;
    }


    public String getApi_check() {
        return api_check;
    }

    public void setApi_check(String api_check) {
        this.api_check = api_check;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

