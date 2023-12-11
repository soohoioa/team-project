package bbangshuttle.uitest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.Product;

public class CartFrame extends JFrame {
    private JPanel cartContentPanel;
    private JLabel totalPriceLabel;
    private int totalPrice = 0;

    private CartService cartService;
    private Member currentUser;
    private List<Cart> orderedItems; // 주문한 상품 목록을 저장하기 위한 변수

    public CartFrame(Member currentUser) throws Exception {
        this.currentUser = currentUser;
        cartService = new CartService();

        setTitle("Cart Frame");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 카트 내용을 보여줄 패널 생성
        cartContentPanel = new JPanel();
        cartContentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        getContentPane().add(new JScrollPane(cartContentPanel), BorderLayout.CENTER);

        // 총 가격 표시 라벨
        totalPriceLabel = new JLabel("총 가격: 0원");
        totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        updateTotalPrice();
        getContentPane().add(totalPriceLabel, BorderLayout.NORTH);

        // 장바구니 목록 초기화
        updateCartList();

        // 하단 버튼들 생성
        JPanel cartBottonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton orderButton = new JButton("주문하기");
        orderButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    orderProducts();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton clearButton = new JButton("장바구니 비우기");
        clearButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clearCart();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 상품 페이지로 돌아가는 버튼 생성
        JButton returnButton = new JButton("상품 페이지로 돌아가기");
        returnButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProductFrame();
            }
        });

        cartBottonPanel.add(clearButton);
        cartBottonPanel.add(orderButton);
        cartBottonPanel.add(returnButton);
        getContentPane().add(cartBottonPanel, BorderLayout.SOUTH);
    }

    private void updateTotalPrice() {
        totalPrice = 0;
        for (Component component : cartContentPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel productPanel = (JPanel) component;
                Cart cart = (Cart) productPanel.getClientProperty("cart");
                totalPrice += cart.getProduct().getPrice() * cart.getCart_qty();
            }
        }
        totalPriceLabel.setText("  총 가격 : 0원");
    }

    private void updateCartList() {
        cartContentPanel.removeAll();
        try {
            List<Cart> carts = cartService.getCartItemByUserId(currentUser.getMemberId());
            for (Cart cart : carts) {
                JPanel productPanel = createProductPanel(cart);
                cartContentPanel.add(productPanel);
            }
            cartContentPanel.revalidate();
            cartContentPanel.repaint();
            updateTotalPrice(); // 장바구니 목록이 업데이트될 때 총 가격도 함께 업데이트
        } catch (Exception e) {
            e.printStackTrace();
        }
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


        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JComboBox<Integer> cartQtyComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            cartQtyComboBox.addItem(i);
        }
        cartQtyComboBox.setSelectedItem(cart.getCart_qty());
        cartQtyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = (int) cartQtyComboBox.getSelectedItem();
                cart.setCart_qty(quantity);
                updateTotalPrice();
            }
        });
        controlPanel.add(cartQtyComboBox);

        JButton removeButton = new JButton("삭제");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cartService.deleteCartItemByCartNo(cart.getCart_no());
                    updateCartList();
                    updateTotalPrice();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        controlPanel.add(removeButton);

        productPanel.add(controlPanel, BorderLayout.SOUTH);

        // Cart 정보를 컴포넌트에 저장하여 사용
        productPanel.putClientProperty("cart", cart);

        return productPanel;
    }

    private void orderProducts() {
        try {
            List<Cart> carts = cartService.getCartItemByUserId(currentUser.getMemberId());
            orderedItems = carts; // 주문한 상품 목록을 저장
            OrderFrame orderFrame = new OrderFrame(currentUser, this, orderedItems);
            orderFrame.setVisible(true);
            JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
            clearCartAfterOrder();
            dispose(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 주문이 완료된 후, 장바구니를 비우는 메소드 추가
    public void clearCartAfterOrder() {
        try {
            cartService.deleteCartItemByUserId(currentUser.getMemberId());
            orderedItems.clear();
            updateCartList();
            updateTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCart() {
        try {
            cartService.deleteCartItemByUserId(currentUser.getMemberId());
            updateCartList();
            updateTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showOrderFrame() {
        try {
            OrderFrame orderFrame = new OrderFrame(currentUser, this, orderedItems);
            orderFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProductFrame() {
        try {
            new ProductFrame(currentUser).setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrderedItems(List<Cart> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void updateOrderList() {
        cartContentPanel.removeAll();
        for (Cart cart : orderedItems) {
            JPanel productPanel = createProductPanel(cart);
            cartContentPanel.add(productPanel);
        }
        cartContentPanel.revalidate();
        cartContentPanel.repaint();
        updateTotalPrice();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CartFrame(new Member()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
