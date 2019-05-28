package com.bjsxt.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/validcode")
public class ValidCodeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建一张图片,单位：像素
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
	
		//透明的玻璃   向画板上画内容必须先设置画笔
		Graphics2D gra = image.createGraphics();
		
		gra.setColor(Color.WHITE);
		//从哪个坐标开始填充，后两个参数，矩形区域
		gra.fillRect(0, 0, 200, 100);
		
		List<Integer> list=new ArrayList<>();
	    Random random=new Random();
		
	    for(int i=0;i<4;i++){
	    	list.add(random.nextInt(10));
	    }

	    gra.setFont(new Font("宋体",Font.ITALIC|Font.BOLD,40));
        Color[] colors=new Color[]{Color.RED,Color.YELLOW,Color.GRAY,Color.GREEN,Color.PINK,Color.BLUE};	    
	    for(int i=0;i<list.size();i++){
		    gra.setColor(colors[random.nextInt(colors.length)]);
	    	gra.drawString(list.get(i)+"", i*40, 70+(random.nextInt(21)-10));
	    }
		
	    for(int i=0;i<2;i++){
	    	gra.setColor(colors[random.nextInt(colors.length)]);
	    	gra.drawLine(0, random.nextInt(101), 200, random.nextInt(101));
		    	
	    }
	    
		ServletOutputStream outputStream = resp.getOutputStream();
		
		//工具类
		ImageIO.write(image, "jpg", outputStream);
	
		HttpSession session=req.getSession();
        session.setAttribute("code", ""+list.get(0)+list.get(1)+list.get(2)+list.get(3));		
	}
}
