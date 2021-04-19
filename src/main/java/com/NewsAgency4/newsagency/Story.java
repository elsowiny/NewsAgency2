/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NewsAgency4.newsagency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author elsow
 */
@Table(name="stories")
public class Story {
	
	int idstories;
    
    String headline;
    
	
    String stories;
    
    int agencyId;
    
    String agencyreporting;
    
    public Story(String headline, String stories, int agencyId, String agencyreporting){
        this.headline = headline;
        this.stories = stories;
        this.agencyId = agencyId;
        this.agencyreporting = agencyreporting;
    }

	public int getIdstories() {
		return idstories;
	}

	public void setIdstories(int idstories) {
		this.idstories = idstories;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getStories() {
		return stories;
	}

	public void setStories(String stories) {
		this.stories = stories;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyreporting() {
		return agencyreporting;
	}

	public void setAgencyreporting(String agencyreporting) {
		this.agencyreporting = agencyreporting;
	}

	@Override
	public String toString() {
		return "Story [idstories=" + idstories + ", headline=" + headline + ", stories=" + stories + ", agencyId="
				+ agencyId + ", agencyreporting=" + agencyreporting + "]";
	}
	
	
}
