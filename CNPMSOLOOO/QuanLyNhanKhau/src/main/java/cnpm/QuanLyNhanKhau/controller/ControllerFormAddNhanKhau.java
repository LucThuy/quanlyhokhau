package cnpm.QuanLyNhanKhau.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cnpm.QuanLyNhanKhau.App;
import cnpm.QuanLyNhanKhau.Connector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerFormAddNhanKhau implements Initializable {

	@FXML
	private TextField textfieldHoTen;
	@FXML
	private DatePicker datepickerNgaySinh;
	@FXML
	private ToggleGroup togglegroupGioiTinh;
	@FXML
	private TextField textfieldCCCD;

	@FXML
	private Label labelThongBao;
	
	@FXML
	private Button buttonThemMoi;

	private static ControllerNhanKhau controllerNhanKhau;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textfieldHoTen.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
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
				buttonThemMoi.fire();
			}
		});

		textfieldHoTen.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				labelThongBao.setText("");
				textfieldHoTen.getStyleClass().removeAll("inputfield-error");
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
	}

	@FXML
	public void addNhanKhau() {
		if(isMissingField()) {
			labelThongBao.setText("Điền đầy đủ các mục bắt buộc");
			return;
		}
		RadioButton selectedRadioButton = (RadioButton) togglegroupGioiTinh.getSelectedToggle();
		if (Connector.addNhanKhau(textfieldHoTen.getText(), datepickerNgaySinh.getValue(),
				selectedRadioButton.getText(), textfieldCCCD.getText())) {
			controllerNhanKhau.refreshNhanKhau();
			App.closeStageForm();
		}
	}
	
	@FXML
	public void cancel() {
		App.closeStageForm();
	}

	private boolean isMissingField() {
		boolean check = false;
		if (textfieldHoTen.getText().isEmpty()) {
			textfieldHoTen.getStyleClass().add("inputfield-error");
			check = true;
		}
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
		if (textfieldCCCD.getText().isEmpty()) {
			textfieldCCCD.getStyleClass().add("inputfield-error");
			check = true;
		}
		return check;
	}
	
	public static ControllerNhanKhau getControllerNhanKhau() {
		return controllerNhanKhau;
	}
	public static void setControllerNhanKhau(ControllerNhanKhau controllerNhanKhau) {
		ControllerFormAddNhanKhau.controllerNhanKhau = controllerNhanKhau;
	}
}
