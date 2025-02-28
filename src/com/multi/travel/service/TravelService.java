package com.multi.travel.service;

import com.multi.travel.common.DBConnectionMgr;
import com.multi.travel.dao.TravelDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TravelService 클래스는 관광지 관련 비즈니스 로직을 담당하는 서비스 계층입니다.
 * DAO(Data Access Object)와 연결하여 데이터베이스 작업을 수행하며,
 * 데이터베이스 커넥션 풀을 이용한 효율적인 연결 관리를 제공합니다.
 */
public class TravelService {

    // TravelDao 객체 선언: 데이터베이스에 접근하는 DAO 객체를 관리합니다.
    private final com.multi.travel.dao.TravelDao travelDao;

    // Connection 객체 선언: 데이터베이스 연결을 관리합니다.
    Connection con = null;

    // DBConnectionMgr 객체 선언: 커넥션 풀을 관리하는 객체입니다.
    DBConnectionMgr dbcp;

    /**
     * TravelService 기본 생성자
     * DBConnectionMgr 인스턴스를 초기화하고,
     * 초기 커넥션 개수를 설정하여 데이터베이스 연결을 준비합니다.
     */
    public TravelService() {
        // 싱글톤 패턴을 사용하여 DBConnectionMgr의 인스턴스를 가져옵니다.
        dbcp = DBConnectionMgr.getInstance();

        // 현재 사용 가능한 커넥션 개수가 0이면 초기 커넥션 개수를 설정합니다.
        if (dbcp.getConnectionCount() == 0) {
            try {
                // 초기 오픈할 커넥션 개수를 10개로 설정
                dbcp.setInitOpenConnections(10);
            } catch (SQLException e) {
                // SQLException 발생 시 예외를 던져 프로그램이 중단되지 않도록 처리
                throw new RuntimeException(e);
            }
        }

        // TravelDao 객체를 생성하여 DAO 계층과 연결을 설정
        this.travelDao = new TravelDao();
    }

    /**
     * insertTravel 메서드: 새로운 관광지 정보를 데이터베이스에 추가합니다.
     *
     * @param no          관광지 번호 (고유 식별자)
     * @param district    관광지가 속한 지역
     * @param title       관광지 이름 또는 제목
     * @param description 관광지에 대한 설명
     * @param address     관광지 주소
     * @param phone       관광지 연락처
     * @return            데이터 삽입 성공 여부 (1: 성공, 0: 실패)
     * @throws Exception  데이터베이스 연결 또는 SQL 실행 중 오류가 발생할 경우 예외 발생
     */
    public int insertTravel(int no, String district, String title, String description, String address, String phone) throws Exception {
        int result = 0;

        // 커넥션 풀에서 커넥션을 가져옴
        con = dbcp.getConnection();

        // 자동 커밋을 비활성화하여 트랜잭션을 수동으로 관리
        con.setAutoCommit(false);

        // TravelDao의 insertTravel 메서드를 호출하여 관광지 데이터를 삽입
        result = travelDao.insertTravel(no, district, title, description, address, phone);

        // 삽입 결과에 따라 트랜잭션을 커밋 또는 롤백
        if (result > 0) {
            con.commit();  // 삽입 성공 시 커밋
        } else {
            con.rollback();  // 삽입 실패 시 롤백
        }

        return result;  // 삽입 결과 반환 (1: 성공, 0: 실패)
    }
}
