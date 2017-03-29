package com.owner.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.core.ejb.UserDao;
import org.core.model.EntityTest;

@Named
@RequestScoped
public class Mbean {

	@EJB
	private UserDao userDao;

	private EntityTest entityTest = new EntityTest();

	private String helloWorld = "hello world !!";

	public String getHelloWorld() {
		return helloWorld;
	}

	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}

	public List<EntityTest> fetchAllElement() {
		return userDao.findAll();
	}

	public void addEntity() {
		userDao.merge(entityTest);
	}

	public EntityTest getEntityTest() {
		return entityTest;
	}

	public void setEntityTest(EntityTest entityTest) {
		this.entityTest = entityTest;
	}

}
