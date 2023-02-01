package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class ControllerSignIn implements Initializable {

	@FXML
	private TextField textfieldTaiKhoan;
	@FXML
	private PasswordField passwordfieldMatKhau;
	@FXML
	private Button buttonDangNhap;
	@FXML
	private Label labelThongBao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldTaiKhoan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				passwordfieldMatKhau.requestFocus();
			}
		});

		passwordfieldMatKhau.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonDangNhap.requestFocus();
				buttonDangNhap.fire();
			}
		});

		textfieldTaiKhoan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldTaiKhoan.getStyleClass().removeAll("inputfield-error");
			}
		});

		passwordfieldMatKhau.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				passwordfieldMatKhau.getStyleClass().removeAll("inputfield-error");
			}
		});
	}

	@FXML
	public void signIn() throws IOException {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		ModelUser user = Connector.queryUserByTaiKhoanAndMatKhau(textfieldTaiKhoan.getText(), passwordfieldMatKhau.getText());
		if(!Connector.signIn(textfieldTaiKhoan.getText(), passwordfieldMatKhau.getText())) {
			labelThongBao.setText("Tài khoản hoặc mật khẩu không chính xác");
		}
		else if(user.getRole().equals("Tổ Trưởng")) {
			App.setRoot("view/ViewMainAdmin");
		}
		else if(user.getRole().equals("Nhân Viên") && user.getCapQuyen().equals("Đã cấp quyền")){
			App.setRoot("view/ViewMainNhanVien");
		}
		else if(user.getRole().equals("Cán Bộ Quản Lý") && user.getCapQuyen().equals("Đã cấp quyền")) {
			App.setRoot("view/ViewMainQuanLy");
		}
		else {
			labelThongBao.setText("Tài khoản của bạn đang chờ được cấp phép");
		}
	}

	@FXML
	public void switchToViewSignUp() {
		try {
			App.setRoot("view/ViewSignUp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isMissingField() {
		boolean check = false;
		if(textfieldTaiKhoan.getText().isEmpty()) {
			textfieldTaiKhoan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if(passwordfieldMatKhau.getText().isEmpty()) {
			passwordfieldMatKhau.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

}
