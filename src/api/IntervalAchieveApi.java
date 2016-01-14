package api;

import java.util.ArrayList;
import java.util.List;

import model.CvCalculateResult;
import model.CvCalculateResult2;
import model.CvCalculateResult3;
import model.QosCalculateResult;
import model.QosCalculateResult2;
import model.QosCalculateResult3;

/**
 * 调用相应model的getMinRt等方法即可获得最小最大值区间
 */
public class IntervalAchieveApi {

	/**
	 * 云模型下 获取不划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 *            -rt熵
	 * @param d2
	 *            -rt超熵
	 * @param d3
	 *            -re
	 * @return 稳定服务的集合(包含最大值最小值)
	 */
	public static List<QosCalculateResult> getIntervalOfStableServiceByCloud(
			double d1, double d2, double d3) {
		List<QosCalculateResult> list = QosCalculateServiceOfInterval
				.getQosResult();// 获取用于比较的数据
		List<QosCalculateResult> listResult = new ArrayList<QosCalculateResult>();// 用户返回的稳定数据

		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtEn()) <= d1
					&& Double.valueOf(list.get(i).getRtHe()) <= d2
					&& Double.valueOf(list.get(i).getRe()) <= d3
				) {
				listResult.add(list.get(i));
			}
		}
		return listResult;

	}

	/**
	 * 云模型下 获取国家划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 */
	public static List<QosCalculateResult2> getIntervalOfStableServiceByCloud2(
			double d1, double d2, double d3) {
		List<QosCalculateResult2> list = QosCalculateServiceOfInterval
				.getQosResult2();// 获取用于比较的数据
		List<QosCalculateResult2> listResult = new ArrayList<QosCalculateResult2>();// 用户返回的稳定数据
		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtEn()) <= d1
					&& Double.valueOf(list.get(i).getRtHe()) <= d2
					&& Double.valueOf(list.get(i).getRe()) <= d3
		) {
				listResult.add(list.get(i));
			}
		}

		return listResult;
	}

	/**
	 * 云模型下 获取州级划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 */
	public static List<QosCalculateResult3> getIntervalOfStableServiceByCloud3(
			double d1, double d2, double d3) {
		List<QosCalculateResult3> list = QosCalculateServiceOfInterval
				.getQosResult3();// 获取用于比较的数据
		List<QosCalculateResult3> listResult = new ArrayList<QosCalculateResult3>();// 用户返回的稳定数据
		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtEn()) <= d1
					&& Double.valueOf(list.get(i).getRtHe()) <= d2
					&& Double.valueOf(list.get(i).getRe()) <= d3
			) {
				listResult.add(list.get(i));
			}
		}

		return listResult;
	}

	/**
	 * cv模型下 获取不划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static List<CvCalculateResult> getIntervalOfStableServiceByCv(
			double d1, double d2) {
		List<CvCalculateResult> list = QosCalculateServiceOfInterval
				.getCvResult();
		List<CvCalculateResult> listResult = new ArrayList<CvCalculateResult>();// 用户返回的稳定数据

		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtCv()) <= d1
					&& Double.valueOf(list.get(i).getRe()) <= d2) {
				listResult.add(list.get(i));
			}
		}

		return listResult;
	}

	/**
	 * cv模型下 获取国家划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static List<CvCalculateResult2> getIntervalOfStableServiceByCv2(
			double d1, double d2) {
		List<CvCalculateResult2> list = QosCalculateServiceOfInterval
				.getCvResult2();
		List<CvCalculateResult2> listResult = new ArrayList<CvCalculateResult2>();// 用户返回的稳定数据

		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtCv()) <= d1
					&& Double.valueOf(list.get(i).getRe()) <= d2) {
				listResult.add(list.get(i));
			}
		}

		return listResult;
	}

	/**
	 * cv模型下 获取州级划分的 稳定服务的 所有属性的 最大值最小值
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static List<CvCalculateResult3> getIntervalOfStableServiceByCv3(
			double d1, double d2) {
		List<CvCalculateResult3> list = QosCalculateServiceOfInterval
				.getCvResult3();
		List<CvCalculateResult3> listResult = new ArrayList<CvCalculateResult3>();// 用户返回的稳定数据

		for (int i = 0; i < list.size(); i++) {

			if (Double.valueOf(list.get(i).getRtCv()) <= d1
					&& Double.valueOf(list.get(i).getRe()) <= d2) {
				listResult.add(list.get(i));
			}
		}

		return listResult;
	}
}
