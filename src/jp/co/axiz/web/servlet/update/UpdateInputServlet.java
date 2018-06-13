package jp.co.axiz.web.servlet.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axiz.web.entity.UserInfo;

/**
 * Servlet implementation class UpdateInputServlet
 */
@WebServlet("/updateInput")
public class UpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newName = request.getParameter("newName");
		String newTel = request.getParameter("newTel");
		String newPass = request.getParameter("newPass");

		HttpSession session = request.getSession();
		UserInfo afterUser = (UserInfo) session.getAttribute("afterUser");

		String errmsg = null;

		if (newPass == null || newPass.isEmpty()) {
			errmsg = "PASSは必須です";
		} else {
			afterUser.setPassword(newPass);
		}

		if (newTel == null || newTel.isEmpty()) {
			errmsg = "TELは必須です";
		} else {
			afterUser.setTelephone(newTel);
		}

		if (newName == null || newName.isEmpty()) {
			errmsg = "名前は必須です";
		} else {
			afterUser.setName(newName);
		}

		if (errmsg != null) {
			request.setAttribute("errmsg", errmsg);
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			return;
		}

		UserInfo beforeUser = (UserInfo) session.getAttribute("beforeUser");
		if (beforeUser.equals(afterUser)) {
			request.setAttribute("errmsg", "１項目以上変更してください");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			return;
		}

		if (beforeUser.getPassword().equals(afterUser.getPassword())) {
			request.setAttribute("rePass", afterUser.getPassword());
		}

		request.getRequestDispatcher("updateConfirm.jsp").forward(request, response);
	}

}
