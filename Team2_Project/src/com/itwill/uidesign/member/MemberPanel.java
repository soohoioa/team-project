package com.itwill.uidesign.member;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import java.awt.Font;

public class MemberPanel extends JPanel {
	
    private JTextField IdField;
    private JTextField NameField;
    private JTextField EmailField;
    private JTextField AddressField;
    private JTextField BirthField;
    private JTextField NumberField;
    private JTextField idListField;
    private JPasswordField passwordListField;
    private JTextField idUpdateField;
    private JPasswordField passwordUpdateField;
    private JTextField nameUpdateField;
    private JTextField emailUpdateField;
    private JTextField addressUpdateField;
    private JTextField birthUpdateField;
    private JTextField numberUpdateField;

    private MemberService memberService;

    public MemberPanel() throws Exception {
        setBackground(new Color(252, 207, 252));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        memberService = new MemberService();

        JTabbedPane memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        memberTabbedPane.setPreferredSize(new Dimension(10, 10));
        add(memberTabbedPane);

        JPanel memberListPanel = new JPanel();
        memberTabbedPane.addTab("로그인", null, memberListPanel, null);
        memberListPanel.setLayout(null);

        JLabel loginLabel = new JLabel("로그인");
        loginLabel.setBounds(159, 32, 57, 15);
        memberListPanel.add(loginLabel);

        idListField = new JTextField();
        idListField.setBounds(133, 99, 116, 21);
        memberListPanel.add(idListField);
        idListField.setColumns(10);

        passwordListField = new JPasswordField();
        passwordListField.setBounds(133, 158, 116, 21);
        memberListPanel.add(passwordListField);

        JButton loginListButton = new JButton("로그인");
        loginListButton.setBounds(43, 231, 97, 23);
        memberListPanel.add(loginListButton);
        
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(43, 102, 57, 15);
        memberListPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("P/W");
        lblNewLabel_1.setBounds(43, 161, 57, 15);
        memberListPanel.add(lblNewLabel_1);
        
        JButton addMemberButton = new JButton("회원가입");
        addMemberButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		memberTabbedPane.setSelectedIndex(2);
        	}
        });
        addMemberButton.setBounds(178, 231, 97, 23);
        memberListPanel.add(addMemberButton);
        loginListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberId = idListField.getText();
                String password = new String(passwordListField.getPassword());
                try {
                    int result = memberService.login(memberId, password);
                    if (result == 1) {
                       
                    } else {
                        
                    }
                } catch (Exception ex) {
                    
                    ex.printStackTrace();
                }
            }
        });

        // Other UI components...

        JPanel memberUpdatePanel = new JPanel();
        memberTabbedPane.addTab("수정", null, memberUpdatePanel, null);
        memberUpdatePanel.setLayout(null);

        idUpdateField = new JTextField();
        idUpdateField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 아이디, 이름, 생년월일은 수정이 안되므로 이벤트 처리 필요
            }
        });
        idUpdateField.setColumns(10);
        idUpdateField.setBounds(157, 83, 116, 21);
        memberUpdatePanel.add(idUpdateField);

        passwordUpdateField = new JPasswordField();
        passwordUpdateField.setBounds(157, 114, 116, 21);
        memberUpdatePanel.add(passwordUpdateField);

        // Other UI components...

        JButton userInfoUpdateButton = new JButton("회원정보");
        userInfoUpdateButton.setBounds(54, 363, 97, 23);
        memberUpdatePanel.add(userInfoUpdateButton);
        userInfoUpdateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원 정보 보기 기능 구현
                String memberPassword = new String(passwordUpdateField.getPassword());
                try {
                    Member member = memberService.memberDetail(memberPassword);
                    // member 객체를 이용해 필요한 정보를 UI에 보여주거나 처리
                } catch (Exception ex) {
                    // Handle exceptions if necessary
                    ex.printStackTrace();
                }
            }
        });

        // Other UI components...

        JPanel memberJoinPanel = new JPanel();
        memberJoinPanel.setBackground(new Color(217, 252, 255));
        memberTabbedPane.addTab("가입", null, memberJoinPanel, null);
        memberJoinPanel.setLayout(null);

        IdField = new JTextField();
        IdField.setBounds(181, 35, 116, 21);
        memberJoinPanel.add(IdField);
        IdField.setColumns(10);

        // Other UI components...

        JButton checkButton = new JButton("중복확인");
        checkButton.setBounds(309, 34, 97, 23);
        memberJoinPanel.add(checkButton);
        
        JLabel notiLabel = new JLabel("");
        notiLabel.setFont(new Font("굴림", Font.BOLD, 12));
        notiLabel.setForeground(new Color(255, 0, 0));
        notiLabel.setBounds(181, 66, 116, 15);
        memberJoinPanel.add(notiLabel);
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberId = IdField.getText();
                try {
                    boolean isDuplicated = memberService.isDuplicatedId(memberId);
                    if (isDuplicated) {
                        // 아이디 중복이면 처리
                    	JOptionPane.showMessageDialog(null, "이미 있는 아이디 입니다! 다시 입력해주세요.");
                    	IdField.setSelectionStart(0);
                    	IdField.setSelectionEnd(memberId.length());
                    	IdField.requestFocus();
                    	
                    } else {
                    	notiLabel.setText("사용가능한 아이디입니다.");
                    	
                    }
                } catch (Exception ex) {
                    // Handle exceptions if necessary
                    ex.printStackTrace();
                }
            }
        });

        
        
        // Other UI components...
    }
}
