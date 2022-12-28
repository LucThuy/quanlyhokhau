package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ControllerMain implements Initializable {

	@FXML
	private Button buttonNhanKhau;
	@FXML
	private Button buttonHoKhau;
	
	@FXML
	private Parent viewNhanKhau;
	@FXML
	private Parent viewHoKhau;
	
	private Button currentButton;
	private Parent currentView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		currentButton = buttonNhanKhau;
		currentView = viewNhanKhau;
		
		buttonNhanKhau.setTranslateX(-10);
		buttonHoKhau.setTranslateX(-10);
		
		setViewNhanKhau();
	}
	
	@FXML
	public void setViewNhanKhau() {
		setView(viewNhanKhau, buttonNhanKhau);	
	}
	
	@FXML
	public void setViewHoKhau() {
		setView(viewHoKhau, buttonHoKhau);
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