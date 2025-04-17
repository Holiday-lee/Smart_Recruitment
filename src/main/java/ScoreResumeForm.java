import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.CandidateResume;
import generated.grpc.candidatefilteringservice.ResumeScore;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import distsys.smart_recruitment.auth.BearerToken; // for authorise problem
import distsys.smart_recruitment.auth.JwtUtil; // for authorise problem

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jiaki
 */
public class ScoreResumeForm extends javax.swing.JFrame {

    // to store the candidate & resume score result - static to be accessible from other forms
    private static Map<String, ResumeScore> scoredResumes = new HashMap<>();
    private static Map<String, CandidateResume> candidateResumes = new HashMap<>();

    private ManagedChannel channel;
    private CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub;
    private Main mainMenu; // variable links to mainMenu

    /**
     * Creates new form ScoreResumeForm
     */
    public ScoreResumeForm(Main mainMenu) {
        initComponents();
        this.mainMenu = mainMenu; // link the backToMenu to main menu
        setupGrpcConnection(); // set up to gRPC service
        addEventListeners(); // add event listeners to buttons
        jTextArea1.setEditable(false); // results text area should be read-only
    }

    // constructor needed for NetBeans designer in line 402
    private ScoreResumeForm() {
        initComponents();
        addEventListeners();
    }

    // To set up the gRPC service with improved error handling
    private void setupGrpcConnection() {
        try {
            // Connect to your gRPC service
            channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                    .usePlaintext()
                    .build();

            // Generate JWT token for authentication
            String jwt = JwtUtil.generateToken("CandidateFilteringClient");
            System.out.println("Generated JWT token for authentication");

            // Create authentication credentials with the token
            BearerToken token = new BearerToken(jwt);

            // Add authentication to the stub
            blockingStub = CandidateFilteringServiceGrpc.newBlockingStub(channel)
                    .withCallCredentials(token);

            System.out.println("Successfully connected to gRPC service");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error setting up gRPC connection: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error connecting to gRPC service: " + e.getMessage() + "\n\nPlease make sure the server is running.",
                "Connection Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add event listeners to buttons
    private void addEventListeners() {
        // Submit new request button
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRequestButtonActionPerformed(evt);
            }
        });

        // Back to menu button
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenuButtonActionPerformed(evt);
            }
        });

        // close window = close the gRPC
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeGrpcConnection();
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
        jLabel1 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        textArea1 = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Scoring Candidate Resume");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Please paste the resume content and click submit: ");

        jButton2.setText("Submit a New Request");

        jButton3.setText("Back to Menu");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Scoring Result:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(textArea1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(submitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Submit button with improved error handling
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // get resume text from text area
        String resumeText = textArea1.getText().trim();

        if (resumeText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Please paste resume content first.",
                "Empty Content",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check if blockingStub is null and try to reconnect
        if (blockingStub == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Not connected to the server. Attempting to reconnect...",
                "Connection Issue",
                javax.swing.JOptionPane.WARNING_MESSAGE);

            setupGrpcConnection();

            // Check if reconnection was successful
            if (blockingStub == null) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Cannot connect to the server. Please make sure the CandidateFilteringServer is running.",
                    "Connection Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            // Extract information from resume text
            Map<String, String> extractedInfo = extractResumeInfo(resumeText);

            // generate a candidate ID
            String candidateId = "candidate_" + System.currentTimeMillis();

            // build gRPC request
            CandidateResume.Builder resumeBuilder = CandidateResume.newBuilder()
                .setCandidateId(candidateId)
                .setResumeText(resumeText);

            // add candidate name
            if (extractedInfo.containsKey("name")) {
                resumeBuilder.setCandidateName(extractedInfo.get("name"));
            } else {
                resumeBuilder.setCandidateName("Anonymous Candidate");
            }

            // add years of experience
            if (extractedInfo.containsKey("experience")) {
                try {
                    int years = Integer.parseInt(extractedInfo.get("experience"));
                    resumeBuilder.setYearsExperience(years);
                } catch (NumberFormatException e) {
                    resumeBuilder.setYearsExperience(0);
                }
            } else {
                resumeBuilder.setYearsExperience(0);
            }

            // add skills
            if (extractedInfo.containsKey("skills")) {
                String[] skills = extractedInfo.get("skills").split(",");
                for (String skill : skills) {
                    if (!skill.trim().isEmpty()) {
                        resumeBuilder.addSkills(skill.trim());
                    }
                }
            }

            // Log extracted information for debugging
            System.out.println("Extracted Resume Information:");
            System.out.println("Name: " + extractedInfo.getOrDefault("name", "Not detected"));
            System.out.println("Experience: " + extractedInfo.getOrDefault("experience", "Not detected") + " years");
            System.out.println("Skills: " + extractedInfo.getOrDefault("skills", "Not detected"));

            // build the resume object
            CandidateResume candidateResume = resumeBuilder.build();

            // call gRPC service
            ResumeScore response = blockingStub.scoringCandidateResume(candidateResume);

            // store the results
            scoredResumes.put(candidateId, response);
            candidateResumes.put(candidateId, candidateResume);

            // display result
            displayResults(response, extractedInfo);

        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error analyzing resume: " + ex.getMessage(),
                "Service Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    // extractResumeInfo method with improved pattern matching
    private Map<String, String> extractResumeInfo(String resumeText) {
        Map<String, String> info = new HashMap<>();

        // Debug info
        System.out.println("Analyzing resume text of length: " + resumeText.length());

        // Name detection - looks for patterns like "Name: John Doe" or name at the beginning
        Pattern namePattern = Pattern.compile("(?i)(?:name\\s*:\\s*|^\\s*)(\\w+\\s+\\w+)");
        Matcher nameMatcher = namePattern.matcher(resumeText);
        if (nameMatcher.find()) {
            info.put("name", nameMatcher.group(1).trim());
            System.out.println("Found name: " + nameMatcher.group(1).trim());
        } else {
            System.out.println("No name detected");
        }

        // Experience detection - improved pattern to catch more variations
        Pattern expPattern = Pattern.compile("(?i)(\\d+)\\s*(?:years?|yrs?)(?:\\s+(?:of\\s+)?(?:experience|exp|work))?");
        Matcher expMatcher = expPattern.matcher(resumeText);
        if (expMatcher.find()) {
            info.put("experience", expMatcher.group(1));
            System.out.println("Found experience: " + expMatcher.group(1) + " years");
        } else {
            System.out.println("No experience detected - searching for: " + expPattern.pattern());
            // Print the first 200 characters of text to see what we're analyzing
            System.out.println("Text sample: " + resumeText.substring(0, Math.min(200, resumeText.length())));
        }

        // Skills detection - improved pattern to catch more variations
        Pattern skillsPattern = Pattern.compile("(?i)(?:skills|technical skills|core competencies|expertise)\\s*:?\\s*([^\\n\\r.]*(?:[\\n\\r.][^\\n\\r.]*){0,8})");
        Matcher skillsMatcher = skillsPattern.matcher(resumeText);
        if (skillsMatcher.find()) {
            String skillsText = skillsMatcher.group(1).replaceAll("[\\n\\r\\t•●■]+", ",");
            skillsText = skillsText.replaceAll("\\s+", " ");
            skillsText = skillsText.replaceAll(",\\s*,", ",");
            info.put("skills", skillsText);
            System.out.println("Found skills: " + skillsText);
            System.out.println("Number of skills: " + skillsText.split(",").length);
        } else {
            System.out.println("No skills section detected");
        }

        return info;
    }

    // Method to display results in the text area
    private void displayResults(ResumeScore response, Map<String, String> extractedInfo) {
        // Get the score as an integer for clearer display
        int intScore = (int) response.getScore();

        // Create a formatted results string for the text area
        StringBuilder results = new StringBuilder();
        results.append("Resume Score: ").append(intScore).append("/100");

        // Display only the score in the results text area
        jTextArea1.setText(results.toString());

        // Optional: you can also log the detailed information if needed
        System.out.println("Score Result Information:");
        System.out.println("Name: " + extractedInfo.getOrDefault("name", "Not detected"));
        System.out.println("Experience: " + extractedInfo.getOrDefault("experience", "Not detected") + " years");
        System.out.println("Skills: " + extractedInfo.getOrDefault("skills", "Not detected"));
        System.out.println("Final Score: " + intScore);
    }

    // Submit new request button
    private void newRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear the text areas
        textArea1.setText("");
        jTextArea1.setText("");
    }

    // Back to Menu button - CORRECTED METHOD
	private void backToMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// Close this form and return to main menu
		closeGrpcConnection();

		// Check if mainMenu is not null before using it
		if (mainMenu != null) {
			// Show the existing main menu that was passed to the constructor
			mainMenu.setVisible(true);
		} else {
			// If mainMenu is null, create a new Main instance
			Main newMain = new Main();
			newMain.setVisible(true);
		}

		// Dispose this form
		this.dispose();
	}

    // Method to close gRPC connection
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

    // Static methods to access scored resumes from other forms
    public static Map<String, ResumeScore> getScoredResumes() {
        return scoredResumes;
    }

    public static Map<String, CandidateResume> getCandidateResumes() {
        return candidateResumes;
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
            java.util.logging.Logger.getLogger(ScoreResumeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreResumeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreResumeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreResumeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScoreResumeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton submitButton;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}