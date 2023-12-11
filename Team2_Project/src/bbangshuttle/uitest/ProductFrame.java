package bbangshuttle.uitest;
// 집 가고싶오요
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import bbangshuttle.cart.Cart;
import bbangshuttle.cart.CartService;
import bbangshuttle.member.Member;
import bbangshuttle.product.Product;
import bbangshuttle.product.ProductService;
import javax.swing.border.EmptyBorder;

public class ProductFrame extends JFrame {
    private JPanel productPopularContentPanel;
    private JComboBox<String> categoryComboBox;
    private JButton mainFrameButton;
    private JButton cartFrameButton;

    private ProductService productService;
    private CartService cartService;
    private Member currentUser;
    private JLabel lblNewLabel;
    private JTextField searchField;
    private JButton searchButton;

    public ProductFrame(Member currentUser) throws Exception {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(ProductFrame.class.getResource("/bbangshuttle/images/3035467_and_bread_drink_food_wine_icon.png")));
       getContentPane().setName("ProductContentPane");
        this.currentUser = currentUser;
        productService = new ProductService();
        cartService = new CartService();

        setTitle(currentUser.getMemberName()+"님 의 빵셔틀");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 카테고리 선택 콤보박스 생성
        categoryComboBox = new JComboBox<>(new String[]{"전체상품", "베이커리", "음료"});
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                displayProductListByCategory(selectedCategory);
            }
        });

        // Main Frame으로 가는 버튼 생성
        mainFrameButton = new JButton("Main Frame로 가기");
        mainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainFrame();
            }
        });

        // Cart Frame으로 가는 버튼 생성
        cartFrameButton = new JButton("Cart Frame으로 가기");
        cartFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showCartFrame();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 카테고리 선택 콤보박스와 버튼들을 담을 패널 생성
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
      
     
        menuPanel.add(categoryComboBox);
        menuPanel.add(mainFrameButton);
        menuPanel.add(cartFrameButton);
        getContentPane().add(menuPanel, BorderLayout.NORTH);
        
        searchField = new JTextField();
        menuPanel.add(searchField);
        searchField.setColumns(10);
        
        searchButton = new JButton("");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 사용자가 상품명 또는 상품설명을 입력하고 검색 버튼을 클릭하면 입력된 상품을 가져옴.
        		String productName = searchField.getText();
        		try {
                    List<Product> products = productService.ProductFindByKetword(productName);
                    displayProductList(products);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ProductFrame.this, "상품 검색 과정에서 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        	
        searchButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setHorizontalTextPosition(SwingConstants.CENTER);
        searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setBackground(SystemColor.menu);
        searchButton.setIcon(new ImageIcon(ProductFrame.class.getResource("/images/search_image20.png")));
        searchButton.setPreferredSize(new Dimension(30, 20));
        menuPanel.add(searchButton);

        // 인기 상품 패널 생성 및 추가
        productPopularContentPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        JScrollPane scrollPane = new JScrollPane(productPopularContentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 처음에 전체상품을 보여줌
        displayProductListByCategory("전체상품");
    }

    private void displayProductListByCategory(String category) {
        try {
            List<Product> products;
            if ("전체상품".equals(category)) {
                products = productService.ProductFindByAll();
            } else if ("베이커리".equals(category)) {
                products = productService.productCategoryAll(1);
            } else if ("음료".equals(category)) {
                products = productService.productCategoryAll(2);
            } else {
                // 잘못된 카테고리 처리
                products = Collections.emptyList();
            }
            displayProductList(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayProductList(List<Product> productList) {
        productPopularContentPanel.removeAll();
        for (Product product : productList) {
            /************* 제품1개UI디자인 *******************/
            JPanel productPanel = new JPanel();
            productPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
            productPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            productPanel.setBounds(new Rectangle(0, 0, 180, 150)); 
            productPanel.setMaximumSize(new Dimension(230, 200));
            productPanel.setMinimumSize(new Dimension(150, 150));
            productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            productPanel.setBackground(Color.WHITE);
            productPanel.setBorder(null);
            productPanel.setSize(new Dimension(150, 120));
            productPanel.setPreferredSize(new Dimension(200, 220)); 
            productPanel.setLayout(null);

            
            JLabel productImageLabel = new JLabel();
            //System.out.println("imagePath= "+product.getP_image());
            productImageLabel.setIcon(new ImageIcon(ProductFrame.class.getResource(product.getP_image())));
            
            productImageLabel.setBounds(3, 1, 160, 120); 
            productPanel.add(productImageLabel);

            JLabel productDescLabel = new JLabel("<html><font size='3'>" + "상품명 : " + product.getP_name() + "<br>"
                    + "가격 : " + new DecimalFormat("#,###").format(product.getPrice()) + "<br>" + "설명 : "
                    + product.getP_desc() + "</font></html>");
            productDescLabel.setVerticalAlignment(SwingConstants.TOP);
            productDescLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            productDescLabel.setHorizontalAlignment(SwingConstants.LEFT);
            productDescLabel.setBounds(3, 143, 245, 47); 
            productPanel.add(productDescLabel);

            JComboBox<String> cartQtyComboBox = new JComboBox<>();
            cartQtyComboBox.setAutoscrolls(true);
            cartQtyComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
            cartQtyComboBox.setOpaque(false);
            cartQtyComboBox.setBorder(null);
            cartQtyComboBox.setBackground(Color.WHITE);
            cartQtyComboBox.setBounds(5, 120, 50, 23);
            cartQtyComboBox.setMaximumRowCount(cartQtyComboBox.getModel().getSize());
            productPanel.add(cartQtyComboBox);

            JButton cartAddButton = new JButton("장바구니에 담기");
            cartAddButton.addActionListener(new ActionListener() {
                private Product p = product;

                public void actionPerformed(ActionEvent e) {
                   if (currentUser == null) {
                        JOptionPane.showMessageDialog(null, "로그인을 해주세요!");
                        try {
							showLoginFrame();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
                    } else {
                        // 로그인한 경우 장바구니에 상품을 담을지 확인하는 메시지 다이얼로그 표시
                        int option = JOptionPane.showOptionDialog(ProductFrame.this,
                                "장바구니에 담으시겠습니까?", "장바구니 추가 확인", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, new String[]{"예", "아니요"}, "아니요");

                        if (option == JOptionPane.YES_OPTION) {
                            // 사용자가 "예" 버튼을 선택한 경우 상품을 카트에 추가
                            int quantity = Integer.parseInt((String) cartQtyComboBox.getSelectedItem());
                            try {
                                Cart cart = new Cart(0, quantity, currentUser.getMemberId(), p);
                                cartService.addCart(cart);
                                showCartFrame();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }

            
            });
            cartAddButton.setBounds(107, 119, 130, 23);
            productPanel.add(cartAddButton);

            productPopularContentPanel.add(productPanel);
        }
        productPopularContentPanel.revalidate();
        productPopularContentPanel.repaint();
    }

    private void showMainFrame() {
        MainFrame mainFrame = new MainFrame(currentUser);
        mainFrame.setVisible(true);
        dispose();
    }

    private void showCartFrame() throws Exception {
        CartFrame cartFrame = new CartFrame(currentUser);
        cartFrame.setVisible(true);
        dispose();
    }

    //검색 메소드
//    public void search() {
//       productService.ProductFindByKetword()
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProductFrame(new Member()).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
   
    private void showLoginFrame() throws Exception {
        LoginFrame loginFrame = new LoginFrame(null);
        loginFrame.setVisible(true);
    }
}