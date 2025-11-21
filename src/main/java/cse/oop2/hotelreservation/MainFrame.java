package cse.oop2.hotelreservation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    
    private MapPanel mapPanel;

    public MainFrame() {
        // NetBeans 기본 설정
        initComponents();
        // 커스텀 설정
        initCustomComponents();
    }

    private void initCustomComponents() {
        this.setSize(1400, 900);
        this.setLocationRelativeTo(null); 

        // 1. MapPanel 생성
        mapPanel = new MapPanel();
        
        // 2. 레이아웃 설정 (스크롤바 없이 꽉 차게)
        mapContainer.setLayout(new BorderLayout());
        mapContainer.add(mapPanel, BorderLayout.CENTER);
        
        // 3. 층 선택 이벤트 연결
        floorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMapInfo();
            }
        });
        
        // 초기 화면 로드
        updateMapInfo();
    }
    
    private void updateMapInfo() {
        String selectedFloor = (String) floorComboBox.getSelectedItem();
        
        mapPanel.clearRooms();
        
        String imageFileName = "";
        
        // 층별 이미지 및 기준 해상도 설정
        if("1F".equals(selectedFloor)) {
            imageFileName = "1F.jpeg";
            mapPanel.setReferenceSize(1440, 1034); 
        }
        else if("2F".equals(selectedFloor)) {
            imageFileName = "2F.jpeg";
            mapPanel.setReferenceSize(1440, 1032);
        }
        else if("3F".equals(selectedFloor)) {
            imageFileName = "3F.jpeg";
            mapPanel.setReferenceSize(1440, 1024);
        }
        else if("4F".equals(selectedFloor)) {
            imageFileName = "4F.jpeg";
            mapPanel.setReferenceSize(1440, 1032);
        }
        else if("5F".equals(selectedFloor)) {
            imageFileName = "5F.jpeg";
            mapPanel.setReferenceSize(1440, 1036);
        }
        
        mapPanel.setBackgroundImage(imageFileName);

        // ★★★ 여기서 좌표 설정을 하시면 됩니다 ★★★
        // (현재는 예시 좌표입니다. 그림판/드래그로 딴 좌표를 넣으세요)
        switch (selectedFloor) {
            case "1F":
                mapPanel.addRoom("101", 672, 280, 142, 177);
                mapPanel.addRoom("102", 825, 278, 164, 180);
                mapPanel.addRoom("103", 1002, 278, 164, 180);
                mapPanel.addRoom("104", 1174, 279, 164, 180);
                mapPanel.addRoom("105", 826, 540, 164, 180);
                mapPanel.addRoom("106", 1002, 541, 164, 180);
                mapPanel.addRoom("107", 1174, 542, 164, 180);
                break;

            case "2F":
                mapPanel.addRoom("201", 666, 283, 147, 179);
                mapPanel.addRoom("202", 825, 280, 165, 177);
                mapPanel.addRoom("203", 1005, 282, 165, 177);
                mapPanel.addRoom("204", 1176, 281, 165, 177);
                mapPanel.addRoom("205", 666, 540, 147, 179);
                mapPanel.addRoom("206", 825, 545, 165, 177);
                mapPanel.addRoom("207", 1001, 541, 165, 177);
                mapPanel.addRoom("208", 1177, 538, 165, 177);
                mapPanel.addRoom("209", 482, 775, 147, 179);
                mapPanel.addRoom("210", 320, 773, 147, 179);
                break;
                
            // 3F, 4F, 5F 계속 추가...
        }
        
        mapPanel.repaint();
        mapContainer.revalidate();
        mapContainer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        floorComboBox = new javax.swing.JComboBox<>();
        mapContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select Floor");

        floorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1F", "2F", "3F", "4F", "5F" }));

        mapContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(floorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mapContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(floorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(mapContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> floorComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel mapContainer;
    // End of variables declaration                   
}