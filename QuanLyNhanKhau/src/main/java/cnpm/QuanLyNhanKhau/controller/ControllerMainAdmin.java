package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ControllerMainAdmin implements Initializable {

	@FXML
	private Button buttonQuanLy;
	@FXML
	private Button buttonNhanKhau;
	@FXML
	private Button buttonHoKhau;
	@FXML
	private Button buttonTamTruTamVang;
	@FXML
	private Button buttonQuanLyNhaVanHoa;

	@FXML
	private Parent viewQuanLy;
	@FXML
	private Parent viewNhanKhau;
	@FXML
	private Parent viewHoKhau;
	@FXML
	private Parent viewTamTruTamVang;
	@FXML
	private Parent viewQuanLyNhaVanHoa;
	
	private Button currentButton;
	private Parent currentView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		currentButton = buttonQuanLy;
		currentView = viewQuanLy;
		
		buttonQuanLy.setTranslateX(-10);
		buttonNhanKhau.setTranslateX(-10);
		buttonHoKhau.setTranslateX(-10);
		buttonTamTruTamVang.setTranslateX(-10);
		
		setViewQuanLy();
	}
	
	@FXML
	public void setViewQuanLy() {
		setView(viewQuanLy, buttonQuanLy);
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
}