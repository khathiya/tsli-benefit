package com.tsli.model;

import java.math.BigDecimal;
import java.util.Date;

public class BenefitResult {
	
	private Long benefitResultId;
	private String recordType;
	private Long benefitTypeId;
	private Long calScheduleId;
	private Long acsPsnInf;	
	

	private String agentCode;		
	private String agentPosCode;
	private String fromAgentCode;	
	private BigDecimal benefitAmount;
	private Date transDate;
	private Date paidDate;
	private String isPaid;
	private Date createDate;
	private Long createBy;
	private Date updateDate;
	private Long updateBy;
	private String isDeleted;
	private Long effectSchedule;
	private String performanceMonth;
	private String performanceYear;
	
	private Agent agentInfo;
	private Benefit benefit;
	
	public Long getBenefitResultId() {
		return benefitResultId;
	}
	public void setBenefitResultId(Long benefitResultId) {
		this.benefitResultId = benefitResultId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public Long getBenefitTypeId() {
		return benefitTypeId;
	}
	public void setBenefitTypeId(Long benefitTypeId) {
		this.benefitTypeId = benefitTypeId;
	}
	public Long getCalScheduleId() {
		return calScheduleId;
	}
	public void setCalScheduleId(Long calScheduleId) {
		this.calScheduleId = calScheduleId;
	}
	public Long getAcsPsnInf() {
		return acsPsnInf;
	}
	public void setAcsPsnInf(Long acsPsnInf) {
		this.acsPsnInf = acsPsnInf;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentPosCode() {
		return agentPosCode;
	}
	public void setAgentPosCode(String agentPosCode) {
		this.agentPosCode = agentPosCode;
	}
	public String getFromAgentCode() {
		return fromAgentCode;
	}
	public void setFromAgentCode(String fromAgentCode) {
		this.fromAgentCode = fromAgentCode;
	}
	public BigDecimal getBenefitAmount() {
		return benefitAmount;
	}
	public void setBenefitAmount(BigDecimal benefitAmount) {
		this.benefitAmount = benefitAmount;
	}
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public String getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getEffectSchedule() {
		return effectSchedule;
	}
	public void setEffectSchedule(Long effectSchedule) {
		this.effectSchedule = effectSchedule;
	}
	public String getPerformanceMonth() {
		return performanceMonth;
	}
	public void setPerformanceMonth(String performanceMonth) {
		this.performanceMonth = performanceMonth;
	}
	public String getPerformanceYear() {
		return performanceYear;
	}
	public void setPerformanceYear(String performanceYear) {
		this.performanceYear = performanceYear;
	}
	public Agent getAgentInfo() {
		return agentInfo;
	}
	public void setAgentInfo(Agent agentInfo) {
		this.agentInfo = agentInfo;
	}
	public Benefit getBenefit() {
		return benefit;
	}
	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}
}
