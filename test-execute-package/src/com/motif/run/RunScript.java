package com.motif.run;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.tsli.benefit.BenefitFMA;
import com.tsli.util.DBConnection;
import com.tsli.util.ModelAbstract;

public class RunScript extends ModelAbstract{

	
	private static Logger logger = Logger.getLogger(RunScript.class);
 
	// Simple JDBC Connection Pooling
	// Here I am passing param companyId which is IN param to stored procedure
	// which will return me some value.
	public static void main(String... args) throws SQLException {

		 try {

			ArrayList<HashMap<String, String>> paymentLists = getPaymentPeriod();
			for(HashMap<String, String> payment : paymentLists){
				BenefitFMA.runBenefitFMA(payment);
			}
			
		} catch (Exception ex) {
			logger.error(RunScript.class.getName(),ex);
			System.exit(-1);
		}
		System.exit(0);
	}
	

	public static ArrayList<HashMap<String, String>> getPaymentPeriod() throws Exception{
		//String DRIVER = "oracle.jdbc.driver.OracleDriver";
      
        ArrayList<HashMap<String, String>> paymentLists = new    ArrayList<HashMap<String,String>>();

        HashMap<String,String>  paymentParams  = new HashMap<String, String>();
        Connection conn = null;
        
		try {
			PreparedStatement pstm = null;

	    	DBConnection db = new DBConnection();
	    	conn = db.getConnection();
	    	conn.setAutoCommit(false);
            
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT SCHEDULE_ID,PERIOD_YEAR,PERIOD_MONTH,PAYMENT_DATE FROM ICOM.ACS_SCHEDULE WHERE SCHEDULE_ID = (SELECT MIN(SCHEDULE_ID) FROM ICOM.ACS_SCHEDULE WHERE IS_PAID = 'F') ");
            
            pstm = conn.prepareCall(sb.toString());
            ResultSet rs = null;
			rs = pstm.executeQuery();
            while(rs.next()){
            	int i = 1;
            	paymentParams.put("scheduleId",rs.getString(i++));
            	paymentParams.put("performanceYear",rs.getString(i++));
            	paymentParams.put("performanceMonth",rs.getString(i++));
            	paymentParams.put("paymentDate",rs.getString(i++));
            	logger.debug("scheduleId : "  + paymentParams.get("scheduleId") + " performanceYear : " + paymentParams.get("performanceYear") + " perfomrnace_month : " + paymentParams.get("performanceMonth"));
            	paymentLists.add(paymentParams);
            }
            
		} catch (SQLException ex) {
            try {
            	conn.rollback();
            	logger.error(RunScript.class.getName(),ex);
            } catch (SQLException ex1) {
            	logger.error(RunScript.class.getName(),ex);
            }
        } finally{
			if (null != conn )
				conn.close();
        }
		 
        return paymentLists;
	}
	
}
