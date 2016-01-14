package action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UserQueryResult;
import dao.DBConnect;

public class UserQueryAction extends PageActionBase{
	private String userIp;
	//--
	private List<UserQueryResult> list;
	
	public String execute() throws Exception{
		/**存储集合*/
		list=new ArrayList<UserQueryResult>();
		
		
		/*数据库操作*/
		Connection conn = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		/* SQL语句 */
		String sql = " SELECT COUNT(DISTINCT WSID) PAGESIZE "+
				" FROM \"qws_2\""+
				" WHERE IP=\'"+userIp+"\'";
		
		/*按当前页查询*/
		String sql2 =" SELECT WSID,NUM"+
				" FROM("+
				" SELECT row_number() over(order by COUNT(WSID) desc) curnum"+
				" ,WSID,COUNT(WSID) NUM"+
				" FROM \"qws_2\""+
				" WHERE IP=\'"+userIp+"\'"+
				" GROUP BY WSID)"+
				" WHERE curnum>"+(curPage-1)*pageCount+
				" AND curnum<="+(curPage*pageCount);

		try {
			/*执行查询 */
			conn=DBConnect.OracleConnection();
			st=conn.createStatement();
			rs1 = st.executeQuery(sql);
			while (rs1.next()) {
				pageSize=Integer.valueOf(rs1.getString("PAGESIZE"));

			
				if(pageSize%pageCount==0)
					totalPage=pageSize/pageCount;
				else {
					totalPage=pageSize/pageCount+1;
				}
			}
						
			rs2 = st.executeQuery(sql2);
			
			while (rs2.next()) {
				String serverId = rs2.getString("WSID");
				int useCount=Integer.valueOf(rs2.getString("NUM"));
				UserQueryResult userQueryResult=new UserQueryResult();
				userQueryResult.setServerId(serverId);
				userQueryResult.setUseCount(useCount);
				list.add(userQueryResult);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			   try {
				    rs1.close();
				    rs2.close();
				    st.close();
				    conn.close();
				    } catch (SQLException e) {
				     e.printStackTrace();
				    }				    
			}
		

		
		return "success";
	}



	public List<UserQueryResult> getList() {
		return list;
	}



	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}



	
}
