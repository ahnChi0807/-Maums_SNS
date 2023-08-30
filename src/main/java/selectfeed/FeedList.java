package selectfeed;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.FeedAllBIZ;
import dto.UserFeedDTO;

@WebServlet(name = "/FeedList", urlPatterns = { "/feedlist" })
public class FeedList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FeedAllBIZ biz = new FeedAllBIZ();
		List<UserFeedDTO> userFeedList = biz.feedAll();
		
		if(userFeedList != null) {
			request.setAttribute("feedlist", userFeedList);
	        System.out.println("good");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/page/main_page.jsp");
	        dispatcher.forward(request, response);
		} else {
			System.out.println("fail");
		}
	}

}
