package com.multi.travel.dao;

import com.multi.travel.common.DBConnectionMgr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TravelDao {
    public int insertTravel(int no, String district, String title, String description, String address, String phone) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("드라이버로드성공");

            String user = "scott";
            String password = "tiger";
            String url = "jdbc:mysql://localhost:3306/scott?";

            con = DriverManager.getConnection(url, user, password);

            System.out.println("db 연결성공");

            con.setAutoCommit(false);
            System.out.println("오토커밋 비활성화");


            String sql = "INSERT INTO travel (no, district, title, description, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setInt(1, no);
            ps.setString(2, district);
            ps.setString(3, title);
            ps.setString(4, description);
            ps.setString(5, address);
            ps.setString(6, phone);

            result = ps.executeUpdate();

            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}