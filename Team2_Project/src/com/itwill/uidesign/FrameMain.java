package com.itwill.uidesign;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.uidesign.member.MemberPanel;
import com.itwill.uidesign.product.ProductPanel;

import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;
import bbangshuttle.order.OrderService;
import bbangshuttle.product.ProductService;

public class FrameMain extends JFrame {

	private MemberService ms;
	private CartService cs;
	private ProductService ps;
	private OrderService os;

	private Member loginMember;
	
	private JPanel contentPane;
	private JPanel parentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain frame = new FrameMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public FrameMain() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northNavigationPanel = new JPanel();
		northNavigationPanel.setPreferredSize(new Dimension(10, 35));
		contentPane.add(northNavigationPanel, BorderLayout.NORTH);
		northNavigationPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("회원");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*** MemberPanel 보여주기 ***/
				CardLayout cardLayout = (CardLayout)parentPanel.getLayout();
				
				cardLayout.show(parentPanel, "member");
			}
		});
		btnNewButton.setBounds(0, 10, 97, 23);
		northNavigationPanel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("주문");
		btnNewButton_2.setBounds(328, 10, 97, 23);
		northNavigationPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("카트");
		btnNewButton_3.setBounds(218, 10, 97, 23);
		northNavigationPanel.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("상품");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*** ProductPanel 보여주기 ***/
				CardLayout cardLayout = (CardLayout)parentPanel.getLayout();
				
				cardLayout.show(parentPanel, "product");
			}
		});
		btnNewButton_1.setBounds(109, 10, 97, 23);
		northNavigationPanel.add(btnNewButton_1);
		
		parentPanel = new JPanel();
		contentPane.add(parentPanel, BorderLayout.CENTER);
		parentPanel.setLayout(new CardLayout(0, 0));
		
		MemberPanel memberPanel = new MemberPanel();
		parentPanel.add(memberPanel, "member");
		
		ProductPanel productPanel = new ProductPanel();
		parentPanel.add(productPanel, "product");
		
		
		ms = new MemberService();
		ps = new ProductService();
		os = new OrderService();
		cs = new CartService();
	}
	
	public void loginProcess(Member loginMember) {
    	this.loginMember = loginMember;
    }
	
}
