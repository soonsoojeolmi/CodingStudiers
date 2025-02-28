package com.multi.travel.view;

import com.multi.travel.controller.TravelController;
import com.multi.travel.dto.Travel;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TravelApp {

    private static Scanner sc = new Scanner(System.in);
    private TravelController travelController = new TravelController();

    public void mainMenu() {
        int choice = 0;

        do {
            try {
                System.out.println("\n*******관광명소 관리 프로그램********");
                System.out.println("1.전체 목록 ");
                System.out.println("2.권역별 관광지");
                System.out.println("3.관광지 검색");
                System.out.println("4.관광지 추가");
                System.out.println("5.관광지 삭제");
                System.out.println("6.프로그램 종료");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();  // 사용자로부터 입력 받기

                switch (choice) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        travelController.selectByTitle(inputTitleName());
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    default:
                        System.out.println("번호를 잘못입력하였습니다.");
                        break;
                }

            } catch (InputMismatchException e) {
                // 숫자가 아닌 값이 입력되었을 때 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                sc.nextLine();  // 잘못된 입력을 버퍼에서 제거
            }
        } while (true);  // 무한 루프
    }

    private String inputTitleName() {
        System.out.println("찾는 키워드 입력 : ");
        return sc.next();
    }

    public void displayTravel(Travel travel) {
        System.out.println("\n조회된 관광지 정보는 다음과 같습니다.");
        System.out.println("\n번호\t권역\t제목\t설명\t주소\t전화번호");
        System.out.println("------------------------------------");

        System.out.println(travel);
    }

    public void displayNoData() {
        System.out.println("조회된 결과가 없습니다.");
    }

    public void displayError(String message) {
        System.out.println("서비스 요청 처리 실패 :"+ message);
    }

    public void displayTravelList(ArrayList<Travel> list) {
        System.out.println("\n조회된 관광지 정보는 다음과 같습니다.");
        System.out.println("\n번호\t권역\t제목\t설명\t주소\t전화번호");
        System.out.println("------------------------------------");

        for (Travel t : list) {
            System.out.println(t);
        }
    }
}