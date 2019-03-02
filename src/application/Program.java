package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao  sellerDao = DaoFactory.creSellerDao();
		
		System.out.println("=== TESTE Fing By id ====");
		
		Seller seller = sellerDao.findById(2);
		
		System.out.println(seller);
	}

}
