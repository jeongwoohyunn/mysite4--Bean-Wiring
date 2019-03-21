package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;

	@Autowired
	private FileuploadService fileuploadService;
	// 주입받음

	@Auth(Role.ADMIN)
	@RequestMapping({ "", "/main" })
	public String main(Model model) {
		//
		model.addAttribute("siteVo", siteService.get());
		return "admin/main";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@Auth(Role.ADMIN)
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

	@RequestMapping("/main/update")
	public String update(@ModelAttribute SiteVo siteVo,
			@RequestParam(value = "upload-profile") MultipartFile multipartFile) {

		String profile = fileuploadService.restore(multipartFile);
		siteVo.setProfile(profile);
		siteService.update(siteVo);
		return "redirect:/admin/main";
	}

}
