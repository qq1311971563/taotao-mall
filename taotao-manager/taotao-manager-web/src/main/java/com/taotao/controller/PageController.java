package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * @Description: 打开首页
	 */
	@RequestMapping("/")
	public String toIndex() {
		return "index";
	}

	@RequestMapping("/{page}")
	public String toPage(@PathVariable String page) {
		return page;
	}
}
