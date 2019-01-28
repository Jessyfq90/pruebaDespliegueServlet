package pruebaDeDespliegueServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/holamundo")
public class HolaMundoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
		resp.setContentType("text/html");
		PrintWriter pw= null;
		try {
			pw=resp.getWriter();
			pw.println("<html>");
			pw.println("	<head>");
			pw.println("		<title>Prueba Servlet</title>");
			pw.println("		<meta charset='utf-8'/>");
			pw.println("	</head>");
			pw.println("	<body>");
			pw.println("		<h1>Hola Mundo</h1>");
			pw.println("	</body>");
			pw.println("</html>");
		}finally {
			if(pw!=null)
				pw.close();
		}
		
	}
	
}
