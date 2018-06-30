package front.dao;

import entity.User;

import java.sql.*;

public class CommonDao {
    public Connection conn;
    public PreparedStatement ps, ps1, ps2 = null;
    public ResultSet rs, rs1, rs2 = null;


    //链接数据库
    public Connection openDatabase() {
        String DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://" + "127.0.0.1" + ":3306/" + "java_easyshop" + "?user=" + "root" + "&password=" + "8080" + "&useUnicode=true&characterEncoding=utf8&useSSL=true";
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet executeQuerys(String preparedSql, String[] param) {
        try {
            ps = conn.prepareStatement(preparedSql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setString(i + 1, param[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    //关闭连接
    public void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public int executeUpdate(String preparedSql, User user) {
        int num = 0;
        try {
            ps = conn.prepareStatement(preparedSql);

            System.out.println(ps);
            num = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
