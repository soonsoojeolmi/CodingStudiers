package com.multi.travel.service;

import com.multi.travel.common.DBConnectionMgr;
import com.multi.travel.dao.TravelDao;

import java.sql.Connection;
import java.sql.SQLException;

public class TravelService {

    private final com.multi.travel.dao.TravelDao travelDao;
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

        this.travelDao = new TravelDao();

    }

    public int insertTravel(int no, String district, String title, String description, String address, String phone) throws Exception {
        int result = 0;
        con=dbcp.getConnection();
        con.setAutoCommit(false);
        result = travelDao.insertTravel(no, district, title, description, address, phone);

        if(result > 0){
            con.commit();
        }else{
            con.rollback();
        }
        return result;
    }
}
