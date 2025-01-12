package com.a.b.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a.b.service.IBoarderService;
import com.a.b.service.LoginService;
import com.a.b.service.MModifyService;
import com.a.b.service.Constant;
import com.a.b.dao.MDao;
import com.a.b.dto.Member;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		Constant.sqlSession = this.sqlSession;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	IBoarderService service;
	SqlSession sqlSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("locale", locale);
		return "main";
	}
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(Locale locale, Model model, HttpServletRequest request) {
		return "main";
	}
	@RequestMapping(value = "/memberjoin")
	public String memberJoin(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return "/memberjoin";
	}
	@RequestMapping(value = "/login")
	public String flogin(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
		return "login";
	}
	@RequestMapping("/memberdo")
	public String memberok(HttpServletRequest request, Model model, HttpSession session) {
		MDao dao = sqlSession.getMapper(MDao.class);
		String bId = request.getParameter("mid");
		Member vo = dao.memberView(bId);
		
		if (!(vo == null)) {
			System.out.println(bId + "회원가입 실패");
			return "main";
		} else {
			String bName = request.getParameter("name");
			String bPw = request.getParameter("password");
			String bGender = request.getParameter("gender");
			Date bBirthday = Date.valueOf(request.getParameter("birthday"));
			Timestamp bJoindate = new Timestamp(System.currentTimeMillis());
			
			if (!bPw.equals(request.getParameter("password2"))) {
				System.out.println(bId + "패스워드가 다릅니다.");
			} else {

				try {
					dao.memberJoin(bId, bPw, bName, bGender, bBirthday, bJoindate);
					System.out.println(bId + " 회원 가입완료");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(bId + " 회원 가입 되지 않았습니다.");
				}
			}

		}
		return "redirect:main";
	}
	@RequestMapping("/logindo")
	public String logindo(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);
		model.addAttribute("session", session);
		service = new LoginService();
		service.execute(model);
		return "redirect:main";
	}
	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request, HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("pw");
		session.removeAttribute("loginOk");
		session.removeAttribute("mainpage");
		session.removeAttribute("joinVo");
		return "redirect:main";
	}
//	@RequestMapping(value="/user/idCheck", method=RequestMethod.GET)
//	@ResponseBody
//	public int idCheck(@RequestParam("mid") String mid) {
//		MDao dao = sqlSession.getMapper(MDao.class);
//		
//		return dao.idcheck(mid);
//	}
	@RequestMapping("/memdata")
	public String memdata(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		MDao dao = sqlSession.getMapper(MDao.class);
		Member info = dao.memberView(id);
		model.addAttribute("memView", info);
		
		return "memdata";
	}
//	@RequestMapping("/membermodify")
//	public String memmodify(Model model, HttpServletRequest request) {
//		String id = request.getParameter("id");
//		model.addAttribute("request", request);
//		MModifyService service = new MModifyService();
//		return "membermodify";
//	}
//	@RequestMapping("/memmodifydo")
//	public String memmodifydo(HttpServletRequest request, Model model) {
//		MDao dao = sqlSession.getMapper(MDao.class);
//		HttpSession session = request.getSession();
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		
//		dao.memberModify(id, password, name);
//		
//		return "redirect:memdata";
//	}
	
	
	@RequestMapping("/adminpage")
	public String adminpage(Locale locale, Model model, HttpServletRequest request) {
		model.addAttribute("locale", locale);
		return "./admin/adminpage";
	}
	
}
