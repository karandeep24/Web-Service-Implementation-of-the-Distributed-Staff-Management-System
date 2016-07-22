package org.dsms.mtl.dsms_mtl_rest.utility;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;


public class InitiateServer extends HttpServlet{
	
	public void init() throws ServletException
    {
          System.out.println("---------- CrunchifyServletExample Initialized successfully ----------");
          ServerUtility util = new ServerUtility();
          util.init(ServerSpot.SERVER_MTL, ServerSpot.PORT_UDP_SERVER_MTL);
    }

}
