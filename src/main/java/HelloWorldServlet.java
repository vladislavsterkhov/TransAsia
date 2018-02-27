import jdbc.PostgresqlHelper;
import jdbc.pojo.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    static PostgresqlHelper postgresqlHelper;

    static {
        try {
            postgresqlHelper = new PostgresqlHelper();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("pn", request.getParameter("pn")!=null ? request.getParameter("pn") : session.getAttribute("pn"));
        session.setAttribute("partName", request.getParameter("partName")!=null ? request.getParameter("partName") : session.getAttribute("partName"));
        session.setAttribute("vendor", request.getParameter("vendor")!=null ? request.getParameter("vendor") : session.getAttribute("vendor"));
        session.setAttribute("qty", request.getParameter("qty")!=null ? request.getParameter("qty") : session.getAttribute("qty"));
        session.setAttribute("afterShipped", request.getParameter("afterShipped")!=null ? request.getParameter("afterShipped") : session.getAttribute("afterShipped"));
        session.setAttribute("beforeShipped", request.getParameter("beforeShipped")!=null ? request.getParameter("beforeShipped") : session.getAttribute("beforeShipped"));
        session.setAttribute("afterReceived", request.getParameter("afterReceived")!=null ? request.getParameter("afterReceived") : session.getAttribute("afterReceived"));
        session.setAttribute("beforeReceived", request.getParameter("beforeReceived")!=null ? request.getParameter("beforeReceived") : session.getAttribute("beforeReceived"));


        List<Part> entityList = postgresqlHelper.gePart(session, request);
        request.setAttribute("listEntity", entityList);

            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }



    String sort="";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String newSort = request.getParameter("name");

        if (newSort!=null) {
            if (sort.equals(newSort)){
                newSort = sort.replace("ASC", "DESC");
            }

            request.setAttribute("name", newSort);
            sort=newSort;
        }

        if (newSort==null)
            sort="";


        System.out.println(sort);

        doGet(request, response);
    }
}