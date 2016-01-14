package util;

/**
 * 手动操作 更新存储QoS计算结果的表
 * */
public class UpdateData {

	public static void main(String[] args) {

		// 不划分的QOS计算结果存入数据库：101
		QosCalculateResultToDatabase.updateQosResult1();

		// 国家划分的QOS计算结果存入数据库：2727
		QosCalculateResultToDatabase.updateQosResult2();

		// 省级划分的QOS计算结果存入数据库：101*27*省级数量
		QosCalculateResultToDatabase.updateQosResult3();

		CvCalculateResultToDatabase.updateCvResult1();
		CvCalculateResultToDatabase.updateCvResult2();
		CvCalculateResultToDatabase.updateCvResult3();
	}

}
