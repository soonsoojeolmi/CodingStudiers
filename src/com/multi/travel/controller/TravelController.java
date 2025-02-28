package com.multi.travel.controller;

import com.multi.travel.dto.Travel;
import com.multi.travel.service.TravelService;
import com.multi.travel.view.TravelApp;

public class TravelController {

    private TravelService travelService = new TravelService();

    public void insertTravel(int no, String district, String title, String description, String address, String phone){
        TravelApp travelApp = new TravelApp();

        try {
            int result = travelService.insertTravel(no, district, title, description, address, phone);

            if (result > 0) {
                travelApp.displayInsertSuccess("관광지 추가가 완료되었습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            travelApp.displayInsertError("관광지 추가 실패, 관리자에게 문의하세요.");
        }
    }
}