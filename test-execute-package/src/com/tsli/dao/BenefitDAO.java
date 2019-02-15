package com.tsli.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tsli.benefit.BenefitFMA;
import com.tsli.model.Agent;
import com.tsli.model.Benefit;
import com.tsli.model.BenefitResult;
import com.tsli.model.Policy;
import com.tsli.util.DBConnection;
import com.tsli.util.ModelAbstract;



public class BenefitDAO  extends ModelAbstract {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String RUN_FMA_BNF = "{call ICOM_TSLI.TSLI_CAL_SFC_BENEFIT.SFC_FMA(?,?,?,?)}";

	
	public BenefitDAO(Connection conn) throws Exception {
        try {
        	DBConnection db = new DBConnection();
        	this.conn = db.getConnection();
        	this.conn.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	

	public ArrayList<Agent> getFMAAgentCalculate(String performanceYear,String performanceMonth,String agentCode){
		ArrayList<Agent> agentList = new ArrayList<Agent>();
		
		try {  
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ID,AGENTCODE ,POSITION,LEVELCD,HASLICENSE ,ACTIVEAGENT,ROOTAGENT ,MONTHSINCEJOIN,MONTHSINCEPAID,WORKMONTH,MONTHSINCETRANPOSITION ");
            sb.append(" ,DAYSINCELICENSE,CARPARK,WORKINGPAYMENTRATE,STDSTATUS,SUMSOLIBIZRT,SUMPRSTBIZRT,TERMINATEYM,NUMBERMM,NUMBERBM,APR_YN,PREVIOUS_SUB_POSITION ,PERSISTENCY_RATE  ");
            sb.append(" FROM TABLE(TSLI_GET_SFC_FMA_AGENT(?,?,NULL,?,?,?)) ");
            
            pstmt = conn.prepareCall(sb.toString());
            
            
            pstmt.setString(1, performanceYear+performanceMonth);
            pstmt.setString(2, performanceYear+performanceMonth);
            pstmt.setString(3, agentCode);
            pstmt.setString(4, performanceYear);
            pstmt.setString(5, performanceMonth);

			rs = pstmt.executeQuery();
            while(rs.next()){
            	Agent agent = new Agent();
            	int i = 1;
            	agent.setId(rs.getString(i++));
            	agent.setAgentCode(rs.getString(i++));
            	agent.setPosition(rs.getString(i++));
            	agent.setLevelCd(rs.getString(i++));
            	agent.setHasLicense(Boolean.parseBoolean(rs.getString(i++)));
            	agent.setActiveAgent(Boolean.parseBoolean(rs.getString(i++)));
            	agent.setRootAgent(Boolean.parseBoolean(rs.getString(i++)));
            	agent.setMonthsSinceJoin(Integer.parseInt(rs.getString(i++)));
            	agent.setMonthsSincePaid(Integer.parseInt(rs.getString(i++)));
            	agent.setWorkMonth(Integer.parseInt(rs.getString(i++)));
            	agent.setMonthsSinceTranPosition(Integer.parseInt(rs.getString(i++)));
            	agent.setDaySinceLicense(Integer.parseInt(rs.getString(i++)));
            	agent.setCarpark(rs.getString(i++));
            	agent.setWorkingPaymentRate(Integer.parseInt(rs.getString(i++)));
            	agent.setStdStatus(rs.getString(i++));
            	agent.setSumSoliBizrt(new BigDecimal(rs.getString(i++)));
            	agent.setSumPrstBizrt(new BigDecimal(rs.getString(i++)));
            	agent.setTerminateYn(rs.getString(i++));
            	agent.setNumberMM(Integer.parseInt(rs.getString(i++)));
            	agent.setNumberBM(Integer.parseInt(rs.getString(i++)));
            	agent.setAprYn(rs.getString(i++));
            	agent.setPreviousSubPosition(rs.getString(i++));
            	String prs = rs.getString(i++);
            	agent.setPersistency(null != prs ? new BigDecimal(prs) : BigDecimal.ZERO);
            	agentList.add(agent);
            	
            }
            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
		
		return agentList;
	}
	
	public ArrayList<Policy> getFMACommCalculate(String performanceYear,String performanceMonth,String agentCode){
		ArrayList<Policy> policyList = new ArrayList<Policy>();
	
		try {       
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT   CALYM,ID,AGENTID,POLICYID,FYP,FYC,RYP,RYC,AFYP,ARYP,ARYP2 ");
            sb.append(" ,PAYMENTMODE,TIMES,ISACTIVE,PERFORMANCEYM,POLICYTYPE,POLICYAGE,LASTPAIDTIME,PAIDDATE,DUEDATE,TIER ");
            sb.append(" FROM TABLE(TSLI_GET_SFC_FMA_COMM(?,?,?,NULL,?,?,'NA',NULL)) ");
            
            
            pstmt = conn.prepareCall(sb.toString());
            
            
            pstmt.setString(1, performanceYear+performanceMonth);
            pstmt.setString(2, performanceYear+performanceMonth);
            pstmt.setString(3, agentCode);
            pstmt.setString(4, performanceYear);
            pstmt.setString(5, performanceMonth);

			rs = pstmt.executeQuery();
            while(rs.next()){
            	Policy policy = new Policy();
            	int i = 1;
            	policy.setCalYm(rs.getString(i++));
            	policy.setId(rs.getString(i++));
            	policy.setAgentId(rs.getString(i++));
            	policy.setPolicyId(rs.getString(i++));
            	policy.setFyp(new BigDecimal(rs.getString(i++)));
            	policy.setFyc(new BigDecimal(rs.getString(i++)));
            	policy.setRyp(new BigDecimal(rs.getString(i++)));
            	policy.setRyc(new BigDecimal(rs.getString(i++)));
            	policy.setAfyp(new BigDecimal(rs.getString(i++)));
            	policy.setAryp(new BigDecimal(rs.getString(i++)));
            	policy.setAryp2(new BigDecimal(rs.getString(i++)));
            	policy.setPaymentMode(rs.getString(i++));
            	policy.setTimes(Integer.parseInt(rs.getString(i++)));
            	policy.setPerformanceYM(rs.getString(i++));
            	policy.setPolicyAge(Integer.parseInt(rs.getString(i++)));
            	policy.setLastPaidTime(rs.getString(i++));
            	policy.setPaidDate(rs.getString(i++));
            	policy.setDueDate(rs.getString(i++));
            	policy.setTier(rs.getString(i++));
            	policyList.add(policy);
            	
            }
            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
		
		return policyList;
	}
	
	public  void insertFMABenefit(Benefit benefit,Long scheduleId,Long detailFMAId,Long benefitResultId,Long benefitTypeId,BenefitResult benefitResult) throws Exception {
		try {       
          
            StringBuilder sb = new StringBuilder();
            sb.append("insert into \"ICOM\".\"ACS_TSLI_DETAIL_FMA\" (");
            sb.append("DETAIL_FMA_ID  ,SCHEDULE_ID ,benefit_type_id, benefit_result_id,agent_id,total_afyp ,fma_paid ,create_by,create_date,update_by ,update_date ,is_deleted ");
            sb.append(")values (");
            sb.append("?1,");
            sb.append("?2,");
            sb.append("?3,");
            sb.append("?4,");
            sb.append("?5,");
            sb.append("?6,");
            sb.append("?7,");
            sb.append("?8,");
            sb.append("SYSDATE,");
            sb.append("?9,");
            sb.append("SYSDATE,");
            sb.append("?10)");

            int j = 1;
            pstmt = conn.prepareCall(sb.toString());
            pstmt.setLong(j++, detailFMAId);
            pstmt.setLong(j++, scheduleId);
            pstmt.setLong(j++, benefitTypeId);
            if(null != benefitResultId)
            	pstmt.setString(j++, benefitResultId.toString());
            else pstmt.setNull(j++, Types.VARCHAR);
            pstmt.setString(j++, benefit.getAgentId());
            pstmt.setBigDecimal(j++, benefit.getPc());
            pstmt.setBigDecimal(j++, benefit.getAmount());
            pstmt.setInt(j++,  BenefitFMA.USER_APP);
            pstmt.setInt(j++,  BenefitFMA.USER_APP);
            pstmt.setString(j++, "F");
            
            pstmt.executeUpdate();
            
            if(null != benefitResultId)
            	insertBenefitResult(benefitResult);
            
            conn.commit();
           

            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
    }
	
	public  void insertBenefitResult(BenefitResult benefitResult) throws Exception {
	
		try {                 
            StringBuilder sb = new StringBuilder();
            sb.append("insert into \"ICOM\".\"ACS_BENEFIT_RESULT\" (");
            sb.append(" BENEFIT_RESULT_ID,RECORD_TYPE,BENEFIT_TYPE_ID,PSN_INFO_ID,AGENT_CODE ");
            sb.append(" ,TRANS_DATE,BENEFIT_AMOUNT,CAL_SCHEDULE_ID,IS_DELETED,IS_PAID,PAID_DATE,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY ");
            sb.append(")values (");
            sb.append("?1,");
            sb.append("?2,");
            sb.append("?3,");
            sb.append("?4,");
            sb.append("?5,");
            sb.append("SYSDATE,");
            sb.append("?6,");
            sb.append("?7,");
            sb.append("?8,");
            sb.append("?9,");
            sb.append("?10,");
            sb.append("SYSDATE,");
            sb.append("?11,");
            sb.append("SYSDATE,");
            sb.append("?12)");

            int j = 1;
            pstmt = conn.prepareCall(sb.toString());
            pstmt.setLong(j++, benefitResult.getBenefitResultId());
            pstmt.setString(j++, benefitResult.getRecordType());
            pstmt.setLong(j++, BenefitFMA.benefitTypeId);
            pstmt.setString(j++, benefitResult.getAgentInfo().getId());
            pstmt.setString(j++, benefitResult.getAgentInfo().getAgentCode());
            pstmt.setBigDecimal(j++, benefitResult.getBenefit().getAmount());
            pstmt.setLong(j++, benefitResult.getCalScheduleId());
            pstmt.setString(j++, "F");
            pstmt.setString(j++, "F");
            pstmt.setDate(j++, new java.sql.Date(benefitResult.getPaidDate().getTime()));
            pstmt.setInt(j++,  BenefitFMA.USER_APP);
            pstmt.setInt(j++,  BenefitFMA.USER_APP);
            
            
            pstmt.executeUpdate();
            
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
    }
	
	public void runBenefitFMA(String scheduleId,String performanceYear,String performanceMonth,String agentCode){

		try {       
			pstmt = conn.prepareCall(RUN_FMA_BNF);
			// very top
			pstmt.setString(1, scheduleId);// Passing AgentCode or other parameter
			pstmt.setString(2, performanceYear);// Passing AgentCode or other parameter
			pstmt.setString(3, performanceMonth);// Passing AgentCode or other parameter
			pstmt.setString(4, agentCode);// Passing AgentCode or other parameter
		//		stmt.registerOutParameter(2, OracleTypes.CURSOR);// Refcursor  selects the row based upon query  results  provided in  Package.
			
			pstmt.execute();	

            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
	}
	
	public Long getMaxId(String column,String table){
		Long maxId = new Long(0);
		try {       
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT MAX("+column+") FROM "+table+" ");

            pstmt = conn.prepareCall(sb.toString());
            
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            while(rs.next()){
            	maxId = new Long(rs.getString(1));
            }

            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
		
		return maxId;
	}
	
	
	public void rollBackData(String scheduleId,String table,Long benefitTypeId){
	
		try { 
            StringBuilder sb = new StringBuilder();
            sb.append(" DELETE "+table+" WHERE SCHEDULE_ID = ? AND BENEFIT_TYPE_ID = ? ");

            pstmt = conn.prepareCall(sb.toString());
            pstmt.setString(1, scheduleId);
            pstmt.setLong(2, benefitTypeId);
            rs = pstmt.executeQuery();
            
            sb = new StringBuilder();
            sb.append(" DELETE ICOM.ACS_BENEFIT_RESULT WHERE CAL_SCHEDULE_ID = ? AND BENEFIT_TYPE_ID = ? ");

            pstmt = conn.prepareCall(sb.toString());
            pstmt.setString(1, scheduleId);
            pstmt.setLong(2, benefitTypeId);
            rs = pstmt.executeQuery();
            
            conn.commit();
            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
	}
	
	public BigDecimal getValueConfig(Long benefitTypeId,String outValue,String outVal1,String outVal2,String outVal3,String outVal4,BigDecimal inVal1,BigDecimal inVal2,BigDecimal inVal3,BigDecimal inVal4){
		BigDecimal valueConfig = BigDecimal.ZERO;
		try {     
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT NVL(TSLI_GET_BENEFIT_CONFIG(?,?,?,?,?,?,?,?,?,?),0) FROM DUAL ");

            pstmt = conn.prepareCall(sb.toString());
            pstmt.setString(1, benefitTypeId.toString());
            pstmt.setString(2, outValue);
            pstmt.setString(3, outVal1);
            pstmt.setString(4, outVal2);
            pstmt.setString(5, outVal3);
            pstmt.setString(6, outVal4);
            pstmt.setBigDecimal(7, inVal1);
            pstmt.setBigDecimal(8, inVal2);
            pstmt.setBigDecimal(9, inVal3);
            pstmt.setBigDecimal(10, inVal4);
            
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            while(rs.next()){
            	valueConfig = new BigDecimal(rs.getString(1));
            }

            this.closeResultSet();
            this.closePreparedStatement();
		} catch (SQLException ex) {
            try {
            	conn.rollback();
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	 try {
             	conn.rollback();
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, e);
             } catch (SQLException ex1) {
                 Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex1);
             }
		} 
		return valueConfig;
	}
	
}
