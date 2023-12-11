package com.itwill.uidesign.product;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.uidesign.member.MemberPanel;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import bbangshuttle.product.Product;

public class ProductPanelTestFarmeMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					ProductPanelTestFarmeMain frame = new ProductPanelTestFarmeMain();
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
	public ProductPanelTestFarmeMain() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProductPanel productPanel = new ProductPanel();
		productPanel.setBounds(32, 45, 473, 360);
		contentPane.add(productPanel);
	}
}
