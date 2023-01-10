package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelTamTru;
import cnpm.QuanLyNhanKhau.model.ModelTamVang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class ControllerFormDetailTamVang implements Initializable {
	
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
	private Label labelThongBao;

	@FXML
	private Button buttonLuuThayDoi;
	
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
	
	private ModelTamVang data;
	private ModelNhanKhau nguoiDangKy;
	private static ControllerTamTruTamVang controllerTamVang;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
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
				buttonLuuThayDoi.fire();
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
	public void editTamVang() {
		if(isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		data.setIdNhanKhau(nguoiDangKy.getIdNhanKhau());
		data.setModelNhanKhau(nguoiDangKy);
		data.setNoiTamTru(textfieldNoiTamTru.getText());
		data.setNgayHieuLuc(Date.valueOf(datepickerNgayHieuLuc.getValue()));
		data.setNgayHetHieuLuc(Date.valueOf(datepickerNgayHetHieuLuc.getValue()));
		data.setLyDo(textfieldLyDo.getText());
		
		if (Connector.editTamVang(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");

			controllerTamVang.refreshTamVang();
		}
	}
	
	@FXML
	public void deleteTamVang() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			if(Connector.deleteTamVang(data)) {
				controllerTamVang.refreshTamVang();
				App.closeStageForm();
			}
		}	
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}
	
	public void setLabelThongBao(String message) {
		labelThongBao.setText(message);
	}
	
	private void loadData(List<Integer> listId) {
		data = Connector.getTamVang(listId.get(0));
		
		textfieldHoTenNguoiDangKy.setText(data.getHoTenNhanKhau());
		nguoiDangKy = Connector.getNhanKhau(data.getIdNhanKhau());
		textfieldNoiTamTru.setText(data.getNoiTamTru());
		datepickerNgayHieuLuc.setValue(new Date(data.getNgayHieuLuc().getTime()).toLocalDate());
		datepickerNgayHetHieuLuc.setValue(new Date(data.getNgayHetHieuLuc().getTime()).toLocalDate());
		textfieldLyDo.setText(data.getLyDo());
		
	}
	
	private boolean isMissingField() {
		boolean check = false;
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
		return controllerTamVang;
	}
	
	public static void setControllerTamTruTamVang(ControllerTamTruTamVang controllerTamVang) {
		ControllerFormDetailTamVang.controllerTamVang = controllerTamVang;
	}
}
