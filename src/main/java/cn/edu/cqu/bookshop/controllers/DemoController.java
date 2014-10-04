package cn.edu.cqu.bookshop.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cqu.bookshop.domains.Category;

@Controller
public class DemoController {
	@RequestMapping(value="/demo/hello.do")
	public ModelAndView hello(String name)
	{
		ModelAndView mv=new ModelAndView();
		if(name==null||name.equals(""))
		{
			name="world";
		}
		mv.addObject("name",name);
		mv.setViewName("/demo/hello");
		return mv;
	}
	@RequestMapping(value="/demo/map.json")
	@ResponseBody
	public Map<String,String> mapJson()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("message", "hello world!");
		map.put("type","just for demo" );
		return map;
	}
	@RequestMapping(value="/demo/category.json")
	@ResponseBody
	public Category category()
	{
		Category c=new Category();
		c.setCode("123");
		c.setName("abc");
		return c;
	}
}
