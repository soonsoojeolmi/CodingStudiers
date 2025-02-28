package com.multi.travel.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {

        private static final String URL = "jdbc:mysql://localhost:3306/travel?serverTimezone=UTC";
        private static final String USER = "scott";
        private static final String PASSWORD = "tiger";

        // 데이터베이스 연결을 반환하는 메서드
        public static Connection getConnection() {
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("DB 연결 성공!");
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("DB 연결 실패: " + e.getMessage());
            }
            return conn;
        }

        // 특정 권역의 관광지를 조회하는 메서드
        public static List<String> getTravelsByDistrict(String district) {
            List<String> travelList = new ArrayList<>();
            String sql = "SELECT title FROM travel WHERE district = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, district);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    travelList.add(rs.getString("title"));
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("데이터 조회 실패: " + e.getMessage());
            }
            return travelList;
        }

        // 연결 종료 메서드
        public static void close(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("DB 연결 종료");
                } catch (SQLException e) {
                    System.out.println("DB 연결 종료 실패: " + e.getMessage());
                }
            }
        }
    }

//33333












