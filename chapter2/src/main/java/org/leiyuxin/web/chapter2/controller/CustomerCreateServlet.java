package org.leiyuxin.web.chapter2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建客户  改Servlet只有一个请求路径，但可以处理两种不同的请求类型
 * doGet 用于处理get请求，doPost 用于处理post请求 但他们对应的请求路径都是/customer_create
 * 在Servelet 中有GET、Post、Put、DELETE他们分别对应doGet,doPost,doPut,doDelete方法
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {

    /**
	 *
	 */
	private static final long serialVersionUID = -3583759173215667773L;

	/**
     * 进入 创建客户 界面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }

    /**
     * 处理 创建客户 请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}
