package bbangshuttle.test;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import bbangshuttle.product.Product;
import bbangshuttle.product.ProductService;

public class ProductFrame extends JFrame {
    private ProductService productService;
    private JTable productTable;
    private JTextField keywordTextField;

    public ProductFrame() {
        try {
            productService = new ProductService();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JFrame 설정
        setTitle("상품 관리");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 테이블 컬럼 이름
        String[] columnNames = {"이미지", "상품번호", "상품명", "상품설명", "가격"};

        // 테이블 데이터
        Object[][] data = getProductData();

        // JTable 생성
        productTable = new JTable(data, columnNames);

        // 이미지 컬럼에 대한 TableCellRenderer 설정
        productTable.getColumnModel().getColumn(0).setCellRenderer(new ImageTableCellRenderer());

        // JScrollPane에 테이블 추가
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // 키워드 검색을 위한 텍스트 필드와 검색 버튼
        JPanel searchPanel = new JPanel();
        keywordTextField = new JTextField(20);
        JButton searchButton = new JButton("검색");
        searchPanel.add(keywordTextField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        // 검색 버튼의 ActionListener 구현
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String keyword = keywordTextField.getText().trim();
                    List<Product> productList = productService.ProductFindByKetword(keyword);

                    // 테이블 데이터 갱신
                    updateTableData(productList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 프레임이 보여지도록 설정
        setVisible(true);
    }

    // 상품 목록 데이터를 JTable 형식에 맞게 변환
    private Object[][] getProductData() {
        try {
            List<Product> productList = productService.ProductFindByAll();
            Object[][] data = new Object[productList.size()][5];

            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                data[i][0] = new ImageIcon(product.getP_image());
                data[i][1] = product.getP_no();
                data[i][2] = product.getP_name();
                data[i][3] = product.getP_desc();
                data[i][4] = product.getPrice();
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    // JTable의 데이터 갱신
    private void updateTableData(List<Product> productList) {
        Object[][] data = new Object[productList.size()][5];

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = new ImageIcon(product.getP_image());
            data[i][1] = product.getP_no();
            data[i][2] = product.getP_name();
            data[i][3] = product.getP_desc();
            data[i][4] = product.getPrice();
        }

        // TableModel 갱신
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{"이미지", "상품번호", "상품명", "상품설명", "가격"}
        ));

        // 이미지 컬럼에 대한 TableCellRenderer 설정
        productTable.getColumnModel().getColumn(0).setCellRenderer(new ImageTableCellRenderer());
    }

    // 이미지를 표시하기 위한 TableCellRenderer 클래스
    private class ImageTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                // 이미지를 JLabel로 표시
                setIcon((ImageIcon) value);
                setText(null);
            } else {
                super.setValue(value);
            }
        }
    }

    public static void main(String[] args) {
        // 프레임 생성
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductFrame();
            }
        });
    }
}
