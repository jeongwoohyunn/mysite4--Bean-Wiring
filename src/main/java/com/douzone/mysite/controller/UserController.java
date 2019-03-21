package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void auth() {

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() {

	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(/* @ModelAttribute UserVo userVo */Model model) {
		model.addAttribute("userVo", new UserVo());
		// get에 빈껍데기를 넣어서 오류를 없애준다.
		// null안들고오게 vo는적어주고 valid는 뺴준다.
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {// modelattribute를 쓰면
																									// join.jsp에서 갔다
																									// 쓸수있다.

		if (result.hasErrors()) {// 하나라도 문제가있으면 이리로
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error:list) {
//				System.out.println(error);
//			}
			model.addAttribute(result.getModel());
			return "user/join";
		}

		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@Auth(Auth.Role.USER)
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		System.out.println(authUser);

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo userVo) {

		userVo.setNo(authUser.getNo());
		userService.modifyUser(userVo);

		// session의 authUser 변경
		authUser.setName(userVo.getName());

		return "redirect:/user/modify?result=success";
	}
}
