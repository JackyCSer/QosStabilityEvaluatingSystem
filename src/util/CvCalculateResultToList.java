package util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import model.CvCalculateResult;
import model.CvCalculateResult2;
import model.CvCalculateResult3;
import model.Quality;

public class CvCalculateResultToList {

	public static List<CvCalculateResult> getCvCalculateResultList(
			Map<String, Quality> map) {

		// 返回结果list
		List<CvCalculateResult> list = new ArrayList<CvCalculateResult>();

		DecimalFormat df = new DecimalFormat("0.00");

		for (String wsid : map.keySet()) {
			Quality quality = map.get(wsid);
			CvCalculateResult cvCalculateResult = new CvCalculateResult();

			cvCalculateResult.setWsid(wsid);
			
			List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
			cvCalculateResult.setRtCv(df.format(
					CloudModelMath.CvCalculate(list1)));
			//从list1中取出最小最大值
			Collections.sort(list1);
			cvCalculateResult.setMinRt(list1.get(0).toString());
			cvCalculateResult.setMaxRt(list1.get(list1.size()-1).toString());
			//从list1取出[μ-3σ, μ+3σ]
			cvCalculateResult.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
			cvCalculateResult.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
			
			List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换
			//re可靠性（就是求reList的期望）
			cvCalculateResult.setRe(df.format(CloudModelMath.ExCalculate(list2)));
			
			list.add(cvCalculateResult);
		}

		return list;
	}

	public static List<CvCalculateResult2> getCvCalculateResult2List(
			Map<String, Map<String, Quality>> map) {

		// 返回结果list
		List<CvCalculateResult2> list = new ArrayList<CvCalculateResult2>();

		/* list计算 */
		DecimalFormat df = new DecimalFormat("0.00");
		for (String wsid : map.keySet()) {
			Map<String, Quality> mapChild = map.get(wsid);
			for (String countryCode : mapChild.keySet()) {

				Quality quality = mapChild.get(countryCode);
				CvCalculateResult2 cvCalculateResult2 = new CvCalculateResult2();

				cvCalculateResult2.setWsid(wsid);
				cvCalculateResult2.setCountryCode(countryCode);

				
				List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
				cvCalculateResult2.setRtCv(df.format(
						CloudModelMath.CvCalculate(list1)));
				//从list1中取出最小最大值
				Collections.sort(list1);
				cvCalculateResult2.setMinRt(list1.get(0).toString());
				cvCalculateResult2.setMaxRt(list1.get(list1.size()-1).toString());
				//从list1取出[μ-3σ, μ+3σ]
				cvCalculateResult2.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
				cvCalculateResult2.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
				
				List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换
				//re可靠性（就是求reList的期望）
				cvCalculateResult2.setRe(df.format(CloudModelMath.ExCalculate(list2)));
				


				list.add(cvCalculateResult2);
			}
		}

		return list;

	}

	public static List<CvCalculateResult3> getCvCalculateResult3List(
			Map<String, Map<String, Map<String, Quality>>> map) {

		// 返回集合
		List<CvCalculateResult3> list = new ArrayList<CvCalculateResult3>();

		/* list计算 */
		DecimalFormat df = new DecimalFormat("0.00");
		for (String wsid : map.keySet()) {
			Map<String, Map<String, Quality>> mapChild = map.get(wsid);
			for (String countryCode : mapChild.keySet()) {
				Map<String, Quality> mapGrandSon = mapChild.get(countryCode);
				for (String state : mapGrandSon.keySet()) {
					Quality quality = mapGrandSon.get(state);

					CvCalculateResult3 cvCalculateResult3 = new CvCalculateResult3();

					cvCalculateResult3.setWsid(wsid);
					cvCalculateResult3.setCountryCode(countryCode);
					cvCalculateResult3.setState(state);

					
					List<Double> list1 = CloudModelMath.ListToRtList(quality.rtList);
					cvCalculateResult3.setRtCv(df.format(
							CloudModelMath.CvCalculate(list1)));
					//从list1中取出最小最大值
					Collections.sort(list1);
					cvCalculateResult3.setMinRt(list1.get(0).toString());
					cvCalculateResult3.setMaxRt(list1.get(list1.size()-1).toString());
					//从list1取出[μ-3σ, μ+3σ]
					cvCalculateResult3.setPautaLeftRt(df.format(CloudModelMath.PautaLeft(list1)));
					cvCalculateResult3.setPautaRightRt(df.format(CloudModelMath.PautaRight(list1)));
					
					List<Double> list2 = quality.reList;// 可靠性的数据集合需要分组转换
					//re可靠性（就是求reList的期望）
					cvCalculateResult3.setRe(df.format(CloudModelMath.ExCalculate(list2)));
				
					list.add(cvCalculateResult3);

				}
			}
		}

		return list;
	}
}
