package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoatDong;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelTamTru;
import cnpm.QuanLyNhanKhau.model.ModelTamVang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormDetailNhanKhau implements Initializable {

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
	private Button buttonLuuThayDoi;

	private ModelNhanKhau data;
	
	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
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
			if (e.getCode() == KeyCode.ENTER) {
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
				buttonLuuThayDoi.fire();
			}
		});
		
		textfieldHoTen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
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
			if (newVal) {
				labelThongBao.setText("");
				textfieldDanToc.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNoiThuongTru.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
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
			if (newVal) {
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
	}

	@FXML
	public void editNhanKhau() {
		if (isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		RadioButton selectedRadioButton = (RadioButton) togglegroupGioiTinh.getSelectedToggle();

		data.setHoTen(textfieldHoTen.getText());
		data.setBiDanh(textfieldBiDanh.getText());
		data.setNgaySinh(Date.valueOf(datepickerNgaySinh.getValue()));
		data.setGioiTinh(selectedRadioButton.getText());
		data.setCccd(textfieldCCCD.getText());
		data.setNgayCap(datepickerNgayCap.getValue() != null ? Date.valueOf(datepickerNgayCap.getValue()) : null);
		data.setNoiCap(textfieldNoiCap.getText());
		data.setNguyenQuan(textfieldNguyenQuan.getText());
		data.setDanToc(textfieldDanToc.getText());
		data.setNoiThuongTru(textfieldNoiThuongTru.getText());
		data.setNgayDangKyThuongTru(Date.valueOf(datepickerNgayDangKyThuongTru.getValue()));
		data.setTrinhDoHocVan(textfieldTrinhDoHocVan.getText());
		data.setTrinhDoNgoaiNgu(textfieldTrinhDoNgoaiNgu.getText());
		data.setNgheNghiep(textfieldNgheNghiep.getText());
		data.setNoiLamViec(textfieldNoiLamViec.getText());
		data.setTonGiao(textfieldTonGiao.getText());
		data.setQuocTich(textfieldQuocTich.getText());
		data.setTrinhDoChuyenMon(textfieldTrinhDoChuyenMon.getText());
		data.setGhiChu(textfieldGhiChu.getText());

		if (Connector.editNhanKhau(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");
			controllerNhanKhau.refreshNhanKhau();
		}
	}

	@FXML
	public void deleteNhanKhau() {
		ModelHoKhau modelHoKhau = Connector.searchHoKhauByIdNhanKhau(data.getIdNhanKhau());
		ModelHoKhauNhanKhau modelHoKhauNhanKhau = Connector.searchHoKhauNhanKhauByIdNhanKhau(data.getIdNhanKhau());
		List<ModelTamTru> listTamTru = Connector.searchTamTruByIdNhanKhau(data.getIdNhanKhau());
		List<ModelTamVang> listTamVang = Connector.searchTamVangByIdNhanKhau(data.getIdNhanKhau());
		List<ModelHoatDong> listHoatDong = Connector.searchHoatDongByIdNhanKhau(data.getIdNhanKhau());
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		if(modelHoKhau != null || modelHoKhauNhanKhau != null) {
			String contentText = "Các mục liên quan sau cũng sẽ bị xóa:\n";
			contentText += modelHoKhau.getHoTenNhanKhau() + " - " + modelHoKhau.getDiaChi() + "\n";
			contentText += modelHoKhauNhanKhau.getHoTenNhanKhauHoKhau() + " - " + modelHoKhauNhanKhau.getDiaChiHoKhau() + " - " + modelHoKhauNhanKhau.getQuanHe() + "\n";
			for(ModelTamTru tamTru: listTamTru) {
				contentText += tamTru.getHoTenNhanKhau() + "\n";
			}
			for(ModelTamVang tamVang: listTamVang) {
				contentText += tamVang.getHoTenNhanKhau() + "\n";
			}
			for(ModelHoatDong hoatDong: listHoatDong) {
				contentText += hoatDong.getHoTenNguoiDangKy() + "\n";
			}
			alert.setContentText(contentText);
		}
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			Connector.deleteHoKhauNhanKhau(modelHoKhauNhanKhau);
			Connector.deleteHoKhau(modelHoKhau);
			for(ModelTamTru tamTru: listTamTru) {
				Connector.deleteTamTru(tamTru);
			}
			for(ModelTamVang tamVang: listTamVang) {
				Connector.deleteTamVang(tamVang);
			}
			for(ModelHoatDong hoatDong: listHoatDong) {
				Connector.deleteHoatDong(hoatDong);
			}
			if(Connector.deleteNhanKhau(data)) {
				controllerNhanKhau.refreshNhanKhau();
				App.closeStageForm();
			}
		}       
	}

	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private void loadData(List<Integer> listId) {
		data = Connector.getNhanKhau(listId.get(0));

		textfieldHoTen.setText(data.getHoTen());
		textfieldBiDanh.setText(data.getBiDanh());
		datepickerNgaySinh.setValue(new Date(data.getNgaySinh().getTime()).toLocalDate());
		togglegroupGioiTinh.getToggles().forEach(toggle -> {
			RadioButton radiobutton = (RadioButton) toggle;
			if (radiobutton.getText().equals(data.getGioiTinh().toUpperCase())) {
				radiobutton.setSelected(true);
			}
		});
		textfieldCCCD.setText(data.getCccd());
		datepickerNgayCap.setValue(data.getNgayCap() != null ? new Date(data.getNgayCap().getTime()).toLocalDate() : null);
		textfieldNoiCap.setText(data.getNoiCap());
		textfieldNguyenQuan.setText(data.getNguyenQuan());
		textfieldDanToc.setText(data.getDanToc());
		textfieldNoiThuongTru.setText(data.getNoiThuongTru());
		datepickerNgayDangKyThuongTru.setValue(new Date(data.getNgayDangKyThuongTru().getTime()).toLocalDate());
		textfieldTrinhDoHocVan.setText(data.getTrinhDoHocVan());
		textfieldTrinhDoNgoaiNgu.setText(data.getTrinhDoNgoaiNgu());
		textfieldNgheNghiep.setText(data.getNgheNghiep());
		textfieldNoiLamViec.setText(data.getNoiLamViec());
		textfieldTonGiao.setText(data.getTonGiao());
		textfieldQuocTich.setText(data.getQuocTich());
		textfieldTrinhDoChuyenMon.setText(data.getTrinhDoChuyenMon());
		textfieldGhiChu.setText(data.getGhiChu());
	}

	private boolean isMissingField() {
		boolean check = false;
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

	public static ControllerNhanKhau getControllerNhanKhau() {
		return controllerNhanKhau;
	}

	public static void setControllerNhanKhau(ControllerNhanKhau controllerNhanKhau) {
		ControllerFormDetailNhanKhau.controllerNhanKhau = controllerNhanKhau;
	}

}
