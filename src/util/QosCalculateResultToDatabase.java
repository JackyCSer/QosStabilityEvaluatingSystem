package util;

import java.sql.*;
import java.util.List;

import model.QosCalculateResult;
import model.QosCalculateResult2;
import model.QosCalculateResult3;
import dao.DBConnect;


public class QosCalculateResultToDatabase {
	/**
	 * 不划分的计算结果存入数据库 仅执行一次
	 */
	public static void updateQosResult1(){
		List<QosCalculateResult> list = QosCalculateResultToList
				.getQosCalculateResultList(QosCalculateResultToList.getQosCalculateMapFromDatabase());

		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO QOS_RESULT_1"+
				" (WSID,RTEX,RTEN,RTHE,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+
				"','"+list.get(i).getRtEx()+"','"+list.get(i).getRtEn()+"','"+list.get(i).getRtHe()+
				"','"+list.get(i).getRe()+
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
	public static void updateQosResult2(){
		List<QosCalculateResult2> list = QosCalculateResultToList
				.getQosCalculateResult2List(QosCalculateResultToList.getQosCalculateMap2FromDatabase());
		
		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO QOS_RESULT_2"+
				" (WSID,COUNTRYCODE,RTEX,RTEN,RTHE,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+"','"+list.get(i).getCountryCode()+
				"','"+list.get(i).getRtEx()+"','"+list.get(i).getRtEn()+"','"+list.get(i).getRtHe()+
				"','"+list.get(i).getRe()+
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
	public static void updateQosResult3(){
		List<QosCalculateResult3> list = QosCalculateResultToList
				.getQosCalculateResult3List(QosCalculateResultToList.getQosCalculateMap3FromDatabase());
		
		Connection conn = null;
		Statement st = null;
		String sql = "";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			
			for(int i=0;i<list.size();i++){
				sql=" INSERT INTO QOS_RESULT_3"+
				" (WSID,COUNTRYCODE,STATE,RTEX,RTEN,RTHE,RE,MIN_RT,MAX_RT,PAUTA_LEFT_RT,PAUTA_RIGHT_RT)"+
				" VALUES"+
				" ('"+list.get(i).getWsid()+"','"+list.get(i).getCountryCode()+"','"+list.get(i).getState()+
				"','"+list.get(i).getRtEx()+"','"+list.get(i).getRtEn()+"','"+list.get(i).getRtHe()+
				"','"+list.get(i).getRe()+
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
