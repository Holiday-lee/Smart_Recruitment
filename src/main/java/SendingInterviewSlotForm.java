import distsys.smart_recruitment.CandidateEngagementClient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
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

public class SendingInterviewSlotForm extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(SendingInterviewSlotForm.class.getName());
    private Main mainMenu;
    private CandidateEngagementClient client;

    /**
     * Creates new form SendingInterviewSlotForm with a link to the main menu
     * @param mainMenu The main menu form
     */
    public SendingInterviewSlotForm(Main mainMenu) {
        initComponents();
        this.mainMenu = mainMenu;
        initClient();
        setupActionListeners();
    }

    /**
     * Default constructor for the form
     */
    private SendingInterviewSlotForm() {
        initComponents();
        initClient();
        setupActionListeners();
    }

    /**
     * Initialize the gRPC client with authentication
     */
    private void initClient() {
        try {
            // Create client with default client ID
            client = new CandidateEngagementClient("localhost", 50053);
            logger.info("Connected to gRPC server at localhost:50053");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to connect to gRPC server: {0}", e.getMessage());
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
        // Add action listener for the submit button
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
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
     * Handle submit button click - send interview slots to candidate
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (client == null) { // client 50053 connection = null
            JOptionPane.showMessageDialog(this,
                "Client not connected to server",
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // get the input from textArea1
            String input = textArea1.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please enter candidate information and interview slots",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // set first line = name, the following line continued by time and location
            /* 
            * Name
            * interview time 
            * interview location
            */ 
            String[] lines = input.split("\\n"); // store the input(whole interview slots) 
            if (lines.length < 2) {
                JOptionPane.showMessageDialog(this,
                    "Please enter at least one interview slot (time and location)",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // lines[0] = first line from input text is candidateName
            String candidateName = lines[0];

            // the lines[1][2] need to be parsed and store in String[] times & String[] locations
            int slotCount = lines.length - 1; 
            String[] times = new String[slotCount]; // = String[1]
            String[] locations = new String[slotCount]; // = String [2]

            for (int i = 0; i < slotCount; i++) {
                String[] parts = lines[i + 1].split(",");
                if (parts.length < 2) {
                    JOptionPane.showMessageDialog(this,
                        "Please format each slot as 'time, location'",
                        "Format Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                times[i] = parts[0].trim();
                locations[i] = parts[1].trim();
            }

            // send the interviewSlots message with parameters of Name, Times, Location to the server by client(client = that server port 50053 connection)
            client.sendInterviewSlots(candidateName, times, locations);

            // need to create a log message with date, interview slots and candidateName
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            StringBuilder logMessage = new StringBuilder();
            logMessage.append(sdf.format(new Date()))
                     .append(" - Sent ")
                     .append(slotCount)
                     .append(" interview slots to ")
                     .append(candidateName)
                     .append("\n");

            jTextArea1.append(logMessage.toString());

        } catch (Exception e) {
            // Log error
            jTextArea1.append("ERROR: " + e.getMessage() + "\n");
            logger.log(Level.SEVERE, "Error sending interview slots: {0}", e.getMessage());

            // Show error message
            JOptionPane.showMessageDialog(this,
                "Error sending interview slots: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handle back to menu button click
     */
    private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Close resources
        if (client != null) {
            try {
                client.shutdown();
                logger.info("Client connection closed");
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Error shutting down client: {0}", e.getMessage());
            }
        }

        // Return to main menu
        if (mainMenu != null) {
            mainMenu.setVisible(true);
        }

        // Close this form
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backToMenuButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        jLabel2 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backToMenuButton.setText("Back to Menu");

        jLabel1.setText("SENDING INTERVIEW SLOT");

        jLabel2.setText("Please enter candidate name and provide the interview slots info:");

        submitButton.setText("Submit");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Interview notification sending log: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backToMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
                        .addContainerGap(45, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(submitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(backToMenuButton)
                .addGap(17, 17, 17))
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
            java.util.logging.Logger.getLogger(SendingInterviewSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendingInterviewSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendingInterviewSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendingInterviewSlotForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendingInterviewSlotForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton submitButton;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}