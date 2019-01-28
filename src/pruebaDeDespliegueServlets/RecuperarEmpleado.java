package pruebaDeDespliegueServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RecuperarEmpleado")
public class RecuperarEmpleado extends HttpServlet {
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conexion=null;
		ResultSet rs=null;
		PrintWriter pw=null;
		
		//tipo de la respuesta
		resp.setContentType("text/html");
		//cojo la fecha del formulario
		String fecha=req.getParameter("fecha");
		try {
			//inicio la conexcion con la base de datos
			//Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection("jdbc:mysql://localhost/employees","root","practicas");
			Statement st=conexion.createStatement();
			
			//abro el printwriter
			pw=resp.getWriter();
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("	<head>");
			pw.println("	</head>");
			pw.println("	<body>");
			//pw.println("		<h1>La fecha seleccionada es: "+fecha+"</h1>");
			pw.println("		<table>");
			pw.println("			<tr>");
			pw.println("				<th>Nombre</th>");
			pw.println("				<th>Apellidos</th>");
			pw.println("				<th>fecha contratación</th>");
			pw.println("			</tr>");
			rs=st.executeQuery("select * from employees where hire_date > "+req.getParameter("fecha"));//mirar como hacer la consulta
			while(rs.next()) {
				pw.println("			<tr>");
				pw.println("				<td>"+rs.getString("first_name")+"</td>");
				pw.println("				<td>"+rs.getString("last_name")+"</td>");
				pw.println("				<td>"+rs.getDate("hire_date")+"</td>");
				pw.println("			</tr>");
			}
			pw.println("		</table>");
			pw.println("	</body>");
			pw.println("</html>");
			
		} catch (SQLException e) {
			throw new ServletException(e);
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(conexion!=null)
					conexion.close();
				
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			
			
		}
		
	}
}
