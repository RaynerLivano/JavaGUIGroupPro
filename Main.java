import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private ListView<String> productListView;
	private ObservableList<String> productList = FXCollections.observableArrayList();
	private VBox vb;
	private String userRole;
	private String userName;
	private String userID = generateID();
	String loggedInUserID = userID;
	private Cart selectedCartItem;

	// Login Page
	Scene loginscene;
	BorderPane loginbp = new BorderPane();
	GridPane logingp = new GridPane();
	FlowPane loginfp = new FlowPane();
	Label logintitle = new Label("Login");
	Label loginusernameLbl = new Label("Username: ");
	Label loginpasswordLbl = new Label("Password: ");
	Label logindescriptionLbl = new Label("Don't have an account yet?");
	Label loginLbl = new Label(" register here");
	TextField loginusernameTF = new TextField();
	PasswordField loginpasswordPF = new PasswordField();
	Button LoginBtn = new Button("Login");
	Label historyLogIn = new Label();

	// Register Page
	Scene RegisterScene;
	BorderPane Regisbp = new BorderPane();
	GridPane Regisgp = new GridPane();
	FlowPane Regisfp = new FlowPane();
	FlowPane Regisfp1 = new FlowPane();

	Label Registitle = new Label("Register");
	Label RegisusernameLbl = new Label("Username: ");
	Label RegisemailLbl = new Label("Email: ");
	Label RegispasswordLbl = new Label("Password: ");
	Label RegisConpasswordLbl = new Label("Confirm Password: ");
	Label RegisphoneNumber = new Label("Phone Number: ");
	Label RegisaddressLbl = new Label("Address");
	Label RegisgenderLbl = new Label("Gender");
	Label RegisdescriptionLbl = new Label("Have an account?");
	Label RegisLbl = new Label(" login here");
	TextField RegisusernameTF = new TextField();
	TextField RegisemailField = new TextField();
	TextField RegisPhonenumberField = new TextField();
	PasswordField RegispasswordPF = new PasswordField();
	PasswordField ConPasswordPF = new PasswordField();
	Button RegisterBtn = new Button("Register");
	TextArea addressTA = new TextArea();
	RadioButton maleRB = new RadioButton("Male");
	RadioButton femaleRB = new RadioButton("Female");
	ToggleGroup genderTG = new ToggleGroup();
	CheckBox checkbox = new CheckBox("i agree to the terms and conditions");

	// userHome
	Scene userHomeScene;
	BorderPane menubp;
	GridPane menugp;
	MenuBar menuBar = new MenuBar();

	Menu homeMenu = new Menu("Home");
	Menu cartMenu = new Menu("Cart");
	Menu accountMenu = new Menu("Account");

	MenuItem homePageItem = new MenuItem("HomePage");
	MenuItem myCartItem = new MenuItem("My Cart");
	MenuItem purchaseHistory = new MenuItem("Purchase History");
	MenuItem logOut = new MenuItem("Log Out");

	// cart
	Scene cartScene;
	BorderPane cartbp;
	GridPane cartgp;
	Button addBtn = new Button("Add To Cart");
	Button makePurchaseBtn = new Button();

	// Transaction
	Scene TransactionScene;
	BorderPane transbp;
	GridPane transgp;
	MenuBar TransMenuBar = new MenuBar();

	// adminHome
	Scene adminHomeScene;

	BorderPane adminbp;
	GridPane admingp;
	MenuBar adminmenuBar = new MenuBar();

	Menu adminhomeMenu = new Menu("Home");
	Menu adminmanageMenu = new Menu("Manage Products");
	Menu adminaccountMenu = new Menu("Account");

	MenuItem adminhomePageItem = new MenuItem("HomePage");
	MenuItem adminmanageItem = new MenuItem("Manage Products");
	MenuItem adminlogOut = new MenuItem("Log Out");

	// adminManageProduct
	Scene adminManageProduct;

	BorderPane managebp;
	GridPane managegp;

	// addProduct
	Scene addProductScene;

	BorderPane addbp;
	GridPane addgp;

	// updateProduct

	Scene updateProductScene;

	BorderPane updtbp;
	GridPane updtgp;

	// removeProduct

	Scene removeProductScene;

	BorderPane removebp;
	GridPane removegp;

	// Label & Text

	Label nameLabelText = new Label("Input product name");
	TextField nameInput = new TextField();

	// Product price field
	Label priceLabelText = new Label("Input product price");
	TextField priceInput = new TextField();

	// Product description field
	Label descriptionLabelText = new Label("Input product description...");
	TextArea descriptionInput = new TextArea();

	public void LoginElement() {
		loginscene = new Scene(loginbp, 900, 700);

		loginbp.setCenter(logingp);

		logingp.add(logintitle, 1, 0);
		logingp.add(loginusernameLbl, 0, 1);
		logingp.add(loginpasswordLbl, 0, 2);
		logingp.add(logindescriptionLbl, 1, 3);
		logingp.add(loginLbl, 1, 3);
		logingp.add(LoginBtn, 1, 4);
		logingp.add(loginusernameTF, 1, 1);
		logingp.add(loginpasswordPF, 1, 2);
		logingp.add(loginfp, 1, 3);

		loginusernameTF.setMaxWidth(215);
		loginpasswordPF.setMaxWidth(215);

		loginfp.getChildren().addAll(logindescriptionLbl, loginLbl);
	}

	public void LoginPosition() {
		logingp.setAlignment(Pos.CENTER);
		logingp.setPadding(new Insets(0, 0, 0, 195));
		logingp.setVgap(10);
		logingp.setHgap(10);
		loginfp.setHgap(0);
		logintitle.setFont(new Font("Calibri", 30));
		Font font = Font.font("Calibri", FontWeight.BOLD, 30);
		logintitle.setFont(font);
		logindescriptionLbl.setAlignment(Pos.CENTER_RIGHT);
		loginLbl.setAlignment(Pos.CENTER_LEFT);
		loginLbl.setTextFill(Color.BLUE);
	}

	public void LogineHandler() {
		loginLbl.setOnMouseClicked(e -> {
			RegisterElement();
			RegisterPosition();
			RegistereHandler();
			primaryStage.setScene(RegisterScene);
		});

		LoginBtn.setOnMouseClicked(e -> {
			userName = loginusernameTF.getText();
			String username = loginusernameTF.getText();
			String password = loginpasswordPF.getText();

			if (username.isEmpty() || password.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Failed To Login");
				alert.setContentText("All Fields Must be Filled");
				alert.showAndWait();
			} else {
				boolean loginSuccess = logIn(username, password);
				if (!loginSuccess) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Login Failed");
					alert.setContentText("Invalid Credential");
					alert.showAndWait();
				} else {
					if (userRole.equals("Admin")) {
						adminmenuBar.getMenus().clear();
						adminhomeMenu.getItems().clear();
						adminmanageMenu.getItems().clear();
						adminaccountMenu.getItems().clear();
						adminHome();
						WelcomeMessageAwal();
					} else if (userRole.equals("User")) {
						menuBar.getMenus().clear();
						homeMenu.getItems().clear();
						cartMenu.getItems().clear();
						accountMenu.getItems().clear();
						userHome();
					}
					loggedInUserID = userID;
				}
			}
		});
	}

	public boolean logIn(String username, String password) {
		Connect connect = Connect.getInstance();
		String query = "SELECT * FROM user WHERE username = ? AND password = ?";
		boolean loginSuccess = false;

		try (Connection conn = connect.getConnection();
				PreparedStatement loginStatement = conn.prepareStatement(query)) {

			loginStatement.setString(1, username);
			loginStatement.setString(2, password);

			try (ResultSet rs = loginStatement.executeQuery()) {
				if (rs.next()) {
					userRole = rs.getString("role");
					userName = rs.getString("username");
					userID = rs.getString("userID");

					loginSuccess = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginSuccess;
	}

	public void RegisterElement() {
		RegisterScene = new Scene(Regisbp, 900, 700);

		Regisbp.setCenter(Regisgp);
		Regisgp.add(Registitle, 1, 0);
		Regisgp.add(RegisusernameLbl, 0, 1);
		Regisgp.add(RegisemailLbl, 0, 2);
		Regisgp.add(RegispasswordLbl, 0, 3);
		Regisgp.add(RegisConpasswordLbl, 0, 4);
		Regisgp.add(RegisphoneNumber, 0, 5);
		Regisgp.add(RegisaddressLbl, 0, 6);
		Regisgp.add(RegisgenderLbl, 0, 7);
		Regisgp.add(checkbox, 1, 8);
		Regisgp.add(RegisterBtn, 1, 10);
		RegisterBtn.setMaxWidth(100);
		RegisterBtn.setMinHeight(10);

		Regisgp.add(RegisusernameTF, 1, 1);
		Regisgp.add(RegisemailField, 1, 2);
		Regisgp.add(RegispasswordPF, 1, 3);
		Regisgp.add(ConPasswordPF, 1, 4);
		Regisgp.add(RegisPhonenumberField, 1, 5);
		Regisgp.add(addressTA, 1, 6);
		Regisgp.add(RegisdescriptionLbl, 1, 9);
		Regisgp.add(RegisLbl, 1, 9);
		Regisgp.add(Regisfp, 1, 7);
		Regisgp.add(Regisfp1, 1, 9);
		Regisgp.setAlignment(Pos.CENTER);
		Regisfp.getChildren().addAll(maleRB, femaleRB);
		GridPane.setColumnSpan(checkbox, 2);
		Regisfp1.getChildren().addAll(RegisdescriptionLbl, RegisLbl);

		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);

		primaryStage.setTitle("Register");

		RegisLbl.setOnMouseClicked(e -> {
			primaryStage.setScene(loginscene);
		});

	}

	public void RegisterPosition() {
		Regisgp.setPadding(new Insets(10));
		Regisgp.setVgap(10);
		Regisgp.setHgap(10);
		Regisfp.setHgap(15);
		Registitle.setFont(new Font("Calibri", 30));
		Font font = Font.font("Calibri", FontWeight.BOLD, 30);
		Registitle.setFont(font);
		Registitle.setTextFill(Color.BLACK);
		RegisdescriptionLbl.setAlignment(Pos.CENTER_LEFT);
		checkbox.setFont(new Font("Calibri", 10));
		Font font1 = Font.font("Calibri", 10);
		checkbox.setFont(font1);
		maleRB.setFont(new Font("Calibri", 10));
		maleRB.setFont(font1);
		femaleRB.setFont(new Font("Calibri", 10));
		femaleRB.setFont(font1);
		RegisLbl.setAlignment(Pos.CENTER_RIGHT);
		RegisLbl.setTextFill(Color.BLUE);
	}

	public void RegistereHandler() {
		RegisterBtn.setOnAction(e -> {
			String username = RegisusernameTF.getText();
			String password = RegispasswordPF.getText();
			String phoneNumber = RegisPhonenumberField.getText();
			String address = addressTA.getText();
			String gender = "";
			String role = "User";

			if (maleRB.isSelected()) {
				gender = "male";
			} else if (femaleRB.isSelected()) {
				gender = "female";
			}

			if (RegisusernameTF.getText().isEmpty() || RegisusernameTF.getText().length() < 5
					|| RegisusernameTF.getText().length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("username Error");
				alert.setContentText("username must be filled 5-20 characters");
				alert.showAndWait();
			} else if (RegispasswordPF.getText().isEmpty() || RegispasswordPF.getText().length() < 5
					|| !RegispasswordPF.getText().matches("[a-zA-Z0-9]+")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Password Error");
				alert.setContentText("Password must be at least 5 characters");
				alert.showAndWait();
			} else if (ConPasswordPF.getText().isEmpty()
					|| !ConPasswordPF.getText().equals(RegispasswordPF.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Confirmation password Error");
				alert.setContentText("Password does not match");
				alert.showAndWait();
			} else if (!RegisPhonenumberField.getText().startsWith("+62")
					|| !RegisPhonenumberField.getText().matches("[+\\d]+")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Phone Number Error");
				alert.setContentText("Phone Number must start with '+62' and must be numeric ");
				alert.showAndWait();
			} else {
				boolean success = newRegister(generateID(), username, password, role, phoneNumber, address, gender);
				if (success) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Registration Successful");
					alert.setContentText("You have successfully registered!");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Registration Failed");
					alert.setContentText("Failed to register user!");
					alert.showAndWait();
				}
				primaryStage.setScene(loginscene);
			}
		});
	}

	public boolean newRegister(String ID, String username, String password, String role, String address,
			String phoneNumber, String gender) {
		Connect connect = Connect.getInstance();
		String query = "INSERT INTO user (userID, username, password, role, address, phone_num, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect.getConnection();
				PreparedStatement regisStatement = conn.prepareStatement(query)) {

			regisStatement.setString(1, ID);
			regisStatement.setString(2, username);
			regisStatement.setString(3, password);
			regisStatement.setString(4, role);
			regisStatement.setString(5, phoneNumber);
			regisStatement.setString(6, address);
			regisStatement.setString(7, gender);

			int affectedRows = regisStatement.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String generateID() {
		Connect connect = Connect.getInstance();
		String latestID = null;

		try (Connection conn = connect.getConnection();
				PreparedStatement statement = conn
						.prepareStatement("SELECT userID FROM user ORDER BY userID DESC LIMIT 1");
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				latestID = resultSet.getString("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (latestID != null) {
			int ID;
			try {
				ID = Integer.parseInt(latestID.substring(2));
				String newID = "CU" + String.format("%03d", ID + 1);
				return newID;
			} catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
				ex.printStackTrace();
				return null;
			}
		} else {
			return "CU001";
		}
	}

	public boolean validasiuserID(String userID) {
		Connect connect = Connect.getInstance();
		String query = "SELECT COUNT(*) AS count FROM user WHERE userID = ?";

		try (Connection conn = connect.getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, userID);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt("count");
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void userHome() {
		BorderPane menubp = new BorderPane();
		GridPane menugp = new GridPane();
		userHomeScene = new Scene(menugp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		displayWelcomeMessage();

		menuBar.getMenus().addAll(homeMenu, cartMenu, accountMenu);

		homeMenu.getItems().addAll(homePageItem);
		cartMenu.getItems().addAll(myCartItem);
		accountMenu.getItems().addAll(purchaseHistory, logOut);

		myCartItem.setOnAction(e -> cart());

		logOut.setOnAction(e -> {
			primaryStage.setScene(loginscene);
		});

		purchaseHistory.setOnAction(e -> Transaction());

		productListView = new ListView<>();
		productListView.setItems(productList);
		loadProducts();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				displayWelcomeMessage();
			} else {
				displayProductDetails(newValue);
			}
		});

		Label title = new Label("SeRuput Teh");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		menugp.add(title, 0, 0);
		menugp.add(productListView, 0, 1);
		menugp.add(vb, 1, 1);
		menubp.setTop(menuBar);
		menubp.setCenter(menugp);

		menugp.setPadding(new Insets(25));
		menugp.setVgap(10);
		menugp.setHgap(10);

		Scene scene = new Scene(menubp, 900, 700);
		primaryStage.setTitle("Home");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void displayWelcomeMessage() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage, new Label("Select a product to view details"));
	}

	private void displayCartWelcomeMessage() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage, new Label("Select a product to add and remove"));
	}

	private void displayProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = getProductDetails(productName);

		if (product != null) {
			Label nameLbl = new Label(product.getName());
			nameLbl.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
			Label descriptionLbl = new Label(product.getDescription());
			descriptionLbl.setWrapText(true);
			descriptionLbl.setMaxWidth(400);
			Label priceLbl = new Label("Price: Rp." + product.getPrice());

			HBox quantityBox = new HBox();
			Label lblQuantity = new Label("Quantity : ");
			Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
			Label totalLbl = new Label();
			totalLbl.setText("Total : Rp." + product.getPrice());

			quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
				if (newValue != null) {

					int total = newValue * product.getPrice();
					totalLbl.setText("Total : Rp." + total);
				}
			});

			totalLbl.setPadding(new Insets(0, 0, 0, 10));

			quantityBox.getChildren().addAll(lblQuantity, quantitySpinner, totalLbl);

			addBtn.setMaxWidth(120);
			addBtn.setMinHeight(10);

			addBtn.setOnAction(e -> {
				String productID = product.getId();
				int quantity = quantitySpinner.getValue();
				addCart(productID, loggedInUserID, quantity);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Added to Cart");
				alert.showAndWait();
			});

			vb.getChildren().addAll(nameLbl, descriptionLbl, priceLbl, quantityBox, addBtn);
		}
	}

	private void loadProducts() {
		productList.clear();
		Connect connect = Connect.getInstance();
		String query = "SELECT product_name FROM product";

		try (Connection conn = connect.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				productList.add(rs.getString("product_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Product getProductDetails(String productName) {
		Connect connect = Connect.getInstance();
		String query = "SELECT * FROM product WHERE product_name = ?";
		Product product = null;

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, productName);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Product(rs.getString("productID"), rs.getString("product_name"),
							rs.getInt("product_price"), rs.getString("product_des"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void cart() {
		GridPane cartgp = new GridPane();
		BorderPane cartbp = new BorderPane();
		String query = "SELECT phone_num, address FROM user WHERE userID = ?";
		Connect connect = Connect.getInstance();
		ListView<Cart> cartLV = new ListView<>();

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		vb.setPadding(new Insets(10));

		Label orderInfoLbl = new Label("Order Information");
		orderInfoLbl.setFont(Font.font("Calibri", FontWeight.BOLD, 16));

		Label usernameCartLbl = new Label("Username :" + userName);
		Label phoneNoLbl = new Label();
		Label addressCartLbl = new Label();
		Label totalLbl = new Label("Total : Rp.0");

		VBox cartUserLbl = new VBox(5);
		cartUserLbl.getChildren().addAll(orderInfoLbl, usernameCartLbl, phoneNoLbl, addressCartLbl);

		Button makePurchaseBtn = new Button("Make Purchase");
		makePurchaseBtn.setOnAction(e -> {
			if (cartLV.getItems().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Cart is Empty");
				alert.showAndWait();
				return;
			}

			Label confirmationLbl = new Label("Are you sure you want to make the purchase?");
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");

			yesBtn.setOnAction(ev -> {
				for (Cart cartItem : cartLV.getItems()) {
					String transactionID = generateTransactionId();
					String productID = cartItem.getProductID();
					int quantity = cartItem.getQuantity();

					Alert purchaseAlert = new Alert(AlertType.INFORMATION);
					purchaseAlert.setTitle("Message");
					purchaseAlert.setHeaderText("Successfully Purchased");
					purchaseAlert.showAndWait();

					moveCartDataToTransactionTables(transactionID, loggedInUserID, productID, quantity);
				}
				refreshCartScene();
			});

			noBtn.setOnAction(ev -> {
				primaryStage.setScene(cartScene);
			});

			VBox confirmationBox = new VBox(10);
			confirmationBox.setAlignment(Pos.CENTER);
			confirmationBox.setPadding(new Insets(20));
			confirmationBox.getChildren().addAll(confirmationLbl, yesBtn, noBtn);

			Scene confirmationScene = new Scene(confirmationBox, 400, 200);
			Stage confirmationStage = new Stage();
			confirmationStage.setScene(confirmationScene);
			confirmationStage.show();
		});

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, loggedInUserID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String phoneNumber = rs.getString("phone_num");
				String address = rs.getString("address");

				phoneNoLbl.setText("Phone Number: " + phoneNumber);
				addressCartLbl.setText("Address: " + address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (cartLV.getItems().isEmpty()) {
			Label NoProductHeader = new Label("No Item in Cart");
			Label welcomeMessage = new Label("Consider adding one");
			NoProductHeader.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
			vb.getChildren().addAll(NoProductHeader, welcomeMessage);
		}

		displayCartWelcomeMessage();

		homePageItem.setOnAction(e -> {
			menuBar.getMenus().clear();
			homeMenu.getItems().clear();
			cartMenu.getItems().clear();
			accountMenu.getItems().clear();
			userHome();
		});

		myCartItem.setOnAction(e -> {
			cart();
		});

		logOut.setOnAction(e -> {
			primaryStage.setScene(loginscene);
		});

		purchaseHistory.setOnAction(e -> {
			Transaction();
		});

		cartbp.setLeft(cartLV);
		cartLV.setMaxHeight(200);
		cartLV.setMaxWidth(900);

		cartLV.getSelectionModel().clearSelection();
		cartLV.getItems().clear();

		cartLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				displayCartProductDetails(newValue.getProductName());
				selectedCartItem = newValue;
			} else {
				vb.getChildren().clear();
				selectedCartItem = null;
			}
		});

		Label title = new Label(userName + " " + "'s Cart");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		cartgp.add(title, 0, 0);
		cartgp.add(cartLV, 0, 1);
		cartgp.add(totalLbl, 0, 2);
		cartgp.add(cartUserLbl, 0, 3);
		cartgp.add(makePurchaseBtn, 0, 8);
		cartgp.add(vb, 1, 1);

		cartbp.setTop(menuBar);
		cartbp.setCenter(cartgp);

		cartgp.setPadding(new Insets(25));
		cartgp.setVgap(10);
		cartgp.setHgap(10);

		displayCartDetails(userID, cartLV);

		Scene cartScene = new Scene(cartbp, 900, 700);
		primaryStage.setScene(cartScene);
		primaryStage.show();
	}

	private void displayCartDetails(String userID, ListView<Cart> cartLV) {
		ObservableList<Cart> cartItems = FXCollections.observableArrayList();

		Connect connect = Connect.getInstance();
		String query = "SELECT p.productID, p.product_name, p.product_price, c.quantity " + "FROM cart c "
				+ "JOIN product p ON c.productID = p.productID " + "WHERE c.userID = ?";

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, userID);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String productID = rs.getString("productID");
					String productName = rs.getString("product_name");
					Integer productPrice = rs.getInt("product_price");
					int quantity = rs.getInt("quantity");

					Cart cartItem = new Cart(productID, productName, productPrice, quantity);
					cartItems.add(cartItem);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cartLV.setItems(cartItems);
	}

	public static void addCart(String productID, String userID, int quantity) {
		Connect connect = Connect.getInstance();
		String userQuery = "SELECT * FROM user WHERE userID = ?";

		try (Connection connection = connect.getConnection();
				PreparedStatement ps = connection.prepareStatement(userQuery)) {

			ps.setString(1, userID);

			try (ResultSet userResultSet = ps.executeQuery()) {
				if (userResultSet.next()) {

					String insertQuery = "INSERT INTO cart (productID, userID, quantity) VALUES (?, ?, ?)";
					try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
						preparedStatement.setString(1, productID);
						preparedStatement.setString(2, userID);
						preparedStatement.setInt(3, quantity);

						int rowsAffected = preparedStatement.executeUpdate();

						if (rowsAffected > 0) {
							System.out.println("Added to cart successfully!");
						} else {
							System.out.println("Failed to add to cart!");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateCartQuantity(String productID, String userID, int newQuantity) {
		String query = "UPDATE cart SET quantity = ? WHERE productID = ? AND userID = ?";
		try (Connection conn = Connect.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, newQuantity);
			ps.setString(2, productID);
			ps.setString(3, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void removeCartItem(String productID, String userID, ObservableList<Cart> cartItems) {
		ListView<Cart> cartLV = new ListView<>();
		String query = "DELETE FROM cart WHERE productID = ? AND userID = ?";
		try (Connection conn = Connect.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, productID);
			ps.setString(2, userID);
			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				cartItems.removeIf(cart -> cart.getProductID().equals(productID));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void displayCartProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = getProductDetails(productName);
		ListView<Cart> cartLV = new ListView<>();

		if (product != null) {
			Label nameLbl = new Label(product.getName());
			nameLbl.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
			Label descriptionLbl = new Label(product.getDescription());
			descriptionLbl.setWrapText(true);
			descriptionLbl.setMaxWidth(400);
			Label priceLbl = new Label("Price: Rp." + product.getPrice());

			HBox quantityBox = new HBox();
			Label lblQuantity = new Label("Quantity : ");
			Spinner<Integer> quantitySpinner = new Spinner<>(-10, 10, 1);
			Label totalLbl = new Label();

			totalLbl.setText("Total : Rp." + product.getPrice());

			quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
				if (newValue != null) {
					int hargaTotal = 0;
					for (Cart cartItem : cartLV.getItems()) {
						int itemTotal = cartItem.getProductPrice() * cartItem.getQuantity();
						hargaTotal += itemTotal;
					}

					totalLbl.setText("Total : Rp." + hargaTotal);
				}
			});

			totalLbl.setPadding(new Insets(0, 0, 0, 10));
			quantityBox.getChildren().addAll(lblQuantity, quantitySpinner, totalLbl);

			Button updateCartBtn = new Button("Update Cart");
			Button removeFromCartBtn = new Button("Remove From Cart");

			updateCartBtn.setOnAction(e -> {
				if (selectedCartItem != null) {
					int oldQuantity = selectedCartItem.getQuantity();
					int newQuantity = quantitySpinner.getValue();

					if (newQuantity >= 0) {
						updateCartQuantity(selectedCartItem.getProductID(), loggedInUserID, newQuantity);
						selectedCartItem.setQuantity(newQuantity);
						displayCartDetails(loggedInUserID, cartLV);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Updated Cart");
						alert.showAndWait();
					} else if (newQuantity == -1 && oldQuantity > 0) {
						int updatedQuantity = oldQuantity - 1;
						updateCartQuantity(selectedCartItem.getProductID(), loggedInUserID, updatedQuantity);
						selectedCartItem.setQuantity(updatedQuantity);
						displayCartDetails(loggedInUserID, cartLV);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Updated Cart");
						alert.showAndWait();
					}
				}
			});

			removeFromCartBtn.setOnAction(e -> {
				if (selectedCartItem != null) {
					removeCartItem(selectedCartItem.getProductID(), loggedInUserID, cartLV.getItems());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Deleted from cart");
					alert.showAndWait();
				}
			});

			VBox contentVBox = new VBox(10);
			contentVBox.getChildren().addAll(nameLbl, descriptionLbl, priceLbl, quantityBox);

			HBox buttonBox = new HBox(10);
			buttonBox.getChildren().addAll(updateCartBtn, removeFromCartBtn);

			vb.getChildren().addAll(contentVBox, buttonBox);
		}
	}

	public static String generateTransactionId() {
		Connect connect = Connect.getInstance();
		String retTransactionId = null;

		String query = "SELECT MAX(transactionID) AS MAX FROM transaction_header";

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery();

			if (rs != null && rs.next()) {
				retTransactionId = rs.getString("MAX");
				if (retTransactionId == null || retTransactionId.isEmpty()) {
					retTransactionId = "TR001";
				} else {
					int nowId = Integer.parseInt(retTransactionId.substring(2));
					retTransactionId = String.format("TR%03d", nowId + 1);
				}
			} else {
				retTransactionId = "TR001";
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			retTransactionId = "";
		}

		return retTransactionId;
	}

	private void moveCartDataToTransactionTables(String transactionID, String userID, String productID, int qty) {
		Connect connect = Connect.getInstance();

		String insertTransactionHeaderQuery = "INSERT INTO transaction_header (transactionID, userID) VALUES (?, ?)";
		String insertTransactionDetailQuery = "INSERT INTO transaction_detail (transactionID, productID, quantity) VALUES (?, ?, ?)";

		try (Connection conn = connect.getConnection();
				PreparedStatement psTH = conn.prepareStatement(insertTransactionHeaderQuery);
				PreparedStatement psTD = conn.prepareStatement(insertTransactionDetailQuery)) {

			psTH.setString(1, transactionID);
			psTH.setString(2, userID);
			psTH.executeUpdate();

			psTD.setString(1, transactionID);
			psTD.setString(2, productID);
			psTD.setInt(3, qty);
			psTD.executeUpdate();

			clearUserCart(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void clearUserCart(String userID) {
		Connect connect = Connect.getInstance();
		String query = "DELETE FROM cart WHERE userID = ?";

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, userID);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Cleared user's cart successfully.");
			} else {
				System.out.println("No items found in the user's cart to clear.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Transaction() {
		ListView<String> transactionListView = ambilTransactionsForUser(loggedInUserID);
		transactionListView.setMaxHeight(500);
		transactionListView.setMinWidth(200);
		BorderPane transbp = new BorderPane();
		GridPane transgp = new GridPane();

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);

		if (transactionListView.getItems().isEmpty()) {
			Label productHeader = new Label("There's No History");
			Label welcomeMessage = new Label("Consider Purchasing Our Products");
			productHeader.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
			vb.getChildren().addAll(productHeader, welcomeMessage);
		} else {
			displayTransactionWelcomeMessage();
		}

		homePageItem.setOnAction(e -> userHome());

		myCartItem.setOnAction(e -> {
			cart();
		});

		logOut.setOnAction(e -> {
			primaryStage.setScene(loginscene);
		});

		Label titleLbl = new Label(userName + "'s Purchase History");
		titleLbl.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		GridPane.setColumnSpan(titleLbl, 2);
		transgp.add(titleLbl, 0, 0);
		transgp.add(transactionListView, 0, 1);
		transgp.add(vb, 1, 1);

		transbp.setTop(menuBar);
		transbp.setCenter(transgp);

		transgp.setPadding(new Insets(25));
		transgp.setVgap(10);
		transgp.setHgap(10);

		Scene homeScene = new Scene(transbp, 900, 700);
		primaryStage.setTitle("History");
		primaryStage.setScene(homeScene);
		primaryStage.show();
	}

	private void displayTransactionWelcomeMessage() {
		vb.getChildren().clear();
		String welcomeMessageText = "Select a Transaction to View Details";
		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage);
	}

	private void displayTransactionDetails(String transactionID, String loggedInUserID, String userName,
			ListView<Cart> cartLV) {
		String query = "SELECT phone_num, address FROM user WHERE userID = ?";
		Connect connect = Connect.getInstance();

		try (Connection conn = connect.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setString(1, loggedInUserID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String PhoneNumber = rs.getString("phone_num");
				String Address = rs.getString("address");

				vb.getChildren().clear();

				Label transactionIDLbl = new Label("Transaction ID: " + transactionID);
				Label usernameLbl = new Label("Username: " + userName);
				Label phoneNumberLbl = new Label("Phone Number: " + PhoneNumber);
				Label addressLbl = new Label("Address: " + Address);

				double total = ambilTotalForTransaction(transactionID);
				Label totalLbl = new Label("Total: Rp." + total);

				ListView<Cart> transactionDetailsLV = new ListView<>();
				ObservableList<Cart> transactionDetails = FXCollections.observableArrayList();

				Connect connectDetails = Connect.getInstance();
				String queryT = "SELECT p.product_name, p.product_price, td.quantity " + "FROM transaction_detail td "
						+ "JOIN product p ON td.productID = p.productID " + "WHERE td.transactionID = ?";

				try (Connection connDetails = connectDetails.getConnection();
						PreparedStatement statementDetails = connDetails.prepareStatement(queryT)) {

					statementDetails.setString(1, transactionID);
					ResultSet rsDetails = statementDetails.executeQuery();

					while (rsDetails.next()) {
						String productName = rsDetails.getString("product_name");
						Integer productPrice = rsDetails.getInt("product_price");
						int quantity = rsDetails.getInt("quantity");

						Cart transactionDetail = new Cart(productName, productName, productPrice, quantity);
						transactionDetails.add(transactionDetail);
					}

					transactionDetailsLV.setItems(transactionDetails);
					transactionDetailsLV.setMaxHeight(300);
					transactionDetailsLV.setMaxWidth(800);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				vb.getChildren().addAll(transactionIDLbl, usernameLbl, phoneNumberLbl, addressLbl, totalLbl,
						transactionDetailsLV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ListView<String> ambilTransactionsForUser(String userID) {
		ListView<String> transactionListView = new ListView<>();
		ListView<Cart> cartLV = new ListView<>();

		String query = "SELECT transactionID FROM transaction_header WHERE userID = ?";

		try (Connection conn = Connect.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, userID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String transactionID = rs.getString("transactionID");
					transactionListView.getItems().add(transactionID);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (transactionListView.getItems().isEmpty()) {
			Label noHistoryLabel = new Label("There's No History");
			Label welcomeMessageLabel = new Label("Consider Purchasing Our Products");
			noHistoryLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
			vb.getChildren().addAll(noHistoryLabel, welcomeMessageLabel);
		}

		transactionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				displayTransactionDetails(newValue, loggedInUserID, userName, cartLV);
			}
		});

		return transactionListView;
	}

	private double ambilTotalForTransaction(String transactionID) {
		double total = 0.0;
		String query = "SELECT total FROM transaction_header WHERE transactionID = ?";

		try (Connection conn = Connect.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, transactionID);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					total = rs.getDouble("total");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	private void refreshCartScene() {
		cart();
	}

	public void adminHome() {

		admingp = new GridPane();
		adminbp = new BorderPane();

		adminHomeScene = new Scene(admingp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		WelcomeMessageAwal();

		adminmenuBar.getMenus().addAll(adminhomeMenu, adminmanageMenu, adminaccountMenu);

		adminhomeMenu.getItems().addAll(adminhomePageItem);
		adminmanageMenu.getItems().addAll(adminmanageItem);
		adminaccountMenu.getItems().addAll(adminlogOut);

		adminmanageItem.setOnAction(e -> manageProduct());

		adminlogOut.setOnAction(event -> {
			primaryStage.setScene(loginscene);
		});

		productListView = new ListView<>();
		productListView.setItems(productList);
		loadProducts();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				WelcomeMessageAwal();
			} else {
				AdminProductDetails(newValue);
			}
		});

		Label title = new Label("SeRuput Teh");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		admingp.add(title, 0, 0);
		admingp.add(productListView, 0, 1);
		admingp.add(vb, 1, 1);

		adminbp.setTop(adminmenuBar);
		adminbp.setCenter(admingp);

		admingp.setPadding(new Insets(25));
		admingp.setVgap(10);
		admingp.setHgap(10);
		Scene scene = new Scene(adminbp, 900, 700);
		primaryStage.setTitle("Home");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void WelcomeMessageUpdate() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage, new Label("Select a product to Update"));
	}

	private void WelcomeMessageRemove() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage, new Label("Select a product to Remove"));
	}

	private void WelcomeMessageAwal() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
		vb.getChildren().addAll(welcomeMessage, new Label("Select a product to view details"));
	}

	private void AdminWelcomeMessageManage() {
		vb.getChildren().clear();
		String welcomeMessageText;

		welcomeMessageText = "Welcome, " + userName;

		Label welcomeMessage = new Label(welcomeMessageText);
		welcomeMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 14));

		Label selectProductLabel = new Label("Select a product to update");
		selectProductLabel.setFont(Font.font("Calibri", 12));

		Label labelkosong = new Label();
		labelkosong.setMinHeight(10);

		Button AddProductButton = new Button("Add Product");
		AddProductButton.setFont(Font.font("Calibri", 12));
		AddProductButton.setMinWidth(100);
		AddProductButton.setMinHeight(10);

		AddProductButton.setOnAction(e -> {
			addProduct();
		});

		vb.getChildren().addAll(welcomeMessage, selectProductLabel, labelkosong, AddProductButton);
	}

	private void AdminProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = ambilProductDetails(productName);
		if (product != null) {
			Label nameLabel = new Label(product.getName());
			nameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
			Label descriptionLabel = new Label(product.getDescription());
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(400);
			Label priceLabel = new Label("Price: Rp." + product.getPrice());

			Label totalLabel = new Label();
			totalLabel.setText("Total : Rp." + product.getPrice());

			totalLabel.setPadding(new Insets(0, 0, 0, 10));

			vb.getChildren().addAll(nameLabel, descriptionLabel, priceLabel);
		}
	}

	private void AdminManageProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = ambilProductDetails(productName);
		if (product != null) {
			Label nameLabel = new Label(product.getName());
			nameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
			Label descriptionLabel = new Label(product.getDescription());
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(400);
			Label priceLabel = new Label("Price: Rp." + product.getPrice());

			Label totalLabel = new Label();
			totalLabel.setText("Total : Rp." + product.getPrice());

			totalLabel.setPadding(new Insets(0, 0, 0, 10));

			Button AddProductButton = new Button("Add Product");
			AddProductButton.setFont(Font.font("Calibri", 12));
			AddProductButton.setMinWidth(100);
			AddProductButton.setMinHeight(10);

			AddProductButton.setOnAction(e -> {
				addProduct();
			});

			Button updateProductButton = new Button("Update Product");
			updateProductButton.setFont(Font.font("Calibri", 12));
			updateProductButton.setMinWidth(100);
			updateProductButton.setMinHeight(10);

			updateProductButton.setOnAction(e -> {
				updateProduct();
			});

			Button removeProductButton = new Button("Remove Product");
			removeProductButton.setFont(Font.font("Calibri", 12));
			removeProductButton.setMinWidth(100);
			removeProductButton.setMinHeight(10);

			removeProductButton.setOnAction(e -> {
				removeProduct();
			});

			adminhomePageItem.setOnAction(e -> {
				adminmenuBar.getMenus().clear();
				adminhomeMenu.getItems().clear();
				adminmanageMenu.getItems().clear();
				adminaccountMenu.getItems().clear();
				adminHome();
			});

			adminlogOut.setOnAction(event -> {
				primaryStage.setScene(loginscene);
			});

			HBox buttonContainer = new HBox(10);
			buttonContainer.getChildren().addAll(updateProductButton, removeProductButton);
			buttonContainer.setAlignment(Pos.CENTER_LEFT);

			vb.getChildren().addAll(nameLabel, descriptionLabel, priceLabel, AddProductButton, buttonContainer);
		}
	}

	private void AdminAddProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = ambilProductDetails(productName);
		if (product != null) {
			Label nameLabel = new Label(product.getName());
			nameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
			Label descriptionLabel = new Label(product.getDescription());
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(400);
			Label priceLabel = new Label("Price: Rp." + product.getPrice());

			Label totalLabel = new Label();
			totalLabel.setText("Total : Rp." + product.getPrice());

			totalLabel.setPadding(new Insets(0, 0, 0, 10));

			Button addButton = new Button("Add Product");
			addButton.setMinWidth(100);
			addButton.setMinHeight(10);

			addButton.setOnAction(e -> {
				String productID = generateProductID();
				String name = nameInput.getText();
				int price = Integer.valueOf(priceInput.getText());
				String description = descriptionInput.getText();

				boolean success = addProductToDatabase(productID, name, price, description);
				if (success) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Product Added");
					alert.setContentText("Product has been added");
					alert.showAndWait();

					refreshProducts();

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Error");
					alert.setContentText("Failed to add the product");
					alert.showAndWait();
				}
			});

			Button backButton = new Button("Back");
			backButton.setMinWidth(100);
			backButton.setMinHeight(10);

			backButton.setOnAction(e -> {
				manageProduct();
			});

			adminhomePageItem.setOnAction(e -> {
				adminmenuBar.getMenus().clear();
				adminhomeMenu.getItems().clear();
				adminmanageMenu.getItems().clear();
				adminaccountMenu.getItems().clear();
				adminHome();
			});

			adminlogOut.setOnAction(event -> {
				primaryStage.setScene(loginscene);
			});

			HBox addreButton = new HBox(10);
			addreButton.getChildren().addAll(addButton, backButton);
			addreButton.setAlignment(Pos.CENTER_LEFT);

			vb.getChildren().addAll(nameLabel, descriptionLabel, priceLabel, nameLabelText, nameInput, priceLabelText,
					priceInput, descriptionLabelText, descriptionInput, addreButton);
		}
	}

	private void AdminUpdateProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = ambilProductDetails(productName);
		if (product != null) {
			Label nameLabel = new Label(product.getName());
			nameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
			Label descriptionLabel = new Label(product.getDescription());
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(400);
			Label priceLabel = new Label("Price: Rp." + product.getPrice());

			Label totalLabel = new Label();
			totalLabel.setText("Total : Rp." + product.getPrice());

			totalLabel.setPadding(new Insets(0, 0, 0, 10));

			Label updtLabel = new Label("Update Product");
			TextField updtInput = new TextField();

			Button updtButton = new Button("Update Product");
			updtButton.setMinWidth(100);
			updtButton.setMinHeight(10);

			updtButton.setOnAction(e -> {
				int updatedPrice = Integer.valueOf(updtInput.getText());

				boolean success = updateProductPriceInDatabase(product.getId(), updatedPrice);

				if (success) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Product Updated");
					alert.setContentText("Product price has been updated");
					alert.showAndWait();

					refreshProducts();

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Update Failed");
					alert.setContentText("Failed to update product price.");
					alert.showAndWait();
				}
			});

			Button backButton = new Button("Back");
			backButton.setMinWidth(100);
			backButton.setMinHeight(10);

			backButton.setOnAction(e -> {
				manageProduct();
			});

			adminhomePageItem.setOnAction(e -> {
				adminmenuBar.getMenus().clear();
				adminhomeMenu.getItems().clear();
				adminmanageMenu.getItems().clear();
				adminaccountMenu.getItems().clear();
				adminHome();
			});

			adminlogOut.setOnAction(event -> {
				primaryStage.setScene(loginscene);
			});

			HBox updateButton = new HBox(10);
			updateButton.getChildren().addAll(updtButton, backButton);
			updateButton.setAlignment(Pos.CENTER_LEFT);

			vb.getChildren().addAll(nameLabel, descriptionLabel, priceLabel, updtLabel, updtInput, updateButton);
		}
	}

	private void AdminRemoveProductDetails(String productName) {
		vb.getChildren().clear();
		Product product = ambilProductDetails(productName);
		if (product != null) {
			Label nameLabel = new Label(product.getName());
			nameLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
			Label descriptionLabel = new Label(product.getDescription());
			descriptionLabel.setWrapText(true);
			descriptionLabel.setMaxWidth(400);
			Label priceLabel = new Label("Price: Rp." + product.getPrice());

			Label totalLabel = new Label();
			totalLabel.setText("Total : Rp." + product.getPrice());

			totalLabel.setPadding(new Insets(0, 0, 0, 10));

			Label removeLabel = new Label("Are you sure, you want to remove this product?");

			Button rmvButton = new Button("Remove Product");
			rmvButton.setMinWidth(100);
			rmvButton.setMinHeight(10);

			rmvButton.setOnAction(e -> {
				ambilProductDetails(productName);
				if (product != null) {
					boolean success = removeProductFromDatabase(product.getId());
					if (success) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Product Removed");
						alert.setContentText("Product has been removed");
						alert.showAndWait();

						refreshProducts();

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Error");
						alert.setContentText("Failed to remove the product");
						alert.showAndWait();
					}
				}
			});

			Button backButton = new Button("Back");
			backButton.setMinWidth(100);
			backButton.setMinHeight(10);

			backButton.setOnAction(e -> {
				manageProduct();
			});

			adminhomePageItem.setOnAction(e -> {
				adminmenuBar.getMenus().clear();
				adminhomeMenu.getItems().clear();
				adminmanageMenu.getItems().clear();
				adminaccountMenu.getItems().clear();
				adminHome();
			});

			adminlogOut.setOnAction(event -> {
				primaryStage.setScene(loginscene);
			});

			HBox removeButton = new HBox(10);
			removeButton.getChildren().addAll(rmvButton, backButton);
			removeButton.setAlignment(Pos.CENTER_LEFT);

			vb.getChildren().addAll(nameLabel, descriptionLabel, priceLabel, removeLabel, removeButton);
		}
	}

	private Product ambilProductDetails(String productName) {
		Connect connect = Connect.getInstance();
		String query = "SELECT * FROM product WHERE product_name = ?";
		Product product = null;

		try (Connection conn = connect.getConnection(); PreparedStatement menuDetails = conn.prepareStatement(query)) {

			menuDetails.setString(1, productName);
			try (ResultSet rs = menuDetails.executeQuery()) {
				if (rs.next()) {
					return new Product(rs.getString("productID"), rs.getString("product_name"),
							rs.getInt("product_price"), rs.getString("product_des"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	private void refreshProducts() {
		productList.clear();
		Connect connect = Connect.getInstance();
		String query = "SELECT product_name FROM product";

		try (Connection conn = connect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				productList.add(rs.getString("product_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void manageProduct() {
		managegp = new GridPane();
		managebp = new BorderPane();
		adminManageProduct = new Scene(managegp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		AdminWelcomeMessageManage();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				WelcomeMessageAwal();
			} else {
				AdminManageProductDetails(newValue);
			}
		});

		Label title = new Label("Manage Products");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		managegp.add(title, 0, 0);
		managegp.add(productListView, 0, 1);
		managegp.add(vb, 1, 1);

		managebp.setTop(adminmenuBar);
		managebp.setCenter(managegp);

		adminhomePageItem.setOnAction(e -> {
			adminmenuBar.getMenus().clear();
			adminhomeMenu.getItems().clear();
			adminmanageMenu.getItems().clear();
			adminaccountMenu.getItems().clear();
			adminHome();
		});

		managegp.setPadding(new Insets(25));
		managegp.setVgap(10);
		managegp.setHgap(10);

		Scene scene = new Scene(managebp, 900, 700);
		primaryStage.setTitle("Products");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void addProduct() {
		addgp = new GridPane();
		addbp = new BorderPane();
		addProductScene = new Scene(addgp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		WelcomeMessageAwal();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				WelcomeMessageAwal();
			} else {
				AdminAddProductDetails(newValue);
			}
		});

		Button addButton = new Button("Add Product");
		addButton.setMinWidth(100);
		addButton.setMinHeight(10);

		addButton.setOnAction(e -> {
			String productID = generateProductID();
			String name = nameInput.getText();
			int price = Integer.valueOf(priceInput.getText());
			String description = descriptionInput.getText();

			boolean success = addProductToDatabase(productID, name, price, description);
			if (success) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Product Added");
				alert.setContentText("The product has been successfully added to the database.");
				alert.showAndWait();

				refreshProducts();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Failed to add the product to the database.");
				alert.showAndWait();
			}
		});

		Button backButton = new Button("Back");
		backButton.setMinWidth(100);
		backButton.setMinHeight(10);

		backButton.setOnAction(e -> {
			manageProduct();
		});

		Label title = new Label("Manage Products");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		addgp.add(title, 0, 0);
		addgp.add(productListView, 0, 1);
		addgp.add(vb, 1, 1);

		addbp.setTop(adminmenuBar);
		addbp.setCenter(addgp);

		addgp.add(nameLabelText, 0, 2);
		addgp.add(nameInput, 1, 2);
		addgp.add(priceLabelText, 0, 3);
		addgp.add(priceInput, 1, 3);
		addgp.add(descriptionLabelText, 0, 4);
		addgp.add(descriptionInput, 1, 4);
		GridPane.setMargin(descriptionInput, new Insets(0, 0, 0, 0));
		addgp.add(addButton, 1, 5);
		GridPane.setHalignment(backButton, HPos.RIGHT);
		addgp.add(backButton, 2, 5);
		GridPane.setHalignment(addButton, HPos.LEFT);

		HBox addbackButton = new HBox(10);
		addbackButton.getChildren().addAll(addButton, backButton);
		addbackButton.setAlignment(Pos.CENTER_LEFT);

		addgp.setPadding(new Insets(25));
		addgp.setVgap(10);
		addgp.setHgap(10);

		Scene scene = new Scene(addbp, 900, 700);
		primaryStage.setTitle("Products");
		primaryStage.setScene(scene);
		primaryStage.show();

		vb.getChildren().addAll(nameLabelText, nameInput, priceLabelText, priceInput, descriptionLabelText,
				descriptionInput, addbackButton);
	}

	public boolean addProductToDatabase(String productID, String name, int price, String description) {
		Connect connect = Connect.getInstance();
		String query = "INSERT INTO product (productID, product_name, product_price, product_des) VALUES (?, ?, ?, ?)";

		try (Connection conn = connect.getConnection(); PreparedStatement preS = conn.prepareStatement(query)) {

			preS.setString(1, productID);
			preS.setString(2, name);
			preS.setInt(3, price);
			preS.setString(4, description);

			int affectedRows = preS.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProductPriceInDatabase(String productID, int newPrice) {
		Connect connect = Connect.getInstance();
		String query = "UPDATE product SET product_price = ? WHERE productID = ?";

		try (Connection conn = connect.getConnection(); PreparedStatement preS = conn.prepareStatement(query)) {

			preS.setInt(1, newPrice);
			preS.setString(2, productID);

			int affectedRows = preS.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeProductFromDatabase(String productID) {
		Connect connect = Connect.getInstance();
		String query = "DELETE FROM product WHERE productID = ?";

		try (Connection conn = connect.getConnection(); PreparedStatement preS = conn.prepareStatement(query)) {

			preS.setString(1, productID);
			int affectedRows = preS.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generateProductID() {
		Connect connect = Connect.getInstance();
		String latestID = null;

		try (Connection conn = connect.getConnection();
				PreparedStatement statement = conn
						.prepareStatement("SELECT productID FROM product ORDER BY productID DESC LIMIT 1");
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				latestID = resultSet.getString("productID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (latestID != null) {
			int numberID;
			try {
				numberID = Integer.valueOf(latestID.substring(2));
				String newID = "TE" + String.format("%03d", numberID + 1);
				return newID;
			} catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
				ex.printStackTrace();
				return null;
			}
		} else {
			return "TE001";
		}
	}

	public void updateProduct() {
		updtgp = new GridPane();
		updtbp = new BorderPane();
		updateProductScene = new Scene(updtgp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		WelcomeMessageUpdate();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				WelcomeMessageUpdate();
			} else {
				AdminUpdateProductDetails(newValue);
			}
		});

		Label title = new Label("Manage Products");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		updtgp.add(title, 0, 0);
		updtgp.add(productListView, 0, 1);
		updtgp.add(vb, 1, 1);

		updtbp.setTop(adminmenuBar);
		updtbp.setCenter(updtgp);

		updtgp.setPadding(new Insets(25));
		updtgp.setVgap(10);
		updtgp.setHgap(10);
		Scene scene = new Scene(updtbp, 900, 700);
		primaryStage.setTitle("Products");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void removeProduct() {
		removegp = new GridPane();
		removebp = new BorderPane();
		removeProductScene = new Scene(removegp, 900, 700);

		vb = new VBox(10);
		vb.setAlignment(Pos.TOP_LEFT);
		WelcomeMessageRemove();

		productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				WelcomeMessageRemove();
			} else {
				AdminRemoveProductDetails(newValue);
			}
		});

		Label title = new Label("Manage Products");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));

		removegp.add(title, 0, 0);
		removegp.add(productListView, 0, 1);
		removegp.add(vb, 1, 1);

		removebp.setTop(adminmenuBar);
		removebp.setCenter(removegp);

		removegp.setPadding(new Insets(25));
		removegp.setVgap(10);
		removegp.setHgap(10);
		Scene scene = new Scene(removebp, 900, 700);
		primaryStage.setTitle("Products");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		LoginElement();
		LoginPosition();
		LogineHandler();
		primaryStage.setTitle("Login");
		primaryStage.setScene(loginscene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}