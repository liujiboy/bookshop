package cn.edu.cqu.bookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cqu.bookshop.domains.Category;
import cn.edu.cqu.bookshop.services.CategoryCRUD;

@Controller
public class CategoryController {
	@Autowired
	@Qualifier("categoryCRUDHibernate")
	private CategoryCRUD categoryCRUD;
	@RequestMapping(value="/category/add.ui")
	public ModelAndView addCategoryUI()
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/category/addCategory");
		return mv;
	}
	@RequestMapping(value="/category/add.do")
	public ModelAndView doAddCategory(String code,String name)
	{
		ModelAndView mv=new ModelAndView();
		if(isValid(code,name,mv))
		{
			Category c=new Category();
			c.setCode(code);
			c.setName(name);
			categoryCRUD.add(c);
			mv.setViewName("redirect:/category/listAll.ui");
		}else
		{
			mv.addObject("code", code);
			mv.addObject("name",name);
			mv.setViewName("/category/addCategory");
		}
		return mv;
	}
	
	private boolean isValid(String code, String name, ModelAndView mv) {
		boolean valid=true;
		//检查code不能为空，且长度不能大于10字节
		if(code==null||code.equals(""))
		{
			mv.addObject("codeError", "code不能为空");
			valid=false;
		}else
		{
			if(code.getBytes().length>10)
			{
				mv.addObject("codeError", "code的长度不能大于10字节");
				valid=false;
			}
		}
		//检查name不能为空，且长度不能大于100字节
		if(name==null||name.equals(""))
		{
			mv.addObject("nameError", "name不能为空");
			valid=false;
		}else
		{
			if(name.getBytes().length>100)
			{
				mv.addObject("nameError", "name的长度不能大于100字节");
				valid=false;
			}
		}
		if(valid)
		{
			//检查不能存在相同的code
			if(categoryCRUD.getByCode(code)!=null)
			{
				mv.addObject("codeError", "code重复");
				valid=false;
			}
			//检查不能存在相同的nam
			if(categoryCRUD.getByName(name)!=null)
			{
				mv.addObject("nameError", "name重复");
				valid=false;
			}
		}
		return valid;
	}
	@RequestMapping(value="/category/listAll.ui")
	public ModelAndView listAll()
	{
		ModelAndView mv=new ModelAndView();
		List<Category> categories=categoryCRUD.getAll();
		mv.addObject("categories",categories);
		mv.setViewName("/category/listAll");
		return mv;
	}
}
