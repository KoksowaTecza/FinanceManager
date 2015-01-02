package com.project.user.avatars;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.project.domain.ProfileImage;
import com.project.services.ImageService;
import com.project.services.UserService;

public class ImageServlet extends HttpServlet {
	@Autowired
	ImageService imageService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = (String) request.getParameter("id");
			ProfileImage profileImage = imageService.getUserProfileImage(username);
			Blob image = profileImage.getProfile_image();
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("image/gif");
			InputStream in;
			int length;
			in = image.getBinaryStream();

			length = (int) image.length();

			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			while ((length = in.read(buffer)) != -1) {
				// System.out.println("writing " + length + " bytes");
				out.write(buffer, 0, length);
			}

			in.close();
			out.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
