package com.multi.travel.controller;

import com.multi.travel.dto.Travel;
import com.multi.travel.service.TravelService;
import com.multi.travel.view.TravelApp;

import java.util.ArrayList;

public class TravelController {

    private TravelService travelService = new TravelService();

    public void selectByTitle(String TitleIncludes) {
        TravelApp travelApp = new TravelApp();
        try {
            ArrayList<Travel> list = travelService.selectByTitle(TitleIncludes);
            if(!list.isEmpty()) {
                travelApp.displayTravelList(list);
            }else{
                travelApp.displayNoData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            travelApp.displayError("관광지 조회 실패. 관리자에게 문의하세요.");
        }
    }
}