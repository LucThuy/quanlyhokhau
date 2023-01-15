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
import cnpm.QuanLyNhanKhau.model.ModelNhaVanHoa;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormDetailQuanLyNhaVanHoa implements Initializable {

	@FXML
	private Label labelSoLuongBan;
	@FXML
	private Label labelHienTrangBan;
	@FXML
	private Label labelSoLuongGhe;
	@FXML
	private Label labelHienTrangGhe;
	@FXML
	private Label labelSoLuongLoa;
	@FXML
	private Label labelHienTrangLoa;
	@FXML
	private Label labelSoLuongDai;
	@FXML
	private Label labelHienTrangDai;
	@FXML
	private Label labelSoLuongManHinh;
	@FXML
	private Label labelHienTrangManHinh;
	@FXML
	private Label labelSoLuongDen;
	@FXML
	private Label labelHienTrangDen;
	
	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonLuuThayDoi;

	private ModelNhaVanHoa data;
	
	private static ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy;
	private static ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData(Holder.getInstance().getId());
	
		
		hideNotice();
	}

	
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}
	
	private void loadData(List<Integer> listId) {
		data = Connector.getQuanLyNhaVanHoa(listId.get(0));

		labelSoLuongBan.setText(data.getSoLuongBan());
		labelHienTrangBan.setText(data.getHienTrangBan());
		labelSoLuongGhe.setText(data.getSoLuongGhe());
		labelHienTrangGhe.setText(data.getHienTrangGhe());
		labelSoLuongLoa.setText(data.getSoLuongLoa());
		labelHienTrangLoa.setText(data.getHienTrangLoa());
		labelSoLuongDai.setText(data.getSoLuongDai());
		labelHienTrangDai.setText(data.getHienTrangDai());
		labelSoLuongManHinh.setText(data.getSoLuongManHinh());
		labelHienTrangManHinh.setText(data.getHienTrangManHinh());
		labelSoLuongDen.setText(data.getSoLuongDen());
		labelHienTrangDen.setText(data.getHienTrangDen());

	}
	
	@FXML
	public void deleteQuanLyNhaVanHoa() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getDialogPane().getStylesheets().add(App.class.getResource("css/main.css").toExternalForm());
		alert.setHeaderText("Xóa mục được chọn?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK) {
			if(Connector.deleteQuanLyNhaVanHoa(data)) {
				controllerNhaVanHoaQuanLy.refreshQuanLyNhaVanHoa();
//				controllerNhaVanHoaAdmin.refreshQuanLyNhaVanHoa();
				App.closeStageForm();
			}
		}
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
	
	public static ControllerNhaVanHoaQuanLy getControllerNhaVanHoaQuanLy() {
		return controllerNhaVanHoaQuanLy;
	}
	public static void setControllerNhaVanHoaQuanLy(ControllerNhaVanHoaQuanLy controllerNhaVanHoaQuanLy) {
		ControllerFormDetailQuanLyNhaVanHoa.controllerNhaVanHoaQuanLy = controllerNhaVanHoaQuanLy;
	}
	
	public static ControllerNhaVanHoaAdmin getControllerNhaVanHoaAdmin() {
		return controllerNhaVanHoaAdmin;
	}
	public static void setControllerNhaVanHoaAdmin(ControllerNhaVanHoaAdmin controllerNhaVanHoaAdmin) {
		ControllerFormDetailQuanLyNhaVanHoa.controllerNhaVanHoaAdmin = controllerNhaVanHoaAdmin;
	}
}
