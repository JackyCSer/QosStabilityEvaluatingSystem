package action;

public class PageActionBase {
	protected int pageCount;
	protected int curPage;
	//--
	protected int pageSize;
	protected int totalPage;
	
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

}
