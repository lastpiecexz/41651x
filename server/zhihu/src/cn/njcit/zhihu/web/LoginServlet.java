package cn.njcit.zhihu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.njcit.zhihu.pojo.User;
import cn.njcit.zhihu.pojo.ZhihuResult;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("-------------------");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username+" "+password);
		
		ZhihuResult zhihuResult = new ZhihuResult();
		if("zhangsan".equals(username) && "11111".equals(password)) {
			
			zhihuResult.setResult(true);
			zhihuResult.setMsg("µÇÂ¼³É¹¦");
			
			User user = new User();
			user.setId(10001);
			user.setUsername("zhangsan");
			user.setPassword("11111");
			user.setBirth(new Date());
			
			zhihuResult.setObj(user);
			
		}else {
			zhihuResult.setResult(false);
			zhihuResult.setMsg("µÇÂ¼Ê§°Ü");
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		Cookie cookie = new Cookie("userdata","zhangsan:11111");
		cookie.setMaxAge(180);
		
		response.addCookie(cookie);
		
		String jsonString = JSON.toJSONString(zhihuResult);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
		out.close();
		
	}

}
