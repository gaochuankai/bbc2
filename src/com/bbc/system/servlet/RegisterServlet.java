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

		// ����֤
		String loginName = request.getParameter("loginName");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String imgcode = request.getParameter("imgcode");// ����
		String testcode = (String) request.getSession()
				.getAttribute("testcode");
		if (StringUtils.isBlank(loginName)) {
			// ��¼������Ϊ��
			request.setAttribute("errorMsg", "��¼������Ϊ�գ�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(name)) {
			// �ǳƲ���Ϊ��
			request.setAttribute("errorMsg", "�ǳƲ���Ϊ�գ�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(password)) {
			// ���벻��Ϊ��
			request.setAttribute("errorMsg", "���벻��Ϊ�գ�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(repassword)) {
			// �ٴ��������벻��Ϊ��
			request.setAttribute("errorMsg", "�ٴ��������벻��Ϊ�գ�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (StringUtils.isBlank(imgcode)) {
			// ��֤�벻��Ϊ��
			request.setAttribute("errorMsg", "��֤�벻��Ϊ�գ�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (!StringUtils.equals(password, repassword)) {
			// �������벻һ��
			request.setAttribute("errorMsg", "�������벻һ�£�");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}
		if (!StringUtils.equalsIgnoreCase(imgcode, testcode)) {
			// ��֤���������
			request.setAttribute("errorMsg", "��֤���������");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		}

		// ����service����
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
