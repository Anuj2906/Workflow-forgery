package com.example.workflowManagement.repository;

import com.example.workflowManagement.entity.ExecutedWorkflow;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExecutedWorkflowRepo extends MongoRepository<ExecutedWorkflow, ObjectId> {
}
