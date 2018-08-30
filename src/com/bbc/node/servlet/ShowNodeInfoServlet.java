package com.bbc.node.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbc.base.utils.BaseUtils;
import com.bbc.node.entity.NodeInfoEntity;
import com.bbc.node.entity.NodeReplyEntity;
import com.bbc.node.service.NodeInfoService;
import com.bbc.node.service.NodeReplyService;
import com.bbc.node.service.impl.NodeInfoServiceImpl;
import com.bbc.node.service.impl.NodeReplyServiceImpl;

/**
 * 
 * @author gck
 *
 */
public class ShowNodeInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(BaseUtils.RESPONSE_CONTENTTYPE);
		String nodeInfoId = request.getParameter("nodeInfoId");
		// 获取NodeInfoEntity
		NodeInfoService nodeInfoService = new NodeInfoServiceImpl();
		NodeInfoEntity nodeInfoEntity = nodeInfoService
				.findOne(Long.parseLong(nodeInfoId));
		// 获取NodeReplyEntity列表
		NodeReplyService nodeReplyService = new NodeReplyServiceImpl();
		List<NodeReplyEntity> list = nodeReplyService
				.findListByNodeInfoId(nodeInfoEntity.getId());
		request.setAttribute("nodeInfoEntity", nodeInfoEntity);
		request.setAttribute("nodeReplyList", list);
		request.getRequestDispatcher("/reply.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
