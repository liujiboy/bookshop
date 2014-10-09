package cn.edu.cqu.bookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cqu.bookshop.domains.Category;
import cn.edu.cqu.bookshop.json.FormResponse;
import cn.edu.cqu.bookshop.services.CategoryCRUD;

@Controller
public class CategoryController {
	@Autowired
	@Qualifier("categoryCRUDHibernate")
	private CategoryCRUD categoryCRUD;

	@RequestMapping(value = "/category/add.ui")
	public ModelAndView addCategoryUI() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/category/addCategory");
		return mv;
	}

	@RequestMapping(value = "/category/listAll.ui")
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/category/listAll");
		return mv;
	}
	@RequestMapping(value = "/category/categories.json")
	@ResponseBody
	public List<Category> categories()
	{
		List<Category> categories = categoryCRUD.getAll();
		return categories;
	}

	@RequestMapping(value = "/category/addCategory.do")
	@ResponseBody
	public FormResponse add(String name, String code) {
		FormResponse response = new FormResponse();
		try {
			if (categoryCRUD.getByCode(code) != null) {
				response.addError("code", "编码重复");
			}
			if (categoryCRUD.getByName(name) != null) {
				response.addError("name", "名称重复");
			}
			if (response.hasError()) {
				response.setMsg("数据异常,无法添加！");
			} else {
				Category category = new Category();
				category.setCode(code);
				category.setName(name);
				categoryCRUD.add(category);
				response.setMsg("添加成功！");

			}
		} catch (Exception e) {
			response.setMsg("系统错误，无法添加！");
			response.setSuccess(false);
			e.printStackTrace();
		}
		return response;
	}
}
