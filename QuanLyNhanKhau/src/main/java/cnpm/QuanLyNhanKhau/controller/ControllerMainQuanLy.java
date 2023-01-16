package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ControllerMainQuanLy implements Initializable {

	@FXML
	private Button buttonQuanLy;
	@FXML
	private Button buttonHoatDong;
	
	@FXML
	private Parent viewNhaVanHoaQuanLy;
	@FXML
	private Parent viewHoatDong;

	
	private Button currentButton;
	private Parent currentView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		currentButton = buttonQuanLy;
		currentView = viewNhaVanHoaQuanLy;
		
		buttonQuanLy.setTranslateX(-10);
		buttonHoatDong.setTranslateX(-10);
		
		setViewNhaVanHoaQuanLy();
	}
	
	@FXML
	public void setViewNhaVanHoaQuanLy() {
		setView(viewNhaVanHoaQuanLy, buttonQuanLy);	
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
}