import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jiaki
 */
public class QualifiedCandidatesForm extends javax.swing.JFrame {

    private Main mainMenu;
    /**
     * Creates new form QualifiedCandidatesForm
     */
    // Update constructor to accept Main form so that the Back to Menu links to Main menu
    public QualifiedCandidatesForm(Main mainMenu) {
        initComponents();
        this.mainMenu = mainMenu;

        // Initialise with empty text field
        minimumScoreCriteria.setText("");

         // Clear the results area
        qualifiedCandidateList.setText("");

    }

    // constructor needed for NetBeans designer
    private QualifiedCandidatesForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        minimumScoreCriteria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        qualifiedCandidateList = new javax.swing.JTextArea();
        backToMenuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Qualified Candidate List");

        minimumScoreCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimumScoreCriteriaActionPerformed(evt);
            }
        });

        jLabel2.setText("Minimum Score: ");

        jLabel3.setText("Please enter the minimun score (criteria) to filter qualified candidates.");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        qualifiedCandidateList.setColumns(20);
        qualifiedCandidateList.setRows(5);
        jScrollPane1.setViewportView(qualifiedCandidateList);

        backToMenuButton.setText("Back to Menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backToMenuButton)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(minimumScoreCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(minimumScoreCriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(backToMenuButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minimumScoreCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimumScoreCriteriaActionPerformed


    }//GEN-LAST:event_minimumScoreCriteriaActionPerformed

    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMenuButtonActionPerformed
        // back to menu button
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMenuButtonActionPerformed

private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // Submit button handler
    // Clear previous results
    qualifiedCandidateList.setText("");

    try {
        // Get the minimum score from the text field
        double minScore = Double.parseDouble(minimumScoreCriteria.getText());

        // Create the gRPC channel
        String host = "localhost";
        int port = 50051;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            // Generate JWT token for authentication
            String jwt = JwtUtil.generateToken("CandidateFilteringClient");

            // Create authentication credentials with the token
            BearerToken token = new BearerToken(jwt);

            // Create the asynchronous stub with authentication
            CandidateFilteringServiceGrpc.CandidateFilteringServiceStub asyncStub =
                    CandidateFilteringServiceGrpc.newStub(channel)
                    .withCallCredentials(token);

            // Create the request for filtering qualified candidates
            QualificationCriteria request = QualificationCriteria.newBuilder()
                    .setMinScore(minScore)
                    .build();

            // Collect candidates in a list to display them all at once
            List<QualifiedCandidate> candidates = new ArrayList<>();

            // Make the asynchronous call with a StreamObserver to handle the streaming responses
            asyncStub.qualifiedCandidateList(request, new StreamObserver<QualifiedCandidate>() {
                @Override
                public void onNext(QualifiedCandidate candidate) {
                    // Store each candidate as it arrives
                    candidates.add(candidate);
                }

                @Override
                public void onError(Throwable t) {
                    // Handle errors that occur during the stream
                    qualifiedCandidateList.setText("Error retrieving candidates:\n" + t.getMessage());

                    // Clean up the channel
                    try {
                        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                }

                @Override
                public void onCompleted() {
                    // Display all candidates when the server has finished sending responses
                    if (candidates.isEmpty()) {
                        qualifiedCandidateList.setText("No qualified candidates found with score >= " + minScore);
                    } else {
                        // Clear any previous text
                        qualifiedCandidateList.setText("");

                        // Display each candidate with the correct numbering
                        for (int i = 0; i < candidates.size(); i++) {
                            QualifiedCandidate candidate = candidates.get(i);
                            qualifiedCandidateList.append((i + 1) + ". " + candidate.getCandidateName());
                            qualifiedCandidateList.append("  Score: " + candidate.getScore() + "\n\n");
                        }

                        // Add total count at the end
                        qualifiedCandidateList.append("Total qualified candidates: " + candidates.size());
                    }

                    // Clean up the channel
                    try {
                        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                }
            });

        } catch (Exception e) {
            qualifiedCandidateList.setText("Error: " + e.getMessage());

            // Clean up the channel
            try {
                channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ie) {
                // Ignore
            }
        }

    } catch (NumberFormatException e) {
        // Handle invalid input
        qualifiedCandidateList.setText("Please enter a valid number for the minimum score.");
    }
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
            java.util.logging.Logger.getLogger(QualifiedCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QualifiedCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QualifiedCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QualifiedCandidatesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QualifiedCandidatesForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField minimumScoreCriteria;
    private javax.swing.JTextArea qualifiedCandidateList;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}