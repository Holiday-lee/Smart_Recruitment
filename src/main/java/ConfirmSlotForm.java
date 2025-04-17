/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import generated.grpc.candidateengagementservice.SlotSelection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author jiaki
 */
public class ConfirmSlotForm extends javax.swing.JFrame {

    private Main mainMenu;
    private static final Logger logger = Logger.getLogger(ConfirmSlotForm.class.getName());
    private ManagedChannel channel;
    private CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub;
    private JButton submitButton;

    /**
     * Creates new form ConfirmSlotForm
     */
    public ConfirmSlotForm(Main mainMenu) {
        initComponents();
        this.mainMenu = mainMenu;
        setupGrpcConnection();
        initializeAdditionalComponents();
    }

    // constructor needed for NetBeans designer
    public ConfirmSlotForm() {
        initComponents();
        setupGrpcConnection();
        initializeAdditionalComponents();
    }

    /**
     * Sets up the gRPC connection
     */
    private void setupGrpcConnection() {
        String host = "localhost";
        int port = 50053;

        // Create the channel
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            // Generate JWT token for authentication
            String jwt = JwtUtil.generateToken("CandidateEngagementClient");
            logger.info("Generated JWT token for authentication");

            // Create authentication credentials with the token
            BearerToken token = new BearerToken(jwt);

            // Create a stub for Client Streaming with authentication
            asyncStub = CandidateEngagementServiceGrpc.newStub(channel)
                    .withCallCredentials(token);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up gRPC connection: " + e.getMessage(), e);
            JOptionPane.showMessageDialog(this,
                    "Error connecting to server: " + e.getMessage(),
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initializes additional UI components not handled by the form designer
     */
    private void initializeAdditionalComponents() {
        // Add back button functionality
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMenu();
            }
        });

        // Create submit button
        submitButton = new javax.swing.JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitInterviewSlots();
            }
        });

        // Add the submit button to the layout
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) getContentPane().getLayout();
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 150, Short.MAX_VALUE)
                        .addComponent(submitButton)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1)))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(submitButton))
                .addGap(15, 15, 15))
        );

        // Set a sample text in the TextArea to guide users
        textArea1.setText("Enter interview slots in the following format (one per line):\n" +
                "candidateId,selectedTime,selectedLocation\n\n" +
                "Example:\n" +
                "CAND001,2025-05-01 14:00,Office Room 302\n" +
                "CAND001,2025-05-02 10:30,Virtual Meeting\n" +
                "CAND001,2025-05-03 16:15,Conference Room A");

        // Center the form on screen
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Back to Menu");

        jLabel1.setText("Confirming Interview Slot");

        jLabel2.setText("Please enter the interview slots and click SUBMIT button:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 239, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Process and submit the interview slots to the gRPC service
     */
    private void submitInterviewSlots() {
        // Check if connection is available
        if (channel == null || asyncStub == null) {
            JOptionPane.showMessageDialog(this,
                    "Not connected to server. Please restart the application.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String slotsText = textArea1.getText();
        String[] lines = slotsText.split("\n");

        List<SlotSelection> slots = new ArrayList<>();

        // Skip instruction lines
        boolean dataStarted = false;
        for (String line : lines) {
            line = line.trim();
            // Skip empty lines
            if (line.isEmpty()) {
                continue;
            }

            // Skip instruction lines until we see data
            if (!dataStarted) {
                if (line.contains(",")) {
                    dataStarted = true;
                } else {
                    continue;
                }
            }

            // Process slot data
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String candidateId = parts[0].trim();
                String selectedTime = parts[1].trim();
                String selectedLocation = parts[2].trim();

                // Validate data
                if (candidateId.isEmpty() || selectedTime.isEmpty() || selectedLocation.isEmpty()) {
                    logger.warning("Invalid data in line: " + line);
                    continue;
                }

                // Create slot and add to list
                SlotSelection slot = SlotSelection.newBuilder()
                        .setCandidateId(candidateId)
                        .setSelectedTime(selectedTime)
                        .setSelectedLocation(selectedLocation)
                        .build();

                slots.add(slot);
                logger.info("Added slot: " + candidateId + ", " + selectedTime + ", " + selectedLocation);
            } else {
                logger.warning("Invalid line format: " + line);
            }
        }

        if (slots.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No valid interview slots found. Please enter slots in the format: candidateId,selectedTime,selectedLocation",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show a processing message
        JOptionPane.showMessageDialog(this,
                "Sending " + slots.size() + " interview slots to the server...",
                "Processing",
                JOptionPane.INFORMATION_MESSAGE);

        // Disable submit button while processing
        submitButton.setEnabled(false);

        // Send slots to the server in a separate thread to keep UI responsive
        new Thread(() -> {
            // Create latch for synchronization
            final CountDownLatch finishLatch = new CountDownLatch(1);
            final StringBuilder errorMessageBuilder = new StringBuilder();
            final boolean[] success = {false};

            // Create the response observer
            StreamObserver<SchedulingConfirmation> responseObserver = new StreamObserver<SchedulingConfirmation>() {
                @Override
                public void onNext(SchedulingConfirmation confirmation) {
                    logger.info("Received confirmation: " + confirmation.getConfirmed());
                    success[0] = confirmation.getConfirmed();
                }

                @Override
                public void onError(Throwable t) {
                    logger.log(Level.SEVERE, "Error during stream: " + t.getMessage(), t);
                    errorMessageBuilder.append(t.getMessage());
                    finishLatch.countDown();
                }

                @Override
                public void onCompleted() {
                    logger.info("Server completed processing interview slots");
                    finishLatch.countDown();
                }
            };

            // Get the request observer
            StreamObserver<SlotSelection> requestObserver = asyncStub.confirmInterviewSlot(responseObserver);

            try {
                // Send each slot to the server
                for (SlotSelection slot : slots) {
                    logger.info("Sending slot: " + slot.getCandidateId() + ", " + slot.getSelectedTime() + ", " + slot.getSelectedLocation());
                    requestObserver.onNext(slot);

                    // Check if cancelled after each send
                    if (finishLatch.getCount() == 0) {
                        break;
                    }
                }

                // Complete the request stream if not yet cancelled
                if (finishLatch.getCount() > 0) {
                    requestObserver.onCompleted();
                }

                // Wait for the server to respond (with timeout)
                if (!finishLatch.await(30, TimeUnit.SECONDS)) {
                    errorMessageBuilder.append("Timeout waiting for server response");
                }

                // Show the result on the EDT
                String errorMessage = errorMessageBuilder.toString();
                final boolean finalSuccess = success[0];

                javax.swing.SwingUtilities.invokeLater(() -> {
                    // Re-enable submit button
                    submitButton.setEnabled(true);

                    if (finalSuccess && errorMessage.isEmpty()) {
                        JOptionPane.showMessageDialog(ConfirmSlotForm.this,
                                "Interview slots confirmed successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ConfirmSlotForm.this,
                                "Failed to confirm interview slots. " + (!errorMessage.isEmpty() ? "Error: " + errorMessage : ""),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });

            } catch (StatusRuntimeException e) {
                logger.log(Level.SEVERE, "RPC failed: " + e.getStatus(), e);

                javax.swing.SwingUtilities.invokeLater(() -> {
                    submitButton.setEnabled(true);
                    JOptionPane.showMessageDialog(ConfirmSlotForm.this,
                            "RPC failed: " + e.getStatus().getDescription(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                });
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error sending interview slots", e);

                javax.swing.SwingUtilities.invokeLater(() -> {
                    submitButton.setEnabled(true);
                    JOptionPane.showMessageDialog(ConfirmSlotForm.this,
                            "An error occurred: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    /**
     * Go back to the main menu
     */
    private void goBackToMenu() {
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        }
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(ConfirmSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmSlotForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables

    /**
     * Clean up resources when the form is closed
     */
    @Override
    public void dispose() {
        // Shutdown the gRPC channel
        if (channel != null && !channel.isShutdown()) {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                logger.info("gRPC channel shutdown complete");
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Error shutting down channel: " + e.getMessage(), e);
            }
        }
        super.dispose();
    }
}