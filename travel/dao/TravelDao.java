package com.multi.travel.dao;

import com.multi.travel.dto.Travel;
import com.multi.travel.common.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


    private DBConnectionMgr pool = DBConnectionMgr.getInstance();

    public List<Travel> getTravelsByDistrict(String district, Connection con) {
        List<Travel> travelList = new ArrayList<>();
        String sql = "SELECT no, district, title, description, address, phone FROM travel WHERE district = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, district);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Travel travel = new Travel();
                    travel.setNo(rs.getInt("no"));
                    travel.setDistrict(rs.getString("district"));
                    travel.setTitle(rs.getString("title"));
                    travel.setDescription(rs.getString("description"));
                    travel.setAddress(rs.getString("address"));
                    travel.setPhone(rs.getString("phone"));
                    travelList.add(travel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB 조회 중 오류 발생", e);  //
        }
        return travelList;
    }
}
