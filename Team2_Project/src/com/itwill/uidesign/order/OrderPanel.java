package com.itwill.uidesign.order;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class OrderPanel extends JPanel {
	private JPanel orderContenePanel;
	public OrderPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 435, 489);
		add(scrollPane);
		
		orderContenePanel = new JPanel();
		orderContenePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		orderContenePanel.setPreferredSize(new Dimension(300, 2000));
		scrollPane.setViewportView(orderContenePanel);
		orderContenePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel orderTitlePanel = new JPanel();
		orderTitlePanel.setLayout(null);
		orderTitlePanel.setPreferredSize(new Dimension(350, 30));
		orderTitlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		orderTitlePanel.setBackground(Color.LIGHT_GRAY);
		orderContenePanel.add(orderTitlePanel);
		
		JLabel orderItemOrderNoTitleLabel = new JLabel("주문번호");
		orderItemOrderNoTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemOrderNoTitleLabel.setFont(new Font("D2Coding", Font.PLAIN, 11));
		orderItemOrderNoTitleLabel.setBounds(87, 3, 56, 25);
		orderTitlePanel.add(orderItemOrderNoTitleLabel);
		
		JLabel orderItemTotPriceLabel = new JLabel("￦ 1,050,000");
		orderItemTotPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemTotPriceLabel.setFont(new Font("D2Coding", Font.BOLD, 13));
		orderItemTotPriceLabel.setBounds(222, 3, 100, 25);
		orderTitlePanel.add(orderItemTotPriceLabel);
		
		JLabel orderItemTotalTitleLabel = new JLabel("Total:");
		orderItemTotalTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemTotalTitleLabel.setFont(new Font("굴림", Font.BOLD, 11));
		orderItemTotalTitleLabel.setBounds(182, 3, 45, 25);
		orderTitlePanel.add(orderItemTotalTitleLabel);
		
		JLabel orderNoLabel = new JLabel("0");
		orderNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderNoLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		orderNoLabel.setBounds(143, 3, 28, 26);
		orderTitlePanel.add(orderNoLabel);
		
		JLabel orderItemOrderDateLabel = new JLabel("2023/01/31");
		orderItemOrderDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemOrderDateLabel.setFont(new Font("D2Coding", Font.BOLD, 14));
		orderItemOrderDateLabel.setBounds(1, 2, 87, 28);
		orderTitlePanel.add(orderItemOrderDateLabel);
		
		JButton orderItemDeleteButton = new JButton("");
		orderItemDeleteButton.setIcon(new ImageIcon(OrderPanel.class.getResource("/images/cart_item_delete30.png")));
		orderItemDeleteButton.setBorder(null);
		orderItemDeleteButton.setBackground(Color.WHITE);
		orderItemDeleteButton.setBounds(320, 4, 24, 22);
		orderTitlePanel.add(orderItemDeleteButton);
		
		JPanel orderItemPanel = new JPanel();
		orderItemPanel.setLayout(null);
		orderItemPanel.setPreferredSize(new Dimension(350, 50));
		orderItemPanel.setBorder(null);
		orderItemPanel.setBackground(Color.WHITE);
		orderContenePanel.add(orderItemPanel);
		
		JLabel orderItemImageLabel = new JLabel("");
		orderItemImageLabel.setIcon(new ImageIcon(OrderPanel.class.getResource("/images/cart25.png")));
		orderItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		orderItemImageLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		orderItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemImageLabel.setBounds(85, 7, 50, 40);
		orderItemPanel.add(orderItemImageLabel);
		
		JLabel orderItemDescLabel = new JLabel("<html><b>비글 X 3</b> <br/> ￦ 550,000</html>");
		orderItemDescLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		orderItemDescLabel.setBounds(147, 7, 100, 40);
		orderItemPanel.add(orderItemDescLabel);
		
		JLabel orderItemTotPrice = new JLabel("￦ 9,000,000");
		orderItemTotPrice.setHorizontalAlignment(SwingConstants.LEFT);
		orderItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
		orderItemTotPrice.setBounds(259, 6, 91, 41);
		orderItemPanel.add(orderItemTotPrice);
	}
	
	
	

}
