package com.example.workflowManagement.entity;


import java.util.List;

public class TaskNew {

    private String api_check;
    private List<String> condition;
    private String action;
    private String status;


    public String getApi_check() {
        return api_check;
    }

    public void setApi_check(String api_check) {
        this.api_check = api_check;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
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

