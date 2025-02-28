package com.multi.travel.view;

import com.multi.travel.controller.TravelController;
import com.multi.travel.dto.Travel;

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
                        Travel travel = addTravel();
                        if (travel != null) {
                            System.out.println("디버깅 - insertTravel 호출: " + travel.toString());
                            travelController.insertTravel(travel.getNo(), travel.getDistrict(), travel.getTitle(), travel.getDescription(), travel.getAddress(), travel.getPhone());
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                        }

                        break;
                    case 5:

                        break;
                    case 6:
                        System.out.println("정말로 끝내시겠습니까??(y/n)");
                        if ('y' == sc.next().toLowerCase().charAt(0)) {
                            //memberController.exitProgram();
                            return;  // 프로그램 종료
                        }
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

    public Travel addTravel() {
        System.out.println("관광지 정보를 입력하세요.");
        try {
            System.out.print("번호: ");
            int no = sc.nextInt();
            sc.nextLine();
            System.out.print("권역: ");
            String district = sc.nextLine();
            System.out.print("제목: ");
            String title = sc.nextLine();
            System.out.print("설명: ");
            String description = sc.nextLine();
            System.out.print("주소: ");
            String address = sc.nextLine();
            System.out.print("전화번호: ");
            String phone = sc.nextLine();

            // 디버깅용 출력 추가
            System.out.println("디버깅 - 입력된 값 확인: " + no + ", " + district + ", " + title + ", " + description + ", " + address + ", " + phone);


            if (title.isEmpty() || district.isEmpty() || description.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                System.out.println("입력값이 올바르지 않습니다. 모든 필드를 입력하세요.");
                return null;
            }

            return new Travel(no, district, title, description, address, phone);
        } catch (InputMismatchException e) {
            System.out.println("올바른 형식의 데이터를 입력하세요.");
            sc.nextLine();
            return null;
        }
    }

    public void displayInsertSuccess(String message) {
        System.out.println("[성공] " + message);
    }

    public void displayInsertError(String message) {
        System.out.println("[오류] " + message);
    }
}