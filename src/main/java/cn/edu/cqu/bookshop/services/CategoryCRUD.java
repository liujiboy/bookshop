package cn.edu.cqu.bookshop.services;
import java.util.List;
import cn.edu.cqu.bookshop.domains.Category;
public interface CategoryCRUD {
	public Category getById(Long id);
	public void add(Category category);
	public void edit(Long id,Category newCategory);
	public Category getByCode(String code);
	public Category getByName(String name);
	public List<Category> getAll();
	public void deleteById(Long id);
}
