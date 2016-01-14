package util;

import java.util.Map;

import model.Quality;
import api.IntervalAchieveApi;


/**
 * 串行 时间 测试
 * */
public class TimeTest {

	/**
	 * 云模型 串行计算 执行时间
	 */
	public void cloudExecuteTime() {

		/* 不划分 计算时间 */
		Map<String, Quality> map = QosCalculateResultToList.getQosCalculateMapFromDatabase();
		
		float sum = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			QosCalculateResultToList.getQosCalculateResultList(map);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum += resultTime;
		}

		System.out.println("不划分计算时间："+sum / 10 /1000+ " s");
		
		/* 国家划分计算时间 */
		Map<String, Map<String, Quality>> map2 = QosCalculateResultToList.getQosCalculateMap2FromDatabase();
		
		float sum2 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			QosCalculateResultToList.getQosCalculateResult2List(map2);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum2 += resultTime;
		}
		System.out.println("国家划分计算时间："+sum2 / 10 /1000+ " s");
		
		
		/* 省级划分计算时间 (有数据去除无法参与比较)*/
		Map<String, Map<String, Map<String, Quality>>> map3 = QosCalculateResultToList.getQosCalculateMap3FromDatabase();
		
		float sum3 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			QosCalculateResultToList.getQosCalculateResult3List(map3);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum3 += resultTime;
		}
		System.out.println("州划分计算时间："+sum3 / 10 /1000+ " s");

	}

	/**
	 * cv 串行计算 执行时间
	 */
	public void cvExecuteTime(){
		/* 不划分 计算时间 */
		Map<String, Quality> map = QosCalculateResultToList.getQosCalculateMapFromDatabase();
		
		float sum = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			CvCalculateResultToList.getCvCalculateResultList(map);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum += resultTime;
		}

		System.out.println("cv不划分计算时间："+sum / 10 /1000+ " s");
		
		/* 国家划分计算时间 */
		Map<String, Map<String, Quality>> map2 = QosCalculateResultToList.getQosCalculateMap2FromDatabase();
		
		float sum2 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			CvCalculateResultToList.getCvCalculateResult2List(map2);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum2 += resultTime;
		}
		System.out.println("cv国家划分计算时间："+sum2 / 10 /1000+ " s");
		
		
		/* 省级划分计算时间 (有数据去除无法参与比较)*/
		Map<String, Map<String, Map<String, Quality>>> map3 = QosCalculateResultToList.getQosCalculateMap3FromDatabase();
		
		float sum3 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			CvCalculateResultToList.getCvCalculateResult3List(map3);
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			sum3 += resultTime;
		}
		System.out.println("cv州划分计算时间："+sum3 / 10 /1000+ " s");
	}
	
	
	/**
	 *  云模型 串行稳定评估 执行时间
	 */
	public void cloudEvaExecuteTime(){
		
		float sum = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCloud(1000,1000, 0.05);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum += resultTime;
		}
		System.out.println("云模型不划分评估时间："+sum / 10 /1000+ " s");
		
		float sum2 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCloud2(1000,1000, 0.05);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum2 += resultTime;
		}
		System.out.println("云模型国家划分评估时间："+sum2 / 10 /1000+ " s");
		
		float sum3 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCloud3(1000,1000, 0.05);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum3 += resultTime;
		}
		System.out.println("云模型州划分评估时间："+sum3 / 10 /1000+ " s");
	}
	
	
	/**
	 *  cv 串行稳定评估 执行时间
	 */
	public void cvEvaExecuteTime(){
		float sum = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCv(100,10);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum += resultTime;
		}
		System.out.println("Cv不划分评估时间："+sum / 10 /1000+ " s");
		
		float sum2 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCv2(100,10);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum2 += resultTime;
		}
		System.out.println("Cv国家划分评估时间："+sum2 / 10 /1000+ " s");
		
		float sum3 = 0;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();//--↓--
			IntervalAchieveApi.getIntervalOfStableServiceByCv3(100,10);			
			long endTime = System.currentTimeMillis();//--↑--
			long resultTime = endTime - startTime;
			sum3 += resultTime;
		}
		System.out.println("Cv州划分评估时间："+sum3 / 10 /1000+ " s");
	}
	
	
	
	/**
	 * 启动程序
	 */
	public static void main(String[] args) {
		new TimeTest().cloudExecuteTime();
		new TimeTest().cvExecuteTime();
		new TimeTest().cloudEvaExecuteTime();
		new TimeTest().cvEvaExecuteTime();
	}

}
