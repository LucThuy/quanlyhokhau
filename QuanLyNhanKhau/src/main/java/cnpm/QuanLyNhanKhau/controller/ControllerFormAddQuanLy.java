package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerFormAddQuanLy implements Initializable {

	@FXML
	private TextField textfieldLabel;
	
	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonThemMoi;

	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		hideNotice();
	}

	@FXML
	public void addNhanKhau() {
		if(isMissingField()) {
			setNotice("Điền đầy đủ các mục bắt buộc");
			return;
		}
		
		App.closeStageForm();
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
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
		ControllerFormAddQuanLy.controllerNhanKhau = controllerNhanKhau;
	}
}
