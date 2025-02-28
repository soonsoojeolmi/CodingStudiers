package com.multi.travel.dao;

import com.multi.travel.common.DBConnectionMgr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TravelDao 클래스는 데이터베이스와 직접 연결하여 관광지 정보를 추가하는 역할을 합니다.
 * insertTravel 메서드를 통해 새로운 관광지 데이터를 travel 테이블에 삽입합니다.
 */
public class TravelDao {

    /**
     * insertTravel 메서드는 주어진 관광지 정보를 데이터베이스에 삽입하는 기능을 수행합니다.
     *
     * @param no          관광지 번호 (고유 식별자)
     * @param district    관광지가 위치한 지역
     * @param title       관광지의 이름
     * @param description 관광지 설명
     * @param address     관광지의 주소
     * @param phone       관광지의 연락처
     * @return 데이터 삽입 성공 시 1 이상의 값, 실패 시 0 반환
     */
    public int insertTravel(int no, String district, String title, String description, String address, String phone) {
        int result = 0; // 삽입 결과 값을 저장할 변수
        Connection con = null; // 데이터베이스 연결을 위한 Connection 객체
        PreparedStatement ps = null; // SQL 실행을 위한 PreparedStatement 객체

        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로드 성공");

            // 데이터베이스 접속 정보 설정
            String user = "scott"; // DB 사용자명
            String password = "tiger"; // DB 비밀번호
            String url = "jdbc:mysql://localhost:3306/scott?"; // DB URL

            // 데이터베이스 연결 생성
            con = DriverManager.getConnection(url, user, password);
            System.out.println("DB 연결 성공");

            // 자동 커밋 기능 비활성화 (트랜잭션 관리)
            con.setAutoCommit(false);
            System.out.println("오토커밋 비활성화");

            // SQL 문 작성 (travel 테이블에 새로운 관광지 정보 삽입)
            String sql = "INSERT INTO travel (no, district, title, description, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

            // PreparedStatement 객체 생성 및 SQL 문 설정
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);          // 첫 번째 '?'에 관광지 번호 설정
            ps.setString(2, district); // 두 번째 '?'에 지역 설정
            ps.setString(3, title);    // 세 번째 '?'에 관광지 이름 설정
            ps.setString(4, description); // 네 번째 '?'에 설명 설정
            ps.setString(5, address);  // 다섯 번째 '?'에 주소 설정
            ps.setString(6, phone);    // 여섯 번째 '?'에 전화번호 설정

            // SQL 실행 및 결과 저장 (1 이상의 값 반환 시 성공)
            result = ps.executeUpdate();

            // 삽입 성공 시 commit, 실패 시 rollback 수행
            if (result > 0) {
                con.commit(); // 변경 사항 저장
            } else {
                con.rollback(); // 변경 사항 취소
            }

        } catch (ClassNotFoundException e) {
            // 드라이버 로드 실패 시 예외 발생
            throw new RuntimeException("JDBC 드라이버를 찾을 수 없습니다.", e);
        } catch (SQLException e) {
            // SQL 실행 중 오류 발생 시 예외 발생
            throw new RuntimeException("데이터베이스 작업 중 오류가 발생했습니다.", e);
        } finally {
            // 자원 해제 (PreparedStatement 및 Connection)
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException("데이터베이스 연결 해제 중 오류가 발생했습니다.", e);
            }
        }

        // 실행 결과 반환 (1 이상이면 성공, 0이면 실패)
        return result;
    }
}
