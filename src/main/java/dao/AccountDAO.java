package dao;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.AccountDTO;

public class AccountDAO {
	
	// 로그인을 위해 DB에 접근하는 로직
	public AccountDTO accountLogin(String email, String password) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		AccountDTO accountDto = null;
		ResultSet rs = null;

		try {
			String sql = "select * from users where email=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				accountDto = new AccountDTO();
				accountDto.setEmail(rs.getString("email"));
				accountDto.setEmail(rs.getString("password"));
				accountDto.setName(rs.getString("name"));
				accountDto.setNickname(rs.getString("nickname"));
				accountDto.setProfile_image(rs.getString("profile_image"));
			}
		} catch (SQLException e) {
			//TODO
			e.printStackTrace();
		} finally {
			close(conn);
			close(rs);
			close(pstmt);
		}
		return accountDto;
	}

	//회원가입을 위해 데이터베이스에 접근하는 로직
	public boolean accountRegister(AccountDTO accountDto) {
		boolean isJoin = false;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into users values(seq_user_num.NEXTVAL,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountDto.getEmail());
			pstmt.setString(2, accountDto.getName());
			pstmt.setString(3, accountDto.getNickname());
			pstmt.setString(4, accountDto.getPassword());
			pstmt.setString(5, accountDto.getProfile_image());
			int n = pstmt.executeUpdate();
			
			if(n > 0) { //db에 데이터 추가를 성공한 경우
				isJoin = true;
				commit(conn);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
			close(pstmt);
		}
		return isJoin;
	}
}