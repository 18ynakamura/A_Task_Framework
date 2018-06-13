package jp.co.axiz.web.servlet.insert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.UserInfo;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String pass = request.getParameter("pass");

		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setTelephone(tel);
		userInfo.setPassword(pass);

		HttpSession session = request.getSession();
		session.setAttribute("registerUser", userInfo);

		if (name == null || name.isEmpty()) {
			request.setAttribute("errmsg", "名前は必須です");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		if (tel == null || tel.isEmpty()) {
			request.setAttribute("errmsg", "TELは必須です");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		if (pass == null || pass.isEmpty()) {
			request.setAttribute("errmsg", "PASSは必須です");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);
	}

}
