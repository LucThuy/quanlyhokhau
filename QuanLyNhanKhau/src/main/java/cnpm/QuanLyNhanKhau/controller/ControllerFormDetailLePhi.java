package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelLePhi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormDetailLePhi implements Initializable {

	@FXML
	private TextField textfieldGiaThueNgay;
	@FXML
	private TextField textfieldGiaThueBan;
	@FXML
	private TextField textfieldGiaThueGhe;
	@FXML
	private TextField textfieldGiaThueLoa;
	@FXML
	private TextField textfieldGiaThueDai;
	@FXML
	private TextField textfieldGiaThueManHinh;
	@FXML
	private TextField textfieldGiaThueDen;
	
	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonLuuThayDoi;
	
	public static ModelLePhi currentLePhi;

	private static ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy;
	private static ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		
		textfieldGiaThueNgay.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueBan.requestFocus();
			}
		});
		
		textfieldGiaThueBan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueGhe.requestFocus();
			}
		});
		
		textfieldGiaThueGhe.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueLoa.requestFocus();
			}
		});
		
		textfieldGiaThueLoa.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueDai.requestFocus();
			}
		});
		
		textfieldGiaThueDai.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueManHinh.requestFocus();
			}
		});
		
		textfieldGiaThueManHinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldGiaThueDen.requestFocus();
			}
		});
		
		textfieldGiaThueDen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonLuuThayDoi.fire();
			}
		});
		
		textfieldGiaThueNgay.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueNgay.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueBan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueBan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueGhe.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueGhe.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueLoa.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueLoa.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueDai.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueDai.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueManHinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueManHinh.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGiaThueDen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldGiaThueDen.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		
	
		
		hideNotice();
	}

	@FXML
	public void editLePhi() {
		if(isMissingField()) {
			setNotice("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		currentLePhi.setGiaThueNgay(Integer.valueOf(textfieldGiaThueNgay.getText()));
		currentLePhi.setGiaThueBan(Integer.valueOf(textfieldGiaThueBan.getText()));
		currentLePhi.setGiaThueGhe(Integer.valueOf(textfieldGiaThueGhe.getText()));
		currentLePhi.setGiaThueLoa(Integer.valueOf(textfieldGiaThueLoa.getText()));
		currentLePhi.setGiaThueDai(Integer.valueOf(textfieldGiaThueDai.getText()));
		currentLePhi.setGiaThueManHinh(Integer.valueOf(textfieldGiaThueManHinh.getText()));
		currentLePhi.setGiaThueDen(Integer.valueOf(textfieldGiaThueDen.getText()));

		
		if(Connector.editLePhi(currentLePhi)) {
			labelThongBao.getStyleClass().add("notice-green");
			setNotice("Đã lưu thay đổi");
		}
	
	} 
		
	
	
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldGiaThueNgay.getText().isEmpty()) {
			textfieldGiaThueNgay.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueBan.getText().isEmpty()) {
			textfieldGiaThueBan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueGhe.getText().isEmpty()) {
			textfieldGiaThueGhe.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueLoa.getText().isEmpty()) {
			textfieldGiaThueLoa.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueDai.getText().isEmpty()) {
			textfieldGiaThueDai.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueManHinh.getText().isEmpty()) {
			textfieldGiaThueManHinh.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldGiaThueDen.getText().isEmpty()) {
			textfieldGiaThueDen.getStyleClass().add("inputfield-error");
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
	
	private void loadData() {
		currentLePhi = Connector.getLePhi();

		textfieldGiaThueNgay.setText(String.valueOf(currentLePhi.getGiaThueNgay()));
		textfieldGiaThueBan.setText(String.valueOf(currentLePhi.getGiaThueBan()));
		textfieldGiaThueGhe.setText(String.valueOf(currentLePhi.getGiaThueGhe()));
		textfieldGiaThueLoa.setText(String.valueOf(currentLePhi.getGiaThueLoa()));
		textfieldGiaThueDai.setText(String.valueOf(currentLePhi.getGiaThueDai()));
		textfieldGiaThueManHinh.setText(String.valueOf(currentLePhi.getGiaThueManHinh()));
		textfieldGiaThueDen.setText(String.valueOf(currentLePhi.getGiaThueDen()));
//		System.out.println(currentLePhi.getGiaThueDai());

	}
	
	public static ControllerNhaVanHoaQuanLy getControllerNhaVanHoaQuanLy() {
		return controllerNhaVanHoaQuanLy;
	}
	public static void setControllerNhaVanHoaQuanLy(ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy) {
		ControllerFormDetailLePhi.controllerNhaVanHoaQuanLy = controllerNhaVanHoaQuanLy;
	}
	
	public static ControllerNhaVanHoaAdmin getControllerNhaVanHoaAdmin() {
		return controllerNhaVanHoaAdmin;
	}
	public static void setControllerNhaVanHoaAdmin(ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin) {
		ControllerFormDetailLePhi.controllerNhaVanHoaAdmin = controllerNhaVanHoaAdmin;
	}
}
