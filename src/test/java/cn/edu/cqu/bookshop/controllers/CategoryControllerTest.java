package cn.edu.cqu.bookshop.controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cqu.bookshop.domains.Category;
import cn.edu.cqu.bookshop.services.CategoryCRUD;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})  
public class CategoryControllerTest {
	@Autowired
	@Qualifier("categoryCRUDHibernate")
	private CategoryCRUD categoryCRUD;
	@Autowired
	private CategoryController categoryController;
	@Before
	public void setUp() throws Exception {
		//删除所有的数据
		deleteAll();
	}
	private void deleteAll() {
		List<Category> categories=categoryCRUD.getAll();
		for(Category c:categories)
		{
			categoryCRUD.deleteById(c.getId());
		}
	}
	@After
	public void tearDown() throws Exception {
		deleteAll();
	}

	@Test
	public void testAddCategoryUI() {
		ModelAndView mv=categoryController.addCategoryUI();
		assertEquals("/category/addCategory",mv.getViewName());
	}

	@Test
	public void testDoAddCategory() {
		Category c=new Category();
		c.setCode("1");
		c.setName("2");
		categoryCRUD.add(c);
		//测试code为空
		ModelAndView mv=categoryController.doAddCategory("", "2");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("code不能为空",mv.getModelMap().get("codeError"));
		//测试code超长
		mv=categoryController.doAddCategory("111111111111", "2");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("code的长度不能大于10字节",mv.getModelMap().get("codeError"));
		//测试存在相同的code
		mv=categoryController.doAddCategory("1", "3");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("code重复",mv.getModelMap().get("codeError"));
		//测试name为空
		mv=categoryController.doAddCategory("2", "");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("name不能为空",mv.getModelMap().get("nameError"));
		//测试name超长
		mv=categoryController.doAddCategory("1", "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("name的长度不能大于100字节",mv.getModelMap().get("nameError"));
		//测试存在相同的name
		mv=categoryController.doAddCategory("2", "2");
		assertEquals("/category/addCategory",mv.getViewName());
		assertEquals("name重复",mv.getModelMap().get("nameError"));
		//测试正常添加
		mv=categoryController.doAddCategory("4", "5");
		assertEquals("redirect:/category/listAll.ui",mv.getViewName());
		//其他测试...
	}

	@Test
	public void testListAll() {
		ModelAndView mv=categoryController.listAll();
		assertEquals("/category/listAll",mv.getViewName());
	}

}
