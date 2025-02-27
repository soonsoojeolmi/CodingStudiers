package com.multi.travel.controller;

import com.multi.travel.service.TravelService;
import com.multi.travel.view.TravelApp;

public class TravelController {

    private TravelService travelService = new TravelService();


    public void deleteTravel(int no) {
        TravelApp travelApp = new TravelApp();

        try {
            int result = travelService.deleteTravel(no);

            if (result > 0) {
                travelApp.displayDeleteSuccess("관광지 삭제가 완료되었습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            travelApp.displayDeleteError("관광지 삭제 실패, 관리자에게 문의하세요.");
        }

    }
}