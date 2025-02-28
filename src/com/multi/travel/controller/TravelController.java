package com.multi.travel.controller;

import com.multi.travel.dto.Travel;
import com.multi.travel.service.TravelService;
import com.multi.travel.view.TravelApp;

/**
 * TravelController 클래스는 관광지 정보를 관리하는 컨트롤러 역할을 합니다.
 * 사용자의 입력을 받아 서비스를 호출하고, 그 결과를 뷰에 전달하는 역할을 합니다.
 */
public class TravelController {

    // TravelService 객체 생성: 비즈니스 로직을 수행하는 서비스 계층과 연결합니다.
    private TravelService travelService = new TravelService();

    /**
     * insertTravel 메서드는 새로운 관광지 정보를 추가하는 기능을 담당합니다.
     *
     * @param no          관광지 번호 (고유한 식별자)
     * @param district    관광지가 위치한 지역
     * @param title       관광지의 이름 또는 제목
     * @param description 관광지에 대한 설명
     * @param address     관광지의 주소
     * @param phone       관광지의 연락처
     */
    public void insertTravel(int no, String district, String title, String description, String address, String phone) {
        // TravelApp 객체 생성: 사용자에게 결과를 출력하는 역할을 합니다.
        TravelApp travelApp = new TravelApp();

        try {
            // TravelService의 insertTravel 메서드를 호출하여 관광지 정보를 데이터베이스에 추가합니다.
            int result = travelService.insertTravel(no, district, title, description, address, phone);

            // 결과가 0보다 크면 (성공적으로 추가되면) 성공 메시지를 출력합니다.
            if (result > 0) {
                travelApp.displayInsertSuccess("관광지 추가가 완료되었습니다.");
            }
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지를 출력하고, 예외 정보를 출력합니다.
            e.printStackTrace();
            travelApp.displayInsertError("관광지 추가 실패, 관리자에게 문의하세요.");
        }
    }
}
