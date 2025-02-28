package com.multi.travel.service;

import com.multi.travel.common.DBConnectionMgr;
import com.multi.travel.dao.TravelDao;
import com.multi.travel.dto.Travel;
import com.multi.travel.view.TravelApp;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TravelService {
    private final TravelDao travelDao;
    Connection con = null;
    DBConnectionMgr dbcp;

    public TravelService() {
        dbcp = DBConnectionMgr.getInstance();
        if (dbcp.getConnectionCount() == 0) {
            try {
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        travelDao = new TravelDao();
    }

    public ArrayList<Travel> selectByTitle(String TitleIncludes) {
        ArrayList<Travel> list;
        try {
            con = dbcp.getConnection();
            list = travelDao.selectByTitle(con, TitleIncludes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            if(con != null){
                dbcp.freeConnection(con);
            }
        }
        return list;
    }
}
