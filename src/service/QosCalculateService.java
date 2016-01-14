package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import model.CvCalculateResult;
import model.QosCalculateResult;
import dao.DBConnect;

public class QosCalculateService {

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
	public static List<QosCalculateResult> getQosResult2() {

		List<QosCalculateResult> list = new ArrayList<QosCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		/*
		 * SELECT WSID, AVG(RTEX) RTEX,AVG(RTEN) RTEN,AVG(RTHE) RTHE, AVG(RE)
		 * RE FROM QOS_RESULT_2 GROUP BY WSID
		 * ORDER BY WSID
		 */
		String sql = " SELECT WSID," + //
				" AVG(RTEX) RTEX,AVG(RTEN) RTEN,AVG(RTHE) RTHE," + //
				" AVG(RE) RE" + //
				" FROM QOS_RESULT_2" + //
				" GROUP BY WSID" + //
				" ORDER BY WSID";//

		DecimalFormat df = new DecimalFormat("0.00");
		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				QosCalculateResult qosCalculateResult = new QosCalculateResult();
				qosCalculateResult.setWsid(rs.getString("WSID"));
				qosCalculateResult.setRtEx(df.format(Double.valueOf(rs
						.getString("RTEX"))));
				qosCalculateResult.setRtEn(df.format(Double.valueOf(rs
						.getString("RTEN"))));
				qosCalculateResult.setRtHe(df.format(Double.valueOf(rs
						.getString("RTHE"))));
				qosCalculateResult.setRe(df.format(Double.valueOf(rs
						.getString("RE"))));

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
	public static List<QosCalculateResult> getQosResult3() {

		List<QosCalculateResult> list = new ArrayList<QosCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		/*
		 * SELECT WSID, AVG(RTEX) RTEX,AVG(RTEN) RTEN,AVG(RTHE) RTHE, AVG(RE)
		 * RE FROM QOS_RESULT_3 GROUP BY WSID
		 * ORDER BY WSID
		 */
		String sql = " SELECT WSID," + //
				" AVG(RTEX) RTEX,AVG(RTEN) RTEN,AVG(RTHE) RTHE," + //
				" AVG(RE) RE" + //
				" FROM QOS_RESULT_3" + //
				" GROUP BY WSID" + //
				" ORDER BY WSID";//

		DecimalFormat df = new DecimalFormat("0.00");
		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				QosCalculateResult qosCalculateResult = new QosCalculateResult();
				qosCalculateResult.setWsid(rs.getString("WSID"));
				qosCalculateResult.setRtEx(df.format(Double.valueOf(rs
						.getString("RTEX"))));
				qosCalculateResult.setRtEn(df.format(Double.valueOf(rs
						.getString("RTEN"))));
				qosCalculateResult.setRtHe(df.format(Double.valueOf(rs
						.getString("RTHE"))));
				qosCalculateResult.setRe(df.format(Double.valueOf(rs
						.getString("RE"))));

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
	public static List<CvCalculateResult> getCvResult2(){
		List<CvCalculateResult> list = new ArrayList<CvCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		/*
		 * SELECT WSID,  AVG(RTCV) RTCV,AVG(RE) RE
		 * FROM CV_RESULT_2 GROUP BY WSID
		 * ORDER BY WSID
		 */
		String sql = " SELECT WSID," + //
				" AVG(RTCV) RTCV,AVG(RE) RE" + //
				" FROM CV_RESULT_2" + //
				" GROUP BY WSID" + //
				" ORDER BY WSID";//

		DecimalFormat df = new DecimalFormat("0.00");
		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CvCalculateResult cvCalculateResult = new CvCalculateResult();
				cvCalculateResult.setWsid(rs.getString("WSID"));
				cvCalculateResult.setRtCv(df.format(Double.valueOf(rs
						.getString("RTCV"))));
				cvCalculateResult.setRe(df.format(Double.valueOf(rs
						.getString("RE"))));
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
	public static List<CvCalculateResult> getCvResult3(){
		List<CvCalculateResult> list = new ArrayList<CvCalculateResult>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		/*
		 * SELECT WSID, AVG(RTCV) RTCV,AVG(RE) RE
		 * FROM QOS_CV_3 GROUP BY WSID
		 * ORDER BY WSID
		 */
		String sql = " SELECT WSID," + //
				" AVG(RTCV) RTCV,AVG(RE) RE" + //
				" FROM CV_RESULT_3" + //
				" GROUP BY WSID" + //
				" ORDER BY WSID";//

		DecimalFormat df = new DecimalFormat("0.00");
		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				CvCalculateResult cvCalculateResult = new CvCalculateResult();
				cvCalculateResult.setWsid(rs.getString("WSID"));
				cvCalculateResult.setRtCv(df.format(Double.valueOf(rs
						.getString("RTCV"))));
				cvCalculateResult.setRe(df.format(Double.valueOf(rs
						.getString("RE"))));
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
