package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JdbcTemplate;
import dto.UserFeedDTO;
public class UserFeedDAO {

	public List<UserFeedDTO> feedAll() {
		List<UserFeedDTO> userFeedList = new ArrayList<>();
		UserFeedDTO userDto = null;
		Connection conn = JdbcTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select f.feed_id, f.email, f.feed_image, f.feed_content, u.name, u.nickname"
					+ " from feeds f join users u ON f.email = u.email";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userDto = new UserFeedDTO();
				userDto.setFeed_id(rs.getInt("feed_id"));
				userDto.setEmail(rs.getString("email"));
				userDto.setFeed_image(rs.getString("feed_image"));
				userDto.setFeed_content(rs.getString("feed_content"));
				userDto.setName(rs.getString("name"));
				userDto.setNickname(rs.getString("nickname"));
				
				userFeedList.add(userDto);
			}
			JdbcTemplate.commit(conn);
		} catch (SQLException e) {
			JdbcTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(conn);
			JdbcTemplate.close(pstmt);
			JdbcTemplate.close(rs);
		}
		return userFeedList;
	}

}
