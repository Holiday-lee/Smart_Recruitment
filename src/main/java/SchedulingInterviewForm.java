import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.InterviewSlot;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jiaki
 */

public class SchedulingInterviewForm extends javax.swing.JFrame {

    private Main mainMenu;
    private ManagedChannel channel;
    private InterviewSchedulingServiceGrpc.InterviewSchedulingServiceStub asyncStub;

    /**
     * Creates new form SchedulingInterviewForm
     */
    public SchedulingInterviewForm(Main mainMenu) {
        initComponents();
        this.mainMenu = mainMenu;

        // Set up gRPC connection
        setupGrpcConnection();

        // Add event listeners
        addEventListeners();

        // Set initial instruction text
        setInstructionText();
    }

    // Constructor needed for NetBeans designer
    private SchedulingInterviewForm() {
        initComponents();

        // Create main menu to avoid null pointer when going back
        this.mainMenu = new Main();

        // Set up connection and event listeners
        setupGrpcConnection();
        addEventListeners();

        // Set initial instruction text
        setInstructionText();
    }

    private void setupGrpcConnection() {
        try {
            // Connect to the gRPC service
            channel = ManagedChannelBuilder.forAddress("localhost", 50054)
                    .usePlaintext()
                    .build();

            // Generate JWT token for authentication
            String jwt = JwtUtil.generateToken("InterviewSchedulingClient");

            // Create authentication credentials with the token
            BearerToken token = new BearerToken(jwt);

            // Create the asynchronous stub with authentication
            asyncStub = InterviewSchedulingServiceGrpc.newStub(channel)
                    .withCallCredentials(token);

            System.out.println("Successfully connected to gRPC service");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error setting up gRPC connection: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Error connecting to gRPC service: " + e.getMessage() + "\n\nPlease make sure the server is running.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEventListeners() {
        // Add action listener for back to menu button
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        // Add action listener for submit button
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        // Close window = close the gRPC
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeGrpcConnection();
            }
        });
    }

    private void setInstructionText() {
        textArea1.setText("Please enter candidate names for interview scheduling.\n\n" +
                          "Enter one candidate name per line. For example:\n\n" +
                          "John Smith\n" +
                          "Jane Doe\n" +
                          "Alex Johnson\n");
    }

private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
    String candidateInput = textArea1.getText().trim();

    if (candidateInput.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Please enter candidate names first.",
            "Empty Input",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (asyncStub == null) {
        JOptionPane.showMessageDialog(this,
            "Not connected to the server. Attempting to reconnect...",
            "Connection Issue",
            JOptionPane.WARNING_MESSAGE);

        setupGrpcConnection();

        if (asyncStub == null) {
            JOptionPane.showMessageDialog(this,
                "Cannot connect to the server. Please make sure the InterviewSchedulingServer is running.",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    try {
        // Clear previous results
        textArea2.setText("Requesting interview slots...");

        // Parse the input to get candidate names
        List<String> candidateNames = extractCandidateNames(candidateInput);

        if (candidateNames.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No valid candidate names found. Please enter at least one candidate.",
                "No Candidates",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // For counting when the stream is completed
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // StringBuilder to collect all slots
        final StringBuilder slotsBuilder = new StringBuilder();
        slotsBuilder.append("INTERVIEW SLOTS:\n\n");

        // Create a stream observer to handle the response(interviewSlot) stream
        StreamObserver<InterviewSlot> responseObserver = new StreamObserver<InterviewSlot>() {
            private int slotCount = 0;
            private final Map<String, List<String>> candidateSlots = new HashMap<>();
            private String lastCandidate = null;
            private int candidateIndex = 0;

            @Override
            public void onNext(InterviewSlot slot) {
                // Add the slot to our display
                slotCount++;

                // Determine which candidate this slot belongs to
                // We need to track which candidate we're processing
                if (candidateIndex < candidateNames.size()) {
                    String candidateName = candidateNames.get(candidateIndex);

                    // Get or create the list for this candidate
                    List<String> slots = candidateSlots.computeIfAbsent(
                        candidateName, k -> new ArrayList<>());

                    // Add slot info to this candidate's list
                    String slotInfo = "Time: " + slot.getTime() +
                                      ", Location: " + slot.getLocation();
                    slots.add(slotInfo);

                    // If we've received 3 slots for this candidate, move to the next one
                    // Note: The server sends 3-5 slots per candidate, but we'll use 3 as our threshold
                    // to move to the next candidate for simplicity
                    if (slots.size() >= 3) {
                        candidateIndex++;
                    }

                    System.out.println("Received slot: " + slot.getTime() + " at " + slot.getLocation() +
                                      " for candidate: " + candidateName);

                    // Update the UI as slots arrive
                    updateSlotDisplay();
                }
            }

            private void updateSlotDisplay() {
                StringBuilder display = new StringBuilder("INTERVIEW SLOTS:\n\n");

                // Build the display text with all candidates and their slots
                for (String candidateName : candidateNames) {
                    List<String> slots = candidateSlots.get(candidateName);
                    if (slots != null && !slots.isEmpty()) {
                        display.append("\nFor candidate: ").append(candidateName).append("\n");
                        for (String slotInfo : slots) {
                            display.append(slotInfo).append("\n");
                        }
                    }
                }

                // Update the UI on the EDT
                javax.swing.SwingUtilities.invokeLater(() -> {
                    textArea2.setText(display.toString());
                });
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving interview slots: " + t.getMessage());
                slotsBuilder.append("\nError: ").append(t.getMessage());
                textArea2.setText(slotsBuilder.toString());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed receiving interview slots.");

                if (slotCount == 0) {
                    slotsBuilder.append("No interview slots received from server.");
                } else {
                    updateSlotDisplay();
                    slotsBuilder.append("\nTotal slots received: ").append(slotCount);
                    slotsBuilder.append("\n\nPlease note these slots for confirmation in the next step.");
                }

                // Update the UI on the EDT
                javax.swing.SwingUtilities.invokeLater(() -> {
                    textArea2.setText(textArea2.getText() +
                                     "\n\nTotal slots received: " + slotCount);
                });

                finishLatch.countDown();
            }
        };

        // Start the bidirectional streaming
        StreamObserver<CandidateName> requestObserver = asyncStub.arrangeInterviewSlot(responseObserver);

        // Send each candidate as a separate request
        for (String name : candidateNames) {
            // Skip empty lines
            if (name.trim().isEmpty()) {
                continue;
            }

            // Send the candidate name
            CandidateName candidateNameProto = CandidateName.newBuilder()
                    .setCandidateName(name)
                    .build();

            requestObserver.onNext(candidateNameProto);
            System.out.println("Sent candidate: " + name);

            // Add a small delay between candidates to prevent overwhelming the server
            try {
                Thread.sleep(200);  // Increased from 100 to 200
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Mark the end of requests
        requestObserver.onCompleted();
        System.out.println("Done sending candidates");

        // Wait for the response to complete - increased timeout
        if (!finishLatch.await(2, TimeUnit.MINUTES)) {
            textArea2.setText(textArea2.getText() + "\n\nTimed out waiting for server response");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Error scheduling interviews: " + e.getMessage(),
            "Service Error",
            JOptionPane.ERROR_MESSAGE);
    }
}


    // Extract candidate names from the text input
    private List<String> extractCandidateNames(String input) {
        List<String> candidateNames = new ArrayList<>();

        // Split by newline
        String[] lines = input.split("\\r?\\n");

        // Process each line
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty()) {
                // Add each non-empty line as a candidate name
                candidateNames.add(line);
            }
        }

        return candidateNames;
    }

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Close the gRPC connection
        closeGrpcConnection();

        // Return to main menu
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        } else {
            // If mainMenu is null, create a new one
            mainMenu = new Main();
            mainMenu.setVisible(true);
        }

        // Close this form
        this.dispose();
    }

    private void closeGrpcConnection() {
        if (channel != null && !channel.isShutdown()) {
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                System.out.println("gRPC connection closed successfully");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Error closing gRPC connection: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        jLabel3 = new javax.swing.JLabel();
        textArea2 = new java.awt.TextArea();
        SubmitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Back to Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Scheduling Interview Slot");

        jLabel2.setText("Please enter candidate names (one per line) and click SUBMIT button: ");

        jLabel3.setText("Interview Slot Result:");

        SubmitButton.setText("Submit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textArea2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SubmitButton))
                .addGap(6, 6, 6)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // This will be handled by our addEventListeners method
        backToMenuButtonActionPerformed(evt);
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
            java.util.logging.Logger.getLogger(SchedulingInterviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchedulingInterviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchedulingInterviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchedulingInterviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchedulingInterviewForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton SubmitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private java.awt.TextArea textArea1;
    private java.awt.TextArea textArea2;
    // End of variables declaration
}