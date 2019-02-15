package com.tsli.model;

import java.math.BigDecimal;

public class Benefit {
	String agentId;
	String type;
	BigDecimal amount;
	String status;
	String transactionPeriod;
	String schedulePeriod;
	String scheduleType;
	Integer duration;
	
	BigDecimal fyp;
	BigDecimal fyc;
	BigDecimal ryp;
	BigDecimal ryc;
	BigDecimal afyp;
	BigDecimal aryp;
	BigDecimal aryp2;
	BigDecimal pc;
	
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionPeriod() {
		return transactionPeriod;
	}
	public void setTransactionPeriod(String transactionPeriod) {
		this.transactionPeriod = transactionPeriod;
	}
	public String getSchedulePeriod() {
		return schedulePeriod;
	}
	public void setSchedulePeriod(String schedulePeriod) {
		this.schedulePeriod = schedulePeriod;
	}
	public String getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public BigDecimal getFyp() {
		return fyp;
	}
	public void setFyp(BigDecimal fyp) {
		this.fyp = fyp;
	}
	public BigDecimal getFyc() {
		return fyc;
	}
	public void setFyc(BigDecimal fyc) {
		this.fyc = fyc;
	}
	public BigDecimal getRyp() {
		return ryp;
	}
	public void setRyp(BigDecimal ryp) {
		this.ryp = ryp;
	}
	public BigDecimal getRyc() {
		return ryc;
	}
	public void setRyc(BigDecimal ryc) {
		this.ryc = ryc;
	}
	public BigDecimal getAfyp() {
		return afyp;
	}
	public void setAfyp(BigDecimal afyp) {
		this.afyp = afyp;
	}
	public BigDecimal getAryp() {
		return aryp;
	}
	public void setAryp(BigDecimal aryp) {
		this.aryp = aryp;
	}
	public BigDecimal getAryp2() {
		return aryp2;
	}
	public void setAryp2(BigDecimal aryp2) {
		this.aryp2 = aryp2;
	}
	public BigDecimal getPc() {
		return pc;
	}
	public void setPc(BigDecimal pc) {
		this.pc = pc;
	}
}
