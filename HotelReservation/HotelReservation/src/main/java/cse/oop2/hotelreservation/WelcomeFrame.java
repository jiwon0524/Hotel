package cse.oop2.hotelreservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        initUI();
    }

    private void initUI() {
        // 1. 기본 창 설정
        setTitle("메인 메뉴");
        setSize(400, 300); // 창 크기
        setLocationRelativeTo(null); // 화면 가운데 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // 배경 흰색

        // 2. 환영 메시지 라벨 (위쪽)
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>환영합니다<br>호텔 예약 서비스입니다</div></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10)); // 여백 주기

        // 3. 버튼들이 들어갈 패널 (가운데)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 20)); // 2행 1열, 간격 20
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50)); // 좌우 여백

        // 4. [예약하기] 버튼
        JButton btnReservation = new JButton("예약하기");
        btnReservation.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        btnReservation.setBackground(new Color(204, 255, 204)); // 연두색 배경
        
        // [예약하기] 버튼 클릭 시 -> MainFrame(지도 화면)으로 이동
        btnReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                dispose(); // 현재(환영) 창 닫기
            }
        });

        // 5. [내 예약내역] 버튼
        JButton btnHistory = new JButton("내 예약내역");
        btnHistory.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        btnHistory.setBackground(new Color(204, 229, 255)); // 하늘색 배경
        
        // [내 예약내역] 버튼 클릭 시 -> (나중에 구현할 화면 연결)
        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 아직 예약 내역 화면이 없으므로 알림창만 띄움
                JOptionPane.showMessageDialog(null, "예약 내역 화면은 준비 중입니다.");
                
                // 나중에 예약 내역 화면(HistoryFrame)을 만들면 아래 주석을 풀고 사용하세요.
                // HistoryFrame historyFrame = new HistoryFrame();
                // historyFrame.setVisible(true);
                // dispose();
            }
        });

        // 패널에 버튼 추가
        buttonPanel.add(btnReservation);
        buttonPanel.add(btnHistory);

        // 메인 프레임에 컴포넌트 추가
        add(welcomeLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        new WelcomeFrame().setVisible(true);
    }
}