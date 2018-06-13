package jp.co.axiz.web.servlet.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;

/**
 * Servlet implementation class InsertConfirmServlet
 */
@WebServlet("/insertConfirm")
public class InsertConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rePass = request.getParameter("rePass");

		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("registerUser");

		if (!userInfo.getPassword().equals(rePass)) {
			request.setAttribute("errmsg", "前画面で入力したパスワードと一致しません");
			request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);
			return;
		}

		UserInfoDao userInfoDao = new UserInfoDao();
		userInfoDao.register(userInfo);

		session.removeAttribute("registerUser");
		session.setAttribute("registerUserId", userInfo.getId());

		request.getRequestDispatcher("insertResult.jsp").forward(request, response);
	}

}
