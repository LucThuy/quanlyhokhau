package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;


public class ControllerFormAddTamVang implements Initializable {
	
	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField textfieldHoTenNguoiDangKy;
	@FXML
	private TextField textfieldNoiTamTru;
	@FXML
	private DatePicker datepickerNgayHieuLuc;
	@FXML
	private DatePicker datepickerNgayHetHieuLuc;
	@FXML
	private TextField textfieldLyDo;
	
	@FXML
	private Button buttonTimKiem;
	
	@FXML
	private TableView<ModelNhanKhau> tableviewNhanKhau;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnHoTen;
	@FXML
	private TableColumn<ModelNhanKhau, Date> tablecolumnNgaySinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnGioiTinh;
	@FXML
	private TableColumn<ModelNhanKhau, String> tablecolumnCCCD;
	
	@FXML
	private Label labelThongBao;

	@FXML
	private Button buttonDangKy;
	
	private ModelNhanKhau nguoiDangKy;
	
	private static ControllerTamTruTamVang controllerTamTruTamVang;


	
	public void initialize(URL location, ResourceBundle resources) {
		
		textfieldHoTenNguoiDangKy.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonTimKiem.fire();
			}
		});
		
		textfieldNoiTamTru.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgayHieuLuc.requestFocus();
			}
		});
		
		datepickerNgayHieuLuc.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgayHetHieuLuc.requestFocus();
			}
		});
		
		datepickerNgayHieuLuc.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldLyDo.requestFocus();
			}
		});
		
		textfieldLyDo.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonDangKy.fire();
			}
		});
		
		textfieldHoTenNguoiDangKy.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldHoTenNguoiDangKy.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldNoiTamTru.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldNoiTamTru.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayHieuLuc.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgayHieuLuc.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayHetHieuLuc.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				datepickerNgayHetHieuLuc.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldLyDo.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldLyDo.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		tablecolumnHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		tablecolumnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		tablecolumnGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		tablecolumnCCCD.setCellValueFactory(new PropertyValueFactory<>("cccd"));
		tableviewNhanKhau.setRowFactory( val -> {
			TableRow<ModelNhanKhau> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					nguoiDangKy = row.getItem();
					textfieldHoTenNguoiDangKy.setText(nguoiDangKy.getHoTen());
				}
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					nguoiDangKy = row.getItem();
					textfieldHoTenNguoiDangKy.setText(nguoiDangKy.getHoTen());
					gridpane.getRowConstraints().get(1).setPrefHeight(0);
					tableviewNhanKhau.setVisible(false);
					textfieldNoiTamTru.requestFocus();
				}
			});
			return row;
		});		
		
	}
	
	@FXML
	public void searchNhanKhau() {
		List<ModelNhanKhau> listNhanKhau = Connector.searchNhanKhauByHoTen(textfieldHoTenNguoiDangKy.getText());
		
		tableviewNhanKhau.getItems().clear();
		listNhanKhau.forEach(nhanKhau -> {
			tableviewNhanKhau.getItems().add(nhanKhau);
		});
		
		tableviewNhanKhau.setVisible(true);
		gridpane.getRowConstraints().get(1).setPrefHeight(150);
	}
	
	@FXML
	public void addTamVang() {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		if(nguoiDangKy.getTrangThai().equals("Tạm Vắng") || nguoiDangKy.getTrangThai().equals("Tạm Trú") ||
				!Connector.addTamVang(nguoiDangKy.getIdNhanKhau(), textfieldNoiTamTru.getText(), 
						datepickerNgayHieuLuc.getValue(), datepickerNgayHetHieuLuc.getValue(),
						textfieldLyDo.getText())) {
			labelThongBao.setText("Nhân khẩu không hợp lệ");
		} 
		else {
			controllerTamTruTamVang.refreshTamVang();
			App.closeStageForm();
		}
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}
	
	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTenNguoiDangKy.getText().isEmpty()) {
			textfieldHoTenNguoiDangKy.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldNoiTamTru.getText().isEmpty()) {
			textfieldNoiTamTru.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgayHieuLuc.getValue() == null) {
			datepickerNgayHieuLuc.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgayHetHieuLuc.getValue() == null) {
			datepickerNgayHetHieuLuc.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldLyDo.getText().isEmpty()) {
			textfieldLyDo.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}
	
	public static ControllerTamTruTamVang getControllerTamTruTamVang() {
		return controllerTamTruTamVang;
	}

	public static void setControllerTamTruTamVang(ControllerTamTruTamVang controllerTamTruTamVang) {
		ControllerFormAddTamVang.controllerTamTruTamVang = controllerTamTruTamVang;
	}
	
}
