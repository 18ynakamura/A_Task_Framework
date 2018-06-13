package jp.co.axiz.web.servlet.update;

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
 * Servlet implementation class UpdateConfirmServlet
 */
@WebServlet("/updateConfirm")
public class UpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rePass = request.getParameter("rePass");

		HttpSession session = request.getSession();
		UserInfo afterUser = (UserInfo) session.getAttribute("afterUser");

		if (!afterUser.getPassword().equals(rePass)) {
			request.setAttribute("errmsg", "前画面で入力したパスワードと一致しません");
			request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);
			return;
		}

		UserInfoDao userInfoDao = new UserInfoDao();
		userInfoDao.update(afterUser);

		session.removeAttribute("beforeUser");
		session.removeAttribute("afterUser");

		session.setAttribute("updateUserId", afterUser.getId());
		request.getRequestDispatcher("updateResult.jsp").forward(request, response);
	}

}
