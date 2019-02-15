package com.tsli.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tsli.dao.BenefitDAO;


public abstract class ModelAbstract implements Serializable {

    protected Connection conn = null;
    protected ResultSet rs = null;
    protected PreparedStatement pstmt = null;
    protected String method = "";

	public void commitConnection(){
		if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException ex) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
	
	public void closConnection(){
		if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BenefitDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
    /**
     *
     * Close Result Set
     *
     * @throws Exception
     * @throws SQLException
     */
    protected void closeResultSet() throws Exception, SQLException{
        try {
            if (this.rs != null) rs.close();
        } catch (SQLException exc) {
            throw new Exception("Error close resultset: " + exc.getMessage());
        }
    }

    /**
     *
     * Close Prepare Statement
     *
     * @throws Exception
     * @throws SQLException
     */
    protected void closePreparedStatement() throws Exception, SQLException{
        try {
            if (this.pstmt != null) pstmt.close();

        } catch (SQLException exc) {
            throw new Exception("Error close preparestatement: " + exc.getMessage());
        }
    }

    /**
     *
     * Replace null value with "" Character
     *
     * @param str
     * @return String
     * @throws Exception
     */
    protected static String nullValue(String str) throws Exception{
        if (str == null){
            str = "";
        }
        return str;
    }


    /**
     *
     * Format date data type to String by given format
     *
     * @param date
     * @param format
     * @return String
     */
    protected static String setDateFormat (Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        String str = null;
        if (date == null){
            str = "";
        } else {
            str = sdf.format(date);
           if(!str.substring(0,10).equals("01/01/1900") && !str.substring(0,10).equals("09/09/9999")){
               str = convertToBuddhistYear(str);
           } else {
               str = "";
           }
       }
       return str;
   }

    public static String convertToGregorianYear(String str){
        //String strYear = str.substring(6, 10).trim();
        //int year = Integer.parseInt(str.substring(6, 10).trim()) - 543;
        //str = str.replaceAll(strYear, String.valueOf(year));
        str = DateUtil.convertCCyear(str);
        return str;
    }

    public static String convertToBuddhistYear(String str){
        //String strYear = str.substring(6,10).trim();
        //int year = Integer.parseInt(str.substring(6,10).trim()) + 543;
        //str = str.replaceAll(strYear, String.valueOf(year));
        str = DateUtil.convertBBYear(str);
        return str;
    }


    protected String manageException(String msg, String sql, String method) {
        return "Error ["+method+"]" + msg + "\n" + sql;
    }

    public static String convertToDB2Date(String tmpDate){
        /* 05/04/2010*/
        String str = tmpDate.substring(6,10)+"-"+tmpDate.substring(3,5)+"-"+tmpDate.substring(0,2);
        return tmpDate.replaceAll(tmpDate.substring(0,10), str);
    }

}
