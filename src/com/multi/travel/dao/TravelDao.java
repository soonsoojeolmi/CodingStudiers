package com.multi.travel.dao;

import com.multi.travel.common.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TravelDao {
    public int deleteTravel(Connection con, int no) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM TRAVEL WHERE NO = ?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, no);


            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionMgr dbcp = new DBConnectionMgr();
            dbcp.freeConnection(con, pstmt);

        }

        return result;


    }

}
