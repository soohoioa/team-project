package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberDao;
import bbangshuttle.member.MemberService;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {
	private MemberDao memberDao;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signUpButton;

	private MemberService memberService;
	private JButton IdSearchButton;
	private JButton PwSearchButton;
	private String memberEmail;
	private String memberId;

	public LoginFrame(MemberService memberService) throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/images/o_user.png")));
		this.memberService = memberService;

		setTitle("Login Frame");
		setSize(640, 960);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// 아이디 입력 필드
		JLabel userNameLabel = new JLabel("아이디 ");
		userNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		userNameLabel.setBounds(281, 329, 62, 54);
		userNameField = new JTextField(10);
		userNameField.setBounds(215, 409, 189, 45);

		// 레이아웃 설정
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(244, 227, 204));
		loginPanel.setLayout(null);
		loginPanel.add(userNameLabel);
		loginPanel.add(userNameField);

		getContentPane().add(loginPanel);

		// 로그인 버튼
		loginButton = new JButton("로그인");
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.setBackground(new Color(252, 242, 203));
		loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		loginButton.setBounds(168, 627, 120, 50);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 로그인 버튼을 클릭하면 입력된 아이디와 비밀번호를 가져옴.
				String username = userNameField.getText();
				char[] password = passwordField.getPassword();

				// MemberService를 통해 로그인 기능을 수행함.
				try {
					Member member = memberService.loginMember(username, new String(password));
					if (member != null) {
						// 로그인 성공시 회원 프레임을 띄우고 현재 프레임을 숨김.
						MemberFrame memberFrame = new MemberFrame(memberService, member);
						memberFrame.setVisible(true);
						setVisible(false);
					} else {
						// 로그인 실패 처리
						JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패. 아이디와 비밀번호를 확인하세요.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(LoginFrame.this, "로그인 과정에서 오류가 발생했습니다.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				// 비밀번호 필드는 사용 후에 지워줌.
				passwordField.setText("");
			}
		});
		passwordField = new JPasswordField(10);
		passwordField.setBounds(215, 519, 189, 45);
		loginPanel.add(passwordField);

		// 비밀번호 입력 필드
		JLabel passwordLabel = new JLabel("비밀번호 ");
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		passwordLabel.setBounds(274, 464, 80, 45);
		loginPanel.add(passwordLabel);
		loginPanel.add(loginButton);

		this.memberService = memberService;
		memberDao = new MemberDao();
		
		IdSearchButton = new JButton("아이디 찾기");
		IdSearchButton.setBackground(new Color(252, 242, 203));
		IdSearchButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		IdSearchButton.setBounds(168, 706, 120, 45);
		IdSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When IdSearchButton is clicked, prompt user for input
				memberEmail = JOptionPane.showInputDialog(LoginFrame.this, "이메일을 입력하세요:");
				if (memberEmail != null && !memberEmail.isEmpty()) {
					try {
						// Use MemberService to search for the ID
						String id = memberDao.findByEmail(memberEmail);
						if (id != null) {
						    JOptionPane.showMessageDialog(LoginFrame.this, "검색된 아이디: " + id, "이메일 찾기 결과", JOptionPane.INFORMATION_MESSAGE);
						} else {
						    JOptionPane.showMessageDialog(LoginFrame.this, "검색된 이메일이 없습니다.", "이메일 찾기 결과", JOptionPane.INFORMATION_MESSAGE);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(LoginFrame.this, "이메일 검색 과정에서 오류가 발생했습니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, "이메일을 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		loginPanel.add(IdSearchButton);

		this.memberService = memberService;
		memberDao = new MemberDao();
		
		PwSearchButton = new JButton("비밀번호 찾기");
		PwSearchButton.setBackground(new Color(252, 242, 203));
		PwSearchButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		PwSearchButton.setBounds(314, 706, 120, 45);
		PwSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When IdSearchButton is clicked, prompt user for input
				memberId = JOptionPane.showInputDialog(LoginFrame.this, "아이디를 입력하세요:");
				if (memberId != null && !memberId.isEmpty()) {
					try {
						// Use MemberService to search for the ID
						String pw = memberDao.findById(memberId);
						if (pw != null) {
						    JOptionPane.showMessageDialog(LoginFrame.this, "검색된 비밀번호: " + pw, "아이디 찾기 결과", JOptionPane.INFORMATION_MESSAGE);
						} else {
						    JOptionPane.showMessageDialog(LoginFrame.this, "검색된 비밀번호가 없습니다.", "아이디 찾기 결과", JOptionPane.INFORMATION_MESSAGE);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(LoginFrame.this, "비밀번호 검색 과정에서 오류가 발생했습니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, "비밀번호를 입력하세요.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		loginPanel.add(PwSearchButton);

		// 회원가입 버튼
		signUpButton = new JButton("회원가입");
		signUpButton.setBackground(new Color(252, 242, 203));
		signUpButton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		signUpButton.setBounds(314, 627, 120, 50);
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼을 클릭하면 회원가입 폼 프레임을 띄움
				SignupFormFrame signupFormFrame = null;
				try {
					signupFormFrame = new SignupFormFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				signupFormFrame.setVisible(true);
			}
		});
		loginPanel.add(signUpButton);
		
		JLabel loginbread = new JLabel("");
		loginbread.setIcon(new ImageIcon(LoginFrame.class.getResource("/bbangshuttle/images/pngwing.com (4).png")));
		loginbread.setBounds(185, 71, 283, 223);
		loginPanel.add(loginbread);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MemberService memberService = new MemberService();
					LoginFrame loginFrame = new LoginFrame(memberService);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}