package com.multi.travel.service;

import com.multi.travel.common.DBConnectionMgr;
import com.multi.travel.dao.TravelDao;
import com.multi.travel.dto.Travel;

import java.sql.Connection;
import java.util.List;

public class TravelService {
    private final TravelDao travelDao;
    private final DBConnectionMgr dbcp;

    public TravelService() {
        this.dbcp = DBConnectionMgr.getInstance();
        this.travelDao = new TravelDao();
    }
    public int deleteTravel(int no, Connection con) {
        int result = 0;
        try {
            con = dbcp.getConnection();
            con.setAutoCommit(false);
            result = travelDao.deleteTravel(con, no);

            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                dbcp.freeConnection(con);
            }
        }

        return result;
    }

    public List<Travel> getTravelsByDistrict(String district) {
        try (Connection con = dbcp.getConnection()) {
            return travelDao.getTravelsByDistrict(district, con);
        } catch (Exception e) {
            throw new RuntimeException("조회 중 오류 발생", e);
        }
    }
}
