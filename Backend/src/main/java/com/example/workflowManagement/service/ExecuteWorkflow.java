package com.example.workflowManagement.service;

import com.example.workflowManagement.entity.*;
import com.example.workflowManagement.repository.ExecutedWorkflowRepo;
import com.example.workflowManagement.repository.WorkflowRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// workflow execution
@Component
public class ExecuteWorkflow {

    @Autowired
    private ExecutedWorkflowRepo executedWorkflowRepo;
    @Autowired
    private WorkflowRepo workflowRepo;


    // execute function to execute task one by one
    public String execute(ObjectId workflowId, User user) {
        ExecutedWorkflow executedWorkflow = new ExecutedWorkflow();
        Optional<Workflow> workflow = workflowRepo.findById(workflowId);
        List<TaskNew> update_task_list = new ArrayList<>();
        if(workflow.isPresent()){
            Workflow flow = workflow.get();
            List<Task> tasklist= flow.getTasks();
            int i=0;
            for (i=0;i<tasklist.size()-1;i++) {
                TaskNew new_task = new TaskNew();
                if(i>0 && tasklist.get(i-1).getStatus().equalsIgnoreCase("failure")){
                    new_task.setAction(tasklist.get(i).getAction());
                    new_task.setStatus(tasklist.get(i).getStatus());
                    new_task.setApi_check(new_task.getApi_check());
                    List<String> cond = new ArrayList<>();
                    if(tasklist.get(i).getCondition().equalsIgnoreCase("male")){
                        cond.add("male");
                        cond.add("female");
                    }
                    else if(tasklist.get(i).getCondition().equalsIgnoreCase("female")){
                        cond.add("female");
                        cond.add("male");
                    }
                    else if(tasklist.get(i).getCondition().startsWith("age >")){
                        String[] condition = tasklist.get(i).getCondition().split("\\s+");
                        cond.add(tasklist.get(i).getCondition());
                        condition[1]="<";
                        cond.add(String.join(" ", condition));
                    }
                    else if(tasklist.get(i).getCondition().startsWith("age <")){
                        String[] condition = tasklist.get(i).getCondition().split("\\s+");
                        cond.add(tasklist.get(i).getCondition());
                        condition[1]=">";
                        cond.add(String.join(" ", condition));
                    }
                    else{
                        cond.add("starts with" +" " + tasklist.get(i).getCondition());
                        cond.add("not starts with" + " " + tasklist.get(i).getCondition());
                    }
                    new_task.setCondition(cond);
                    update_task_list.add(new_task);
                }
                else{
                    if ("DOB check".equals(tasklist.get(i).getApi_check())) {
                        Task update_task= performDobCheck(tasklist.get(i), user, tasklist.get(i+1).getApi_check());
                        new_task.setAction(update_task.getAction());
                        new_task.setStatus(update_task.getStatus());
                        new_task.setApi_check(update_task.getApi_check());
                        String[] condition = tasklist.get(i).getCondition().split("\\s+");
                        List<String> cond = new ArrayList<>();
                        if(condition[1].equals("<")){
                            cond.add(tasklist.get(i).getCondition());
                            condition[1]=">";
                            cond.add(String.join(" ", condition));
                        }
                        else{
                            cond.add(tasklist.get(i).getCondition());
                            condition[1]="<";
                            cond.add(String.join(" ", condition));
                        }
                        new_task.setCondition(cond);

                        if(update_task.getAction().equalsIgnoreCase("Loan_approved") || update_task.getAction().equalsIgnoreCase("Loan_approval_required"))
                            break;
                        update_task_list.add(new_task);
                    }
                    else if ("Gender check".equals(tasklist.get(i).getApi_check())) {
                        Task update_task=performGenderCheck(tasklist.get(i),user,tasklist.get(i+1).getApi_check());
                        new_task.setAction(update_task.getAction());
                        new_task.setStatus(update_task.getStatus());
                        new_task.setApi_check(update_task.getApi_check());
                        List<String> cond = new ArrayList<>();
                        if(update_task.getCondition().equalsIgnoreCase("male")){
                            cond.add("male");
                            cond.add("female");
                        }
                        else{
                            cond.add("female");
                            cond.add("male");
                        }
                        new_task.setCondition(cond);
                        if(update_task.getAction().equalsIgnoreCase("Loan_approved") || update_task.getAction().equalsIgnoreCase("Loan_approval_required"))
                            break;
                        update_task_list.add(new_task);
                    }
                    else if ("Pincode check".equals(tasklist.get(i).getApi_check())) {
                        Task update_task = performPincodeCheck(tasklist.get(i), user,tasklist.get(i+1).getApi_check());
                        new_task.setAction(update_task.getAction());
                        new_task.setStatus(update_task.getStatus());
                        new_task.setApi_check(update_task.getApi_check());
                        List<String> cond = new ArrayList<>();
                        cond.add("starts with" +" " + update_task.getCondition());
                        cond.add("not starts with" + " " + update_task.getCondition());
                        new_task.setCondition(cond);
                        if (update_task.getAction().equalsIgnoreCase("Loan_approved") || update_task.getAction().equalsIgnoreCase("Loan_approval_required"))
                            break;
                        update_task_list.add(new_task);
                    }
                }
            }
            TaskNew new_task = new TaskNew();
            if(i>0 && tasklist.get(i-1).getStatus().equalsIgnoreCase("failure")){
                new_task.setAction(tasklist.get(i).getAction());
                new_task.setStatus(tasklist.get(i).getStatus());
                new_task.setApi_check(tasklist.get(i).getApi_check());
                List<String> cond = new ArrayList<>();
                if(tasklist.get(i).getCondition().equalsIgnoreCase("male")){
                    cond.add("male");
                    cond.add("female");
                }
                else if(tasklist.get(i).getCondition().equalsIgnoreCase("female")){
                    cond.add("female");
                    cond.add("male");
                }
                else if(tasklist.get(i).getCondition().startsWith("age >")){
                    String[] condition = tasklist.get(i).getCondition().split("\\s+");
                    cond.add(tasklist.get(i).getCondition());
                    condition[1]="<";
                    cond.add(String.join(" ", condition));
                }
                else if(tasklist.get(i).getCondition().startsWith("age <")){
                    String[] condition = tasklist.get(i).getCondition().split("\\s+");
                    cond.add(tasklist.get(i).getCondition());
                    condition[1]=">";
                    cond.add(String.join(" ", condition));
                }
                else{
                    cond.add("starts with" +" " + tasklist.get(i).getCondition());
                    cond.add("not starts with" + " " + tasklist.get(i).getCondition());
                }
                new_task.setCondition(cond);
                update_task_list.add(new_task);
            }
            else{
                if ("DOB check".equals(tasklist.get(tasklist.size()-1).getApi_check())) {
                    Task update_task= performDobCheck(tasklist.get(tasklist.size()-1), user, null);
                    new_task.setAction(update_task.getAction());
                    new_task.setStatus(update_task.getStatus());
                    new_task.setApi_check(update_task.getApi_check());
                    String[] condition = tasklist.get(tasklist.size()-1).getCondition().split("\\s+");
                    List<String> cond = new ArrayList<>();
                    if(condition[1].equals("<")){
                        cond.add(tasklist.get(tasklist.size()-1).getCondition());
                        condition[1]=">";
                        cond.add(String.join(" ", condition));
                    }
                    else{
                        cond.add(tasklist.get(tasklist.size()-1).getCondition());
                        condition[1]="<";
                        cond.add(String.join(" ", condition));
                    }
                    new_task.setCondition(cond);
                    update_task_list.add(new_task);
                }
                else if ("Gender check".equals(tasklist.get(tasklist.size()-1).getApi_check())) {
                    Task update_task=performGenderCheck(tasklist.get(tasklist.size()-1),user,null);
                    new_task.setAction(update_task.getAction());
                    new_task.setStatus(update_task.getStatus());
                    new_task.setApi_check(update_task.getApi_check());
                    List<String> cond = new ArrayList<>();
                    if(update_task.getCondition().equalsIgnoreCase("male")){
                        cond.add("male");
                        cond.add("female");
                    }
                    else{
                        cond.add("female");
                        cond.add("male");
                    }
                    new_task.setCondition(cond);
                    update_task_list.add(new_task);
                }
                else if ("Pincode check".equals(tasklist.get(tasklist.size()-1).getApi_check())) {
                    Task update_task = performPincodeCheck(tasklist.get(tasklist.size()-1), user,null);
                    new_task.setAction(update_task.getAction());
                    new_task.setStatus(update_task.getStatus());
                    new_task.setApi_check(update_task.getApi_check());
                    List<String> cond = new ArrayList<>();
                    cond.add("starts with" + update_task.getCondition());
                    cond.add("not starts with" + update_task.getCondition());
                    new_task.setCondition(cond);
                    update_task_list.add(new_task);
                }
            }


            executedWorkflow.setWorkflowId(workflowId);
            executedWorkflow.setName(flow.getName());
            executedWorkflow.setTask(update_task_list);
            executedWorkflow.setUser(user);
            //saving data
            return executedWorkflowRepo.save(executedWorkflow).getId().toHexString();
        }
        else{
            System.out.println("No task found");
        }
        return null;
    }

    // DOB check task
    private Task performDobCheck(Task task, User user, String name) {
        try{
            LocalDate current = LocalDate.now();
            int age = Period.between(user.getDob(), current).getYears();
            String[] condition = task.getCondition().split("\\s+");
            int conditionAge = Integer.parseInt(condition[2]);
            if(condition[1].equals(">")){
                if(age>conditionAge){
                    if(name!=null){
                        task.setAction(name);
                    }
                    else{
                        task.setAction("Loan_approval_required");
                    }
                    task.setStatus("success");
                }
                else {
                    task.setStatus("failure");
                    task.setAction("Loan_approved");
                }
            }
            else{
                if(age<conditionAge){
                    if(name!=null){
                        task.setAction(name);
                    }
                    else{
                        task.setAction("Loan_approval_required");
                    }
                    task.setStatus("success");
                }
                else {
                    task.setStatus("failure");
                    task.setAction("Loan_approved");
                }
            }
            return task;
        }catch (Exception e) {

            System.out.println("Internal server error");
        }
        return null;
    }

    //    Gender check task
    private Task performGenderCheck(Task task, User user, String name) {
        try{

            if ((user.getGender().equalsIgnoreCase("Male") && user.getGender().equalsIgnoreCase(task.getCondition())) ||
                    (user.getGender().equalsIgnoreCase("Female") && user.getGender().equalsIgnoreCase(task.getCondition()))
            ) {
                if(name!=null){
                    task.setAction(name);
                }
                else{
                    task.setAction("Loan_approval_required");
                }
                task.setStatus("success");
            } else{
                task.setAction("Loan_status_approved");
                task.setStatus("failure");
            }
//            taskRepo.save(task);
            return task;
        }catch(Exception e){
            System.out.println("Internal server error");
        }
        return null;
    }

    //    Pincode check task
    private Task performPincodeCheck(Task task, User user,String name) {
        try{

            if (user.getPincode().startsWith(task.getCondition())) {
                if(name!=null){
                    task.setAction(name);
                }
                else{
                    task.setAction("Loan_approval_required");
                }
                task.setStatus("success");
            } else {

                task.setAction("loan_approved");
                task.setStatus("success");
            }
            return task;
        }catch(Exception e){
            System.out.println("Internal server error");
        }
        return null;
    }
}

//Service --------> Repository