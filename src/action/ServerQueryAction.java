package action;

import java.sql.*;
import java.util.*;

import model.ServerQueryResult;
import dao.DBConnect;


public class ServerQueryAction extends PageActionBase {
	
	private String serverId;
	//--
	private List<ServerQueryResult> list;
	
	public String execute() throws Exception{	
		/**存储集合*/
		list=new ArrayList<ServerQueryResult>();
		
		
		/*数据库操作*/
		Connection conn = null;
		Statement st = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		/* SQL语句 */
		/*查询pageSize*/
		String sql=" SELECT COUNT(DISTINCT IP) PAGESIZE"+
				" FROM \"qws_2\""+
				" WHERE WSID="+serverId;
		
		/*按当前页查询*/
		String sql2 =" SELECT IP,CNAME,NUM"+
				" FROM("+
				" SELECT row_number() over(order by COUNT(x.IP) desc) curnum"+
				" ,x.IP,y.CNAME,COUNT(x.IP) NUM"+
				" FROM \"qws_2\" x,\"qws_feat\" y"+
				" WHERE WSID="+serverId+
				" AND x.IP=y.IP"+
				" GROUP BY x.IP,y.CNAME)"+
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
				String userId = rs2.getString("IP");
				String userCountry=rs2.getString("CNAME");
				int useCount=Integer.valueOf(rs2.getString("NUM"));
				ServerQueryResult serverQueryResult=new ServerQueryResult();
				serverQueryResult.setUseCount(useCount);
				serverQueryResult.setUserCountry(userCountry);
				serverQueryResult.setUserId(userId);
				list.add(serverQueryResult);				
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

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	

	public List<ServerQueryResult> getList() {
		return list;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

}
