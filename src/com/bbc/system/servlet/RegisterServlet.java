package com.bbc.system.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bbc.base.utils.BaseUtils;
import com.bbc.system.entity.UserEntity;
import com.bbc.system.service.UserService;
import com.bbc.system.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType(BaseUtils.RESPONSE_CONTENTTYPE);
		UserEntity userEntity = new UserEntity();

		// 表单验证
		String loginName = request.getParameter("loginName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String imgcode = request.getParameter("imgcode");// 输入
		String testcode = (String) request.getSession()
				.getAttribute("testcode");
		if (StringUtils.isBlank(loginName)) {
			// 登录名不能为空
			request.setAttribute("errorMsg", "登录名不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(name)) {
			// 昵称不能为空
			request.setAttribute("errorMsg", "昵称不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(password)) {
			// 密码不能为空
			request.setAttribute("errorMsg", "密码不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(repassword)) {
			// 再次输入密码不能为空
			request.setAttribute("errorMsg", "再次输入密码不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(imgcode)) {
			// 验证码不能为空
			request.setAttribute("errorMsg", "验证码不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (!StringUtils.equals(password, repassword)) {
			// 密码输入不一致
			request.setAttribute("errorMsg", "密码输入不一致！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (!StringUtils.equalsIgnoreCase(imgcode, testcode)) {
			// 验证码输入错误
			request.setAttribute("errorMsg", "验证码输入错误！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}

		// 调用service方法
		UserService userService = new UserServiceImpl();
		userEntity.setLoginName(loginName);
		userEntity.setName(name);
		userEntity.setPassword(password);
		userEntity.setUuid(UUID.randomUUID().toString());
		userEntity.setCreateDate(new Date());
		Map<String, Object> result = userService.register(userEntity);

		if ((Boolean) result.get("success")) {
			response.sendRedirect(request.getServletContext().getContextPath()
					+ "/login");
			return;
		} else {
			request.setAttribute("errorMsg", result.get("errorMsg"));
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
