package org.core.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private Long id;

	private String nom, prenom;

	private Date dateNaissance;

	@OneToMany
	@JoinColumn(name = "patient_id")
	private List<Consultation> consultation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Consultation> getConsultation() {
		return consultation;
	}

	public void setConsultation(List<Consultation> consultation) {
		this.consultation = consultation;
	}

}
