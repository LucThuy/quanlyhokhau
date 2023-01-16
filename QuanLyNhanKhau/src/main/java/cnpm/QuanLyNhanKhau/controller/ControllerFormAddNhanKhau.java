package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormAddNhanKhau implements Initializable {

	@FXML
	private TextField textfieldHoTen;
	@FXML
	private TextField textfieldBiDanh;
	@FXML
	private DatePicker datepickerNgaySinh;
	@FXML
	private ToggleGroup togglegroupGioiTinh;
	@FXML
	private TextField textfieldCCCD;
	@FXML
	private DatePicker datepickerNgayCap;
	@FXML
	private TextField textfieldNoiCap;
	@FXML
	private TextField textfieldNguyenQuan;
	@FXML
	private TextField textfieldDanToc;
	@FXML
	private TextField textfieldNoiThuongTru;
	@FXML
	private DatePicker datepickerNgayDangKyThuongTru;
	@FXML
	private TextField textfieldTrinhDoHocVan;
	@FXML
	private TextField textfieldTrinhDoNgoaiNgu;
	@FXML
	private TextField textfieldNgheNghiep;
	@FXML
	private TextField textfieldNoiLamViec;
	@FXML
	private TextField textfieldTonGiao;
	@FXML
	private TextField textfieldQuocTich;
	@FXML
	private TextField textfieldTrinhDoChuyenMon;
	@FXML
	private TextField textfieldGhiChu;

	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonThemMoi;

	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldHoTen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldBiDanh.requestFocus();
			}
		});
				
		textfieldBiDanh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgaySinh.requestFocus();
			}
		});

		datepickerNgaySinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				RadioButton radiobuttonGioiTinh = (RadioButton) togglegroupGioiTinh.getToggles().get(0);
				radiobuttonGioiTinh.requestFocus();
			}
		});

		togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			radiobutton.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
				if (e.getCode() == KeyCode.ENTER) {
					textfieldCCCD.requestFocus();
				}
			});
		});

		textfieldCCCD.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgayCap.requestFocus();			}
		});
		
		datepickerNgayCap.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldNoiCap.requestFocus();			}
		});
		
		textfieldNoiCap.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldNguyenQuan.requestFocus();			}
		});
		
		textfieldNguyenQuan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldDanToc.requestFocus();
			}
		});
		
		textfieldDanToc.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldNoiThuongTru.requestFocus();
			}
		});
		
		textfieldNoiThuongTru.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				datepickerNgayDangKyThuongTru.requestFocus();
			}
		});
		
		datepickerNgayDangKyThuongTru.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldTrinhDoHocVan.requestFocus();
			}
		});
		
		textfieldTrinhDoHocVan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldTrinhDoNgoaiNgu.requestFocus();
			}
		});
		
		textfieldTrinhDoNgoaiNgu.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldNgheNghiep.requestFocus();
			}
		});
		
		textfieldNgheNghiep.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldNoiLamViec.requestFocus();
			}
		});
		
		textfieldNoiLamViec.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldTonGiao.requestFocus();
			}
		});
		
		textfieldTonGiao.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldQuocTich.requestFocus();
			}
		});
		
		textfieldQuocTich.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldTrinhDoChuyenMon.requestFocus();
			}
		});
		
		textfieldTrinhDoChuyenMon.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				textfieldGhiChu.requestFocus();
			}
		});
		
		textfieldGhiChu.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if(e.getCode() == KeyCode.ENTER) {
				buttonThemMoi.fire();
			}
		});

		textfieldHoTen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHoTen.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldBiDanh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldBiDanh.getStyleClass().removeAll("inputfield-error");
			}
		});

		datepickerNgaySinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgaySinh.getStyleClass().removeAll("inputfield-error");
			}
		});

		togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			radiobutton.focusedProperty().addListener((obs, oldVal, newVal) -> {
				if (newVal) {
					labelThongBao.setText("");
					togglegroupGioiTinh.getToggles().stream().forEach(t -> {
						RadioButton r = (RadioButton) t;
						r.getStyleClass().removeAll("inputfield-error");
					});
				}
			});
		});

		textfieldCCCD.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldCCCD.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayCap.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgayCap.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNoiCap.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldNoiCap.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNguyenQuan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldNguyenQuan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldDanToc.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldDanToc.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNoiThuongTru.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldNoiThuongTru.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayDangKyThuongTru.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgayDangKyThuongTru.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldTrinhDoHocVan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldTrinhDoHocVan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldTrinhDoNgoaiNgu.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldTrinhDoNgoaiNgu.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNgheNghiep.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldNgheNghiep.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNoiLamViec.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldNoiLamViec.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldTonGiao.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldTonGiao.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldQuocTich.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldQuocTich.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldTrinhDoChuyenMon.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldTrinhDoChuyenMon.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldGhiChu.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				labelThongBao.setText("");
				textfieldGhiChu.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		hideNotice();
	}

	@FXML
	public void addNhanKhau() {
		if(isMissingField()) {
			setNotice("Điền đầy đủ các mục bắt buộc");
			return;
		}
		RadioButton selectedRadioButton = (RadioButton) togglegroupGioiTinh.getSelectedToggle();
		if (Connector.addNhanKhau(textfieldHoTen.getText(), textfieldBiDanh.getText(), datepickerNgaySinh.getValue(),
				selectedRadioButton.getText(), textfieldCCCD.getText(), datepickerNgayCap.getValue(),
				textfieldNoiCap.getText(), textfieldNguyenQuan.getText(), textfieldDanToc.getText(),
				textfieldNoiThuongTru.getText(), datepickerNgayDangKyThuongTru.getValue(),
				textfieldTrinhDoHocVan.getText(), textfieldTrinhDoNgoaiNgu.getText(),
				textfieldNgheNghiep.getText(), textfieldNoiLamViec.getText(), textfieldTonGiao.getText(),
				textfieldQuocTich.getText(), textfieldTrinhDoChuyenMon.getText(), textfieldGhiChu.getText())) {
			controllerNhanKhau.refreshNhanKhau();
			App.closeStageForm();
		} 
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTen.getText().isEmpty()) {
			textfieldHoTen.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgaySinh.getValue() == null) {
			datepickerNgaySinh.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (togglegroupGioiTinh.getSelectedToggle() == null) {
			togglegroupGioiTinh.getToggles().stream().forEach(toggle -> {
				RadioButton radiobutton = (RadioButton) toggle;
				radiobutton.getStyleClass().add("inputfield-error");
			});
			check = true;
		}
		if (textfieldNoiThuongTru.getText().isEmpty()) {
			textfieldNoiThuongTru.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgayDangKyThuongTru.getValue() == null) {
			datepickerNgayDangKyThuongTru.getStyleClass().add("inputfield-error");
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
	
	public static ControllerNhanKhau getControllerNhanKhau() {
		return controllerNhanKhau;
	}
	public static void setControllerNhanKhau(ControllerNhanKhau controllerNhanKhau) {
		ControllerFormAddNhanKhau.controllerNhanKhau = controllerNhanKhau;
	}
}
