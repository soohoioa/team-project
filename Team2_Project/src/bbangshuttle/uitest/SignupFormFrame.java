package bbangshuttle.uitest;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberDao;
import bbangshuttle.member.MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignupFormFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField contactField;
    private JComboBox<String> yearCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<String> dayCombo;
    private JButton signUpButton;
    private JButton cancelButton;

    private MemberService memberService;

    public SignupFormFrame() throws Exception {
        setTitle("Sign Up Form Frame");
        setSize(640, 960);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        memberService = new MemberService();

        // 컴포넌트 생성 및 배치
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("아이디:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("비밀번호:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(confirmPasswordField, gbc);

        JLabel nameLabel = new JLabel("이름:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("이메일:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(emailField, gbc);

        JLabel addressLabel = new JLabel("주소:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(addressLabel, gbc);

        addressField = new JTextField(20);
        addressField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        panel.add(addressField, gbc);

        JLabel contactLabel = new JLabel("연락처:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(contactLabel, gbc);

        contactField = new JTextField(20);
        contactField.setPreferredSize(new Dimension(200, 30)); 
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        panel.add(contactField, gbc);

        // 
        contactField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '-')) {
                    evt.consume();
                }
            }
        });

        JLabel birthdayLabel = new JLabel("생년월일:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(birthdayLabel, gbc);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = Integer.toString(year - i);
        }
        yearCombo = new JComboBox<>(years);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(yearCombo, gbc);

        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = Integer.toString(i + 1);
        }
        monthCombo = new JComboBox<>(months);
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(monthCombo, gbc);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = Integer.toString(i + 1);
        }
        dayCombo = new JComboBox<>(days);
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(dayCombo, gbc);

        signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					signup();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        panel.add(signUpButton, gbc);

        cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					goBackToLogin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        panel.add(cancelButton, gbc);

        add(panel);
    }

    private void signup() throws Exception {
        // 회원가입 정보 DB에 저장
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String contact = contactField.getText();
        String birthday = yearCombo.getSelectedItem() + "-" + monthCombo.getSelectedItem() + "-" + dayCombo.getSelectedItem();

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }

        // MemberDao를 이용하여 회원 정보를 데이터베이스에 저장
        MemberDao memberDao = new MemberDao();
        Member newMember = new Member(username, password, name, email, address,birthday,contact,null,0);
        try {
            memberDao.insert(newMember);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다. 다시 시도해주세요.");
            return;
        }

        // 회원가입 성공 메시지 표시
        JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");

        // 로그인 화면으로 이동
        goBackToLogin();
    }

    private Date parseBirthday(String birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void goBackToLogin() throws Exception {
        new LoginFrame(memberService).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignupFormFrame signupFrame = null;
				try {
					signupFrame = new SignupFormFrame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                signupFrame.setVisible(true);
            }
        });
    }
}
