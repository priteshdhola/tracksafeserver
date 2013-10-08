package com.np.trackserver.services.beans;

import java.io.Serializable;

public class InviteMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer inviteeId;
	
	private String inviteeEmail;
	
	private String inviteMessage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInviteeId() {
		return inviteeId;
	}

	public void setInviteeId(Integer inviteeId) {
		this.inviteeId = inviteeId;
	}

	public String getInviteeEmail() {
		return inviteeEmail;
	}

	public void setInviteeEmail(String inviteeEmail) {
		this.inviteeEmail = inviteeEmail;
	}

	public String getInviteMessage() {
		return inviteMessage;
	}

	public void setInviteMessage(String inviteMessage) {
		this.inviteMessage = inviteMessage;
	}
	
	

}
