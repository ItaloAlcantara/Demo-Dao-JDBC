package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDep {

	public static void main(String[] args) {

		DepartmentDao departmentDao = DaoFactory.creDepartmentDao();
		
		//INSERT
		/*
		 * System.out.println("Inset new Department"); 
		 * Department dep = new Department(null, "Tic"); 
		 * departmentDao.insert(dep);
		 * System.out.println("Insert finish!");
		 */
		 
		
		/* UPDATE
		 * System.out.println("Update department");
		 *  Department depUpdate=departmentDao.findById(5); 
		 *  departmentDao.update(depUpdate);
		 */
		
		/* Search by ID
		 * System.out.println("Search by id"); 
		 * Department depID = departmentDao.findById(5); 
		 * System.out.println(depID);
		 */
		
		/* Delete by id;
		 * System.out.println("delete by id!"); 
		 * departmentDao.deleteById(7);
		 * System.out.println("Delete completed!");
		 */
		
		/*
		 * Find All department 
		 * List<Department> list = departmentDao.findAll();
		 * list.forEach(System.out::println);
		 */
	}

}
