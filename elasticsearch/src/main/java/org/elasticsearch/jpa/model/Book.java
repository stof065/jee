package org.elasticsearch.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Class Book.
 *
 * @author mabourouh
 */
@Entity
public class Book {

	/** The id. */
	@Id
	private String id;

	/** The title. */
	private String title;

	/** The author. */
	private String author;

	/** The release date. */
	private String releaseDate;

	/**
	 * Instantiates a new book.
	 */
	public Book() {
	}

	/**
	 * Instantiates a new book.
	 *
	 * @param id
	 *            the id
	 * @param title
	 *            the title
	 * @param author
	 *            the author
	 * @param releaseDate
	 *            the release date
	 */
	public Book(String id, String title, String author, String releaseDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
	}

	// getters and setters

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author
	 *            the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the release date.
	 *
	 * @return the release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets the release date.
	 *
	 * @param releaseDate
	 *            the new release date
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", author='" + author + '\''
				+ ", releaseDate='" + releaseDate + '\'' + '}';
	}

}