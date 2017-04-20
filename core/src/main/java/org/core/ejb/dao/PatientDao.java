package org.core.ejb.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;

import org.core.ejb.commun.AbstractDao;
import org.core.model.Patient;
import org.core.model.QConsultation;
import org.core.model.QPatient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysema.query.hql.jpa.JPAQuery;

@Stateful
public class PatientDao extends AbstractDao<Patient, Long> {

	private static final Logger LOG = LoggerFactory.getLogger(PatientDao.class);

	// JOIN FETCH
	public List<Patient> findPatientByDateConsultation(Date date) {
		JPAQuery query = new JPAQuery(em);
		query.from(QPatient.patient).join(QPatient.patient.consultation, QConsultation.consultation).fetch()
				.where(QConsultation.consultation.date.eq(date));

		// log jpql
		LOG.info("\n\n" + query.toString() + "\n\n");
		return query.listResults(QPatient.patient).getResults();
	}

	// CROSS SELECT 
	public List<Patient> findPatientByDateConsultationSelectOption(Date date) {

		JPAQuery query = new JPAQuery(em);
		query.from(QPatient.patient).where(QPatient.patient.id.in(query.from(QConsultation.consultation)
				.where(QConsultation.consultation.patient.id.eq(QPatient.patient.id)).list(QPatient.patient.id)));

		// log jpql
		LOG.info("\n\n" + query.toString() + "\n\n");
		return query.listResults(QPatient.patient).getResults();

	}

}
