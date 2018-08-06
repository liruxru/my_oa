package com.bonc.activity;

import lombok.Data;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * activiti 测试
 * 官方文档 https://www.activiti.org/5.x/userguide/
 * 5.14 中文文档 https://wenku.baidu.com/view/540db0ff763231126fdb11bf
 */
@Data
public class Demo {
    protected static Logger logger = LoggerFactory.getLogger(Demo.class);
    /**
     * 使用默认配置文件 activiti.cfg.xml 获取核心api
     */
    @Test
    public void  test(){
        // will initialize and build a process engine the first time it is
        // called and afterwards always return the same process engine.
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

    /**
     * API测试
     */
    @Test
    public void testAPI(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService taskService = processEngine.getTaskService();
        ManagementService managementService = processEngine.getManagementService();
        IdentityService identityService = processEngine.getIdentityService();
        HistoryService historyService = processEngine.getHistoryService();
        FormService formService = processEngine.getFormService();
    }

    /**
     * 解析xml 存储到数据库 (模板任务定义)
     */
    @Test
    public  void testUpload(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("VacationRequest.bpmn20.xml")
                .deploy();
        logger.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
    }

    /**
     * Starting a process instance
     * 选择模板，开启一个流程实例
     */
    @Test
    public  void testStarting(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

        // Verify that we started a new process instance
        logger.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
    }

    /**
     * 查询属于自己的任务并完成
     */
    @Test
    public void complete(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // Fetch all tasks for the management group
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            logger.info("Task available: " + task.getName());
        }

        Task task = tasks.get(0);

        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);
    }

    /**
     * 完成之后再次查询（并没有任务）
     */
    @Test
    public void  completeAfte(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // Fetch all tasks for the management group
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            logger.info("Task available: " + task.getName());
        }
    }

    /**
     * 查询API
     */
    @Test
    public void testQueryAPI(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // Fetch all tasks for the management group
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("kermit")
                .processVariableValueEquals("orderId", "0815")
                .orderByDueDate().asc()
                .list();
        System.out.println(tasks);
    }
}
