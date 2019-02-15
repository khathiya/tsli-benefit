package com.tsli.model;

import java.math.BigDecimal;

public class Policy {
	String id;
	String agentId;
	String calYm;
	String policyId;
	BigDecimal fyp;
	BigDecimal fyc;
	BigDecimal ryp;
	BigDecimal ryc;
	BigDecimal afyp;
	BigDecimal aryp;
	BigDecimal aryp2;
	String paymentMode;
	Integer times;
	String performanceYM;
	String policyTyp;
	Integer policyAge;
	String lastPaidTime;
	String paidDate;
	String dueDate;
	String tier;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getCalYm() {
		return calYm;
	}
	public void setCalYm(String calYm) {
		this.calYm = calYm;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
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
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getPerformanceYM() {
		return performanceYM;
	}
	public void setPerformanceYM(String performanceYM) {
		this.performanceYM = performanceYM;
	}
	public String getPolicyTyp() {
		return policyTyp;
	}
	public void setPolicyTyp(String policyTyp) {
		this.policyTyp = policyTyp;
	}
	public Integer getPolicyAge() {
		return policyAge;
	}
	public void setPolicyAge(Integer policyAge) {
		this.policyAge = policyAge;
	}
	public String getLastPaidTime() {
		return lastPaidTime;
	}
	public void setLastPaidTime(String lastPaidTime) {
		this.lastPaidTime = lastPaidTime;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
}
