package com.blog.ssh.model.pojo;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */

public class Bloginfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String intro;
	private Integer visits;
	private String background;
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