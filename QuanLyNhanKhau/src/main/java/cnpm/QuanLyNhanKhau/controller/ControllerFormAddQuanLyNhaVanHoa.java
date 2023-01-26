package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
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

	private static ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy;
	private static ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin;

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
		
		textfieldSoLuongBan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongBan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangBan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangBan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongGhe.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongGhe.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangGhe.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangGhe.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongLoa.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongLoa.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangLoa.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangLoa.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongDai.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongDai.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangDai.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangDai.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongManHinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongManHinh.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangManHinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangManHinh.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongDen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongDen.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHienTrangDen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHienTrangDen.getStyleClass().removeAll("inputfield-error");
			}
		});
	
		
		hideNotice();
	}

	@FXML
	public void addQuanLyNhaVanHoa() {
		if(isMissingField()) {
			setNotice("Điền đầy đủ các mục bắt buộc");
			return;
		}
		if (Connector.addQuanLyNhaVanHoa(Integer.valueOf(textfieldSoLuongBan.getText()),
				textfieldHienTrangBan.getText(), Integer.valueOf(textfieldSoLuongGhe.getText()),
				textfieldHienTrangGhe.getText(), Integer.valueOf(textfieldSoLuongLoa.getText()),
				textfieldHienTrangLoa.getText(), Integer.valueOf(textfieldSoLuongDai.getText()),
				textfieldHienTrangDai.getText(), Integer.valueOf(textfieldSoLuongManHinh.getText()),
				textfieldHienTrangManHinh.getText(), Integer.valueOf(textfieldSoLuongDen.getText()),
				textfieldHienTrangDen.getText())) {
			if(controllerNhaVanHoaQuanLy != null) {
				controllerNhaVanHoaQuanLy.refreshQuanLyNhaVanHoa();
			}
			if(controllerNhaVanHoaAdmin != null) {
				controllerNhaVanHoaAdmin.refreshQuanLyNhaVanHoa();
			}
			App.closeStageForm();
		} 
		
	}
	
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldSoLuongBan.getText().isEmpty()) {
			textfieldSoLuongBan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHienTrangBan.getText().isEmpty()) {
			textfieldHienTrangBan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongGhe.getText().isEmpty()) {
			textfieldSoLuongGhe.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHienTrangGhe.getText().isEmpty()) {
			textfieldHienTrangGhe.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongLoa.getText().isEmpty()) {
			textfieldSoLuongLoa.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHienTrangLoa.getText().isEmpty()) {
			textfieldHienTrangLoa.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongDai.getText().isEmpty()) {
			textfieldSoLuongDai.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHienTrangDai.getText().isEmpty()) {
			textfieldHienTrangDai.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongManHinh.getText().isEmpty()) {
			textfieldSoLuongManHinh.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongDen.getText().isEmpty()) {
			textfieldSoLuongDen.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHienTrangDen.getText().isEmpty()) {
			textfieldHienTrangDen.getStyleClass().add("inputfield-error");
			check = true;
		}

		return check;
	}
	
	private void setNotice(String notice) {
		labelThongBao.setText(notice);
		labelThongBao.setMinHeight(20);
		labelThongBao.setMaxHeight(20);
	}
	
	private void hideNotice() {
		labelThongBao.setMinHeight(0);
		labelThongBao.setMaxHeight(0);
	}
	
	public static ControllerNhaVanHoaQuanLy getControllerNhaVanHoaQuanLy() {
		return controllerNhaVanHoaQuanLy;
	}
	public static void setControllerNhaVanHoaQuanLy(ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy) {
		ControllerFormAddQuanLyNhaVanHoa.controllerNhaVanHoaQuanLy = controllerNhaVanHoaQuanLy;
	}
	
	public static ControllerNhaVanHoaAdmin getControllerNhaVanHoaAdmin() {
		return controllerNhaVanHoaAdmin;
	}
	public static void setControllerNhaVanHoaAdmin(ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin) {
		ControllerFormAddQuanLyNhaVanHoa.controllerNhaVanHoaAdmin = controllerNhaVanHoaAdmin;
	}
}
