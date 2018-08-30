package com.bbc.system.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bbc.base.utils.BaseUtils;
import com.bbc.base.utils.IpUtil;
import com.bbc.base.utils.LoginContext;
import com.bbc.system.entity.UserEntity;
import com.bbc.system.service.UserService;
import com.bbc.system.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(BaseUtils.RESPONSE_CONTENTTYPE);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(BaseUtils.SYSTEM_MAP_SUCCESS, false);

		// �ж�����ֵ
		String loginname = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		if (StringUtils.isBlank(loginname) || StringUtils.isBlank(password)) {
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG, "�û������벻��Ϊ�գ�");
			request.setAttribute("data", result);
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
			return;
		}

		// �ж���֤��
		String code = request.getParameter("code");
		String sessionCode = (String) request.getSession()
				.getAttribute("testcode");
		if (!StringUtils.equalsIgnoreCase(code, sessionCode)) {
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG, "��֤�����");
			request.setAttribute("data", result);
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
			return;
		}

		// �����ݿ��в�ѯ�Ƿ��и��û�
		UserEntity userEntity = new UserEntity();
		userEntity.setLoginName(loginname);
		userEntity.setPassword(password);
		UserService userService = new UserServiceImpl();
		result = userService.login(userEntity);
		// ���û�в�ѯ��
		if (!(Boolean) result.get(BaseUtils.SYSTEM_MAP_SUCCESS)) {
			result.put(BaseUtils.SYSTEM_MAP_ERROR_MSG, "�û������������");
			request.setAttribute("data", result);
			request.getRequestDispatcher("login.jsp").forward(request,
					response);
			return;
		}

		String saveUserName = request.getParameter("saveUserName");
		// �ж��ǲ��Ǽ�ס����
		if (StringUtils.equals(saveUserName, "1")) {
			// ��Ҫ��ס���봫cookie
			Cookie cookie = new Cookie("loginName", userEntity.getLoginName());
			// ����24Сʱ
			cookie.setMaxAge(60 * 60 * 24);
			cookie.setPath("/");
			Cookie cookie2 = new Cookie("password", userEntity.getPassword());
			cookie2.setMaxAge(60 * 60 * 24);
			cookie2.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie2);
		} else {
			// ����Ҫ��ס���룬����cookie
			Cookie cookie = new Cookie("loginName", "");
			// ����0Сʱ
			cookie.setMaxAge(0);
			cookie.setPath("/");
			Cookie cookie2 = new Cookie("password", "");
			cookie2.setMaxAge(0);
			cookie2.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie2);
		}

		LoginContext loginContext = buildLoginContext(userEntity, request);
		request.getSession().setAttribute("loginContext", loginContext);
		request.getRequestDispatcher("/listNodeInfo").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	LoginContext buildLoginContext(UserEntity userEntity,
			HttpServletRequest request) {
		LoginContext loginContext = new LoginContext();
		loginContext.setUserId(userEntity.getId());
		loginContext.setLoginName(userEntity.getLoginName());
		loginContext.setName(userEntity.getName());
		loginContext.setIp(IpUtil.getIpAddress(request));
		return loginContext;
	}

}
