package bbangshuttle.test;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

public class MemberFrame extends JFrame {
    private MemberService memberService;
    private JTextField memberIdTextField;
    private JTextArea memberInfoTextArea;

    public MemberFrame() {
        try {
            memberService = new MemberService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JFrame 설정
        setTitle("회원 정보 조회");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 회원 아이디 입력을 위한 텍스트 필드와 조회 버튼
        JPanel inputPanel = new JPanel();
        JLabel memberIdLabel = new JLabel("회원 아이디: ");
        memberIdTextField = new JTextField(20);
        JButton searchButton = new JButton("조회");
        inputPanel.add(memberIdLabel);
        inputPanel.add(memberIdTextField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        // 조회된 회원 정보를 표시할 JTextArea
        memberInfoTextArea = new JTextArea();
        memberInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(memberInfoTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // 조회 버튼의 ActionListener 구현
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String memberId = memberIdTextField.getText().trim();
                    Member member = memberService.memberDetail(memberId);

                    if (member != null) {
                        // 회원 정보를 텍스트 영역에 표시
                        String memberInfo = "아이디: " + member.getMemberId() + "\n"
                                + "이름: " + member.getMemberName() + "\n"
                                + "이메일: " + member.getMemberEmail() + "\n"
                                + "주소: " + member.getMemberAddress() + "\n"
                                + "생년월일: " + member.getMemberBirth() + "\n"
                                + "연락처: " + member.getMemberNumber() + "\n"
                                + "가입일: " + member.getMemberRegdate() + "\n"
                                + "보유 포인트: " + member.getMemberPoint();
                        memberInfoTextArea.setText(memberInfo);
                    } else {
                        // 회원 정보가 없는 경우
                        memberInfoTextArea.setText("해당 아이디의 회원 정보를 찾을 수 없습니다.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 프레임이 보여지도록 설정
        setVisible(true);
    }

    public static void main(String[] args) {
        // 프레임 생성
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MemberFrame();
            }
        });
    }
}
