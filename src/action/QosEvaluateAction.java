package action;

import java.util.ArrayList;
import java.util.List;

import model.CvCalculateResult;
import model.QosCalculateResult;
import model.QosEvaluateResult;
import service.QosCalculateService;

public class QosEvaluateAction {
	private String modelName;
	
	private String rtEnThreshold;
	private String rtHeThreshold;
	private String reThresholdOfCloud;
	
	private String rtCvThreshold;
	private String reThresholdOfCv;
    //--
	private List<QosEvaluateResult> list; // for cloud
	private int wsidSize;
	private int noAreaCount;
	private int countryAreaCount;
	private int provinceAreaCount;

	public String execute() throws Exception{
		

		list = new ArrayList<QosEvaluateResult>();
		if(modelName.equals("model0")){
			 List<QosCalculateResult> list1 = QosCalculateService.getQosResult();
			 List<QosCalculateResult> list2 = QosCalculateService.getQosResult2();
			 List<QosCalculateResult> list3 = QosCalculateService.getQosResult3();
			 
			 wsidSize=list1.size();
			 
			 for(int i=0;i<list1.size();i++){
				 QosEvaluateResult qosEvaluateResult =new QosEvaluateResult();
				 qosEvaluateResult.setWsid(list1.get(i).getWsid());
				if( Double.valueOf(list1.get(i).getRtEn())>Double.valueOf(rtEnThreshold)
						||Double.valueOf(list1.get(i).getRtHe())>Double.valueOf(rtHeThreshold)
						||Double.valueOf(list1.get(i).getRe())>Double.valueOf(reThresholdOfCloud)
					)
				{
					qosEvaluateResult.setNoArea("不稳定");
				}else{
					qosEvaluateResult.setNoArea("稳定");
					noAreaCount++;
				}
				
				if( Double.valueOf(list2.get(i).getRtEn())>Double.valueOf(rtEnThreshold)
						||Double.valueOf(list2.get(i).getRtHe())>Double.valueOf(rtHeThreshold)
						||Double.valueOf(list2.get(i).getRe())>Double.valueOf(reThresholdOfCloud)
					)
				{
					qosEvaluateResult.setCountryArea("不稳定");
				}else{
					qosEvaluateResult.setCountryArea("稳定");
					countryAreaCount++;
				}
				
				if( Double.valueOf(list3.get(i).getRtEn())>Double.valueOf(rtEnThreshold)
						||Double.valueOf(list3.get(i).getRtHe())>Double.valueOf(rtHeThreshold)
						||Double.valueOf(list3.get(i).getRe())>Double.valueOf(reThresholdOfCloud)
					)
				{
					qosEvaluateResult.setProvinceArea("不稳定");
				}else{
					qosEvaluateResult.setProvinceArea("稳定");
					provinceAreaCount++;
				}
				
				list.add(qosEvaluateResult);
			 }
		}else if (modelName.equals("model1")) { //cv模型
			 List<CvCalculateResult> list1 = QosCalculateService.getCvResult();
			 List<CvCalculateResult> list2 = QosCalculateService.getCvResult2();
			 List<CvCalculateResult> list3 = QosCalculateService.getCvResult3();
			 
			 wsidSize=list1.size();
			 
			 for(int i=0;i<list1.size();i++){
				 QosEvaluateResult qosEvaluateResult =new QosEvaluateResult();
				 qosEvaluateResult.setWsid(list1.get(i).getWsid());
				if( Double.valueOf(list1.get(i).getRtCv())>Double.valueOf(rtCvThreshold)
						||Double.valueOf(list1.get(i).getRe())>Double.valueOf(reThresholdOfCv)
						)
				{
					qosEvaluateResult.setNoArea("不稳定");
				}else{
					qosEvaluateResult.setNoArea("稳定");
					noAreaCount++;
				}
				
				if( Double.valueOf(list2.get(i).getRtCv())>Double.valueOf(rtCvThreshold)
						||Double.valueOf(list2.get(i).getRe())>Double.valueOf(reThresholdOfCv))
				{
					qosEvaluateResult.setCountryArea("不稳定");
				}else{
					qosEvaluateResult.setCountryArea("稳定");
					countryAreaCount++;
				}
				
				if( Double.valueOf(list3.get(i).getRtCv())>Double.valueOf(rtCvThreshold)
						||Double.valueOf(list3.get(i).getRe())>Double.valueOf(reThresholdOfCv))
				{
					qosEvaluateResult.setProvinceArea("不稳定");
				}else{
					qosEvaluateResult.setProvinceArea("稳定");
					provinceAreaCount++;
				}
				
				list.add(qosEvaluateResult);
			 }
		}
				 
		 
		return "success";
	}

	
	




	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setRtEnThreshold(String rtEnThreshold) {
		this.rtEnThreshold = rtEnThreshold;
	}

	public void setRtHeThreshold(String rtHeThreshold) {
		this.rtHeThreshold = rtHeThreshold;
	}

	public void setReThresholdOfCloud(String reThresholdOfCloud) {
		this.reThresholdOfCloud = reThresholdOfCloud;
	}

	public void setRtCvThreshold(String rtCvThreshold) {
		this.rtCvThreshold = rtCvThreshold;
	}

	public void setReThresholdOfCv(String reThresholdOfCv) {
		this.reThresholdOfCv = reThresholdOfCv;
	}

	//----

	public List<QosEvaluateResult> getList() {
		return list;
	}
	

	public int getWsidSize() {
		return wsidSize;
	}

	public int getNoAreaCount() {
		return noAreaCount;
	}

	public int getCountryAreaCount() {
		return countryAreaCount;
	}

	public int getProvinceAreaCount() {
		return provinceAreaCount;
	}
	
	
	

}
