package jp.co.axiz.web.servlet.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		UserInfo cond = new UserInfo();
		try {
			cond.setId(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// do nothing
		}
		cond.setName(name);
		cond.setTelephone(tel);

		UserInfoDao userInfoDao = new UserInfoDao();
		List<UserInfo> list = userInfoDao.find(cond);

		if (list.isEmpty()) {
			request.setAttribute("errmsg", "入力されたデータはありませんでした");
			request.getRequestDispatcher("select.jsp").forward(request, response);
		} else {
			request.setAttribute("userlist", list);
			request.getRequestDispatcher("selectResult.jsp").forward(request, response);
		}
	}

}
