package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try {
			ps = conn.prepareStatement("INSERT INTO DEPARTMENT (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dep.getName());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					dep.setId(id);
				}

			} else {
				throw new DBException("ERROR: No Rows Affected");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void update(Department dep) {
		PreparedStatement ps = null;
		try {
			if (findById(dep.getId()) == null) {
				throw new DBException("Error ID  not exist");
			} else {
				ps = conn.prepareStatement("Update Department set Name = ? where id =?");
				ps.setString(1, dep.getName());
				ps.setInt(2, dep.getId());

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(ps);

		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			if (findById(id) == null) {
				throw new DBException("Id not exist");
			} else {
				ps = conn.prepareStatement("DELETE FROM Department WHERE id=?");
				ps.setInt(1, id);
				ps.executeUpdate();
				
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * from Department where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				return dep;
			}
			return null;

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Department> dep = new ArrayList<>();
		
		try {
		ps = conn.prepareStatement("SELECT * FROM DEPARTMENT");
		rs =ps.executeQuery();
		
		while(rs.next()) {
			dep.add(instantiateDepartment(rs));
		}

		return dep;
		
		}catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();

		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("name"));
		return dep;
	}

}
