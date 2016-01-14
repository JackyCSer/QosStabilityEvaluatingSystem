package api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CvCalculateResult;
import model.CvCalculateResult2;
import model.CvCalculateResult3;
import model.QosCalculateResult;
import model.QosCalculateResult2;
import model.QosCalculateResult3;
import dao.DBConnect;

/** 
 * 类的描述
 */
public class QosCalculateServiceOfInterval {

	/** 从数据库QOS_RESULT_1中直接获取不划分的结果 */
	public static List<QosCalculateResult> getQosResult() {

		List<QosCalculateResult> list = new ArrayList<QosCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = " SELECT *" + " FROM QOS_RESULT_1";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				QosCalculateResult qosCalculateResult = new QosCalculateResult();
				qosCalculateResult.setWsid(rs.getString("WSID"));
				qosCalculateResult.setRtEx(rs.getString("RTEX"));
				qosCalculateResult.setRtEn(rs.getString("RTEN"));
				qosCalculateResult.setRtHe(rs.getString("RTHE"));
				qosCalculateResult.setRe(rs.getString("RE"));
				//--
				qosCalculateResult.setMinRt(rs.getString("MIN_RT"));
				qosCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				qosCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				qosCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(qosCalculateResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 从数据库QOS_RESULT_2中直接获取结果 101*27国家，共2727条数据，用oracle自己的avg函数聚合成101条结果
	 */
	public static List<QosCalculateResult2> getQosResult2() {

		List<QosCalculateResult2> list = new ArrayList<QosCalculateResult2>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String sql = " SELECT *" + " FROM QOS_RESULT_2";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				QosCalculateResult2 qosCalculateResult = new QosCalculateResult2();
				qosCalculateResult.setWsid(rs.getString("WSID"));
				qosCalculateResult.setCountryCode(rs.getString("COUNTRYCODE"));
				qosCalculateResult.setRtEx(rs.getString("RTEX"));
				qosCalculateResult.setRtEn(rs.getString("RTEN"));
				qosCalculateResult.setRtHe(rs.getString("RTHE"));
				qosCalculateResult.setRe(rs.getString("RE"));
				//--
				qosCalculateResult.setMinRt(rs.getString("MIN_RT"));
				qosCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				qosCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				qosCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(qosCalculateResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 从数据库QOS_RESULT_3中直接获取结果 101*27*省级数量，用oracle自己的avg函数聚合成101条结果
	 */
	public static List<QosCalculateResult3> getQosResult3() {

		List<QosCalculateResult3> list = new ArrayList<QosCalculateResult3>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String sql = " SELECT *" + " FROM QOS_RESULT_3";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				QosCalculateResult3 qosCalculateResult = new QosCalculateResult3();
				qosCalculateResult.setWsid(rs.getString("WSID"));
				qosCalculateResult.setCountryCode(rs.getString("COUNTRYCODE"));
				qosCalculateResult.setState(rs.getString("STATE"));
				qosCalculateResult.setRtEx(rs.getString("RTEX"));
				qosCalculateResult.setRtEn(rs.getString("RTEN"));
				qosCalculateResult.setRtHe(rs.getString("RTHE"));
				qosCalculateResult.setRe(rs.getString("RE"));
				//--
				qosCalculateResult.setMinRt(rs.getString("MIN_RT"));
				qosCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				qosCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				qosCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(qosCalculateResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	public static List<CvCalculateResult> getCvResult(){
		List<CvCalculateResult> list = new ArrayList<CvCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = " SELECT *" + " FROM CV_RESULT_1";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CvCalculateResult cvCalculateResult = new CvCalculateResult();
				cvCalculateResult.setWsid(rs.getString("WSID"));
				cvCalculateResult.setRtCv(rs.getString("RTCV"));
				cvCalculateResult.setRe(rs.getString("RE"));
				//--
				cvCalculateResult.setMinRt(rs.getString("MIN_RT"));
				cvCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				cvCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				cvCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(cvCalculateResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public static List<CvCalculateResult2> getCvResult2(){
		List<CvCalculateResult2> list = new ArrayList<CvCalculateResult2>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String sql = " SELECT *" + " FROM CV_RESULT_2";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CvCalculateResult2 cvCalculateResult = new CvCalculateResult2();
				cvCalculateResult.setWsid(rs.getString("WSID"));
				cvCalculateResult.setCountryCode(rs.getString("COUNTRYCODE"));
				cvCalculateResult.setRtCv(rs.getString("RTCV"));
				cvCalculateResult.setRe(rs.getString("RE"));
				//--
				cvCalculateResult.setMinRt(rs.getString("MIN_RT"));
				cvCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				cvCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				cvCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(cvCalculateResult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public static List<CvCalculateResult3> getCvResult3(){
		List<CvCalculateResult3> list = new ArrayList<CvCalculateResult3>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		String sql = " SELECT *" + " FROM CV_RESULT_3";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CvCalculateResult3 cvCalculateResult = new CvCalculateResult3();
				cvCalculateResult.setWsid(rs.getString("WSID"));
				cvCalculateResult.setCountryCode(rs.getString("COUNTRYCODE"));
				cvCalculateResult.setState(rs.getString("STATE"));
				cvCalculateResult.setRtCv(rs.getString("RTCV"));
				cvCalculateResult.setRe(rs.getString("RE"));
				//--
				cvCalculateResult.setMinRt(rs.getString("MIN_RT"));
				cvCalculateResult.setMaxRt(rs.getString("MAX_RT"));
				cvCalculateResult.setPautaLeftRt(rs.getString("PAUTA_LEFT_RT"));
				cvCalculateResult.setPautaRightRt(rs.getString("PAUTA_RIGHT_RT"));
				list.add(cvCalculateResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
}
