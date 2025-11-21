package cse.oop2.hotelreservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class MapPanel extends JPanel {
    private Image bgImage;
    private List<Room> rooms = new ArrayList<>();

    // 좌표 따기용 변수
    private Point startPoint = null;
    
    // [중요] 원본(그림판) 해상도 기준 (기본값)
    private int referenceWidth = 1440;  
    private int referenceHeight = 1034;

    // 화면 비율 변수
    private double scaleX = 1.0;
    private double scaleY = 1.0;

    public MapPanel() {
        // 1. 마우스 리스너 (좌표 따기 + 예약 기능)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint(); // 클릭 시작
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point endPoint = e.getPoint(); // 클릭 끝

                if (startPoint != null) {
                    // 현재 화면상 드래그 좌표
                    int currentX = Math.min(startPoint.x, endPoint.x);
                    int currentY = Math.min(startPoint.y, endPoint.y);
                    int currentW = Math.abs(startPoint.x - endPoint.x);
                    int currentH = Math.abs(startPoint.y - endPoint.y);

                    // 드래그를 했다면 (좌표 따기 모드)
                    if (currentW > 10 && currentH > 10) {
                        // 비율 역산해서 '원본 좌표' 구하기
                        int realX = (int)(currentX / scaleX);
                        int realY = (int)(currentY / scaleY);
                        int realW = (int)(currentW / scaleX);
                        int realH = (int)(currentH / scaleY);
                        
                        System.out.println("========================================");
                        System.out.println("★ 복사용 코드: mapPanel.addRoom(\"방번호\", " + realX + ", " + realY + ", " + realW + ", " + realH + ");");
                        System.out.println("========================================");
                    } else {
                        // 그냥 클릭했다면 (예약 기능)
                        Point clickedPoint = e.getPoint();
                        for (Room r : rooms) {
                            // 현재 화면 비율에 맞게 늘어난 방의 위치 계산
                            Rectangle originalRect = r.getArea().getBounds();
                            Rectangle scaledRect = new Rectangle(
                                    (int)(originalRect.x * scaleX),
                                    (int)(originalRect.y * scaleY),
                                    (int)(originalRect.width * scaleX),
                                    (int)(originalRect.height * scaleY)
                            );

                            if (scaledRect.contains(clickedPoint)) {
                                r.toggleBooking();
                                repaint();
                                return;
                            }
                        }
                    }
                }
            }
        });

        // 2. 마우스 커서 변경
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean hovered = false;
                for (Room r : rooms) {
                    Rectangle originalRect = r.getArea().getBounds();
                    Rectangle scaledRect = new Rectangle(
                            (int)(originalRect.x * scaleX),
                            (int)(originalRect.y * scaleY),
                            (int)(originalRect.width * scaleX),
                            (int)(originalRect.height * scaleY)
                    );
                    
                    if (scaledRect.contains(e.getPoint())) {
                        hovered = true;
                        break;
                    }
                }
                setCursor(hovered ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // [핵심] MainFrame에서 이 메서드를 부를 수 있게 추가함!
    public void setReferenceSize(int w, int h) {
        this.referenceWidth = w;
        this.referenceHeight = h;
        repaint();
    }

    public void setBackgroundImage(String imageName) {
        try {
            URL url = getClass().getResource(imageName);
            if (url != null) {
                bgImage = new ImageIcon(url).getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void clearRooms() {
        rooms.clear();
        repaint();
    }

    public void addRoom(String id, int x, int y, int w, int h) {
        rooms.add(new Room(id, new Rectangle(x, y, w, h)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // 비율 계산
        if (referenceWidth > 0 && referenceHeight > 0) {
            scaleX = (double) panelWidth / referenceWidth;
            scaleY = (double) panelHeight / referenceHeight;
        }

        // 배경 그리기
        if (bgImage != null) {
            g2.drawImage(bgImage, 0, 0, panelWidth, panelHeight, this);
        }

        // 방 그리기 (투명 버튼 효과)
        for (Room r : rooms) {
            Rectangle originalRect = r.getArea().getBounds();

            // 좌표도 비율만큼 늘려서 그리기
            int scaledX = (int) (originalRect.x * scaleX);
            int scaledY = (int) (originalRect.y * scaleY);
            int scaledW = (int) (originalRect.width * scaleX);
            int scaledH = (int) (originalRect.height * scaleY);
            Rectangle scaledRect = new Rectangle(scaledX, scaledY, scaledW, scaledH);

            if (r.isBooked()) {
                g2.setColor(new Color(100, 100, 100, 180)); // 예약됨
            } else {
                g2.setColor(new Color(0, 255, 0, 50)); // 빈 방 (투명도 50)
            }
            g2.fill(scaledRect);
            
            // 테두리와 글씨는 안 그림 (투명 효과)
        }
    }
}