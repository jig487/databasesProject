import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import javax.swing.*;

public class GUI extends JFrame {
    private HashMap<String, JTextField> searchFields;
    private JPasswordField passField;
    private JButton connectButton;
    private JLabel statusLabel;

    private String DB_HOST = "138.49.184.47";
    private String DB_USERNAME= "wateski3978";
    private String DB_PORT = "3306";
    private String DB_DATABASE = "wateski3978_library";
    private String DB_PASSWORD = "v*hNkwLm74cpFqRj";
    private String url = "jdbc:mysql://138.49.184.47:3306/wateski3978_library?user=wateski3978&password=";
	private Connection connection;

    public GUI() {
        setTitle("Library GUI");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        String[] searchFieldNames = {
            "ISBN",
            "Book Name",
            "Rating",
            "Publish Year",
            "Author Name",
            "Genre",
            "Owner Name",
            "Patron Name"
        };

        searchFields = new HashMap<>();
        for (String fieldName : searchFieldNames) {
            add(new JLabel(fieldName+":"));
            JTextField newField = new JTextField("");
            searchFields.put(fieldName, newField);
            add(newField);
        }

        connectButton = new JButton("Connect");
        add(connectButton);

        statusLabel = new JLabel("Status: Not Connected");
        add(statusLabel);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testConnection();
            }
        });

        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    private void testConnection() {
        try {
            connect();
            statusLabel.setText("Status: Connected Successfully!");
            connection.close();
        } catch (Exception ex) {
            statusLabel.setText("Status: Failed - " + ex.getMessage());
        }
    }
	
	public void connect() throws UnsupportedEncodingException, ClassNotFoundException {
        String encodedPassword;
		try {
            encodedPassword = URLEncoder.encode(DB_PASSWORD, StandardCharsets.UTF_8.toString());
			String encodedPasswordURL = url + encodedPassword;
            Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(encodedPasswordURL);
		} catch (Exception e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}