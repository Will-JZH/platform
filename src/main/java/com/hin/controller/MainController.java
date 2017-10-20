package com.hin.controller;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hin.controller.model.SimulationFinished;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


@Controller
@RequestMapping("/main")
public class MainController
{
	/**
	 * 服务发布 上传文件 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/newDocument")
	public @ResponseBody Object UploadFile(
			@RequestParam(value="file", required=true) MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in");  
        //接收参数  
        Map<String, Object> resMap = new HashMap<String, Object>();  
        resMap.put("result", "error");  
  
        //解析器解析request的上下文  
        CommonsMultipartResolver multipartResolver =  
            new CommonsMultipartResolver(request.getSession().getServletContext());  
          //先判断request中是否包涵multipart类型的数据，  
        if(multipartResolver.isMultipart(request)){  
           //再将request中的数据转化成multipart类型的数据  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
            Iterator iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //这里的name为fileItem的alias属性值，相当于form表单中name  
                String name=(String)iter.next();  
                System.out.println("name:"+name);  
                //根据name值拿取文件  
//                MultipartFile file = multiRequest.getFile(name);  
                if(file != null){  
                    String fileName = file.getOriginalFilename();  
                    String path = "E:/owlsDocument/" + fileName;  
                    File localFile = new File(path);  
                    if(!localFile.getParentFile().exists()) {  
                         //如果目标文件所在的目录不存在，则创建父目录  
                         localFile.getParentFile().mkdirs();  
                         System.out.println("parent:"+localFile.getParentFile().getPath());  
                     }  
                    //写文件到本地  
                    try {  
                        file.transferTo(localFile);  
                    } catch (IOException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                        return resMap;  
                    }  
                }  
            }  
          }else {  
  
              return resMap;  
        }  
          resMap.put("result", "success");  
          return resMap;  
    }  
        
    
    /**
     * 服务发布 获得删除服务页面的内容
     * @return
     * @throws Exception
     */
    @RequestMapping("/getInfoFromRDF4J")
    public @ResponseBody ArrayList<HashSet<String>> getInfoFromRDF4J() throws Exception{
		ArrayList<HashSet<String>> resultSet = new ArrayList<HashSet<String>>();
		
		HashSet<String> arrEnterprise = new HashSet<String>();
		HashSet<String> arrMachine = new HashSet<String>();
		HashSet<String> arrType = new HashSet<String>();
		String rdf4jServer = "http://localhost:8080/rdf4j-server/";
		String repositoryID = "Enterprise";
		Repository repo = new HTTPRepository(rdf4jServer, repositoryID);
		repo.initialize();
	
		RepositoryConnection con = repo.getConnection();
			
		//查询企业信息	
		String queryEnterPrise = 
				"PREFIX products: <http://www.semanticweb.org/jzh/ontologies/2016/10/products#> " +
				"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?enterprise " +
				"WHERE {" +
				"      ?enterprise rdf:type ?b1 . " +
				"	   ?b1 owl:someValuesFrom products:企业基本信息 . " + 
				"	   products:企业 rdfs:subClassOf ?b1 . " +		
				"      }";
				
		String queryMachine = 
			"PREFIX products: <http://www.semanticweb.org/jzh/ontologies/2016/10/products#> " +
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"SELECT ?machine " +
			"WHERE {" +
			"      ?enterprise rdf:type ?b1 . " +
			"	   ?b1 owl:someValuesFrom products:企业基本信息 . " + 
			"	   products:企业 rdfs:subClassOf ?b1 . " +	
			"      ?enterprise products:拥有 ?type . " +
			"	   ?type rdf:type ?machine . " + 
			"	   ?machine rdfs:subClassOf products:机床 . " +		
			"      }";
		String queryType = 
			"PREFIX products: <http://www.semanticweb.org/jzh/ontologies/2016/10/products#> " +
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"SELECT ?type " +
			"WHERE {" +
			"      ?enterprise rdf:type ?b1 . " +
			"	   ?b1 owl:someValuesFrom products:企业基本信息 . " + 
			"	   products:企业 rdfs:subClassOf ?b1 . " +	
			"      ?enterprise products:拥有 ?type . " +
			"	   ?type rdf:type ?machine . " + 
			"	   ?machine rdfs:subClassOf products:机床 . " +		
			"      }";
		TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryEnterPrise);
		TupleQuery tupleQuery2 = con.prepareTupleQuery(QueryLanguage.SPARQL, queryMachine);
		TupleQuery tupleQuery3 = con.prepareTupleQuery(QueryLanguage.SPARQL, queryType);
		TupleQueryResult result = tupleQuery.evaluate();
		TupleQueryResult result2 = tupleQuery2.evaluate();
		TupleQueryResult result3 = tupleQuery3.evaluate();
		while (result.hasNext()) {  // iterate over the result
			BindingSet bindingSet = result.next();
			Value valueOfP = bindingSet.getValue("enterprise");
			arrEnterprise.add(valueOfP.toString().split("#")[1]);
		}
		while (result2.hasNext()) {  // iterate over the result
			BindingSet bindingSet2 = result2.next();
			Value valueOfMachine = bindingSet2.getValue("machine");
			if(!"机床".equals(valueOfMachine.toString().split("#")[1]))
			{
				arrMachine.add(valueOfMachine.toString().split("#")[1]);
			}
		}
		
		while (result3.hasNext()) {  // iterate over the result
			BindingSet bindingSet3 = result3.next();
			Value valueOfType = bindingSet3.getValue("type");
			arrType.add(valueOfType.toString().split("#")[1]);
		}
		
		resultSet.add(arrEnterprise);
		resultSet.add(arrMachine);
		resultSet.add(arrType);
		
		System.out.println(resultSet);
		return resultSet;
    }
	
	/**
	 * 服务组合 检索原子服务
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceSearch", method = RequestMethod.POST)
    public @ResponseBody ArrayList<HashMap<String, String>> getServiceInfo(
    		 @RequestBody Map<String,Object>  params) throws Exception{
		String serviceName = params.get("serviceName").toString();  
		String serviceType = params.get("serviceType").toString();
		ArrayList<HashMap<String, String>> resultSet = new ArrayList<HashMap<String, String>>();
		System.out.println(serviceName);
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码   
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="atomicService";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement(); 
		String sql = "";
		if (serviceType.equals("服务名称")){
			sql = "SELECT * FROM " + tableName + " WHERE serviceName LIKE \'" + serviceName + "%'";
		}
		else if (serviceType.equals("企业名称")){
			sql = "SELECT * FROM " + tableName + " WHERE serviceName LIKE \'%" + serviceName + "\'";
		}
		   
		ResultSet rs = statement.executeQuery(sql);  
		
		while (rs.next()) {
			HashMap<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("id", rs.getString(1));
			resultMap.put("serviceName", rs.getString(2));
			resultMap.put("price", rs.getString(3));
			resultMap.put("time", rs.getString(4));     
			resultSet.add(resultMap);
        }
		System.out.println(resultSet);
		rs.close();  
		statement.close();  
		connection.close(); 
        return resultSet;
    }

	/**
	 * 服务组合 定制组合服务
	 * @param params
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/compositeService", method = RequestMethod.POST)
    public @ResponseBody void compositeService(
    		 @RequestBody Map<String,Object>  params) throws Exception{
		String compositeServiceName = params.get("compositeServiceName").toString();
		ArrayList<String> services = (ArrayList<String>) params.get("services");
		System.out.println(services);
		System.out.println(compositeServiceName);
		
		//*************将组合服务信息添加到数据库中*************************//
		StringBuilder str_builder = new StringBuilder();
		String sqlAtomic = "SELECT sum(price), sum(procTime) FROM atomicService WHERE serviceName in (";
		for(int i = 0; i < services.size(); i++){
			str_builder.append(services.get(i));
			sqlAtomic += "\'"+ services.get(i) + "\'";
	        if(i<services.size()-1){
	        	str_builder.append("+");
	        	sqlAtomic += ", ";
	        }
		}
		sqlAtomic += " )";
		System.out.println(str_builder);
		System.out.println(sqlAtomic);
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="compositeService";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd; 
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);
		Statement statement = (Statement) connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlAtomic); 
		String totalPrice = "";
		String totalTime = "";
		while (rs.next()) {
			totalPrice = rs.getString(1);
			totalTime = rs.getString(2);
        }
		System.out.println(totalPrice);
		System.out.println(totalTime);
		String sql = "INSERT INTO " + tableName + " ( serviceName, serviceContent, totalPrice, totalProcTime  ) "
				+ "VALUES ( \'" + compositeServiceName + "\', \'" + str_builder + "\', \'" 
				+ totalPrice + "\', \'" + totalTime + "\' )"; 
		statement.execute(sql); 
		statement.close();
		connection.close();
		
		//******************组合服务********************//
//		CreateSequence test = new CreateSequence();
//		test.runTest(services, compositeServiceName);
		 
    }

    /**
	 * 服务评价 获得组合服务信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCompositeServiceInfo")
    public @ResponseBody ArrayList<HashMap<String, String>> getCompositeServiceInfo() throws Exception{
		ArrayList<HashMap<String, String>> resultSet = new ArrayList<HashMap<String, String>>();
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="compositeService";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "SELECT * FROM " + tableName;   
		ResultSet rs = statement.executeQuery(sql);  
			
		while (rs.next()) {
			HashMap<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("id", rs.getString(1));
			resultMap.put("name", rs.getString(2));
			resultMap.put("content", rs.getString(3));
			resultMap.put("totalPrice", rs.getString(4));
			resultMap.put("totalTime", rs.getString(5));     
			resultSet.add(resultMap);
			}
		System.out.println(resultSet);
		rs.close();  
		statement.close();  
		connection.close(); 
		
		return resultSet;
    }
	
    /**
     * 服务评价	在待仿真列表中添加信息
     * @param params
     * @throws Exception
     */
    @RequestMapping(value = "/doSimulation", method = RequestMethod.POST)
    public @ResponseBody void doSimulation(
    		 @RequestBody  Map<String,String>  params) throws Exception{
		String name = params.get("name");
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="toBeSimulated";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "INSERT INTO " + tableName + " ( serviceName ) VALUES ( \'"+ name +"\' )"; 
		statement.execute(sql); 
		statement.close();  
		connection.close(); 
    }
	
    /**
     * 服务评价 得到已仿真完成的内容
     */
    @RequestMapping("/simulationFinished")
    public @ResponseBody List<SimulationFinished> simulationFinished() throws Exception{

    	 List<SimulationFinished> results =new ArrayList<>();
		
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="simulationFinished";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").
		newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "SELECT * FROM " + tableName;   
		ResultSet rs = statement.executeQuery(sql);  
			
		while (rs.next()) {
			SimulationFinished result = new SimulationFinished();
			result.setId(rs.getInt(1));
			result.setName(rs.getString(2));
			result.setCost(rs.getInt(3));
			result.setProcessingTime(rs.getLong(4));
			result.setFacilityUtilization(rs.getDouble(5));
			result.setResourceUtilization(rs.getDouble(6));
			result.setMeanRepairTime(rs.getLong(7));
			result.setBottleneckAnalysis(rs.getString(8));
			result.setCompletedQuantity(rs.getInt(9));
			result.setDeadline(rs.getDate(10));
			results.add(result);
		}
		System.out.println(results);
		rs.close();  
		statement.close();  
		connection.close(); 
		
		
		return results;
    }
    
    @RequestMapping(value = "/evaluation", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> getEvaluationResult(
    		 @RequestBody  Map<String,String>  params) throws Exception{
    	Map<String, String> resultSet = new HashMap<String, String>();
    	
		String name = params.get("name");
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="simulationFinished";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "SELECT * FROM " + tableName + "where name = " + name;   
		ResultSet rs = statement.executeQuery(sql);  
			
		while (rs.next()) {
			resultSet.put("id", rs.getString(1));
			resultSet.put("name", rs.getString(2));
			resultSet.put("cost", rs.getString(3));
			resultSet.put("processingTime", rs.getString(4));
			resultSet.put("facilityUtilization", rs.getString(5));
			resultSet.put("resourceUtilization", rs.getString(6));
			resultSet.put("meanRepairTime", rs.getString(7));
			resultSet.put("bottleneckAnalysis", rs.getString(8));
			resultSet.put("completedQuantity", rs.getString(9));
			resultSet.put("deadline", rs.getString(10));  
			}
		statement.execute(sql); 
		statement.close();  
		connection.close();
		
		return resultSet;
    }
    
    /**
     * 服务评价 得到待仿真服务内容
     * @return
     * @throws Exception
     */
	@RequestMapping("/getSimulationInfo")
    public @ResponseBody ArrayList<String> getSimulationInfo() throws Exception{
		ArrayList<String> resultSet = new ArrayList<String>();
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="toBeSimulated";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "SELECT * FROM " + tableName;   
		ResultSet rs = statement.executeQuery(sql);  
			
		while (rs.next()) {
			resultSet.add(rs.getString(2));
		}
		System.out.println(resultSet);
		rs.close();  
		statement.close();  
		connection.close(); 
		
		
		return resultSet;
    }
	
	@RequestMapping(value = "/cancelService", method = RequestMethod.POST)
    public @ResponseBody void cancelService(
    		 @RequestBody  Map<String,String>  params) throws Exception{
		String id = params.get("dataId");
		//驱动程序名  
		String driverName="com.mysql.jdbc.Driver";  
		//数据库用户名  
		String userName="root";  
		//密码  
		String userPasswd="893893";  
		//数据库名  
		String dbName="cloudplatform";  
		//表名  
		String tableName="compositeservice";  
		//联结字符串  
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName + "&password=" + userPasswd;  
		Class.forName("com.mysql.jdbc.Driver").newInstance();  
		Connection connection = (Connection) DriverManager.getConnection(url);  
		Statement statement = (Statement) connection.createStatement();  
		String sql = "DELETE FROM " + tableName + " WHERE id = \'" + id + "\'"; 
		statement.execute(sql); 
		statement.close();  
		connection.close(); 
    }
}
