import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String us=request.getParameter("tus");
		String pa=request.getParameter("tpa");
		String sub=request.getParameter("sub");
		
		if(sub.equals("LOGIN"))
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/gtukb","root","");
				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery("select * from login where user='"+us+"' and pass='"+pa+"'");
				
				if(rs.next())
				{
					request.getRequestDispatcher("/nextpage.html").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("/error.html").forward(request, response);
				}
				
				rs.close();
				st.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("\n LOGIN ERROR : "+e.getMessage());
			}
		}
		else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/gtukb","root","");
				Statement st=con.createStatement();
				
				st.executeUpdate("insert into login values ('"+us+"' ,'"+pa+"')");
				
				st.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("\n LOGIN ERROR : "+e.getMessage());
			}
		}
	}
}