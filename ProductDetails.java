

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.description.annotation.AnnotationDescription;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.jasper.tagplugins.jstl.core.Set;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.List;

import com.ecommerce.Color;
import com.ecommerce.EProuct;
import com.ecommerce.Finance;
import com.ecommerce.HibernateUtil;
import com.mysql.cj.Session;

/**
* Servlet implementation class ProductDetails
*/
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
        
        
    /**
* @see HttpServlet#HttpServlet()
*/
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                 try {
                        SessionFactory factory = HibernateUtil.getSessionFactory();
                        
                        org.hibernate.Session session = factory.openSession();
                        
                         
                        @SuppressWarnings({ "deprecation", "rawtypes" })
						java.util.List list = session.createQuery("from EProduct").list();
                        
                         PrintWriter out = response.getWriter();
                         out.println("<html><body>");
                         
                         out.println("<b>One to One Mapping</b><br>");
                         for(EProuct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                                 AnnotationDescription descrip = p.getPdescrip();
                                 out.println("<br>Description:" + ((Object) descrip).getDescrip());
                                 out.println("<hr>");
                         }                       
                         out.println("<b>One to Many and Many to One Mapping</b><br>");
                         for(EProuct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());                           
                                 List colors = p.getColors();
                                 out.println("<br>Colors: <ul>");
                                 for(Color c: colors) {
                                         out.print("<li>" + c.getName() + "</li>");
                                 }
                                 out.println("</ul>");
                                 out.println("<hr>");
                                 
                         }
                         out.println("<b>Many to Many Mapping</b><br>");
                         for(EProuct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                                 Set finances= p.getFinance();
                                 out.println("<br>Finance Options : <ul>");
                                 for(Finance f: finances) {
                                         out.print("<li>" + f.getFtype() + "</li>");
                                 }
                                 out.println("</ul>");
                                 out.println("<hr>");
                         }
                         
                         
                                session.close();

                     out.println("</body></html>");
                     
                     
                 } catch (Exception ex) {
                         throw ex;
                 }
                    
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}


