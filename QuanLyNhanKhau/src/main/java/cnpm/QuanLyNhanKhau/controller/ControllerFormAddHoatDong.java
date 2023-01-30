package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class ControllerFormAddHoatDong implements Initializable {

	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField textfieldHoTenNguoiDangKy;
	@FXML
	private TextField textfieldHoatDong;
	@FXML
	private DatePicker datepickerNgayBatDau;
	@FXML
	private DatePicker datepickerNgayKetThuc;
	@FXML
	private TextField textfieldSoLuongBan;
	@FXML
	private TextField textfieldSoLuongGhe;
	@FXML
	private TextField textfieldSoLuongLoa;
	@FXML
	private TextField textfieldSoLuongDai;
	@FXML
	private TextField textfieldSoLuongManHinh;
	@FXML
	private TextField textfieldSoLuongDen;
	@FXML
	private Label labelSoLuongBan;
	@FXML
	private Label labelSoLuongGhe;
	@FXML
	private Label labelSoLuongLoa;
	@FXML
	private Label labelSoLuongDai;
	@FXML
	private Label labelSoLuongManHinh;
	@FXML
	private Label labelSoLuongDen;
	
	
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
	private Button buttonTimKiem;
	
	@FXML
	private Button buttonDangKy;
	
	private ModelNhanKhau nguoiDangKy;
	private ModelNhaVanHoa data;

	private static ControllerHoatDongQuanLy controllerHoatDongQuanLy;
	private static ControllerHoatDongAdmin controllerHoatDongAdmin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		loadData();
		
		textfieldHoTenNguoiDangKy.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonTimKiem.fire();
			}
		});
		
		textfieldHoatDong.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgayBatDau.requestFocus();
			}
		});
		
		datepickerNgayBatDau.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				datepickerNgayKetThuc.requestFocus();
			}
		});
		
		datepickerNgayKetThuc.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongBan.requestFocus();
			}
		});
		
		textfieldSoLuongBan.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongGhe.requestFocus();
			}
		});
		
		textfieldSoLuongGhe.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongLoa.requestFocus();
			}
		});
		
		textfieldSoLuongLoa.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongDai.requestFocus();
			}
		});
		
		textfieldSoLuongDai.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongManHinh.requestFocus();
			}
		});
		
		textfieldSoLuongManHinh.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				textfieldSoLuongDen.requestFocus();
			}
		});
		
		textfieldSoLuongDen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonDangKy.fire();
			}
		});
		
		
		
		textfieldHoTenNguoiDangKy.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHoTenNguoiDangKy.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldHoatDong.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldHoatDong.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayBatDau.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				datepickerNgayBatDau.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		datepickerNgayKetThuc.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				datepickerNgayKetThuc.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongBan.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongBan.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongGhe.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongGhe.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongLoa.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongLoa.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongDai.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongDai.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongManHinh.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongManHinh.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		textfieldSoLuongDen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				hideNotice();
				textfieldSoLuongDen.getStyleClass().removeAll("inputfield-error");
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
					textfieldHoatDong.requestFocus();
				}
			});
			return row;
		});
		
		hideNotice();
	}
	
	private void loadData() {
		data = Connector.getTheLatestNhaVanHoa();

		labelSoLuongBan.setText(String.valueOf(data.getSoLuongBan()));
		labelSoLuongGhe.setText(String.valueOf(data.getSoLuongGhe()));
		labelSoLuongLoa.setText(String.valueOf(data.getSoLuongLoa()));
		labelSoLuongDai.setText(String.valueOf(data.getSoLuongDai()));
		labelSoLuongManHinh.setText(String.valueOf(data.getSoLuongManHinh()));
		labelSoLuongDen.setText(String.valueOf(data.getSoLuongDen()));

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
	public void addHoatDong() {
		if(isMissingField()) {
			setNotice("Điền đầy đủ các mục bắt buộc");
			return;
		}
		if(isSuitableField()) {
			setNotice("Số lượng không hợp lệ");
			return;
		}
		if (Connector.addHoatDong(nguoiDangKy.getIdNhanKhau(), textfieldHoatDong.getText(),
				datepickerNgayBatDau.getValue(), datepickerNgayKetThuc.getValue(),
				Integer.valueOf(textfieldSoLuongBan.getText()), Integer.valueOf(textfieldSoLuongGhe.getText()),
				Integer.valueOf(textfieldSoLuongLoa.getText()), Integer.valueOf(textfieldSoLuongDai.getText()),
				Integer.valueOf(textfieldSoLuongManHinh.getText()), Integer.valueOf(textfieldSoLuongDen.getText()))) {
			if(controllerHoatDongQuanLy != null) {
				controllerHoatDongQuanLy.refreshHoatDong();
			}
			if(controllerHoatDongAdmin != null) {
				controllerHoatDongAdmin.refreshHoatDong();
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
		if (textfieldHoTenNguoiDangKy.getText().isEmpty()) {
			textfieldHoTenNguoiDangKy.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldHoatDong.getText().isEmpty()) {
			textfieldHoatDong.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgayBatDau.getValue() == null) {
			datepickerNgayBatDau.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (datepickerNgayKetThuc.getValue() == null) {
			datepickerNgayKetThuc.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongBan.getText().isEmpty()) {
			textfieldSoLuongBan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongGhe.getText().isEmpty()) {
			textfieldSoLuongGhe.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongLoa.getText().isEmpty()) {
			textfieldSoLuongLoa.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldSoLuongDai.getText().isEmpty()) {
			textfieldSoLuongDai.getStyleClass().add("inputfield-error");
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
		
		return check;
	}
	
	private boolean isSuitableField() {
		boolean check = false;
		
		if (Integer.valueOf(textfieldSoLuongBan.getText()) > data.getSoLuongBan()) {
			textfieldSoLuongBan.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (Integer.valueOf(textfieldSoLuongGhe.getText()) > data.getSoLuongGhe()) {
			textfieldSoLuongGhe.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (Integer.valueOf(textfieldSoLuongLoa.getText()) > data.getSoLuongLoa()) {
			textfieldSoLuongLoa.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (Integer.valueOf(textfieldSoLuongDai.getText()) > data.getSoLuongDai()) {
			textfieldSoLuongDai.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (Integer.valueOf(textfieldSoLuongManHinh.getText()) > data.getSoLuongManHinh()) {
			textfieldSoLuongManHinh.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (Integer.valueOf(textfieldSoLuongDen.getText()) > data.getSoLuongDen()) {
			textfieldSoLuongDen.getStyleClass().add("inputfield-error");
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
	
	public static ControllerHoatDongQuanLy getControllerHoatDongQuanLy() {
		return controllerHoatDongQuanLy;
	}
	public static void setControllerHoatDongQuanLy(ControllerHoatDongQuanLy controllerHoatDongQuanLy) {
		ControllerFormAddHoatDong.controllerHoatDongQuanLy = controllerHoatDongQuanLy;
	}
	
	public static ControllerHoatDongAdmin getControllerHoatDongAdmin() {
		return controllerHoatDongAdmin;
	}
	public static void setControllerHoatDongAdmin(ControllerHoatDongAdmin controllerHoatDongAdmin) {
		ControllerFormAddHoatDong.controllerHoatDongAdmin = controllerHoatDongAdmin;
	}
}
