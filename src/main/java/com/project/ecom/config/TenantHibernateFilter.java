package com.project.ecom.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TenantHibernateFilter {

    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.project.ecom.services.*.*(..))")
    public void activateFilter(){
        final String tenantId=TenantContext.getCurrentTenant();
        if(tenantId!=null){
            System.out.println("current tenant"+tenantId);
            final Session session=entityManager.unwrap(Session.class);
            session.enableFilter("tenantFilter").setParameter("tenantId", tenantId);

        }
    }
}
