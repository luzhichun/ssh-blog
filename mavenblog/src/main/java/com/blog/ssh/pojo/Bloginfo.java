package com.blog.ssh.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bloginfo")
public class Bloginfo implements java.io.Serializable {

	// Fields
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	private Integer id;
	@Column
	private String intro;
	@Column
	private Integer visits;
	@Column
	private String background;
	@Column(name = "email_notice_flag")
	private Integer emailNoticeflag;

	// Constructors

	/** default constructor */
	public Bloginfo() {
	}

	public Bloginfo(Integer id, String intro, Integer visits,
			String background) {
		super();
		this.id = id;
		this.intro = intro;
		this.visits = visits;
		this.background = background;
	}

	/** full constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getEmailNoticeflag() {
		return emailNoticeflag;
	}

	public void setEmailNoticeflag(Integer emailNoticeflag) {
		this.emailNoticeflag = emailNoticeflag;
	}
	
}