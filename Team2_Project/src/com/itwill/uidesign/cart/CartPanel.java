package com.itwill.uidesign.cart;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class CartPanel extends JPanel {
	private JPanel cartContentPanel;
	public CartPanel() {
		setLayout(null);

		JScrollPane cartPanelscrollPane = new JScrollPane();
		cartPanelscrollPane.setBounds(12, 10, 426, 530);
		add(cartPanelscrollPane);

		cartContentPanel = new JPanel();
		cartContentPanel.setPreferredSize(new Dimension(300, 900));
		cartPanelscrollPane.setViewportView(cartContentPanel);

		JPanel cartItemPanel = new JPanel();
		cartItemPanel.setLayout(null);
		cartItemPanel.setPreferredSize(new Dimension(400, 72));
		cartItemPanel.setBorder(null);
		cartItemPanel.setBackground(Color.WHITE);
		cartContentPanel.add(cartItemPanel);

		JLabel cartProductImageLabel = new JLabel("");
		cartProductImageLabel.setIcon(new ImageIcon(CartPanel.class.getResource("/images/cart50.png")));
		cartProductImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		cartProductImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		cartProductImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartProductImageLabel.setBounds(0, 0, 69, 72);
		cartItemPanel.add(cartProductImageLabel);

		JLabel cartProductPriceLabel = new JLabel("300,000");
		cartProductPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cartProductPriceLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartProductPriceLabel.setBounds(61, 25, 74, 23);
		cartItemPanel.add(cartProductPriceLabel);

		JButton cartItemDeleteButton = new JButton("");
		cartItemDeleteButton.setBorder(null);
		cartItemDeleteButton.setBackground(Color.WHITE);
		cartItemDeleteButton.setBounds(335, 27, 15, 17);
		cartItemPanel.add(cartItemDeleteButton);

		JLabel cartProductTotPriceLabel = new JLabel("9,000,000");
		cartProductTotPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cartProductTotPriceLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		cartProductTotPriceLabel.setBounds(220, 25, 69, 22);
		cartItemPanel.add(cartProductTotPriceLabel);

		JComboBox cartItemQtyComboBox = new JComboBox();
		cartItemQtyComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		cartItemQtyComboBox.setBounds(157, 25, 39, 23);
		cartItemPanel.add(cartItemQtyComboBox);
	}

	public void displaycartlist() {
		cartContentPanel.removeAll();
		for (int i = 0; i < 5; i++) {

			JPanel cartItemPanel = new JPanel();
			cartItemPanel.setLayout(null);
			cartItemPanel.setPreferredSize(new Dimension(400, 72));
			cartItemPanel.setBorder(null);
			cartItemPanel.setBackground(Color.WHITE);
			cartContentPanel.add(cartItemPanel);

			JLabel cartItemImageLabel = new JLabel("비글");
			cartItemImageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			cartItemImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			cartItemImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			cartItemImageLabel.setBounds(0, 0, 69, 72);
			cartItemPanel.add(cartItemImageLabel);

			JLabel cartItemDescLabel = new JLabel("300,000");
			cartItemDescLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemDescLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemDescLabel.setBounds(61, 25, 74, 23);
			cartItemPanel.add(cartItemDescLabel);

			JButton cartItemDeleteButton = new JButton("");
			cartItemDeleteButton.setBorder(null);
			cartItemDeleteButton.setBackground(Color.WHITE);
			cartItemDeleteButton.setBounds(335, 27, 15, 17);
			cartItemPanel.add(cartItemDeleteButton);

			JLabel cartItemTotPrice = new JLabel("9,000,000");
			cartItemTotPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			cartItemTotPrice.setFont(new Font("D2Coding", Font.PLAIN, 13));
			cartItemTotPrice.setBounds(220, 25, 69, 22);
			cartItemPanel.add(cartItemTotPrice);

			JComboBox cartItemQtyComboBox = new JComboBox();
			cartItemQtyComboBox.setBounds(157, 25, 39, 23);
			cartItemPanel.add(cartItemQtyComboBox);
		}
	}
}
