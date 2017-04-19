package com.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="punishment")
public class Punishment {

	private String punishmentId;
	private String punishmentName;
	private String punishmentLevel;
	private int id;
	private String punishment;
	
	public Punishment() {
		super();
	}
	public String getPunishmentId() {
		return punishmentId;
	}

	public void setPunishmentId(String punishmentId) {
		this.punishmentId = punishmentId;
	}

	public String getPunishmentName() {
		return punishmentName;
	}

	public void setPunishmentName(String punishmentName) {
		this.punishmentName = punishmentName;
	}

	public String getPunishmentLevel() {
		return punishmentLevel;
	}

	public void setPunishmentLevel(String punishmentLevel) {
		this.punishmentLevel = punishmentLevel;
	}
	public String getPunishment() {
		return punishment;
	}
	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}


