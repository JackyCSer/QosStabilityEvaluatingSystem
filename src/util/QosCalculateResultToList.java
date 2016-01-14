package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.QosCalculateResult;
import model.QosCalculateResult2;
import model.QosCalculateResult3;
import model.Quality;
import dao.DBConnect;

/**
 * QoS计算结果的static方法，返回list
 * */
public class QosCalculateResultToList {
	
		/**
	 * 获取 不划分 数据
	 * 
	 * @return
	 */
	public static Map<String, Quality> getQosCalculateMapFromDatabase() {
		Map<String, Quality> map = new LinkedHashMap<String, Quality>();// 存储从数据库得到的直接数据

		/* 数据库操作 */
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = " SELECT WSID ,RT,RESULT " + " FROM \"qws_2\""
				+ " ORDER BY WSID";

		try {
			/* 执行查询 */
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			/* 查询结果+存入map集合 */
			while (rs.next()) {
				String wsid = rs.getString("WSID");
				if (!map.containsKey(wsid)) {
					Quality quality = new Quality();
					quality.rtList = new ArrayList<Double>();
					quality.reList = new ArrayList<Double>();
					quality.rtList.add(Double.valueOf(rs.getString("RT")));
					String result = rs.getString("RESULT");
					if (result.equals("OK")) {
						quality.reList.add(1.0);
					} else {
						quality.reList.add(0.0);
					}
					map.put(wsid, quality);
				} else {
					Quality quality = map.get(wsid);
					quality.rtList.add(Double.valueOf(rs.getString("RT")));
					String result = rs.getString("RESULT");
					if (result.equals("OK")) {
						quality.reList.add(1.0);
					} else {
						quality.reList.add(0.0);
					}
				}

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
		return map;

	}

	/**
	 * 计算 不划分 数据
	 * 
	 * @param map
	 * @return
	 */
	public static List<QosCalculateResult> getQosCalculateResultList(
			Map<String, Quality> map) {

		List<QosCalculateResult> list = new ArrayList<QosCalculateResult>();// 返回结果list

		DecimalFormat df = new DecimalFormat("0.00");
		/* QoS计算 */
		for (String wsid : map.keySet()) {
			Quality quality = map.get(wsid);
			QosCalculateResult qosCalculateResult = new QosCalculateResult();

			qosCalculateResult.setWsid(wsid);

			List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
			//rt的三个云特征值
			qosCalculateResult.setRtEx(df.format(
					CloudModelMath.ExCalculate(list1)).toString());
			qosCalculateResult.setRtEn(df.format(
					CloudModelMath.EnCalculate(list1)).toString());
			qosCalculateResult.setRtHe(df.format(
					CloudModelMath.HeCalculate(list1)).toString());					
			//从list1中取出最小最大值
			Collections.sort(list1);
			qosCalculateResult.setMinRt(list1.get(0).toString());
			qosCalculateResult.setMaxRt(list1.get(list1.size()-1).toString());			
			//从list1取出[μ-3σ, μ+3σ]
			qosCalculateResult.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
			qosCalculateResult.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
			

			List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换			
			//re可靠性（就是求reList的期望）
            qosCalculateResult.setRe(df.format(CloudModelMath.ExCalculate(list2)));

			list.add(qosCalculateResult);

		}

		return list;

	}

	/**
	 * 获取 国家划分 数据
	 * 
	 * @return
	 */
	public static Map<String, Map<String, Quality>> getQosCalculateMap2FromDatabase() {
		// 存储从数据库得到的直接数据; 第一个键是wsid，第二个键是国家
		Map<String, Map<String, Quality>> map = new LinkedHashMap<String, Map<String, Quality>>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		/*
		 * SELECT x.WSID,y.COUNTRYCODE,x.RT,x.RESULT FROM "qws_2" x,"qws_feat" y
		 * WHERE x.IP=y.IP ORDER BY WSID,COUNTRYCODE
		 */
		String sql = "SELECT x.WSID,y.COUNTRYCODE,x.RT,x.RESULT"
				+ " FROM \"qws_2\" x,\"qws_feat\" y" + " WHERE x.IP=y.IP"
				+ " ORDER BY WSID,COUNTRYCODE";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String wsid = rs.getString("WSID");
				String countryCode = rs.getString("COUNTRYCODE");

				if (!map.containsKey(wsid)) {
					Map<String, Quality> mapChild = new LinkedHashMap<String, Quality>();

					Quality quality = new Quality();
					quality.rtList = new ArrayList<Double>();
					quality.reList = new ArrayList<Double>();
					quality.rtList.add(Double.valueOf(rs.getString("RT")));
					String result = rs.getString("RESULT");
					if (result.equals("OK")) {
						quality.reList.add(1.0);
					} else {
						quality.reList.add(0.0);
					}
					mapChild.put(countryCode, quality);

					map.put(wsid, mapChild);
				} else {
					Map<String, Quality> mapChild = map.get(wsid);
					if (mapChild.containsKey(countryCode)) {
						Quality quality = mapChild.get(countryCode);
						quality.rtList.add(Double.valueOf(rs.getString("RT")));
						String result = rs.getString("RESULT");
						if (result.equals("OK")) {
							quality.reList.add(1.0);
						} else {
							quality.reList.add(0.0);
						}
					} else {
						Quality quality = new Quality();
						quality.rtList = new ArrayList<Double>();
						quality.reList = new ArrayList<Double>();
						quality.rtList.add(Double.valueOf(rs.getString("RT")));
						String result = rs.getString("RESULT");
						if (result.equals("OK")) {
							quality.reList.add(1.0);
						} else {
							quality.reList.add(0.0);
						}
						mapChild.put(countryCode, quality);
					}
				}
			}// while
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
		return map;
	}

	/**
	 * 计算 国家划分 数据
	 * 
	 * @param map
	 * @return
	 */
	public static List<QosCalculateResult2> getQosCalculateResult2List(
			Map<String, Map<String, Quality>> map) {

		// 返回结果list
		List<QosCalculateResult2> list = new ArrayList<QosCalculateResult2>();

		/* list计算 */
		DecimalFormat df = new DecimalFormat("0.00");
		for (String wsid : map.keySet()) {
			Map<String, Quality> mapChild = map.get(wsid);
			for (String countryCode : mapChild.keySet()) {
				Quality quality = mapChild.get(countryCode);
				QosCalculateResult2 qosCalculateResult2 = new QosCalculateResult2();

				qosCalculateResult2.setWsid(wsid);
				qosCalculateResult2.setCountryCode(countryCode);

				List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
				//rt的三个云特征值
				qosCalculateResult2.setRtEx(df.format(
						CloudModelMath.ExCalculate(list1)).toString());
				qosCalculateResult2.setRtEn(df.format(
						CloudModelMath.EnCalculate(list1)).toString());
				qosCalculateResult2.setRtHe(df.format(
						CloudModelMath.HeCalculate(list1)).toString());					
				//从list1中取出最小最大值
				Collections.sort(list1);
				qosCalculateResult2.setMinRt(list1.get(0).toString());
				qosCalculateResult2.setMaxRt(list1.get(list1.size()-1).toString());			
				//从list1取出[μ-3σ, μ+3σ]
				qosCalculateResult2.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
				qosCalculateResult2.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
				

				List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换			
				//re可靠性（就是求reList的期望）
	            qosCalculateResult2.setRe(df.format(CloudModelMath.ExCalculate(list2)));

				list.add(qosCalculateResult2);

			}
		}

		return list;

	}

	/**
	 * 获取 州级划分 数据
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Quality>>> getQosCalculateMap3FromDatabase() {
		// 存储从数据库得到的直接数据; 第一个键是wsid，第二个键是国家，第三个键是省级
		Map<String, Map<String, Map<String, Quality>>> map //
		= new LinkedHashMap<String, Map<String, Map<String, Quality>>>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		/*
		 * SELECT x.WSID,y.COUNTRYCODE,y.STATE,x.RT,x.RESULT FROM "qws_2"
		 * x,"qws_feat" y WHERE x.IP=y.IP AND y.STATE IS NOT NULL ORDER BY
		 * WSID,COUNTRYCODE,STATE
		 */
		String sql = "SELECT x.WSID,y.COUNTRYCODE,y.STATE,x.RT,x.RESULT" //
				+ " FROM \"qws_2\" x,\"qws_feat\" y" //
				+ " WHERE x.IP=y.IP AND y.STATE IS NOT NULL" //
				+ " ORDER BY WSID,COUNTRYCODE,STATE";

		try {
			conn = DBConnect.OracleConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String wsid = rs.getString("WSID");
				String countryCode = rs.getString("COUNTRYCODE");
				String state = rs.getString("STATE");

				/*
				 * 0 0 01 0 01 1 01 1 1
				 */
				if (!map.containsKey(wsid)) { // 一级if else 判断wsid
					Map<String, Map<String, Quality>> mapChild = new LinkedHashMap<String, Map<String, Quality>>();
					Map<String, Quality> mapGrandSon = new LinkedHashMap<String, Quality>();

					Quality quality = new Quality();
					quality.rtList = new ArrayList<Double>();
					quality.reList = new ArrayList<Double>();
					quality.rtList.add(Double.valueOf(rs.getString("RT")));
					String result = rs.getString("RESULT");
					if (result.equals("OK")) {
						quality.reList.add(1.0);
					} else {
						quality.reList.add(0.0);
					}

					mapGrandSon.put(state, quality);
					mapChild.put(countryCode, mapGrandSon);
					map.put(wsid, mapChild);
				} else {
					Map<String, Map<String, Quality>> mapChild = map.get(wsid);
					if (!mapChild.containsKey(countryCode)) { // 二级if else 判断国家
						Map<String, Quality> mapGrandSon = new LinkedHashMap<String, Quality>();

						Quality quality = new Quality();
						quality.rtList = new ArrayList<Double>();
						quality.reList = new ArrayList<Double>();
						quality.rtList.add(Double.valueOf(rs.getString("RT")));
						String result = rs.getString("RESULT");
						if (result.equals("OK")) {
							quality.reList.add(1.0);
						} else {
							quality.reList.add(0.0);
						}

						mapGrandSon.put(state, quality);
						mapChild.put(countryCode, mapGrandSon);

					} else {
						Map<String, Quality> mapGrandSon = mapChild
								.get(countryCode);
						if (!mapGrandSon.containsKey(state)) { // 三级if else 判断省级
							Quality quality = new Quality();
							quality.rtList = new ArrayList<Double>();
							quality.reList = new ArrayList<Double>();
							quality.rtList.add(Double.valueOf(rs
									.getString("RT")));
							String result = rs.getString("RESULT");
							if (result.equals("OK")) {
								quality.reList.add(1.0);
							} else {
								quality.reList.add(0.0);
							}

							mapGrandSon.put(state, quality);

						} else {
							Quality quality = mapGrandSon.get(state);
							quality.rtList.add(Double.valueOf(rs
									.getString("RT")));
							String result = rs.getString("RESULT");
							if (result.equals("OK")) {
								quality.reList.add(1.0);
							} else {
								quality.reList.add(0.0);
							}

						}// 三级if else 判断省

					}// 二级if else 判断国

				}// 一级if else 判断wsid
			}// while
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
		return map;
	}

	/**
	 * 计算州级划分数据
	 * @param map
	 * @return
	 */
	public static List<QosCalculateResult3> getQosCalculateResult3List(
			Map<String, Map<String, Map<String, Quality>>> map) {

		// 返回集合
		List<QosCalculateResult3> list = new ArrayList<QosCalculateResult3>();

		/* list计算 */
		DecimalFormat df = new DecimalFormat("0.00");
		for (String wsid : map.keySet()) {
			Map<String, Map<String, Quality>> mapChild = map.get(wsid);
			for (String countryCode : mapChild.keySet()) {
				Map<String, Quality> mapGrandSon = mapChild.get(countryCode);
				for (String state : mapGrandSon.keySet()) {
					Quality quality = mapGrandSon.get(state);

					QosCalculateResult3 qosCalculateResult3 = new QosCalculateResult3();

					qosCalculateResult3.setWsid(wsid);
					qosCalculateResult3.setCountryCode(countryCode);
					qosCalculateResult3.setState(state);

					List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
					//rt的三个云特征值
					qosCalculateResult3.setRtEx(df.format(
							CloudModelMath.ExCalculate(list1)).toString());
					qosCalculateResult3.setRtEn(df.format(
							CloudModelMath.EnCalculate(list1)).toString());
					qosCalculateResult3.setRtHe(df.format(
							CloudModelMath.HeCalculate(list1)).toString());					
					//从list1中取出最小最大值
					Collections.sort(list1);
					qosCalculateResult3.setMinRt(list1.get(0).toString());
					qosCalculateResult3.setMaxRt(list1.get(list1.size()-1).toString());			
					//从list1取出[μ-3σ, μ+3σ]
					qosCalculateResult3.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
					qosCalculateResult3.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
					

					List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换			
					//re可靠性（就是求reList的期望）
		            qosCalculateResult3.setRe(df.format(CloudModelMath.ExCalculate(list2)));

					list.add(qosCalculateResult3);

				}
			}
		}

		return list;
	}

}
