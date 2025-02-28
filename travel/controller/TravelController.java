package com.multi.travel.controller;

import com.multi.travel.service.TravelService;
import com.multi.travel.dto.Travel;

import java.sql.Connection;
import java.util.List;
import com.multi.travel.view.TravelApp;

public class TravelController {
    public void deleteTravel(int no, Connection con) {
        TravelApp travelApp = new TravelApp();

        try {
            int result = travelService.deleteTravel(no, con);

            if (result > 0) {
                travelApp.displayDeleteSuccess("관광지 삭제가 완료되었습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            travelApp.displayDeleteError("관광지 삭제 실패, 관리자에게 문의하세요.");
        }

    }
    private TravelService travelService = new TravelService();

    // 권역별 관광지 조회 메서드
    public void showTravelsByDistrict(String district) {
        List<Travel> travelList = travelService.getTravelsByDistrict(district);

        if (travelList.isEmpty()) {
            System.out.println("해당 권역에 등록된 관광지가 없습니다.");
        } else {
            System.out.println("\n=== " + district + " 지역 관광지 목록 ===");
            for (Travel travel : travelList) {
                System.out.println("[" + travel.getNo() + "] " + travel.getTitle() + " - " + travel.getAddress());
            }
        }
    }
}
