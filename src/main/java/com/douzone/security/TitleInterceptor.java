package com.douzone.security;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.SiteVo;
public class TitleInterceptor extends HandlerInterceptorAdapter {

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		 
//		 //HttpSession session = request.getSession(true);
//		 //session.setAttribute("siteVo", siteService.get());
//		 //response.sendRedirect(request.getContextPath()+"/main");
//			
//		return false;
//	}
	@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {

		ServletContext sc = request.getServletContext();

		ModelMap m = modelAndView.getModelMap();
		System.out.println(m);
		SiteVo siteVo = (SiteVo)m.get("siteVo");
		System.out.println(siteVo);
		if(sc == null) {
			//modelAndView.addObject("siteVo", vo);

			m.addAttribute("siteVo", siteVo);
			System.out.println(m);
		}
	}



}