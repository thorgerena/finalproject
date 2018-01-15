package com.fdmgroup.finalproject.controller;

import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

/**
 * Listener class for initializing and destroying context for the webapp 
 * @author Knight
 *
 */
public class CustomContextListener extends ContextLoaderListener {
	
	private static final String BEANS_CONFIG = "context.xml";
	
	private ApplicationContext context;
	
	/**
     * Default constructor. 
     */
    public CustomContextListener() {
    	super();
    }
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     * destroyer closes entity manager factory and application context at the end of the session
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        ((ConfigurableApplicationContext) context).close();
    }
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * initializer creates application context to be used throughout the session
     */
    public void contextInitialized(ServletContextEvent sce)  {
  
    	context = new ClassPathXmlApplicationContext(BEANS_CONFIG);

    	sce.getServletContext().setAttribute("context",context);

    }
	
}