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
import cnpm.QuanLyNhanKhau.model.ModelHoKhau;
import cnpm.QuanLyNhanKhau.model.ModelHoKhauNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class ControllerFormDetailHoKhauNhanKhau implements Initializable {

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
	
	private ModelHoKhauNhanKhau data;
	private ModelNhanKhau thanhVien;
	
	private static ControllerHoKhau controllerHoKhau;
	private static ControllerFormDetailHoKhauAdmin controllerFormDetailHoKhauAdmin;
	private static ControllerFormDetailHoKhauNhanVien controllerFormDetailHoKhauNhanVien;

	
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
	public void editHoKhauNhanKhau() {
		if(isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}	
		
		data.setIdNhanKhau(thanhVien.getIdNhanKhau());
		data.setQuanHe(textfieldQuanHe.getText());
		
		if(Connector.editHoKhauNhanKhau(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");
			
			controllerFormDetailHoKhauAdmin.refreshHoKhauNhanKhau();
			controllerHoKhau.refreshHoKhauNhanKhau();
		}
	}
	
	@FXML
	public void deleteHoKhauNhanKhau() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			if(Connector.deleteHoKhauNhanKhau(data)) {
				controllerFormDetailHoKhauAdmin.refreshHoKhauNhanKhau();
				controllerHoKhau.refreshHoKhauNhanKhau();
				
				List<Integer> listId = new ArrayList<Integer>();
				listId.add(data.getIdHoKhau());
				Holder.getInstance().setId(listId);
				try {
					App.setRootSceneForm("view/ViewFormDetailHoKhau");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@FXML
	public void cancel() {
		List<Integer> listId = new ArrayList<Integer>();
		listId.add(data.getIdHoKhau());
		Holder.getInstance().setId(listId);
		try {
			App.setRootSceneForm("view/ViewFormDetailHoKhau");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadData(List<Integer> listId) {
		data = Connector.getHoKhauNhanKhau(listId.get(0));
		
		textfieldHoTenThanhVien.setText(data.getHoTenNhanKhau());
		textfieldQuanHe.setText(data.getQuanHe());
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

	public static ControllerFormDetailHoKhauAdmin getControllerFormDetailHoKhauAdmin() {
		return controllerFormDetailHoKhauAdmin;
	}

	public static void setControllerFormDetailHoKhauAdmin(ControllerFormDetailHoKhauAdmin controllerFormDetailHoKhauAdmin) {
		ControllerFormDetailHoKhauNhanKhau.controllerFormDetailHoKhauAdmin = controllerFormDetailHoKhauAdmin;
	}

	public static ControllerHoKhau getControllerHoKhau() {
		return controllerHoKhau;
	}

	public static void setControllerHoKhau(ControllerHoKhau controllerHoKhau) {
		ControllerFormDetailHoKhauNhanKhau.controllerHoKhau = controllerHoKhau;
	}
	
	public static ControllerFormDetailHoKhauNhanVien getControllerFormDetailHoKhauNhanVien() {
		return controllerFormDetailHoKhauNhanVien;
	}

	public static void setControllerFormDetailHoKhauNhanVien(ControllerFormDetailHoKhauNhanVien controllerFormDetailHoKhauNhanVien) {
		ControllerFormDetailHoKhauNhanKhau.controllerFormDetailHoKhauNhanVien = controllerFormDetailHoKhauNhanVien;
	}

}
