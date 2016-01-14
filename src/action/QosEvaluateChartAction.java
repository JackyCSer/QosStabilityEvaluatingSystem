package action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import service.QosCalculateService;
import service.QosEvaluateChartService;

public class QosEvaluateChartAction {

	private String thresholdSelect;
	private Double inputBegin;
	private Double inputEnd;
	private Double inputInterval;
	// ---
	private List<Integer> list1;
	private List<Integer> list2;
	private List<Integer> list3;
	private List<String> xData;

	/**
	 * 第一个图表的处理程序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		getXData();
		
		list1 = QosEvaluateChartService.getList(
				QosCalculateService.getQosResult(), xData, thresholdSelect);
		list2 = QosEvaluateChartService.getList(
				QosCalculateService.getQosResult2(), xData, thresholdSelect);
		list3 = QosEvaluateChartService.getList(
				QosCalculateService.getQosResult3(), xData, thresholdSelect);

		return "success";
	}

	/**
	 * 第二个图表的处理程序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute1() throws Exception {
		getXData();
		
		list1 = QosEvaluateChartService.getCvList(
				QosCalculateService.getCvResult(), xData, thresholdSelect);
		list2 = QosEvaluateChartService.getCvList(
				QosCalculateService.getCvResult2(), xData, thresholdSelect);
		list3 = QosEvaluateChartService.getCvList(
				QosCalculateService.getCvResult3(), xData, thresholdSelect);
		
		return "success";
	}
	
	
	

	/**
	 * 图表x坐标的处理
	 */
	public void getXData() {
		DecimalFormat df = new DecimalFormat("0.00");

		int xCount;
		xData = new ArrayList<String>();

		double intervalSum =Double.valueOf(df.format(inputEnd - inputBegin)) ;
		int tmp = ((int) (intervalSum * 100)) % ((int) (inputInterval * 100));
		if (tmp == 0) {
			xCount = (int) ((intervalSum) / (inputInterval));					
			xData.add(inputBegin.toString());
			for (int i = 1; i <= xCount; i++) {
				xData.add(df.format(inputBegin + i * inputInterval));
			}
		} else {
			xCount = (int) (intervalSum / inputInterval);
			xData.add(inputBegin.toString());
			for (int i = 1; i <= xCount; i++) {
				xData.add(df.format(inputBegin + i * inputInterval));
			}
			xData.add(inputEnd.toString());
		}
	}

	/**
	 * get && set
	 */
	public List<Integer> getList1() {
		return list1;
	}

	public List<Integer> getList2() {
		return list2;
	}

	public List<Integer> getList3() {
		return list3;
	}

	public List<String> getxData() {
		return xData;
	}

	public void setThresholdSelect(String thresholdSelect) {
		this.thresholdSelect = thresholdSelect;
	}

	public void setInputBegin(Double inputBegin) {
		this.inputBegin = inputBegin;
	}

	public void setInputEnd(Double inputEnd) {
		this.inputEnd = inputEnd;
	}

	public void setInputInterval(Double inputInterval) {
		this.inputInterval = inputInterval;
	}

}
