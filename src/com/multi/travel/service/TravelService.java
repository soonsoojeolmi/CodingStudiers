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
}
