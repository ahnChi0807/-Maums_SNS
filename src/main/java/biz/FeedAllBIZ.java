package biz;

import java.util.List;

import dao.UserFeedDAO;
import dto.UserFeedDTO;

public class FeedAllBIZ {

	public List<UserFeedDTO> feedAll() {
		UserFeedDAO dao = new UserFeedDAO();
		List<UserFeedDTO> userFeedList = dao.feedAll();
		return userFeedList;
	}

}
