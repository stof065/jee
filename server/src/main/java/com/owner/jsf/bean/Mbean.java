package com.owner.jsf.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.core.ejb.UserDao;
import org.core.ejb.dao.PatientDao;
import org.core.model.EntityTest;
import org.core.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
public class Mbean {

	private static final Logger LOG = LoggerFactory.getLogger(Mbean.class);

	@EJB
	private UserDao userDao;

	@EJB
	private PatientDao dao;

	@Resource
	UserTransaction trx;

	@PostConstruct
	public void init() {

		try {
			trx.begin();
			List<Patient> patients = dao.findPatientByDateConsultation(
					new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
			patients.stream().forEach(x -> {
				LOG.info(x == null ? "" : x.toString());
			});
			patients = dao.findPatientByDateConsultationSelectOption(
					new SimpleDateFormat("dd-MM-yyyy").parse(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
			patients.stream().forEach(x -> {
				LOG.info(x == null ? "" : x.toString());
			});

			trx.commit();

		} catch (ParseException | NotSupportedException | SystemException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
