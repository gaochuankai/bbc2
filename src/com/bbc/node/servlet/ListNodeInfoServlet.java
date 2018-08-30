package com.bbc.node.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbc.base.utils.BaseUtils;
import com.bbc.node.entity.NodeInfoEntity;
import com.bbc.node.entity.NodeTypeEntity;
import com.bbc.node.service.NodeInfoService;
import com.bbc.node.service.NodeTypeService;
import com.bbc.node.service.impl.NodeInfoServiceImpl;
import com.bbc.node.service.impl.NodeTypeServiceImpl;

/**
 * 帖子列表展示页面
 * 
 * @author gck
 *
 */
public class ListNodeInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType(BaseUtils.RESPONSE_CONTENTTYPE);
		// 获取NodeInfoEntity列表
		NodeInfoService nodeInfoService = new NodeInfoServiceImpl();
		List<NodeInfoEntity> nodeInfoList = nodeInfoService
				.findByParamsList(new HashMap<String, Object>());
		// 获取nodetype列表
		NodeTypeService nodeTypeService = new NodeTypeServiceImpl();
		List<NodeTypeEntity> nodeTypeList = nodeTypeService.findAllList();
		request.setAttribute("nodeInfoList", nodeInfoList);
		request.setAttribute("nodeTypeList", nodeTypeList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
