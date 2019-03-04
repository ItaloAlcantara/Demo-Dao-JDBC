package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	public Connection conn;

	public SellerDaoJDBC(Connection conn) {

		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {

	}

	@Override
	public void update(Seller obj) {

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("Select s.*, d.name " + "as DepName from Seller s " + "join " + "Department d "
					+ " on d.id=s.departmentId" + " where s.id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);

				Seller sel = instantiateSeller(rs, dep);

				return sel;

			}
			return null;

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();

		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();

		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setBaseSalary(rs.getDouble("baseSalary"));
		sel.setDepartment(dep);
		sel.setEmail(rs.getString("email"));
		sel.setBirthDate(rs.getDate("BirthDate"));

		return sel;
	}

	@Override
	public List<Seller> findAll() {

		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Seller> list = new ArrayList<>();
		Map<Integer, Department> map = new HashMap<>();

		try {
			ps = conn.prepareStatement("Select s.*, d.name as DepName from Seller s "
					+ "join Department d on d.id=s.departmentId " + "where d.id=?");
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller sel = instantiateSeller(rs, dep);
				
				list.add(sel);
				

			}
			return list;

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}

	}

}
