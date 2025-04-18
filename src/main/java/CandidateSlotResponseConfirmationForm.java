
import distsys.smart_recruitment.CandidateEngagementClient;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jiaki
 */


public class CandidateSlotResponseConfirmationForm extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(CandidateSlotResponseConfirmationForm.class.getName());
    private CandidateEngagementClient client;
    private String candidateName;
    private String chosenTime;
    private String chosenLocation;
    private Main mainForm; // Reference to the main form

    /**
     * Creates new form CandidateSlotResponseConfirmationForm
     */
    public CandidateSlotResponseConfirmationForm() {
        initComponents();
        initClient();
        displaySampleCandidateChoice();
        setupActionListeners();
    }

    /**
     * Constructor that accepts the Main form as parameter
     * @param main The Main form that opened this form
     */
    public CandidateSlotResponseConfirmationForm(Main main) {
        initComponents();
        this.mainForm = main; // Store reference to main form
        initClient();
        displaySampleCandidateChoice();
        setupActionListeners();
    }

    /**
     * Initialize the gRPC client
     */
    private void initClient() {
        try {
            client = new CandidateEngagementClient("localhost", 50053);
            logger.info("Connected to gRPC server at localhost:50053");
        } catch (Exception e) {
            logger.severe("Failed to connect to gRPC server: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Error connecting to server: " + e.getMessage(),
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set up action listeners for buttons
     */
    private void setupActionListeners() {
        // Add action listener for the confirm button
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        // Add action listener for the back to menu button
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });
    }

    /**
     * Display a sample candidate slot choice for testing
     */
    private void displaySampleCandidateChoice() {
        // Sample data - in a real app, this would come from notification or database
        candidateName = "John Smith";
        chosenTime = "2025-05-15 14:00:00";
        chosenLocation = "Video Conference";

        // Display in the text area
        String details = "Candidate Name: " + candidateName + "\n" +
                         "Chosen Time: " + chosenTime + "\n" +
                         "Chosen Location: " + chosenLocation + "\n" +
                         "Additional Notes: Prefers afternoon sessions";

        candidateChosenSlotDetails.setText(details);
    }

    /**
     * Handle the confirm button click
     */
    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (client == null) {
            JOptionPane.showMessageDialog(this,
                "Client not connected to server",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Call the client method to confirm the slot
            SchedulingConfirmation confirmation = client.receiveCandidateSlotChoiceWithResponse(
                    candidateName, chosenTime, chosenLocation);

            // Log the confirmation
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String logMessage = sdf.format(new Date()) + " - Confirmed slot for " + candidateName +
                   " on " + chosenTime + " at " + chosenLocation +
                   " - Server confirmed: " + confirmation.getConfirmed() +
                   " at " + confirmation.getConfirmationTime() + "\n";

            interviewSlotConfirmationLog.append(logMessage);
            logger.info(logMessage);

            // Show success message
            JOptionPane.showMessageDialog(this,
                "Interview slot confirmed successfully!",
                "Confirmation Success",
                JOptionPane.INFORMATION_MESSAGE);

            // Disable confirm button after successful confirmation
            confirmButton.setEnabled(false);

        } catch (Exception e) {
            // Log error
            String errorMsg = "ERROR: " + e.getMessage() + "\n";
            interviewSlotConfirmationLog.append(errorMsg);
            logger.severe(errorMsg);

            // Show error message
            JOptionPane.showMessageDialog(this,
                "Error confirming slot: " + e.getMessage(),
                "Confirmation Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handle the back to menu button click
     */
    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Close resources
        if (client != null) {
            try {
                client.shutdown();
                logger.info("Client connection closed");
            } catch (InterruptedException e) {
                logger.warning("Error shutting down client: " + e.getMessage());
            }
        }

        // Show main form if available
        if (mainForm != null) {
            mainForm.setVisible(true);
        }

        // Close the window
        this.dispose();
    }

    // The rest of your form code (initComponents, etc.) remains the same
    // ...

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        candidateChosenSlotDetails = new java.awt.TextArea();
        jLabel2 = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        interviewSlotConfirmationLog = new javax.swing.JTextArea();
        backToMenuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CANDIDATE SLOT RESPONSE CONFIRMATION");

        jLabel2.setText("Please click CONFIRM to confirm the candidate chosen slot.");

        confirmButton.setText("CONFIRM");

        jLabel3.setText("Interview Slot Confirmation Log:");

        interviewSlotConfirmationLog.setColumns(20);
        interviewSlotConfirmationLog.setRows(5);
        jScrollPane1.setViewportView(interviewSlotConfirmationLog);

        backToMenuButton.setText("Back to Menu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backToMenuButton)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(candidateChosenSlotDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 72, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(confirmButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(candidateChosenSlotDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backToMenuButton)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CandidateSlotResponseConfirmationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CandidateSlotResponseConfirmationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CandidateSlotResponseConfirmationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CandidateSlotResponseConfirmationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CandidateSlotResponseConfirmationForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private java.awt.TextArea candidateChosenSlotDetails;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextArea interviewSlotConfirmationLog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}