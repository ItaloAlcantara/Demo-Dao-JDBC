package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao  sellerDao = DaoFactory.creSellerDao();
		
		System.out.println("=== TESTE Fing By id ====");
		
		Seller seller = sellerDao.findById(2);
		
		System.out.println(seller);
		
		System.out.println("\n=== TESTE Fing By department id ====");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		
		
		for(Seller sel : list) {
			System.out.println(sel);
		}
		System.out.println("Quantidade de departamentos encontrados: "+list.size());
	}

}
