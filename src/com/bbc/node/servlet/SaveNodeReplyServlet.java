package com.bbc.node.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbc.base.utils.BaseUtils;
import com.bbc.base.utils.LoginContext;
import com.bbc.node.entity.NodeReplyEntity;
import com.bbc.node.service.NodeReplyService;
import com.bbc.node.service.impl.NodeReplyServiceImpl;

public class SaveNodeReplyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(BaseUtils.RESPONSE_CONTENTTYPE);

		// session判断是否登录
		HttpSession session = request.getSession();
		LoginContext loginContext = (LoginContext) session
				.getAttribute("loginContext");
		if (loginContext == null) {
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}

		// 获取页面的传值
		String context = request.getParameter("context");
		Long nodeId = Long.parseLong(request.getParameter("nodeId"));
		Long parentId = Long.parseLong(request.getParameter("replyId"));
		NodeReplyEntity nodeReplyEntity = new NodeReplyEntity();
		nodeReplyEntity.setContext(context);
		nodeReplyEntity.setCreateDate(new Date());
		nodeReplyEntity.setCreateId(loginContext.getUserId());
		nodeReplyEntity.setDeleteStatus(BaseUtils.SYSTEM_DATA_2);
		nodeReplyEntity.setFlootNumber(0);
		nodeReplyEntity.setNodeId(nodeId);
		nodeReplyEntity.setParentId(parentId == null ? 0l : parentId);
		nodeReplyEntity.setUuid(UUID.randomUUID().toString());

		// 去service处理数据
		NodeReplyService nodeReplyService = new NodeReplyServiceImpl();
		nodeReplyEntity = nodeReplyService.noLogSave(nodeReplyEntity);

		request.getRequestDispatcher("/reply.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
