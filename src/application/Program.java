package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.creSellerDao();

		System.out.println("=== TESTE: 1 - Find By id ====");
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);
		

		System.out.println("\n=== TESTE: 2 - Find By department id ====");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		list.forEach(System.out::println);
		qtdLinhas(list.size());

		System.out.println("\n=== TESTE: 3 - find all ====");

		List<Seller> list2 = sellerDao.findAll();
		list2.forEach(System.out::println);
		qtdLinhas(list2.size());
		
		System.out.println("\n=== TESTE: 4 - Insert Seller ====");
		Seller selnew = new Seller(null,"IGOR MATHEUS","igorAranha13@gmail.com",new Date(),3560.0, dep);
		sellerDao.insert(selnew);
		System.out.println("New Seller Inserted, Seller id is : "+selnew.getId());

	}

	public static void qtdLinhas(Integer size) {
		System.out.println("Quantidades de linhas encontradas: " + size);
	}

}
