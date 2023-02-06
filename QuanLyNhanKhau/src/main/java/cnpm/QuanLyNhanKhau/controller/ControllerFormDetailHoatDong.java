package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelHoatDong;
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class ControllerFormDetailHoatDong implements Initializable {
	
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
	private Button buttonLuuThayDoi;
	
	private ModelNhanKhau nguoiDangKy;
	private ModelHoatDong data;
	private ModelNhaVanHoa modelNhaVanHoa;

	private static ControllerHoatDongQuanLy controllerHoatDongQuanLy;
	private static ControllerHoatDongAdmin controllerHoatDongAdmin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
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
				buttonLuuThayDoi.fire();
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
	
	private void loadData(List<Integer> listId) {
		data = Connector.getHoatDong(listId.get(0));
		modelNhaVanHoa = Connector.getTheLatestNhaVanHoa();
		
		textfieldHoTenNguoiDangKy.setText(data.getHoTenNguoiDangKy());
		nguoiDangKy = Connector.getNhanKhau(data.getIdNhanKhau());
		textfieldHoatDong.setText(data.getHoatDong());
		datepickerNgayBatDau.setValue(new Date(data.getNgayBatDau().getTime()).toLocalDate());
		datepickerNgayKetThuc.setValue(new Date(data.getNgayKetThuc().getTime()).toLocalDate());
		textfieldSoLuongBan.setText(String.valueOf(data.getSoLuongBan()));
		textfieldSoLuongGhe.setText(String.valueOf(data.getSoLuongGhe()));
		textfieldSoLuongLoa.setText(String.valueOf(data.getSoLuongLoa()));
		textfieldSoLuongDai.setText(String.valueOf(data.getSoLuongDai()));
		textfieldSoLuongManHinh.setText(String.valueOf(data.getSoLuongManHinh()));
		textfieldSoLuongDen.setText(String.valueOf(data.getSoLuongDen()));	
		
		labelSoLuongBan.setText(String.valueOf(modelNhaVanHoa.getSoLuongBan()));
		labelSoLuongGhe.setText(String.valueOf(modelNhaVanHoa.getSoLuongGhe()));
		labelSoLuongLoa.setText(String.valueOf(modelNhaVanHoa.getSoLuongLoa()));
		labelSoLuongDai.setText(String.valueOf(modelNhaVanHoa.getSoLuongDai()));
		labelSoLuongManHinh.setText(String.valueOf(modelNhaVanHoa.getSoLuongManHinh()));
		labelSoLuongDen.setText(String.valueOf(modelNhaVanHoa.getSoLuongDen()));
		
		
	}
	
	@FXML
	public void deleteHoatDong() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			if(Connector.deleteHoatDong(data)) {
				if(controllerHoatDongQuanLy != null) {
					controllerHoatDongQuanLy.refreshHoatDong();
				}
				if(controllerHoatDongAdmin != null) {
					controllerHoatDongAdmin.refreshHoatDong();
				}
				App.closeStageForm();
			}
		}	
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

//	private boolean isMissingField() {
//		boolean check = false;
//		if (textfieldHoTenNguoiDangKy.getText().isEmpty()) {
//			textfieldHoTenNguoiDangKy.getStyleClass().add("inputfield-error");
//			check = true;
//		}
//		if (textfieldHoatDong.getText().isEmpty()) {
//			textfieldHoatDong.getStyleClass().add("inputfield-error");
//			check = true;
//		}
//		if (datepickerNgayBatDau.getValue() == null) {
//			datepickerNgayBatDau.getStyleClass().add("inputfield-error");
//			check = true;
//		}
//		if (datepickerNgayKetThuc.getValue() == null) {
//			datepickerNgayKetThuc.getStyleClass().add("inputfield-error");
//			check = true;
//		}
//		
//		return check;
//	}
	
//	private void setNotice(String notice) {
//		labelThongBao.setText(notice);
//		labelThongBao.setMinHeight(20);
//		labelThongBao.setMaxHeight(20);
//	}
	
	private void hideNotice() {
		labelThongBao.setMinHeight(0);
		labelThongBao.setMaxHeight(0);
	}
	
	public static ControllerHoatDongQuanLy getControllerHoatDongQuanLy() {
		return controllerHoatDongQuanLy;
	}
	public static void setControllerHoatDongQuanLy(ControllerHoatDongQuanLy controllerHoatDongQuanLy) {
		ControllerFormDetailHoatDong.controllerHoatDongQuanLy = controllerHoatDongQuanLy;
	}
	
	public static ControllerHoatDongAdmin getControllerHoatDongAdmin() {
		return controllerHoatDongAdmin;
	}
	public static void setControllerHoatDongAdmin(ControllerHoatDongAdmin controllerHoatDongAdmin) {
		ControllerFormDetailHoatDong.controllerHoatDongAdmin = controllerHoatDongAdmin;
	}
}
