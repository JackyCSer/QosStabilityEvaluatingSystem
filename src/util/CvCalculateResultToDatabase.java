package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.CvCalculateResult;
import model.CvCalculateResult2;
import model.CvCalculateResult3;
import dao.DBConnect;


public class CvCalculateResultToDatabase {
	
	public static void updateCvResult1(){
		List<CvCalculateResult> list = CvCalculateResultToList
				.getCvCalculateResultList(QosCalculateResultToList.getQosCalculateMapFromDatabase());

		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO CV_RESULT_1"+
				" (WSID,RTCV,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+
				"','"+list.get(i).getRtCv()+"','"+list.get(i).getRe()+
				"','"+list.get(i).getMinRt()+"','"+list.get(i).getMaxRt()+
				"','"+list.get(i).getPautaLeftRt()+"','"+list.get(i).getPautaRightRt()+"')";
				
				st.executeUpdate(sql);
			}
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 国家划分的结果存入数据库
	 */
	public static void updateCvResult2(){
		List<CvCalculateResult2> list = CvCalculateResultToList
				.getCvCalculateResult2List(QosCalculateResultToList.getQosCalculateMap2FromDatabase());
		
		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO CV_RESULT_2"+
				" (WSID,COUNTRYCODE,RTCV,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+"','"+list.get(i).getCountryCode()+
				"','"+list.get(i).getRtCv()+"','"+list.get(i).getRe()+
				"','"+list.get(i).getMinRt()+"','"+list.get(i).getMaxRt()+
				"','"+list.get(i).getPautaLeftRt()+"','"+list.get(i).getPautaRightRt()+"')";
				
				st.executeUpdate(sql);
			}
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 省级划分的结果存入数据库
	 */
	public static void updateCvResult3(){
		List<CvCalculateResult3> list = CvCalculateResultToList
				.getCvCalculateResult3List(QosCalculateResultToList.getQosCalculateMap3FromDatabase());
		
		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO CV_RESULT_3"+
				" (WSID,COUNTRYCODE,STATE,RTCV,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+"','"+list.get(i).getCountryCode()+"','"+list.get(i).getState()+
				"','"+list.get(i).getRtCv()+"','"+list.get(i).getRe()+
				"','"+list.get(i).getMinRt()+"','"+list.get(i).getMaxRt()+
				"','"+list.get(i).getPautaLeftRt()+"','"+list.get(i).getPautaRightRt()+"')";
				
				st.executeUpdate(sql);
			}
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
