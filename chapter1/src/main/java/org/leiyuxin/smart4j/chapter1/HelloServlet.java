package org.leiyuxin.smart4j.chapter1;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = dateFormat.format(new Date());
		req.setAttribute("currentTime", currentTime);
		try {
			req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
		} catch (Exception e) {
			LOGGER.error("Exception 发生", e);
		}

	}
}
