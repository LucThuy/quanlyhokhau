package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class ControllerSignUp implements Initializable {

	@FXML
	private TextField textfieldTenNguoiDung;
	@FXML
	private TextField textfieldTaiKhoan;
	@FXML
	private TextField textfieldMatKhau;
	@FXML
	private Button buttonDangKy;
	@FXML
	private Label labelThongBao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldTenNguoiDung.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldTaiKhoan.requestFocus();
			}
		});

		textfieldTaiKhoan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldMatKhau.requestFocus();
			}
		});

		textfieldMatKhau.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonDangKy.requestFocus();
				buttonDangKy.fire();
			}
		});

		textfieldTenNguoiDung.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldTenNguoiDung.getStyleClass().removeAll("inputfield-error");
			}
		});

		textfieldTaiKhoan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldTaiKhoan.getStyleClass().removeAll("inputfield-error");
			}
		});

		textfieldMatKhau.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldMatKhau.getStyleClass().removeAll("inputfield-error");
			}
		});
	}

	@FXML
	public void signUp() {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		if (Connector.signUp(textfieldTaiKhoan.getText(), textfieldMatKhau.getText(),
				textfieldTenNguoiDung.getText())) {
			try {
				App.setRoot("view/ViewSignIn");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void switchToViewSignIn() {
		try {
			App.setRoot("view/ViewSignIn");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isMissingField() {
		boolean check = false;
		if(textfieldTenNguoiDung.getText().isEmpty()) {
			textfieldTenNguoiDung.getStyleClass().add("inputfield-error");
			check = true;
		}
		if(textfieldTaiKhoan.getText().isEmpty()) {
			textfieldTaiKhoan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if(textfieldMatKhau.getText().isEmpty()) {
			textfieldMatKhau.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

}
