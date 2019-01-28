package pruebaDeDespliegueServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/error")
public class errorServlet extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String msg=(String)req.getAttribute("javax.servlet.error.message");
	PrintWriter pw=null;
	pw=resp.getWriter();
	pw.println("<!DOCTYPE html>");
	pw.println("<html>");
	pw.println("<head>");
	pw.println("</head>");
	pw.println("<body>");
	pw.println("<h1>HA sucedido un error</h1>");
	pw.println("<p>"+msg+"</p>");
	pw.println("</body>");
	pw.println("</html>");
	
}
}
