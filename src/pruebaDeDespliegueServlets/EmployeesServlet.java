package pruebaDeDespliegueServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/empleados")
public class EmployeesServlet extends HttpServlet {

	private DataSource pool;
	
	@Override
	public void init() throws ServletException {

			InitialContext context;
			try {
				context = new InitialContext();
				pool=(DataSource)context.lookup("java:comp/env/jdbc/employees");//java:comp/env/el name que pusimos en el context.xml
				
			} catch (NamingException e) {
				throw new ServletException("Recurso de bases de datos no encontrado",e);
			}
			
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		Connection conexion=null;
		ResultSet rs=null;
		resp.setContentType("text/html");
		PrintWriter pw= null;
		try {
			conexion=pool.getConnection();
			Statement st=conexion.createStatement();
			rs=st.executeQuery("select * from employees");
			
			pw=resp.getWriter();
		
			
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("	<head>");
			pw.println("		<title>Empleados Servlet</title>");
			pw.println("		<meta charset='utf-8'/>");
			pw.println("	</head>");
			pw.println("	<body>");
			pw.println("		<table>");
			pw.println("			<tr>");
			pw.println("				<th>Nº Empleado</th>");
			pw.println("				<th>Fecha de nacimiento</th>");
			pw.println("				<th>Nombre</th>");
			pw.println("				<th>Género</th>");
			pw.println("				<th>Fecha de contratación</th>");
			pw.println("			</tr>");
			while(rs.next()) {
				pw.println("			<tr>");
				pw.println("				<td>"+rs.getInt("emp_no")+"</td>");
				pw.println("				<td>"+rs.getDate("birth_date")+"</td>");
				pw.println("				<td>"+rs.getString("first_name")+" "+rs.getString("last_name")+"</td>");
				pw.println("				<td>"+rs.getString("gender")+"</td>");
				pw.println("				<td>"+rs.getDate("hire_date")+"</td>");
				pw.println("			</tr>");
			}
			pw.println("		</table>");
			pw.println("	</body>");
			pw.println("</html>");
		
		} catch (SQLException e) {//para que haya la excepcion que haya, el programa haga lo mismo
			throw new ServletException(e);
		} finally {
			
			try {
				if(rs!=null)
					rs.close();
				if(conexion!=null)
					conexion.close();
				
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		if(pw!=null)//lo cerramos fuera por si lanza el throw tiene que esperar la respuesta y si lo cerramos dentro error.
			pw.close();
	}


}
