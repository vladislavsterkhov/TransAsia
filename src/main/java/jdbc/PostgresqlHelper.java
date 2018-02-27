package jdbc;

import jdbc.pojo.Part;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlHelper {

    private Connection connection;

    public PostgresqlHelper() throws ClassNotFoundException, SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres","postgres", "toor");
    }




    public List<Part> gePart(HttpSession session, HttpServletRequest request) {
        List<Part> stat = new ArrayList<>();
        Statement statment = null;

        try {
            statment = connection.createStatement();

            String query =
                    "select * from part where 1=1 " ;

            Object pn = session.getAttribute("pn");
            Object partName = session.getAttribute("partName");
            Object vendor = session.getAttribute("vendor");
            Object qty = session.getAttribute("qty");
            Object afterShipped = session.getAttribute("afterShipped");
            Object beforeShipped = session.getAttribute("beforeShipped");
            Object afterReceive = session.getAttribute("afterReceived");
            Object beforeReceive = session.getAttribute("beforeReceived");
            Object name = request.getAttribute("name");


            if (pn!=null && !((String)pn).isEmpty())
                query+=" and partNumber like '%"+pn+"%'";

            if (partName!=null && !((String)partName).isEmpty())
                query+=" and partName like '%"+partName+"%'";

            if (vendor!=null && !((String)vendor).isEmpty())
                query+=" and vendor like '%"+vendor+"%'";

            if (qty!=null && !((String)qty).isEmpty())
                query+=" and qty >= "+qty+"";

            if (afterShipped!=null && !((String)afterShipped).isEmpty())
                query+=" and (shipped > '"+afterShipped+"'::date or shipped is null)";

            if (beforeShipped!=null && !((String)beforeShipped).isEmpty())
                query+=" and (shipped < '"+beforeShipped+"'::date or shipped is null)";

            if (afterReceive!=null && !((String)afterReceive).isEmpty())
                query+=" and (receive > '"+afterReceive+"'::date or receive is null)";

            if (beforeReceive!=null && !((String)beforeReceive).isEmpty())
                query+=" and (receive < '"+beforeReceive+"'::date or receive is null)";

            if (name!=null && !((String)name).isEmpty())
                query+=" ORDER BY "+name;

            ResultSet resultSet = statment.executeQuery(query);

            while(resultSet.next()){
                stat.add(new Part(
                        resultSet.getString("partName"),
                        resultSet.getString("partNumber"),
                        resultSet.getString("vendor"),
                        resultSet.getInt("qty"),
                        resultSet.getDate("shipped"),
                        resultSet.getDate("receive")
                ));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                statment.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e){

            }
        }

        return stat;


    }


}
