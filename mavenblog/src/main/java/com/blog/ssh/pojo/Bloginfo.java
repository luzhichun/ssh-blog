package com.blog.ssh.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Bloginfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bloginfo")
public class BlogInfo implements java.io.Serializable {

	// Fields
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return "Bloginfo{" +
				"id=" + id +
				", intro='" + intro + '\'' +
				", visits=" + visits +
				", background='" + background + '\'' +
				", emailNoticeflag=" + emailNoticeflag +
				'}';
	}
}