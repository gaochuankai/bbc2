package com.bbc.system.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TesCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置验证码的宽和高
		int width = 120;
		int height = 40;
		// 画纸
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获得画笔
		Graphics g = bi.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 设置边框
		g.setColor(Color.red);
		g.drawRect(0, 0, width - 1, height - 1); // 画边框

		String data = "ABCDEFGHIJKMNPQRSTUVWXYZ23456789abcdefghijkmnpqrstuvwxyz"; // 准备一些字
		Random r = new Random();
		String str = "";
		for (int i = 0; i < 4; i++) {
			// 设置画笔的颜色
			g.setColor(
					new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.setFont(new Font("瀹嬩綋", Font.ITALIC, 25));
			char ch = data.charAt(r.nextInt(data.length()));
			str += ch;
			g.drawString(ch + "", 10 + (20 * i), 30);

		}
		HttpSession session = request.getSession();
		session.setAttribute("testcode", str);
		System.err.println("验证码：" + str);

		for (int i = 0; i < 10; i++) {
			// 设置画笔的颜色
			g.setColor(
					new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
					r.nextInt(height));

		}

		// 把图片粘贴到浏览器
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
