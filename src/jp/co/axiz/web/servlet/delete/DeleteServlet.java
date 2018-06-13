package jp.co.axiz.web.servlet.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axiz.web.dao.UserInfoDao;
import jp.co.axiz.web.entity.UserInfo;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
			request.getRequestDispatcher("delete.jsp").forward(request, response);
			return;
		}

		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfo userInfo = userInfoDao.findById(Integer.parseInt(id));

		if (userInfo == null) {
			request.setAttribute("errmsg", "入力されたデータは存在しません");
			request.getRequestDispatcher("delete.jsp").forward(request, response);
			return;
		}

		request.setAttribute("deleteUser", userInfo);
		request.getRequestDispatcher("deleteConfirm.jsp").forward(request, response);
	}

}
