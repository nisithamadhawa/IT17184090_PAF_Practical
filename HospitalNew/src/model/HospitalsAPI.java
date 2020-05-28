package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HospitalsAPI
 */
@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Hospital hospitalObject = new Hospital();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//NOT USE

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String output = hospitalObject.insertHospital(
				request.getParameter("hospitalNo"),   
				request.getParameter("hospitalName"),  
				request.getParameter("hospitalAddress"),
				request.getParameter("hospitalPhone"),  
				request.getParameter("hospitalEmail"),
				request.getParameter("hospitalPassword"));
		
		 response.getWriter().write(output); 
	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		  Map paras = getParasMap(request); 
		  
		 String output = hospitalObject.updateHospital(paras.get("hidHospitalIDSave").toString(),  
				 paras.get("hospitalNo").toString(), 
				 paras.get("hospitalName").toString(),    
				 paras.get("hospitalAddress").toString(),
				 paras.get("hospitalPhone").toString(),    
				 paras.get("hospitalEmail").toString(), 
				 paras.get("hospitalPassword").toString());  
		 
		 response.getWriter().write(output); 
		 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 Map paras = getParasMap(request);  
		 
		 String output = hospitalObject.deleteHospital(paras.get("hospitalID").toString());  
		 
		 response.getWriter().write(output); 
		
	}

	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap<String, String>();  
		try  
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
					scanner.useDelimiter("\\A").next() : "";  
					scanner.close(); 
	 
	  String[] params = queryString.split("&");   
	  for (String param : params)   
	  { 

 
	   String[] p = param.split("=");    
	   map.put(p[0], p[1]);   
	   }  
	  }  
		catch (Exception e)  
		{  
			
		}  return map; }
		

}
