package cn.edu.jxnu.base.config.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Copyright©2018 梦境迷离.All rights reserved.
 * 
 * @description:从数据源配置
 * @Package: cn.edu.jxnu.base.config
 * @author: 梦境迷离
 * @date:2018年3月26日11:04:24
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryCluster", transactionManagerRef = "transactionManagerCluster", basePackages = {
		"cn.edu.jxnu.base.dao" })
public class RepositoryClusterConfig {
	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	@Qualifier("cluster")
	private DataSource cluster;

	@Bean(name = "entityManagerCluster")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactorySecondary(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactoryCluster")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(cluster).properties(getVendorProperties(cluster)).packages("cn.edu.jxnu.base.entity")
				.persistenceUnit("clusterPersistenceUnit").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Bean(name = "transactionManagerCluster")
	PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
	}

}