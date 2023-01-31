package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class ControllerMainAdmin implements Initializable {

	@FXML
	private Button buttonQuanLy;
	@FXML 
	private Button buttonThongKe;
	@FXML
	private Button buttonNhanKhau;
	@FXML
	private Button buttonHoKhau;
	@FXML
	private Button buttonTamTruTamVang;
	@FXML
	private Button buttonQuanLyNhaVanHoa;
	@FXML
	private Button buttonHoatDong;
	@FXML
	private Button buttonDangXuat;

	@FXML
	private Parent viewQuanLyUser;
	@FXML
	private Parent viewThongKe;
	@FXML
	private Parent viewNhanKhau;
	@FXML
	private Parent viewHoKhau;
	@FXML
	private Parent viewTamTruTamVang;
	@FXML
	private Parent viewQuanLyNhaVanHoa;
	@FXML
	private Parent viewHoatDong;
	
	private Button currentButton;
	private Parent currentView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		currentButton = buttonQuanLy;
		currentView = viewQuanLyUser;
		
		buttonQuanLy.setTranslateX(-10);
		buttonThongKe.setTranslateX(-10);
		buttonNhanKhau.setTranslateX(-10);
		buttonHoKhau.setTranslateX(-10);
		buttonTamTruTamVang.setTranslateX(-10);
		buttonQuanLyNhaVanHoa.setTranslateX(-10);
		buttonHoatDong.setTranslateX(-10);
		buttonDangXuat.setTranslateX(-10);
		
		setViewQuanLyUser();
	}
	
	@FXML
	public void setViewQuanLyUser() {
		setView(viewQuanLyUser, buttonQuanLy);
	}
	
	@FXML
	public void setViewThongKe() {
		setView(viewThongKe, buttonThongKe);
	}
	@FXML
	public void setViewNhanKhau() {
		setView(viewNhanKhau, buttonNhanKhau);	
	}
	
	@FXML
	public void setViewHoKhau() {
		setView(viewHoKhau, buttonHoKhau);
	}
	
	@FXML
	public void setViewTamTruTamVang() {
		setView(viewTamTruTamVang, buttonTamTruTamVang);
	}
	
	@FXML
	public void setViewQuanLyNhaVanHoa() {
		setView(viewQuanLyNhaVanHoa, buttonQuanLyNhaVanHoa);
	}
	@FXML
	public void setViewHoatDong() {
		setView(viewHoatDong, buttonHoatDong);
	}

	private void setView(Parent view, Button button) {
		TranslateTransition translateTransition;
		
		currentView.setVisible(false);
		currentButton.getStyleClass().removeAll("active");
		translateTransition = new TranslateTransition(Duration.millis(500), currentButton);
		translateTransition.setFromX(currentButton.getTranslateX());
		translateTransition.setToX(-10);
		translateTransition.play();
		
		view.setVisible(true);
		view.toFront();
		view.requestFocus();
		button.getStyleClass().add("active");
		translateTransition = new TranslateTransition(Duration.millis(500), button);
		translateTransition.setFromX(button.getTranslateX());
		translateTransition.setToX(0);
		translateTransition.play();
		
		currentView = view;
		currentButton = button;
	}
	
	@FXML
	public void logOut() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Bạn có muốn đăng xuất?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			try {
				Connector.lsLogOut();
				App.setRoot("view/ViewSignIn");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}