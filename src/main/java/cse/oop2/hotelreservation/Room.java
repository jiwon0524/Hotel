package cse.oop2.hotelreservation; // [핵심] 이 줄이 꼭 있어야 합니다!

import java.awt.Shape;

public class Room {
    private String id;        // 방 번호
    private Shape area;       // 방의 위치와 크기
    private boolean isBooked; // 예약 여부

    public Room(String id, Shape area) {
        this.id = id;
        this.area = area;
        this.isBooked = false; // 기본은 빈 방
    }

    // Getter & Setter
    public String getId() { return id; }
    public Shape getArea() { return area; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
    
    // 상태 토글 (클릭 시 사용)
    public void toggleBooking() { isBooked = !isBooked; }
}