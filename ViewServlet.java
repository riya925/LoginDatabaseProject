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

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/gtukb","root","");
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery("select * from login");
			
			while(rs.next())
			{
				String us=rs.getString("user");
				String pa=rs.getString("pass");
				
				System.out.println(" "+us+" , "+pa);
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
}