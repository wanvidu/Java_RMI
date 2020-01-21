package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.QuestionController;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GraphGUI extends JFrame {

	private static final long serialVersionUID = -5942963356594972412L;

	private JPanel contentPane;

	QuestionController questionController = new QuestionController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphGUI frame = new GraphGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraphGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 800));
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Image image = null;

		try {

			String baseUrl = "https://quickchart.io/chart?width=400&height=300&c=";

			String[][] categoryData = questionController.getCategorieCount();

			String category[] = new String[categoryData.length];
			String data[] = new String[categoryData.length];
			int i = 0;

			for (String[] strings : categoryData) {
				category[i] = strings[2];
				data[i] = strings[1];
				i++;
			}

			System.out.println(Arrays.toString(category));
			System.out.println(Arrays.toString(data));

			StringBuilder query = new StringBuilder();

			query.append("{type:'bar',data:{labels:[");
			for (String string : category) {
				query.append("\'" + string + "\',");
			}
			query.append("], datasets:[{label:'Categories',data:[");
			for (String string : data) {
				query.append(string + ",");
			}
			query.append("]}]}}");
			
			System.out.println(query.toString());
			
			String encodedQuery = encodeValue(query.toString());

			String completeUrl = baseUrl + encodedQuery;
			URL url = new URL(completeUrl);
			image = ImageIO.read(url.openStream());
		} catch (IOException e) {
			System.out.println("Something went wrong, sorry:" + e.toString());
			e.printStackTrace();
		}

		JLabel lblimage = new JLabel();
		lblimage.setIcon(new ImageIcon(image));
		lblimage.setBounds(5, 5, 878, 674);
		contentPane.add(lblimage);

		JButton btnGoHome = new JButton("Home");
		btnGoHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainGUI().setVisible(true);
			}
		});
		btnGoHome.setBounds(44, 703, 74, 26);
		contentPane.add(btnGoHome);
	}

	
	private String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

}
