package bbangshuttle.uitest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bbangshuttle.cart.Cart;
import bbangshuttle.member.Member;

public class OrderFrame extends JFrame {
    private JPanel orderContentPanel;

    public OrderFrame(Member currentUser, CartFrame cartFrame, List<Cart> orderedItems) {
        setTitle("주문목록");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 주문 목록을 보여줄 패널 생성
        orderContentPanel = new JPanel();
        orderContentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        getContentPane().add(new JScrollPane(orderContentPanel), BorderLayout.CENTER);

        // 주문 정보를 뿌려주기
        for (Cart cart : orderedItems) {
            JPanel productPanel = createProductPanel(cart);
            orderContentPanel.add(productPanel);
        }

        // 상품 페이지로 돌아가는 버튼 생성
        JButton returnButton = new JButton("상품 페이지로 돌아가기");
        returnButton.addActionListener(e -> {
            cartFrame.setVisible(true);
            dispose();
            
        });

        // 메인 프레임으로 돌아가는 버튼 생성
        JButton mainButton = new JButton("메인 화면으로 돌아가기");
        mainButton.addActionListener(e -> {
            new MainFrame(currentUser).setVisible(true);
            dispose();
            
        });

        // 프로그램 종료 버튼 생성
        JButton exitButton = new JButton("프로그램 종료");
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);
        buttonPanel.add(mainButton);
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createProductPanel(Cart cart) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(100, 100)); 
        productPanel.add(imagePanel, BorderLayout.WEST);


        String imagePath = cart.getProduct().getP_image(); 
        ImageIcon imageIcon = new ImageIcon(CartFrame.class.getResource(imagePath));
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imagePanel.add(imageLabel);

        
        JLabel productInfoLabel = new JLabel("<html><font size='3'>" + ": " + cart.getProduct().getP_name() + "<br>"
                + "가격: " + new DecimalFormat("#,###").format(cart.getProduct().getPrice()) + "원<br>"
                + "설명: " + cart.getProduct().getP_desc() + "</font></html>");
        productPanel.add(productInfoLabel, BorderLayout.CENTER);

        return productPanel;
    }
}
