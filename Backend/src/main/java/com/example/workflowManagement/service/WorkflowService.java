//package com.example.workflowManagement.service;
//
//import com.example.workflowManagement.entity.Task;
//import com.example.workflowManagement.entity.Workflow;
//import com.example.workflowManagement.repository.TaskRepo;
//import com.example.workflowManagement.repository.WorkflowRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;

package com.example.workflowManagement.service;

import com.example.workflowManagement.entity.ExecutedWorkflow;
import com.example.workflowManagement.entity.Task;
import com.example.workflowManagement.entity.Workflow;
import com.example.workflowManagement.repository.ExecutedWorkflowRepo;
import com.example.workflowManagement.repository.WorkflowRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class WorkflowService {

    @Autowired
    private WorkflowRepo workflowRepo;

    @Autowired
    private ExecutedWorkflowRepo executedWorkflowRepo;

    // Transactional because there are two operation and anyone fails, all fails
    //save task and workflows
    @Transactional
    public Workflow saveWorkflow(Workflow workflow) {
        try {
            // Save tasks first
            for (Task task : workflow.getTasks()) {
                System.out.println("Saving Task: " + task);

            }

            // Save the workflow with references to the saved tasks
            return workflowRepo.save(workflow);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Workflow();
    }

    // return executed workflows by id
    public Optional<ExecutedWorkflow> findWorkflow(String workId){
        ObjectId id = new ObjectId(workId);
        return executedWorkflowRepo.findById(id);
    }

    //    return list of all workflows
    public List<Workflow> getAll(){
        return workflowRepo.findAll();
    }

    public Optional<Workflow> findByid(ObjectId id){
        return workflowRepo.findById(id);
    }

    // delete workflows by id
    public void deleteWorkflowById(ObjectId id){
        workflowRepo.deleteById(id);
    }
}

//Service --------> Repository
