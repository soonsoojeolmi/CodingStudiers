package com.multi.travel.view;

import com.multi.travel.controller.TravelController;
import com.multi.travel.dto.Travel;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TravelApp 클래스는 사용자와 상호작용하는 콘솔 기반의 UI를 제공합니다.
 * 사용자가 입력한 데이터를 TravelController를 통해 처리하고,
 * 결과를 화면에 출력하는 역할을 합니다.
 */
public class TravelApp {

    // Scanner 객체: 사용자 입력을 처리하기 위한 입력 도구
    private static Scanner sc = new Scanner(System.in);

    // TravelController 객체 생성: 사용자의 요청을 컨트롤러로 전달하여 처리
    private TravelController travelController = new TravelController();

    /**
     * 프로그램의 메인 메뉴를 실행하는 메서드입니다.
     * 사용자는 메뉴를 선택하여 특정 기능을 수행할 수 있습니다.
     */
    public void mainMenu() {
        int choice = 0; // 사용자의 메뉴 선택을 저장할 변수

        do {
            try {
                // 메뉴 출력
                System.out.println("\n*******관광명소 관리 프로그램********");
                System.out.println("1.전체 목록 ");
                System.out.println("2.권역별 관광지");
                System.out.println("3.관광지 검색");
                System.out.println("4.관광지 추가");
                System.out.println("5.관광지 삭제");
                System.out.println("6.프로그램 종료");
                System.out.print("번호선택 : ");

                choice = sc.nextInt();  // 사용자 입력 받기

                // 사용자의 선택에 따라 실행할 기능 결정
                switch (choice) {
                    case 1:
                        // 전체 관광지 목록 조회 기능 (구현 필요)
                        break;
                    case 2:
                        // 권역별 관광지 조회 기능 (구현 필요)
                        break;
                    case 3:
                        // 관광지 검색 기능 (구현 필요)
                        break;
                    case 4:
                        // 새로운 관광지 추가 기능
                        Travel travel = addTravel();
                        if (travel != null) {
                            // 디버깅 메시지 출력 (입력된 값 확인)
                            System.out.println("디버깅 - insertTravel 호출: " + travel.toString());

                            // 컨트롤러를 통해 관광지 추가 요청
                            travelController.insertTravel(
                                    travel.getNo(),
                                    travel.getDistrict(),
                                    travel.getTitle(),
                                    travel.getDescription(),
                                    travel.getAddress(),
                                    travel.getPhone()
                            );
                        } else {
                            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                        }
                        break;
                    case 5:
                        // 관광지 삭제 기능 (구현 필요)
                        break;
                    case 6:
                        // 프로그램 종료 여부 확인
                        System.out.println("정말로 끝내시겠습니까??(y/n)");
                        if ('y' == sc.next().toLowerCase().charAt(0)) {
                            return;  // 프로그램 종료
                        }
                        break;
                    default:
                        // 잘못된 번호 입력 시 안내 메시지 출력
                        System.out.println("번호를 잘못입력하였습니다.");
                        break;
                }

            } catch (InputMismatchException e) {
                // 사용자가 숫자가 아닌 값을 입력했을 경우 예외 처리
                System.out.println("유효한 숫자를 입력해 주세요.");
                sc.nextLine();  // 잘못된 입력을 버퍼에서 제거하여 오류 방지
            }
        } while (true);  // 사용자가 종료를 선택할 때까지 반복 실행
    }

    /**
     * 새로운 관광지 정보를 입력받아 Travel 객체를 생성하는 메서드입니다.
     *
     * @return 입력된 정보를 바탕으로 생성된 Travel 객체 (유효한 입력이 아닐 경우 null 반환)
     */
    public Travel addTravel() {
        System.out.println("관광지 정보를 입력하세요.");

        try {
            // 사용자 입력 받기
            System.out.print("번호: ");
            int no = sc.nextInt(); // 관광지 번호 입력
            sc.nextLine(); // 숫자 입력 후 남아있는 개행 문자 처리

            System.out.print("권역: ");
            String district = sc.nextLine(); // 권역 입력

            System.out.print("제목: ");
            String title = sc.nextLine(); // 관광지 제목 입력

            System.out.print("설명: ");
            String description = sc.nextLine(); // 관광지 설명 입력

            System.out.print("주소: ");
            String address = sc.nextLine(); // 주소 입력

            System.out.print("전화번호: ");
            String phone = sc.nextLine(); // 전화번호 입력

            // 디버깅용 출력: 입력된 값 확인
            System.out.println("디버깅 - 입력된 값 확인: " + no + ", " + district + ", " + title + ", " + description + ", " + address + ", " + phone);

            // 필수 입력값 확인: 하나라도 비어 있으면 null 반환
            if (title.isEmpty() || district.isEmpty() || description.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                System.out.println("입력값이 올바르지 않습니다. 모든 필드를 입력하세요.");
                return null;
            }

            // 입력값을 바탕으로 Travel 객체 생성 후 반환
            return new Travel(no, district, title, description, address, phone);
        } catch (InputMismatchException e) {
            // 입력 형식이 올바르지 않은 경우 예외 처리
            System.out.println("올바른 형식의 데이터를 입력하세요.");
            sc.nextLine(); // 버퍼 비우기
            return null;
        }
    }

    /**
     * 관광지 추가 성공 메시지를 출력하는 메서드입니다.
     *
     * @param message 성공 메시지
     */
    public void displayInsertSuccess(String message) {
        System.out.println("[성공] " + message);
    }

    /**
     * 관광지 추가 실패 메시지를 출력하는 메서드입니다.
     *
     * @param message 오류 메시지
     */
    public void displayInsertError(String message) {
        System.out.println("[오류] " + message);
    }
}
