package com.example.workflowManagement.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "executedWorkflow")
public class ExecutedWorkflow {
    @Id
    private ObjectId id;
    private ObjectId workflowId;
    private String name;
    private List<TaskNew> task;
    private User user;

    public ObjectId getWorkflowId() {
        return workflowId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setWorkflowId(ObjectId workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskNew> getTask() {
        return task;
    }

    public void setTask(List<TaskNew> task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

