package org.zaizi;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

public class HolidayRequest {
    public static void main(String[] args) {

        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable:DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        
        
        RepositoryService respositoryservice = processEngine.getRepositoryService();
        
        Deployment deployment = respositoryservice.createDeployment()
        		.addClasspathResource("holiday-request.bpmn20.xml")
        		.deploy();
        
        ProcessDefinition processDefinition = respositoryservice.createProcessDefinitionQuery()
        		  .deploymentId(deployment.getId())
        		  .singleResult();
        
        System.out.println("Found process Definition: "+ processDefinition.getName());
        
        
    }
}
