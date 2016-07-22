package org.dsms.ddo.dsms_ddo_rest.utility;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;


public class InitiateServer extends HttpServlet{
	
	public void init() throws ServletException
    {
          System.out.println("---------- CrunchifyServletExample Initialized successfully ----------");
          ServerUtility util = new ServerUtility();
          util.init(ServerSpot.SERVER_DDO, ServerSpot.PORT_UDP_SERVER_DDO);
    }

}
