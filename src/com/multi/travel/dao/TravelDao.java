package com.multi.travel.dao;

import com.multi.travel.common.DBConnectionMgr;
import com.multi.travel.dto.Travel;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class TravelDao {
    private Properties prop = null;

    public TravelDao() {
        try {
            prop = new Properties();
            prop.load(new FileReader("resources/query.properties"));
            //prop.loadFromXML(new FileInputStream("resources/query_m.xml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Travel> selectByTitle(Connection conn, String TitleIncludes) {
        ArrayList<Travel> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = prop.getProperty("selectByTitle");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, TitleIncludes);
            rs = pstmt.executeQuery();


            while(rs.next()){
                Travel t = new Travel();

                t.setNo(rs.getInt("no"));
                t.setDistrict(rs.getString("district"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setAddress(rs.getString("address"));
                t.setPhone(rs.getString("phone"));

                list.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBConnectionMgr dbcp = new DBConnectionMgr();
            dbcp.freeConnection(conn, pstmt, rs);
        }
        return list;
    }
}
