package com.socialnet.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;


/**
 * Servlet implementation class Upload
 */
@WebServlet("/uploadx.do")
@MultipartConfig
public class Upload extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part p2=request.getPart("picfile");
		InputStream is2=p2.getInputStream();
		ServletContext ctx=getServletContext();
		String uploadpath=ctx.getRealPath("profilePics");
		System.out.println(uploadpath);
		
		HttpSession hs=request.getSession();
		String logId=(String) hs.getAttribute("logid");
		
		System.out.println(p2.getContentType());
		
		FileOutputStream fos=new FileOutputStream(uploadpath+"/"+logId+".jpg");
		int x=is2.read();
		while (x!=-1) {
			fos.write(x);
			x=is2.read();
			
			
		}
		fos.close();
		
		PrintWriter out=response.getWriter();
		
		out.println("Picture successfully changed...");
		
	}
}
