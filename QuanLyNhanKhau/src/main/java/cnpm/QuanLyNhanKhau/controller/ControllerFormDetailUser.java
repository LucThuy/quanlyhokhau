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
import cnpm.QuanLyNhanKhau.model.ModelLichSu;
import cnpm.QuanLyNhanKhau.model.ModelNhanKhau;
import cnpm.QuanLyNhanKhau.model.ModelUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class ControllerFormDetailUser implements Initializable {

	@FXML
	private GridPane gridpane;

	@FXML
	private Label labelTenNguoiDung;
	@FXML
	private TextField textfieldChucVu;

	@FXML
	private Label labelThongBao;

	@FXML
	private Button buttonLuuThayDoi;

	@FXML
	private TableView<ModelLichSu> tableviewLichSu;
	@FXML
	private TableColumn<ModelLichSu, String> tablecolumnThaoTac;
	@FXML
	private TableColumn<ModelLichSu, Date> tablecolumnThoiGian;
	
	private ModelUser data;
	
	private static ControllerQuanLy controllerQuanLy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
		
		
		textfieldChucVu.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				buttonLuuThayDoi.fire();
			}
		});
		
		
		textfieldChucVu.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldChucVu.getStyleClass().removeAll("inputfield-error");
			}
		});
		
		tablecolumnThaoTac.setCellValueFactory(new PropertyValueFactory<>("thaoTac"));
		tablecolumnThoiGian.setCellValueFactory(new PropertyValueFactory<>("thoiGian"));
		
		refreshTableViewLichSu();
		
	}


	@FXML
	public void editUser() {
		if (isMissingField()) {
			labelThongBao.getStyleClass().removeAll("notice-green");
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		data.setTenNguoiDung(labelTenNguoiDung.getText());
		data.setRole(textfieldChucVu.getText());

		if (Connector.editUser(data)) {
			labelThongBao.getStyleClass().add("notice-green");
			labelThongBao.setText("Đã lưu thay đổi");

			controllerQuanLy.refreshTableViewUserNV();
		}
	}

	@FXML
	public void deleteUser() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			if(Connector.deleteUser(data)) {
				controllerQuanLy.refreshTableViewUserNV();
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
	
	public void refreshLichSu() {
		refreshTableViewLichSu();
	}

	private void refreshTableViewLichSu() {
		tableviewLichSu.getItems().clear();
		System.out.println(data.getIdUser());
		List<ModelLichSu> listLichSu = Connector.searchLichSuByIdUser(data.getIdUser());
		listLichSu.forEach(lichSu -> {
			tableviewLichSu.getItems().add(lichSu);
		});
	}

	private void loadData(List<Integer> listId) {
		data = Connector.getUser(listId.get(0));

		labelTenNguoiDung.setText(data.getTenNguoiDung());
		textfieldChucVu.setText(data.getRole());
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldChucVu.getText().isEmpty()) {
			textfieldChucVu.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}

	public static ControllerQuanLy getControllerQuanLy() {
		return controllerQuanLy;
	}

	public static void setControllerQuanLy(ControllerQuanLy controllerQuanLy) {
		ControllerFormDetailUser.controllerQuanLy = controllerQuanLy;
	}
}
