package action;


import java.util.*;

import model.CvCalculateResult;
import model.QosCalculateResult;
import service.QosCalculateService;




public class QosCalculateAction{
	private String  modelName;
	private String areaName;
	//--
	private List<QosCalculateResult> list;
	private List<CvCalculateResult> list2;
	
	public String execute() throws Exception{
		if(modelName.equals("model0")){
			if(areaName.equals("area0")){
				list = QosCalculateService.getQosResult();
			}
			else if(areaName.equals("area1")){
				list = QosCalculateService.getQosResult2();
			}else{ //area2
				list = QosCalculateService.getQosResult3();
			}
		}else {
			if(areaName.equals("area0")){
				list2=QosCalculateService.getCvResult();
			}
			else if(areaName.equals("area1")){
				list2=QosCalculateService.getCvResult2();
			}else{ //area2
				list2=QosCalculateService.getCvResult3();
			}
		
		}

		
		/*System.out.println(this.getClass());*/
		return "success";
	}
	

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public List<QosCalculateResult> getList() {
		return list;
	}


	public List<CvCalculateResult> getList2() {
		return list2;
	}
	

}
