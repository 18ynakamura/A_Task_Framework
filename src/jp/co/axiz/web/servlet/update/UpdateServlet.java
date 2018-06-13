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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			request.setAttribute("errmsg", "必須項目を入力してください");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}

		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.findById(Integer.parseInt(id));

		if (userInfo == null) {
			request.setAttribute("errmsg", "入力されたデータは存在しません");
			request.getRequestDispatcher("update.jsp").forward(request, response);
			return;
		}

		UserInfo afterUser = new UserInfo();
		afterUser.setId(userInfo.getId());
		afterUser.setName(userInfo.getName());
		afterUser.setTelephone(userInfo.getTelephone());
		afterUser.setPassword(userInfo.getPassword());

		HttpSession session = request.getSession();
		session.setAttribute("beforeUser", userInfo);
		session.setAttribute("afterUser", afterUser);
		request.getRequestDispatcher("updateInput.jsp").forward(request, response);
	}

}
