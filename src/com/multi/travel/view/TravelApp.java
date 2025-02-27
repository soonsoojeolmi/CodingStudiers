package com.multi.travel.view;

import com.multi.travel.controller.TravelController;

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

}