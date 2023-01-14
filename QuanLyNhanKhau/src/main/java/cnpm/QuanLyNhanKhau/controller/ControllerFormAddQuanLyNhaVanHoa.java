package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormAddQuanLyNhaVanHoa implements Initializable {

	@FXML
	private TextField textfieldSoLuongBan;
	@FXML
	private TextField textfieldHienTrangBan;
	@FXML
	private TextField textfieldSoLuongGhe;
	@FXML
	private TextField textfieldHienTrangGhe;
	@FXML
	private TextField textfieldSoLuongLoa;
	@FXML
	private TextField textfieldHienTrangLoa;
	@FXML
	private TextField textfieldSoLuongDai;
	@FXML
	private TextField textfieldHienTrangDai;
	@FXML
	private TextField textfieldSoLuongManHinh;
	@FXML
	private TextField textfieldHienTrangManHinh;
	@FXML
	private TextField textfieldSoLuongDen;
	@FXML
	private TextField textfieldHienTrangDen;
	
	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonThemMoi;

//	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldSoLuongBan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangBan.requestFocus();
			}
		});
		
		textfieldHienTrangBan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongGhe.requestFocus();
			}
		});
		
		textfieldSoLuongGhe.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangGhe.requestFocus();
			}
		});
		
		textfieldHienTrangGhe.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongLoa.requestFocus();
			}
		});
		
		textfieldSoLuongLoa.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangLoa.requestFocus();
			}
		});
		
		textfieldHienTrangLoa.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongDai.requestFocus();
			}
		});
		
		textfieldSoLuongDai.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangDai.requestFocus();
			}
		});
		
		textfieldHienTrangDai.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongManHinh.requestFocus();
			}
		});
		
		textfieldSoLuongManHinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangManHinh.requestFocus();
			}
		});
		
		textfieldHienTrangManHinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongDen.requestFocus();
			}
		});
		
		textfieldSoLuongDen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldHienTrangDen.requestFocus();
			}
		});
		
		textfieldHienTrangDen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonThemMoi.fire();
			}
		});
		
//		hideNotice();
	}

	@FXML
	public void addQuanLyNhaVanHoa() {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		App.closeStageForm();
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		return check;
	}
	
	public static ControllerNhanKhau getControllerNhanKhau() {
		return controllerNhanKhau;
	}
	public static void setControllerNhanKhau(ControllerNhanKhau controllerNhanKhau) {
		ControllerFormAddQuanLy.controllerNhanKhau = controllerNhanKhau;
	}
}
