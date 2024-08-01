package com.example.workflowManagement.controller;
import com.example.workflowManagement.entity.User;
import com.example.workflowManagement.entity.Workflow;
import com.example.workflowManagement.service.ExecuteWorkflow;
import com.example.workflowManagement.service.UserService;
import com.example.workflowManagement.service.WorkflowService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExecuteWorkflow executeWorkflow;
    @Autowired
    private WorkflowService workflowService;

    //  create and save user
    @PostMapping("/user")
    public String saveUser(@RequestBody User user){
        return userService.addUser(user).getId().toHexString();
    }

    // return the executed workflows
    @GetMapping("/user/{userId}/workflow/{workflowId}")
    public ResponseEntity<?> getWorkflow(@PathVariable ObjectId workflowId, @PathVariable ObjectId userId){
        User user = userService.findUser(userId);
        String id = executeWorkflow.execute(workflowId,user);
        return new ResponseEntity<>(workflowService.findWorkflow(id), HttpStatus.OK);
    }
}


// controller ------> Service --------> Repository
