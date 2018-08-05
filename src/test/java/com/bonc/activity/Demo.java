package com.bonc.activity;

import org.activiti.engine.*;
import org.junit.Test;

public class Demo {
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
}
