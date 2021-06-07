package com.moonshot.restaurant.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="feedback_seq_gen")
	@SequenceGenerator(name="feedback_seq_gen", sequenceName="FEEDBACK_SEQ")
	private Long id;
	private int rating;
	private String feedbackComment;
	private Long feedbackBy;
	private String feedbackFor;
	private String feedbackObjectId;
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(Long id, int rating, String feedbackComment, Long feedbackBy, String feedbackFor,
			String feedbackObjectId) {
		super();
		this.id = id;
		this.rating = rating;
		this.feedbackComment = feedbackComment;
		this.feedbackBy = feedbackBy;
		this.feedbackFor = feedbackFor;
		this.feedbackObjectId = feedbackObjectId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFeedbackComment() {
		return feedbackComment;
	}
	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
	public Long getFeedbackBy() {
		return feedbackBy;
	}
	public void setFeedbackBy(Long feedbackBy) {
		this.feedbackBy = feedbackBy;
	}
	public String getFeedbackFor() {
		return feedbackFor;
	}
	public void setFeedbackFor(String feedbackFor) {
		this.feedbackFor = feedbackFor;
	}
	public String getFeedbackObjectId() {
		return feedbackObjectId;
	}
	public void setFeedbackObjectId(String feedbackObjectId) {
		this.feedbackObjectId = feedbackObjectId;
	}
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", rating=" + rating + ", feedbackComment=" + feedbackComment + ", feedbackBy="
				+ feedbackBy + ", feedbackFor=" + feedbackFor + ", feedbackObjectId=" + feedbackObjectId + "]";
	}
		

}
