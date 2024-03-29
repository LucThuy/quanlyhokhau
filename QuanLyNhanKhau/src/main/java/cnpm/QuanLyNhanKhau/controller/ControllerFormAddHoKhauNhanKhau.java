package cnpm.QuanLyNhanKhau.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import cnpm.QuanLyNhanKhau.Holder;
import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class ControllerFormAddHoKhauNhanKhau implements Initializable {

	@FXML
	private GridPane gridpane;
	
	@FXML
	private TextField textfieldHoTenThanhVien;
	@FXML
	private TextField textfieldQuanHe;
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
	
	private ModelHoKhau data;
	private ModelNhanKhau thanhVien;
	
	private static ControllerHoKhau controllerHoKhau;
	private static ControllerFormDetailHoKhauAdmin controllerFormDetailHoKhauAdmin;
	private static ControllerFormDetailHoKhauNhanVien controllerFormDetailHoKhauNhanVien;
	
	private ModelUser currentUser = Connector.currentUser;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
		textfieldHoTenThanhVien.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonTimKiem.fire();
			}
		});
		
		textfieldQuanHe.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonLuuThayDoi.fire();
			}
		});
		
		textfieldHoTenThanhVien.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldHoTenThanhVien.getStyleClass().removeAll("inputfield-error");
			}
		});

		textfieldQuanHe.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldQuanHe.getStyleClass().removeAll("inputfield-error");
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
					thanhVien = row.getItem();
					textfieldHoTenThanhVien.setText(thanhVien.getHoTen());
				}
				if(e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY) && !row.isEmpty()) {
					thanhVien = row.getItem();
					textfieldHoTenThanhVien.setText(thanhVien.getHoTen());
					gridpane.getRowConstraints().get(1).setPrefHeight(0);
					tableviewNhanKhau.setVisible(false);
					textfieldQuanHe.requestFocus();
				}
			});
			return row;
		});
	}
	
	@FXML
	public void searchNhanKhau() {
		List<ModelNhanKhau> listNhanKhau = Connector.searchNhanKhauByHoTen(textfieldHoTenThanhVien.getText());
		
		tableviewNhanKhau.getItems().clear();
		listNhanKhau.forEach(nhanKhau -> {
			tableviewNhanKhau.getItems().add(nhanKhau);
		});
		
		tableviewNhanKhau.setVisible(true);
		gridpane.getRowConstraints().get(1).setPrefHeight(150);
	}
	
	@FXML
	public void addNhanKhauToHoKhau() {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}	
		if(Connector.addHoKhauNhanKhau(data.getIdHoKhau(), thanhVien.getIdNhanKhau(), textfieldQuanHe.getText())) {
			try {
				if(currentUser.getRole().equals("Tổ Trưởng")) {
					controllerFormDetailHoKhauAdmin.setLabelThongBao("Thêm thành viên thành công");
					controllerHoKhau.refreshHoKhau();
					controllerHoKhau.refreshHoKhauNhanKhau();
					App.setRootSceneForm("view/ViewFormDetailHoKhauAdmin");

				}
				else {
					controllerFormDetailHoKhauNhanVien.setLabelThongBao("Thêm thành viên thành công");
					controllerHoKhau.refreshHoKhau();
					controllerHoKhau.refreshHoKhauNhanKhau();
					App.setRootSceneForm("view/ViewFormDetailHoKhauNhanVien");

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			labelThongBao.setText("Nhân khẩu không hợp lệ");
		}
	}
	
	@FXML
	public void cancel() {
		try {
			if(currentUser.getRole().equals("Tổ Trưởng")) {
				App.setRootSceneForm("view/ViewFormDetailHoKhauAdmin");
			}
			else {
				App.setRootSceneForm("view/ViewFormDetailHoKhauNhanVien");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadData(List<Integer> listId) {
		data = Connector.getHoKhau(listId.get(0));
	}
	
	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTenThanhVien.getText().isEmpty()) {
			textfieldHoTenThanhVien.getStyleClass().add("inputfield-error");
			check = true;
		}
		if (textfieldQuanHe.getText().isEmpty()) {
			textfieldQuanHe.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

	public static ControllerHoKhau getControllerHoKhau() {
		return controllerHoKhau;
	}

	public static void setControllerHoKhau(ControllerHoKhau controllerHoKhau) {
		ControllerFormAddHoKhauNhanKhau.controllerHoKhau = controllerHoKhau;
	}

	public static ControllerFormDetailHoKhauAdmin getControllerFormDetailHoKhauAdmin() {
		return controllerFormDetailHoKhauAdmin;
	}

	public static void setControllerFormDetailHoKhauAdmin(ControllerFormDetailHoKhauAdmin controllerFormDetailHoKhauAdmin) {
		ControllerFormAddHoKhauNhanKhau.controllerFormDetailHoKhauAdmin = controllerFormDetailHoKhauAdmin;
	}
	
	public static ControllerFormDetailHoKhauNhanVien getControllerFormDetailHoKhauNhanVien() {
		return controllerFormDetailHoKhauNhanVien;
	}

	public static void setControllerFormDetailHoKhauNhanVien(ControllerFormDetailHoKhauNhanVien controllerFormDetailHoKhauNhanVien) {
		ControllerFormAddHoKhauNhanKhau.controllerFormDetailHoKhauNhanVien = controllerFormDetailHoKhauNhanVien;
	}
}
