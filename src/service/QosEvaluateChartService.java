package service;

import java.util.ArrayList;
import java.util.List;

import model.CvCalculateResult;
import model.QosCalculateResult;

public class QosEvaluateChartService {
	
	/**
	 * 获取第一个图表(云模型)的y坐标值
	 * @param list
	 * @param xData
	 * @param thresholdSelect
	 * @return
	 */
	public static List<Integer> getList(List<QosCalculateResult> list,List<String> xData,String thresholdSelect){
		List<Integer> listResult=new ArrayList<Integer>();
		
		for(int i=0;i<xData.size();i++){
			String str=xData.get(i);
			int num = getCount(str, thresholdSelect, list);
			listResult.add(num);
		}
		return listResult;
		
	}
	
	/**
	 * 获取第二个图表(cv)的y坐标值
	 * @param list
	 * @param xData
	 * @param thresholdSelect
	 * @return
	 */
	public static List<Integer> getCvList(List<CvCalculateResult> list,List<String> xData,String thresholdSelect){
		List<Integer> listResult=new ArrayList<Integer>();
		
		for(int i=0;i<xData.size();i++){
			String str=xData.get(i);
			int num = getCvCount(str, thresholdSelect, list);
			listResult.add(num);
		}
		
		return listResult;
	}
	
	/**
	 * 获取对应每一个x坐标值的y坐标值(云模型)
	 * @param str
	 * @param thresholdSelect
	 * @param list
	 * @return
	 */
	public static Integer getCount(String str,String thresholdSelect,List<QosCalculateResult> list){
		
		int count=0;
		if(thresholdSelect.equals("threshold0")){
			for(int i=0;i<list.size();i++){
				if(Double.valueOf(list.get(i).getRtEn())<=Double.valueOf(str))
				{
					count++;
				}
			}
		}else if(thresholdSelect.equals("threshold1")){
			for(int i=0;i<list.size();i++){
				if(Double.valueOf(list.get(i).getRtHe())<=Double.valueOf(str))
				{
					count++;
				}
			}
		}else if(thresholdSelect.equals("threshold2")){
			for(int i=0;i<list.size();i++){
				if(Double.valueOf(list.get(i).getRe())<=Double.valueOf(str))
				{
					count++;
				}
			}
		}

		
		return count;
	}
	
	/**
	 *  获取对应每一个x坐标值的y坐标值(cv)
	 * @param str
	 * @param thresholdSelect
	 * @param list
	 * @return
	 */
	public static Integer getCvCount(String str,String thresholdSelect,List<CvCalculateResult> list){
		int count=0;
		
		if(thresholdSelect.equals("thresholdOfCv0")){
			for(int i=0;i<list.size();i++){
				if(Double.valueOf(list.get(i).getRtCv())<=Double.valueOf(str))
				{
					count++;
				}
			}
		}else{
			for(int i=0;i<list.size();i++){
				if(Double.valueOf(list.get(i).getRe())<=Double.valueOf(str))
				{
					count++;
				}
			}
		}
		return count;
	}

}
