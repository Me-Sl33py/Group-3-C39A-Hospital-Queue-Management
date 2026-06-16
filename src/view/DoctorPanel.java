/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.MedicalRecord;
import dao.MedicalRecordDAO;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author flexx
 */

public class DoctorPanel extends javax.swing.JFrame {
    
    private List<MedicalRecord> currentPatientRecords = new ArrayList<>();
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DoctorPanel.class.getName());
private controller.LogoutController logoutController;
    /**
     * Creates new form DoctorPanel
     */
    public DoctorPanel() {
        initComponents();     
        addTextAreaPlaceholder(taMessage1, "Enter detailed clinical notes, patient history update, and recommended next steps...");
        
        jTabbedPane1.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
               return 0;
            }
        });

        // Make "Add Medical Records" page scrollable
        int tabIndex4 = jTabbedPane1.indexOfComponent(jPanel4);
        if (tabIndex4 != -1) {
            javax.swing.JScrollPane sp4 = new javax.swing.JScrollPane(jPanel4);
            sp4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            sp4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sp4.getVerticalScrollBar().setUnitIncrement(16);
            sp4.setBorder(null);
            jTabbedPane1.setComponentAt(tabIndex4, sp4);
        }
        
        // Make the other settings/profile page scrollable
        int tabIndex5 = jTabbedPane1.indexOfComponent(jPanel5);
        if (tabIndex5 != -1) {
            javax.swing.JScrollPane sp5 = new javax.swing.JScrollPane(jPanel5);
            sp5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            sp5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sp5.getVerticalScrollBar().setUnitIncrement(16);
            sp5.setBorder(null);
            jTabbedPane1.setComponentAt(tabIndex5, sp5);
        }

        logoutController = new controller.LogoutController(null, this, null, null);
    }
    // =====================================================================
// GETTERS — expose UI components to the controller
// =====================================================================
    public javax.swing.JLabel getLblTitle4() { return lblTitle4; }
    public javax.swing.JLabel getLblQueueName1() { return lblQueueName1; }
public javax.swing.JLabel getLblQueueName2() { return lblQueueName2; }
public javax.swing.JLabel getLblQueueDesc1() { return lblQueueDesc1; }
public javax.swing.JLabel getLblQueueDesc2() { return lblQueueDesc2; }
public javax.swing.JLabel getLblPatientQueueNum1() { return lblPatientQueueNum1; }
public javax.swing.JLabel getLblPatientQueueNum2() { return lblPatientQueueNum2; }
public javax.swing.JPanel getJPatientQueue() { return jPatientQueue; }
public javax.swing.JPanel getJPatientQueue1() { return jPatientQueue1; }
public javax.swing.JLabel getLblRemainingCount() { return jLabel2; }
public javax.swing.JLabel getLblWaitingCount() { return jLabel12; }
public javax.swing.JLabel getLblConfirmedCount() { return jLabel15; }
public javax.swing.JLabel getLblNoShowCount() { return jLabel19; }
public javax.swing.JLabel getLblCompletedCount() { return jLabelCompletedCount; }
public javax.swing.JTextField getTxtPatientIdField()   { return jTextField1; }
public javax.swing.JTextField getTxtPatientNameField() { return jTextField2; }
public javax.swing.JButton getBtnMyQueue()        { return MyQueue;          }
public javax.swing.JButton getBtnCallNextPatient(){ return CallNextPatient;  }
public javax.swing.JButton getBtnAddRecords()     { return AddMedicalRecords;}
public javax.swing.JButton getBtnAccount()        { return Account;          }
public javax.swing.JTabbedPane getTabbedPane() { return jTabbedPane1; }
public javax.swing.JTable getQueueTable()            { return jTable2;}
public javax.swing.JButton getBtnCallNextDashboard() { return jButton6;}
public javax.swing.JLabel getLblActivePatientName()  { return lblPatientName1;}
public javax.swing.JLabel getLblActivePatientId()    { return lblPatientId1;}
    public javax.swing.JButton getBtnCallNext()        { return CallNext;      }
    public javax.swing.JButton getBtnEndSession()        { return EndSession;    }
    public javax.swing.JButton getBtnSkipPatient()       { return SkipPatient;   }
public javax.swing.JButton getBtnViewFullQueue()     { return ViewFullQueue; }
public javax.swing.JTable getSessionHistoryTable()   { return jTableSessionHistory;}
public javax.swing.JTable getNoShowTable()            { return jTableNoShow;}
public javax.swing.JLabel getLblRecordPatientId()    { return lblPatientId;     }
public javax.swing.JLabel getLblRecordPatientName()  { return lblPatientName;   }
public javax.swing.JTextArea getTaMessage()          { return taMessage1;       }
public javax.swing.JButton getBtnSubmitRecord()      { return btnSubmit1;       }
public javax.swing.JButton getBtnCancelRecord()      { return btnCancel1;       }
public javax.swing.JTextField getTxtFullName()       { return txtFullName;      }
public javax.swing.JTextField getTxtPhone()          { return txtPhone;         }
public javax.swing.JTextField getTxtSpecialization() { return txtSpecialization;}
public javax.swing.JTextField getTxtRoom()           { return txtRoom;          }
public javax.swing.JLabel getLblShiftHoursVal()      { return lblShiftHoursVal; }
public javax.swing.JLabel getLblDoctorIdVal()        { return lblDoctorIdVal;   }
public javax.swing.JLabel getLblSecurityLevelVal()   { return lblSecurityLevelVal; }
public javax.swing.JLabel getLblAccountStatusVal()   { return lblAccountStatusVal; }
public javax.swing.JLabel getLblLastLoginVal()       { return lblLastLoginVal;  }
public javax.swing.JButton getBtnSave()              { return btnSave;          }
public javax.swing.JButton getBtnCancelAccount()     { return btnCancel2;       }
public javax.swing.JButton getBtnLogout()            { return jButton5;         }

private void addPlaceholder(javax.swing.JTextField field, String placeholder) {
    field.setForeground(java.awt.Color.GRAY);
    field.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (field.getText().equals(placeholder)) {
                field.setText("");
                field.setForeground(java.awt.Color.BLACK);
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (field.getText().trim().isEmpty()) {
                field.setText(placeholder);
                field.setForeground(java.awt.Color.GRAY);
            }
        }
    });
}
private void addTextAreaPlaceholder(javax.swing.JTextArea area, String placeholder) {
    area.setForeground(java.awt.Color.GRAY);
    area.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (area.getText().equals(placeholder)) {
                area.setText("");
                area.setForeground(java.awt.Color.BLACK);
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (area.getText().trim().isEmpty()) {
                area.setText(placeholder);
                area.setForeground(java.awt.Color.GRAY);
            }
        }
    });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        HospitalManagement = new javax.swing.JLabel();
        MyQueue = new javax.swing.JButton();
        CallNextPatient = new javax.swing.JButton();
        AddMedicalRecords = new javax.swing.JButton();
        Account = new javax.swing.JButton();
        DoctorPanel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabelCompleted = new javax.swing.JLabel();
        jLabelCompletedCount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabelQueueHeader = new javax.swing.JLabel();
        jPanelNoShowList = new javax.swing.JPanel();
        lblNoShowHeader = new javax.swing.JLabel();
        jScrollPaneNoShow = new javax.swing.JScrollPane();
        jTableNoShow = new javax.swing.JTable();
        jTable2 = new javax.swing.JTable();
        lblTitle4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanelActiveConsultation = new javax.swing.JPanel();
        lblActiveConsultation = new javax.swing.JLabel();
        jPanelLiveBadge = new javax.swing.JPanel();
        lblLiveSession = new javax.swing.JLabel();
        lblPatientName1 = new javax.swing.JLabel();
        lblPatientIdLabel = new javax.swing.JLabel();
        lblPatientId1 = new javax.swing.JLabel();
        CallNext = new javax.swing.JButton();
        EndSession = new javax.swing.JButton();
        SkipPatient = new javax.swing.JButton();
        jPanelUpNext = new javax.swing.JPanel();
        lblUpNextINQueue = new javax.swing.JLabel();
        jPatientQueue = new javax.swing.JPanel();
        jPanelNumContainer1 = new javax.swing.JPanel();
        lblPatientQueueNum1 = new javax.swing.JLabel();
        lblQueueName1 = new javax.swing.JLabel();
        lblQueueDesc1 = new javax.swing.JLabel();
        jPatientQueue1 = new javax.swing.JPanel();
        jPanelNumContainer2 = new javax.swing.JPanel();
        lblPatientQueueNum2 = new javax.swing.JLabel();
        lblQueueName2 = new javax.swing.JLabel();
        lblQueueDesc2 = new javax.swing.JLabel();
        ViewFullQueue = new javax.swing.JButton();
        jPanelSessionHistory1 = new javax.swing.JPanel();
        lblHistoryHeader1 = new javax.swing.JLabel();
        jScrollPaneHistory1 = new javax.swing.JScrollPane();
        jTableSessionHistory = new javax.swing.JTable();
        lblCallNextPatient = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        panelPatientInfo = new javax.swing.JPanel();
        lblPatientId = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        panelMedicalHistory = new javax.swing.JPanel();
        lblMedicalHistoryTitle = new javax.swing.JLabel();
        scrollPaneMedicalHistory = new javax.swing.JScrollPane();
        tblMedicalHistory = new javax.swing.JTable();
        panelDoc1 = new javax.swing.JPanel();
        lblDocTitle1 = new javax.swing.JLabel();
        lblMessage1 = new javax.swing.JLabel();
        scrollPane1 = new javax.swing.JScrollPane();
        taMessage1 = new javax.swing.JTextArea();
        btnCancel1 = new javax.swing.JButton();
        btnSubmit1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        lblTitle2 = new javax.swing.JLabel();
        lblSubtitle1 = new javax.swing.JLabel();
        jPanelProfileCard = new javax.swing.JPanel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblContactNumber = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblSpecialization = new javax.swing.JLabel();
        txtSpecialization = new javax.swing.JTextField();
        lblAssignedRoom = new javax.swing.JLabel();
        txtRoom = new javax.swing.JTextField();
        lblShiftHours = new javax.swing.JLabel();
        lblShiftHoursVal = new javax.swing.JLabel();
        btnCancel2 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jPanelStatusCard = new javax.swing.JPanel();
        lblDoctorId = new javax.swing.JLabel();
        lblDoctorIdVal = new javax.swing.JLabel();
        lblSecurityLevel = new javax.swing.JLabel();
        lblSecurityLevelVal = new javax.swing.JLabel();
        lblAccountStatus = new javax.swing.JLabel();
        lblAccountStatusVal = new javax.swing.JLabel();
        lblLastLogin = new javax.swing.JLabel();
        lblLastLoginVal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 51));
        jButton5.setText("Logout");
        jButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton5.addActionListener(this::jButton5ActionPerformed);

        HospitalManagement.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        HospitalManagement.setText("Hospital Management");

        MyQueue.setBackground(new java.awt.Color(0, 102, 255));
        MyQueue.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        MyQueue.setText("My Queue");
        MyQueue.setBorder(null);
        MyQueue.addActionListener(this::MyQueueActionPerformed);

        CallNextPatient.setBackground(new java.awt.Color(0, 102, 255));
        CallNextPatient.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        CallNextPatient.setText("Call Next Patient");
        CallNextPatient.setBorder(null);
        CallNextPatient.addActionListener(this::CallNextPatientActionPerformed);

        AddMedicalRecords.setBackground(new java.awt.Color(0, 102, 255));
        AddMedicalRecords.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        AddMedicalRecords.setText("Add Medical Records");
        AddMedicalRecords.setBorder(null);
        AddMedicalRecords.addActionListener(this::AddMedicalRecordsActionPerformed);

        Account.setBackground(new java.awt.Color(0, 102, 255));
        Account.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Account.setText("Account");
        Account.setBorder(null);
        Account.addActionListener(this::AccountActionPerformed);

        DoctorPanel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DoctorPanel.setText("Doctor Panel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HospitalManagement)
                            .addComponent(DoctorPanel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MyQueue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CallNextPatient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddMedicalRecords, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(Account, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DoctorPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HospitalManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(MyQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(CallNextPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(AddMedicalRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(Account, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel6.setText("Here is the current patient status for your session.");

        jPanel9.setBackground(new java.awt.Color(0, 102, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Resume Session");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("You have 12 patients remaining");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("in your daily queue");

        jButton6.setText("Call Next Patient");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jButton6))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(17, 17, 17))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("00");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Waiting");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel13))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel12)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(20, 20))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel15.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("00");

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Confirmed");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel16))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel15)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(20, 20))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("No Show");

        jLabel19.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("00");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(52, 52, 52))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addContainerGap(20, 20))
        );

        // ── Completed card (jPanel13) ──────────────────────────────────────
        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabelCompleted.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabelCompleted.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCompleted.setText("Completed");

        jLabelCompletedCount.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 36)); // NOI18N
        jLabelCompletedCount.setForeground(new java.awt.Color(0, 180, 90));
        jLabelCompletedCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCompletedCount.setText("00");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelCompleted)
                    .addComponent(jLabelCompletedCount))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabelCompleted)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCompletedCount)
                .addContainerGap(20, 20))
        );

        jTable2.setAutoCreateColumnsFromModel(true);
        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Token #", "Patient Name", "Age", "Gender", "Type", "Reason", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(130);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(90);
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(70);
        jTable2.setRowHeight(24);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(jTable2);

        jLabelQueueHeader.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)); // NOI18N
        jLabelQueueHeader.setForeground(new java.awt.Color(30, 41, 59));
        jLabelQueueHeader.setText("Queue List");

        lblTitle4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle4.setForeground(new java.awt.Color(30, 41, 59));
        lblTitle4.setText("Good Morning, Doctor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelQueueHeader)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitle4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitle4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelQueueHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        javax.swing.JScrollPane jScrollPaneTab1 = new javax.swing.JScrollPane(jPanel2);
        jScrollPaneTab1.setBorder(null);
        jScrollPaneTab1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneTab1.getVerticalScrollBar().setUnitIncrement(16);
        jTabbedPane1.addTab("tab1", jScrollPaneTab1);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jLabel9.setText("Manage the current consultation flow and patient transitions.");

        jPanelActiveConsultation.setBackground(new java.awt.Color(255, 255, 255));

        lblActiveConsultation.setText("ACTIVE CONSULTATION");

        lblLiveSession.setText("● LIVE SESSION");

        javax.swing.GroupLayout jPanelLiveBadgeLayout = new javax.swing.GroupLayout(jPanelLiveBadge);
        jPanelLiveBadge.setLayout(jPanelLiveBadgeLayout);
        jPanelLiveBadgeLayout.setHorizontalGroup(
            jPanelLiveBadgeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLiveBadgeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLiveSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelLiveBadgeLayout.setVerticalGroup(
            jPanelLiveBadgeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLiveBadgeLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblLiveSession)
                .addGap(2, 2, 2))
        );

        lblPatientName1.setText("");

        lblPatientIdLabel.setText("Patient ID:");

        lblPatientId1.setText("");

        CallNext.setText("Call Next Patient");

        EndSession.setText("End Session");

        SkipPatient.setText("Skip / Not Present");

        javax.swing.GroupLayout jPanelActiveConsultationLayout = new javax.swing.GroupLayout(jPanelActiveConsultation);
        jPanelActiveConsultation.setLayout(jPanelActiveConsultationLayout);
        jPanelActiveConsultationLayout.setHorizontalGroup(
            jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                        .addComponent(lblActiveConsultation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelLiveBadge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblPatientName1)
                            .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                                .addComponent(lblPatientIdLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPatientId1))
                            .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                                .addComponent(CallNext, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EndSession, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SkipPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(65, Short.MAX_VALUE))))
        );
        jPanelActiveConsultationLayout.setVerticalGroup(
            jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActiveConsultationLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblActiveConsultation)
                    .addComponent(jPanelLiveBadge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(lblPatientName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientIdLabel)
                    .addComponent(lblPatientId1))
                .addGap(28, 28, 28)
                .addGroup(jPanelActiveConsultationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CallNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndSession, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelUpNext.setBackground(new java.awt.Color(255, 255, 255));

        lblUpNextINQueue.setText("UP NEXT IN QUEUE");

        lblPatientQueueNum1.setText("-");

        javax.swing.GroupLayout jPanelNumContainer1Layout = new javax.swing.GroupLayout(jPanelNumContainer1);
        jPanelNumContainer1.setLayout(jPanelNumContainer1Layout);
        jPanelNumContainer1Layout.setHorizontalGroup(
            jPanelNumContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPatientQueueNum1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );
        jPanelNumContainer1Layout.setVerticalGroup(
            jPanelNumContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPatientQueueNum1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        lblQueueName1.setText("");

        lblQueueDesc1.setText("");

        javax.swing.GroupLayout jPatientQueueLayout = new javax.swing.GroupLayout(jPatientQueue);
        jPatientQueue.setLayout(jPatientQueueLayout);
        jPatientQueueLayout.setHorizontalGroup(
            jPatientQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPatientQueueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNumContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPatientQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQueueName1)
                    .addComponent(lblQueueDesc1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPatientQueueLayout.setVerticalGroup(
            jPatientQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPatientQueueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPatientQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNumContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPatientQueueLayout.createSequentialGroup()
                        .addComponent(lblQueueName1)
                        .addGap(2, 2, 2)
                        .addComponent(lblQueueDesc1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPatientQueueNum2.setText("-");

        javax.swing.GroupLayout jPanelNumContainer2Layout = new javax.swing.GroupLayout(jPanelNumContainer2);
        jPanelNumContainer2.setLayout(jPanelNumContainer2Layout);
        jPanelNumContainer2Layout.setHorizontalGroup(
            jPanelNumContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPatientQueueNum2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );
        jPanelNumContainer2Layout.setVerticalGroup(
            jPanelNumContainer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPatientQueueNum2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        lblQueueName2.setText("");

        lblQueueDesc2.setText("");

        javax.swing.GroupLayout jPatientQueue1Layout = new javax.swing.GroupLayout(jPatientQueue1);
        jPatientQueue1.setLayout(jPatientQueue1Layout);
        jPatientQueue1Layout.setHorizontalGroup(
            jPatientQueue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPatientQueue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNumContainer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPatientQueue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQueueName2)
                    .addComponent(lblQueueDesc2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPatientQueue1Layout.setVerticalGroup(
            jPatientQueue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPatientQueue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPatientQueue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNumContainer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPatientQueue1Layout.createSequentialGroup()
                        .addComponent(lblQueueName2)
                        .addGap(2, 2, 2)
                        .addComponent(lblQueueDesc2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ViewFullQueue.setText("View Full Queue");

        javax.swing.GroupLayout jPanelUpNextLayout = new javax.swing.GroupLayout(jPanelUpNext);
        jPanelUpNext.setLayout(jPanelUpNextLayout);
        jPanelUpNextLayout.setHorizontalGroup(
            jPanelUpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpNextLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelUpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPatientQueue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPatientQueue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelUpNextLayout.createSequentialGroup()
                        .addComponent(lblUpNextINQueue)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUpNextLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ViewFullQueue, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanelUpNextLayout.setVerticalGroup(
            jPanelUpNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpNextLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUpNextINQueue)
                .addGap(18, 18, 18)
                .addComponent(jPatientQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPatientQueue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ViewFullQueue)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelSessionHistory1.setBackground(new java.awt.Color(255, 255, 255));

        lblHistoryHeader1.setText("Session History (Today)");

        jTableSessionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TIME", "PATIENT NAME", "STATUS", "ACTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneHistory1.setViewportView(jTableSessionHistory);

        javax.swing.GroupLayout jPanelSessionHistory1Layout = new javax.swing.GroupLayout(jPanelSessionHistory1);
        jPanelSessionHistory1.setLayout(jPanelSessionHistory1Layout);
        jPanelSessionHistory1Layout.setHorizontalGroup(
            jPanelSessionHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSessionHistory1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelSessionHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneHistory1)
                    .addGroup(jPanelSessionHistory1Layout.createSequentialGroup()
                        .addComponent(lblHistoryHeader1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanelSessionHistory1Layout.setVerticalGroup(
            jPanelSessionHistory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSessionHistory1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHistoryHeader1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneHistory1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        // ── No Show Patients panel ────────────────────────────────────────────
        jPanelNoShowList.setBackground(new java.awt.Color(255, 255, 255));

        lblNoShowHeader.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblNoShowHeader.setForeground(new java.awt.Color(220, 38, 38));
        lblNoShowHeader.setText("No Show Patients (Click to Recall)");

        jTableNoShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "TOKEN", "PATIENT NAME", "PATIENT ID", "ACTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableNoShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPaneNoShow.setViewportView(jTableNoShow);

        javax.swing.GroupLayout jPanelNoShowListLayout = new javax.swing.GroupLayout(jPanelNoShowList);
        jPanelNoShowList.setLayout(jPanelNoShowListLayout);
        jPanelNoShowListLayout.setHorizontalGroup(
            jPanelNoShowListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoShowListLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelNoShowListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneNoShow)
                    .addGroup(jPanelNoShowListLayout.createSequentialGroup()
                        .addComponent(lblNoShowHeader)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanelNoShowListLayout.setVerticalGroup(
            jPanelNoShowListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNoShowListLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNoShowHeader)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneNoShow, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        lblCallNextPatient.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCallNextPatient.setForeground(new java.awt.Color(30, 41, 59));
        lblCallNextPatient.setText("Call Next Patient");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelNoShowList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSessionHistory1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanelActiveConsultation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanelUpNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCallNextPatient))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblCallNextPatient)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUpNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelActiveConsultation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanelSessionHistory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanelNoShowList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.JScrollPane jScrollPaneTab2 = new javax.swing.JScrollPane(jPanel3);
        jScrollPaneTab2.setBorder(null);
        jScrollPaneTab2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneTab2.getVerticalScrollBar().setUnitIncrement(16);
        jTabbedPane1.addTab("tab2", jScrollPaneTab2);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(30, 41, 59));
        lblTitle.setText("Add Medical Record");

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Document clinical findings, diagnoses, and treatment plans for the current patient session.");

        panelPatientInfo.setBackground(new java.awt.Color(255, 255, 255));
        panelPatientInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblPatientId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPatientId.setForeground(new java.awt.Color(30, 41, 59));
        lblPatientId.setText("Patient ID:");

        lblPatientName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPatientName.setForeground(new java.awt.Color(30, 41, 59));
        lblPatientName.setText("Patient Name:");

        javax.swing.GroupLayout panelPatientInfoLayout = new javax.swing.GroupLayout(panelPatientInfo);
        panelPatientInfo.setLayout(panelPatientInfoLayout);
        panelPatientInfoLayout.setHorizontalGroup(
            panelPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPatientInfoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblPatientId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPatientName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPatientInfoLayout.setVerticalGroup(
            panelPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPatientInfoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientId)
                    .addComponent(lblPatientName)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelMedicalHistory.setBackground(new java.awt.Color(255, 255, 255));
        panelMedicalHistory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblMedicalHistoryTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMedicalHistoryTitle.setForeground(new java.awt.Color(30, 41, 59));
        lblMedicalHistoryTitle.setText("Medical History");

        tblMedicalHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"MR-001", "Sita Sharma", "2026-05-01", "Hypertension", "View Details"},
                {"MR-002", "Ram Thapa", "2026-05-15", "Diabetes Type II", "View Details"}
            },
            new String [] {
                "Record ID", "Patient Name", "Date", "Diagnosis", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicalHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicalHistoryMouseClicked(evt);
            }
        });
        scrollPaneMedicalHistory.setViewportView(tblMedicalHistory);

        javax.swing.GroupLayout panelMedicalHistoryLayout = new javax.swing.GroupLayout(panelMedicalHistory);
        panelMedicalHistory.setLayout(panelMedicalHistoryLayout);
        panelMedicalHistoryLayout.setHorizontalGroup(
            panelMedicalHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMedicalHistoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMedicalHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMedicalHistoryTitle)
                    .addComponent(scrollPaneMedicalHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMedicalHistoryLayout.setVerticalGroup(
            panelMedicalHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMedicalHistoryLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblMedicalHistoryTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneMedicalHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );


        panelDoc1.setBackground(new java.awt.Color(255, 255, 255));
        panelDoc1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblDocTitle1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDocTitle1.setForeground(new java.awt.Color(30, 41, 59));
        lblDocTitle1.setText("Clinical Documentation");

        lblMessage1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblMessage1.setForeground(new java.awt.Color(100, 116, 139));
        lblMessage1.setText("MESSAGE");

        taMessage1.setText("Enter detailed clinical notes, patient history update, and recommended next steps...");
        scrollPane1.setViewportView(taMessage1);

        btnCancel1.setText("Cancel");

        btnSubmit1.setBackground(new java.awt.Color(37, 99, 235));
        btnSubmit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSubmit1.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit1.setText("Submit Record");

        javax.swing.GroupLayout panelDoc1Layout = new javax.swing.GroupLayout(panelDoc1);
        panelDoc1.setLayout(panelDoc1Layout);
        panelDoc1Layout.setHorizontalGroup(
            panelDoc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(panelDoc1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDoc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addComponent(scrollPane1)
                    .addGroup(panelDoc1Layout.createSequentialGroup()
                        .addGroup(panelDoc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDoc1Layout.createSequentialGroup()
                                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSubmit1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMessage1)
                            .addComponent(lblDocTitle1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDoc1Layout.setVerticalGroup(
            panelDoc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDoc1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDocTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(panelDoc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblSubtitle))
                        .addGap(0, 104, Short.MAX_VALUE))
                    .addComponent(panelDoc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMedicalHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPatientInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(panelPatientInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMedicalHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        lblTitle2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle2.setForeground(new java.awt.Color(30, 41, 59));
        lblTitle2.setText("Account Settings");

        lblSubtitle1.setText("Manage your professional profile, contact details, and credentials.");

        jPanelProfileCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblFullName.setText("Full Name:");

        txtFullName.addActionListener(this::txtFullNameActionPerformed);

        lblContactNumber.setText("Contact Number:");

        lblSpecialization.setText("Specialization:");

        lblAssignedRoom.setText("Department Name");

        lblShiftHours.setText("Shift Hours:");

        lblShiftHoursVal.setText("Morning Shift (08:00 AM - 02:00 PM)");

        btnCancel2.setText("Cancel");

        btnSave.setText("Save Changes");

        javax.swing.GroupLayout jPanelProfileCardLayout = new javax.swing.GroupLayout(jPanelProfileCard);
        jPanelProfileCard.setLayout(jPanelProfileCardLayout);
        jPanelProfileCardLayout.setHorizontalGroup(
            jPanelProfileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfileCardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelProfileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelProfileCardLayout.createSequentialGroup()
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblShiftHoursVal)
                    .addComponent(lblShiftHours)
                    .addComponent(lblAssignedRoom)
                    .addComponent(lblSpecialization)
                    .addComponent(lblContactNumber)
                    .addComponent(lblFullName)
                    .addComponent(txtFullName, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(txtPhone)
                    .addComponent(txtSpecialization)
                    .addComponent(txtRoom))
                .addGap(24, 24, 24))
        );
        jPanelProfileCardLayout.setVerticalGroup(
            jPanelProfileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfileCardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblFullName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblContactNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSpecialization)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAssignedRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblShiftHours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblShiftHoursVal)
                .addGap(129, 129, 129)
                .addGroup(jPanelProfileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanelStatusCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lblDoctorId.setText("Doctor ID:");

        lblDoctorIdVal.setText("#DOC-9921");

        lblSecurityLevel.setText("Security Clearance:");

        lblSecurityLevelVal.setText("Level 3 Practitioner");

        lblAccountStatus.setText("Account Status:");

        lblAccountStatusVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAccountStatusVal.setText("ACTIVE");

        lblLastLogin.setText("Last Login Session:");

        lblLastLoginVal.setText("Today, 08:30 AM");

        javax.swing.GroupLayout jPanelStatusCardLayout = new javax.swing.GroupLayout(jPanelStatusCard);
        jPanelStatusCard.setLayout(jPanelStatusCardLayout);
        jPanelStatusCardLayout.setHorizontalGroup(
            jPanelStatusCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusCardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelStatusCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLastLoginVal)
                    .addComponent(lblLastLogin)
                    .addComponent(lblSecurityLevelVal)
                    .addComponent(lblSecurityLevel)
                    .addComponent(lblDoctorIdVal)
                    .addComponent(lblDoctorId)
                    .addGroup(jPanelStatusCardLayout.createSequentialGroup()
                        .addComponent(lblAccountStatus)
                        .addGap(18, 18, 18)
                        .addComponent(lblAccountStatusVal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanelStatusCardLayout.setVerticalGroup(
            jPanelStatusCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatusCardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblDoctorId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDoctorIdVal)
                .addGap(24, 24, 24)
                .addComponent(lblSecurityLevel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSecurityLevelVal)
                .addGap(24, 24, 24)
                .addGroup(jPanelStatusCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAccountStatus)
                    .addComponent(lblAccountStatusVal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(lblLastLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLastLoginVal)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle2)
                    .addComponent(lblSubtitle1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanelProfileCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelStatusCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelProfileCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelStatusCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setResizable(false);
        setSize(getWidth(), 700);
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         if (logoutController != null) {
        logoutController.handleLogout();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void AccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_AccountActionPerformed

    private void MyQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyQueueActionPerformed
       
        // TODO add your handling code here:
    }//GEN-LAST:event_MyQueueActionPerformed

    private void CallNextPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CallNextPatientActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_CallNextPatientActionPerformed

    private void AddMedicalRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMedicalRecordsActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_AddMedicalRecordsActionPerformed

    private void txtFullNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullNameActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Account;
    private javax.swing.JButton AddMedicalRecords;
    private javax.swing.JButton CallNext;
    private javax.swing.JButton CallNextPatient;
    private javax.swing.JLabel DoctorPanel;
    private javax.swing.JButton EndSession;
    private javax.swing.JButton SkipPatient;
    private javax.swing.JLabel HospitalManagement;
    private javax.swing.JButton MyQueue;
    private javax.swing.JButton ViewFullQueue;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSubmit1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JLabel jLabelCompleted;
    private javax.swing.JLabel jLabelCompletedCount;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelActiveConsultation;
    private javax.swing.JPanel jPanelLiveBadge;
    private javax.swing.JPanel jPanelNumContainer1;
    private javax.swing.JPanel jPanelNumContainer2;
    private javax.swing.JPanel jPanelProfileCard;
    private javax.swing.JPanel jPanelSessionHistory1;
    private javax.swing.JPanel jPanelStatusCard;
    private javax.swing.JPanel jPanelUpNext;
    private javax.swing.JPanel jPatientQueue;
    private javax.swing.JPanel jPatientQueue1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneHistory1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableSessionHistory;
    private javax.swing.JTable jTableNoShow;
    private javax.swing.JPanel jPanelNoShowList;
    private javax.swing.JLabel lblNoShowHeader;
    private javax.swing.JScrollPane jScrollPaneNoShow;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel panelMedicalHistory;
    private javax.swing.JLabel lblAccountStatus;
    private javax.swing.JLabel lblAccountStatusVal;
    private javax.swing.JLabel lblActiveConsultation;
    private javax.swing.JLabel lblAssignedRoom;
    private javax.swing.JLabel lblCallNextPatient;
    private javax.swing.JLabel lblContactNumber;
    private javax.swing.JLabel lblDocTitle1;
    private javax.swing.JLabel lblDoctorId;
    private javax.swing.JLabel lblDoctorIdVal;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblHistoryHeader1;
    private javax.swing.JLabel lblLastLogin;
    private javax.swing.JLabel jLabelQueueHeader;
    private javax.swing.JLabel lblLastLoginVal;
    private javax.swing.JLabel lblLiveSession;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel lblPatientId;
    private javax.swing.JLabel lblPatientId1;
    private javax.swing.JLabel lblPatientIdLabel;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblMedicalHistoryTitle;
    private javax.swing.JScrollPane scrollPaneMedicalHistory;
    private javax.swing.JTable tblMedicalHistory;
    private javax.swing.JLabel lblPatientName1;
    private javax.swing.JLabel lblPatientQueueNum1;
    private javax.swing.JLabel lblPatientQueueNum2;
    private javax.swing.JLabel lblQueueDesc1;
    private javax.swing.JLabel lblQueueDesc2;
    private javax.swing.JLabel lblQueueName1;
    private javax.swing.JLabel lblQueueName2;
    private javax.swing.JLabel lblSecurityLevel;
    private javax.swing.JLabel lblSecurityLevelVal;
    private javax.swing.JLabel lblShiftHours;
    private javax.swing.JLabel lblShiftHoursVal;
    private javax.swing.JLabel lblSpecialization;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblSubtitle1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle4;
    private javax.swing.JLabel lblUpNextINQueue;
    private javax.swing.JPanel panelDoc1;
    private javax.swing.JPanel panelPatientInfo;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JTextArea taMessage1;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtRoom;
    private javax.swing.JTextField txtSpecialization;
    public void loadMedicalHistory(String patientId, String patientName) {
        jTextField1.setText(patientId);
        jTextField2.setText(patientName);
        
        currentPatientRecords = medicalRecordDAO.getRecordsByPatient(patientId);
        
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblMedicalHistory.getModel();
        model.setRowCount(0); // Clear existing rows
        
        for (MedicalRecord record : currentPatientRecords) {
            model.addRow(new Object[]{
                "MR-" + String.format("%03d", record.getRecordId()),
                patientName,
                record.getCreatedAt(),
                record.getDiagnosis(),
                "View Details"
            });
        }
    }

    private void showRecordDetailsDialog(int row) {
        String recordIdStr = "";
        String patientName = "";
        String dateStr = "";
        String doctorName = "Dr. Anil";
        String diagnosis = "";
        String prescription = "Amlodipine 5mg once";
        String notes = "Follow up in 2 weeks";

        if (currentPatientRecords != null && !currentPatientRecords.isEmpty() && row < currentPatientRecords.size()) {
            // Use real data from DB
            MedicalRecord record = currentPatientRecords.get(row);
            recordIdStr = "MR-" + String.format("%03d", record.getRecordId());
            patientName = jTextField2.getText();
            dateStr = record.getCreatedAt();
            doctorName = record.getDoctorName();
            diagnosis = record.getDiagnosis();
            prescription = record.getPrescription();
            notes = record.getNotes();
        } else {
            // Use dummy data from table so user can test popup
            recordIdStr = (String) tblMedicalHistory.getValueAt(row, 0);
            patientName = (String) tblMedicalHistory.getValueAt(row, 1);
            dateStr = (String) tblMedicalHistory.getValueAt(row, 2);
            diagnosis = (String) tblMedicalHistory.getValueAt(row, 3);
        }
        
        String details = "<html><body style='width: 300px; font-family: sans-serif;'>" +
                "<h2>Medical Record Details</h2>" +
                "<p><b>Record ID:</b> " + recordIdStr + "<br/>" +
                "<b>Patient:</b> " + patientName + "<br/>" +
                "<b>Date:</b> " + dateStr + "<br/>" +
                "<b>Doctor:</b> " + doctorName + "</p>" +
                "<p><b>Diagnosis:</b><br/>" +
                "<div style='border: 1px solid #aaa; padding: 5px; margin-top: 2px;'>" + diagnosis + "</div></p>" +
                "<p><b>Prescription:</b><br/>" +
                "<div style='border: 1px solid #aaa; padding: 5px; margin-top: 2px;'>" + prescription + "</div></p>" +
                "<p><b>Notes:</b><br/>" +
                "<div style='border: 1px solid #aaa; padding: 5px; margin-top: 2px;'>" + notes + "</div></p>" +
                "</body></html>";
                
        javax.swing.JOptionPane.showMessageDialog(this, details, "Medical Record Details", javax.swing.JOptionPane.PLAIN_MESSAGE);
    }

    private void tblMedicalHistoryMouseClicked(java.awt.event.MouseEvent evt) {                                               
        int row = tblMedicalHistory.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            showRecordDetailsDialog(row);
        }
    }

    // End of variables declaration//GEN-END:variables
}
