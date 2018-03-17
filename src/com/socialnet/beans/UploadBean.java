package com.socialnet.beans;

import java.io.*;

import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import javax.servlet.http.*;

public class UploadBean {
	public void uploadFile(FileUploadEvent e){
		try {
			UploadedFile uf=e.getUploadedFile();
			InputStream is=uf.getInputStream();
			String uploadpath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("profilePics");
			System.out.println(uploadpath);
			
			HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			String logId=(String) hs.getAttribute("logid");
			
			System.out.println(uf.getContentType());
			
			FileOutputStream fos=new FileOutputStream(uploadpath+"/"+logId+".jpg");
			int x=is.read();
			while (x!=-1) {
				fos.write(x);
				x=is.read();
				
			}
			fos.close();
		}catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
