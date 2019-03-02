package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBException;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Department obj = new Department(1, "Books");
		try {
		Seller obj1 = new Seller(1, "Italo", "italo@hotmail.com",
				new Date(),2500.0,obj);
		
		System.out.println(obj);
		
		System.out.println(obj1);
		
		}catch (Exception e) {
			throw new DBException(e.getMessage());
		}
	}

}
