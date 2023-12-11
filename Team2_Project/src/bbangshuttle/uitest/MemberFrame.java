package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MemberFrame extends JFrame {
    private MemberService memberService;
    private Member loggedInMember;

    // 회원 정보 표시 구성 요소
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel addressLabel;
    private JLabel phoneLabel;
    private JLabel birthLabel;
    private JLabel joinDateLabel;
    private JLabel pointLabel;
    private JButton infoUpdateButton;
    private JButton memberDeleteButton;

    // 회원 정보 수정 구성 요소
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel viewPanel;
    private JPanel updatePanel;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton mainButton; // 메인 프레임으로 화면 전환하는 버튼
    private JButton logoutButton;
    private JButton logoutNewButton;
    private JLabel lblNewLabel;

    public MemberFrame(MemberService memberService, Member loggedInMember) {
       setIconImage(Toolkit.getDefaultToolkit().getImage(MemberFrame.class.getResource("/bbangshuttle/images/2530816_align_employee_general_human_human list_icon.png")));
        this.memberService = memberService;
        this.loggedInMember = loggedInMember;

        setTitle("Member Frame");
        setSize(640, 960); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 회원 정보 표시 패널 구성
        idLabel = new JLabel("                     아이디 : " + loggedInMember.getMemberId());
        idLabel.setBounds(0, 1, 312, 101);
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        nameLabel = new JLabel("                    이름 : " + loggedInMember.getMemberName());
        nameLabel.setBounds(312, 1, 312, 101);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        emailLabel = new JLabel("              이메일 : " + loggedInMember.getMemberEmail());
        emailLabel.setBounds(0, 102, 312, 101);
        emailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        addressLabel = new JLabel("              주소 : " + loggedInMember.getMemberAddress());
        addressLabel.setBounds(312, 102, 312, 101);
        addressLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        phoneLabel = new JLabel("              연락처 : " + loggedInMember.getMemberNumber());
        phoneLabel.setBounds(0, 203, 312, 101);
        phoneLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        birthLabel = new JLabel("              생년월일 : " + loggedInMember.getMemberBirth());
        birthLabel.setBounds(312, 203, 312, 101);
        birthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        joinDateLabel = new JLabel("              가입일자 : " + formatDate(loggedInMember.getMemberRegdate()));
        joinDateLabel.setBounds(0, 304, 312, 101);
        joinDateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        pointLabel = new JLabel("                    포인트 : " + loggedInMember.getMemberPoint());
        pointLabel.setBounds(312, 304, 312, 101);
        pointLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        infoUpdateButton = new JButton("정보 수정");
        infoUpdateButton.setBackground(new Color(252, 242, 203));
        infoUpdateButton.setBounds(85, 436, 205, 76);
        infoUpdateButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        infoUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 정보 수정 페이지로 전환
                cardLayout.show(cardPanel, "Update");
            }
        });

        memberDeleteButton = new JButton("회원 탈퇴");
        memberDeleteButton.setBackground(new Color(252, 242, 203));
        memberDeleteButton.setBounds(328, 436, 205, 76);
        memberDeleteButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        memberDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MemberFrame.this, "정말로 회원 탈퇴하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        memberService.memberDelete(loggedInMember.getMemberId());
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 탈퇴가 완료되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // 로그인 프레임으로 돌아가기
                        cardLayout.show(cardPanel, "Login");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 탈퇴 과정에서 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        mainButton = new JButton("메인 프레임으로"); // 메인 프레임으로 화면 전환하는 버튼
        mainButton.setBackground(new Color(252, 242, 203));
        mainButton.setBounds(85, 537, 205, 76);
        mainButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 메인 프레임으로 돌아가기
              
                MainFrame mainFrame = null;
            try {
               mainFrame = new MainFrame(loggedInMember);
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
                mainFrame.setVisible(true);
                dispose(); // 현재 프레임 닫기
            }
        });

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(244, 227, 204));
        infoPanel.setLayout(null);
        infoPanel.add(idLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(addressLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(birthLabel);
        infoPanel.add(joinDateLabel);
        infoPanel.add(pointLabel);
        infoPanel.add(infoUpdateButton);
        infoPanel.add(memberDeleteButton);
        infoPanel.add(mainButton); // 메인 프레임으로 화면 전환하는 버튼 추가

        // 회원 정보 수정 패널 구성
        emailField = new JTextField(loggedInMember.getMemberEmail(), 10);
        addressField = new JTextField(loggedInMember.getMemberAddress(), 10);
        phoneField = new JTextField(loggedInMember.getMemberNumber(), 10);
        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);

        saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 정보 수정 기능 구현
                String email = emailField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (password.equals(confirmPassword)) {
                    // 비밀번호가 일치하는 경우에만 업데이트
                    loggedInMember.setMemberEmail(email);
                    loggedInMember.setMemberAddress(address);
                    loggedInMember.setMemberNumber(phone);
                    loggedInMember.setMemberPassword(password);

                    try {
                        memberService.memberUpdate(loggedInMember);
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 정보가 수정되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        showMemberInfo();
                        cardLayout.show(cardPanel, "Info");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(MemberFrame.this, "회원 정보 수정에 실패했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(MemberFrame.this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton = new JButton("취소");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정 취소 후 회원 정보 보기 페이지로 전환
                showMemberInfo();
                cardLayout.show(cardPanel, "Info");
            }
        });

        JPanel infoUpdatePanel = new JPanel();
        infoUpdatePanel.setLayout(new GridLayout(6, 2));
        infoUpdatePanel.add(new JLabel("비밀번호:"));
        infoUpdatePanel.add(passwordField);
        infoUpdatePanel.add(new JLabel("비밀번호 확인:"));
        infoUpdatePanel.add(confirmPasswordField);
        infoUpdatePanel.add(new JLabel("이메일:"));
        infoUpdatePanel.add(emailField);
        infoUpdatePanel.add(new JLabel("주소:"));
        infoUpdatePanel.add(addressField);
        infoUpdatePanel.add(new JLabel("연락처:"));
        infoUpdatePanel.add(phoneField);
        infoUpdatePanel.add(saveButton);
        infoUpdatePanel.add(cancelButton);

        JPanel panel = new JPanel();
        // 카드 레이아웃 설정
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(infoPanel, "Info");
        
        logoutNewButton = new JButton("로그아웃");
        logoutNewButton.setBackground(new Color(252, 242, 203));
        logoutNewButton.setBounds(328, 537, 205, 76);
        logoutNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        infoPanel.add(logoutNewButton);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(MemberFrame.class.getResource("/bbangshuttle/images/pngwing.com (4).png")));
        lblNewLabel.setBounds(173, 655, 281, 230);
        infoPanel.add(lblNewLabel);
        logoutNewButton.addActionListener(e -> logout());

        // ... 이전 코드 생략 ...

        // panel을 frame에 추가
        getContentPane().add(panel, BorderLayout.SOUTH);
      
        
        cardPanel.add(infoUpdatePanel, "Update");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Info");
    }

    private void showMemberInfo() {
        // 회원 정보 표시
        idLabel.setText("아이디: " + loggedInMember.getMemberId());
        nameLabel.setText("이름: " + loggedInMember.getMemberName());
        emailLabel.setText("이메일: " + loggedInMember.getMemberEmail());
        addressLabel.setText("주소: " + loggedInMember.getMemberAddress());
        phoneLabel.setText("연락처: " + loggedInMember.getMemberNumber());
        birthLabel.setText("생년월일: " + loggedInMember.getMemberBirth());
        joinDateLabel.setText("가입일자: " + formatDate(loggedInMember.getMemberRegdate()));
        pointLabel.setText("포인트: " + loggedInMember.getMemberPoint());
    }

    // 로그아웃 기능 구현
    private void logout() {
        loggedInMember = null;
        JOptionPane.showMessageDialog(this, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        // 로그아웃 후 다시 로그인 프레임을 띄움
       System.exit(0);
        
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
}