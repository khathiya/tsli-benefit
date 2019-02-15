package com.tsli.benefit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.motif.run.RunScript;
import com.tsli.dao.BenefitDAO;
import com.tsli.model.Agent;
import com.tsli.model.Benefit;
import com.tsli.model.BenefitResult;
import com.tsli.model.Policy;

public class BenefitFMA {
	private static Logger logger = Logger.getLogger(BenefitFMA.class);
	public static Long benefitTypeId = new Long(246);
	public static final int USER_APP = 26;
	public static String recordType = "CR";
	public static final String agentCode = null;
	
	
	public static void runBenefitFMA(HashMap<String, String> payment) throws Exception{
		
		logger.info("-- START RUN SFC FMA BENEFIT -- " );
		String scheduleId = payment.get("scheduleId");
		String performanceYear = payment.get("performanceYear");
		String performanceMonth = payment.get("performanceMonth");
		Date paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(payment.get("paymentDate"));
		Connection conn = null;
		try {
			BenefitDAO benefitDAO = new BenefitDAO(conn);
			benefitDAO.rollBackData(scheduleId,"ICOM.ACS_TSLI_DETAIL_FMA",benefitTypeId);
			
			//benefitDAO.runBenefitFMA(payment.get("scheduleId"), payment.get("performanceYear"), payment.get("performanceMonth"), agentCode);
			ArrayList<Agent> agents = benefitDAO.getFMAAgentCalculate(performanceYear,performanceMonth , agentCode);
			
			Long fmaDetailMaxId = benefitDAO.getMaxId("DETAIL_FMA_ID","ICOM.ACS_TSLI_DETAIL_FMA");
			
			Long benefitResultMaxId = benefitDAO.getMaxId("BENEFIT_RESULT_ID","ICOM.ACS_BENEFIT_RESULT");
			for(Agent agent:agents){
				Long fmaDetailId = new Long(++fmaDetailMaxId);
				
				
				logger.debug("AgentCode : " + agent.getAgentCode() + " fmaDetailId : " + fmaDetailId + " Agent Status : " + agent.getActiveAgent());
				
				
				ArrayList<Policy> policys = benefitDAO.getFMACommCalculate(performanceYear,performanceMonth , agent.getAgentCode());
			    BigDecimal pc = BigDecimal.ZERO;
				for(Policy policy:policys){
					pc = pc.add(policy.getAfyp().add(policy.getAryp2()));
					logger.debug("PolicyId : " + policy.getPolicyId() + " PolicyTime : " + policy.getTimes() + " AFYP : " + policy.getAfyp() + " ARYP2 : " + policy.getAryp2() + " PC : " + pc);		
				}
				
				
				Benefit benefit = new Benefit();
				benefit.setAgentId(agent.getId());
				benefit.setPc(pc);
				calculateBenefit(benefit,conn);
				
				Long benefitResultId = agent.getActiveAgent()||(!agent.getActiveAgent()&&benefit.getAmount().compareTo(BigDecimal.ZERO)<0)? new Long(++benefitResultMaxId):null;

				logger.debug("Payment FMA Amount : " + benefit.getAmount() + " Paid date : " + paymentDate.toString());
				
				BenefitResult benefitResult = new BenefitResult();
				if(null != benefitResultId){
					benefitResult.setBenefitResultId(benefitResultId);
					benefitResult.setAgentInfo(agent);
					benefitResult.setBenefit(benefit);
					benefitResult.setCalScheduleId(new Long(scheduleId));
					benefitResult.setRecordType(recordType);
					benefitResult.setPaidDate(paymentDate);
				}
				
				benefitDAO.insertFMABenefit(benefit,new Long(scheduleId),fmaDetailId,benefitResultId,benefitTypeId,benefitResult);
				
			}
		} catch (Exception ex) {
			logger.error(RunScript.class.getName(),ex);
        } 
        logger.info("-- END RUN SFC FMA BENEFIT -- ");
	}
	
	public static Benefit calculateBenefit(Benefit benefit,Connection conn){
		BenefitDAO benefitDAO;
		try {
			benefitDAO = new BenefitDAO(conn);
			BigDecimal fmaRate = benefitDAO.getValueConfig(benefitTypeId,"BNF_AMT","PC",null,null,null,benefit.getPc(),null,null,null);
			benefit.setAmount(fmaRate);
		} catch (Exception e) {
			logger.error(RunScript.class.getName(),e);
		}
		
		return benefit;
	}
	
}
